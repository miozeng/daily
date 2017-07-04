package com.fwd.eprecious.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class EpreciousCustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
	private final Log logger = LogFactory.getLog(EpreciousCustomAuthenticationFailureHandler.class);
	
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		logger.error("login ldap failure ",exception);
		response.sendRedirect("login.html?error=true");
		
	}

}
