package com.fwd.eprecious.security;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import com.fwd.eprecious.dto.RestrictedAction;
import com.fwd.eprecious.dto.UserSession;

public class CustomFilter extends GenericFilterBean {

	
	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest request = (HttpServletRequest) arg0;
		HttpServletResponse response = (HttpServletResponse) arg1;
		String url = request.getRequestURI();
		boolean isOk = true;
	//....
		if(isOk){
			chain.doFilter(request, response);
		}else{
			 System.out.println("no auth");
			 response.addHeader("error", "noauth");
			 return;
		}
		}
	


}
