package com.fwd.eprecious.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class EpreciousCustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private final Log logger = LogFactory.getLog(EpreciousCustomAuthenticationSuccessHandler.class);


	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//		LdapUserDetailsImpl authUser = (LdapUserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			response.sendRedirect("main.html#login");
	}

}
