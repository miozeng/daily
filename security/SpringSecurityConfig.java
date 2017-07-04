package com.fwd.eprecious.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private AuthProvider authProvider;
	
	@Override
	public void configure(WebSecurity web) throws Exception {

	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers( "/","/*.html", "js/**", "css/**", "images/**", "lib/**", "/templates/**").permitAll()
		.antMatchers("/All/**"," /Clients/**","/CodeList/**","/Quotation/**","/main.html").authenticated()
		.and()
		.exceptionHandling().accessDeniedPage("/main.html#/")
		.and()
		.csrf().disable().formLogin()
		.loginPage("/login.html")
		.loginProcessingUrl("/j_spring_security_check")
		.usernameParameter("j_username")
		.passwordParameter("j_password")
		.permitAll()
		 .successHandler(loginSuccessHandler())
		 .failureHandler(authenticationFailureHandler())
		.and()
		.logout().logoutUrl("/logout").logoutSuccessUrl("/login.html")
		.permitAll();

		http.headers().httpStrictTransportSecurity();
		http.headers().xssProtection();
		http.headers().contentTypeOptions();
//		http
//		.headers()
//		.contentSecurityPolicy(
//		"   default-src 'self'	*.facebook.com *.google.com *.twitter.com www.google-analytics.com www.gstatic.com ; "
//		+ "	script-src	'self' 'unsafe-eval' 'unsafe-inline' http://www.google-analytics.com https://www.google-analytics.com https://www.googletagmanager.com https://connect.facebook.net ajax.googleapis.com *.google.com www.gstatic.com https://www.google.com/recaptcha/ https://www.gstatic.com/recaptcha/;"
//		+ "	style-src	'self' 'unsafe-eval' 'unsafe-inline' fonts.googleapis.com www.google-analytics.com www.gstatic.com ;"
//		+ "	style-src	'self' 'unsafe-eval' 'unsafe-inline'  www.googletagmanager.com www.gstatic.com  https://www.gstatic.com;"
//		+ "frame-src https://www.google.com/recaptcha/ ;"
//		+ "img-src	'self' 'unsafe-eval' 'unsafe-inline'  *.google.com www.gstatic.com https://www.googletagmanager.com https://www.facebook.com  http://www.google-analytics.com https://www.google-analytics.com data:;");

		http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
	}
	
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

   /* @Bean
    public AuthenticationProvider ldapAuthenticationProvider() throws Exception {
        DefaultSpringSecurityContextSource contextSource = new DefaultSpringSecurityContextSource(ldapSetting.getUrl());
        contextSource.setUserDn(ldapSetting.getDn());
        contextSource.setPassword(ldapSetting.getPassword());
        contextSource.afterPropertiesSet();
        LdapUserSearch ldapUserSearch = new FilterBasedLdapUserSearch(ldapSetting.getSearchbase(), ldapSetting.getSearchfilter(), contextSource);
        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
        bindAuthenticator.setUserSearch(ldapUserSearch);
        LdapAuthenticationProvider ldapAuthenticationProvider = new LdapAuthenticationProvider(bindAuthenticator, new DefaultLdapAuthoritiesPopulator(contextSource, ldapSetting.getGroupsearchbase()));
        return ldapAuthenticationProvider;
    }*/

	
	 @Bean  
	    public EpreciousCustomAuthenticationSuccessHandler loginSuccessHandler(){  
	        return new EpreciousCustomAuthenticationSuccessHandler();  
	    }  
	 
	 @Bean
	 public EpreciousCustomAuthenticationFailureHandler authenticationFailureHandler(){
		 return new EpreciousCustomAuthenticationFailureHandler();
	 }
}
