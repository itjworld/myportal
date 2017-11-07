package com.gspann.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class AjaxAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	  
	private static final String X_AJAX_CALL = "X-Login-Ajax-call";

	private AuthenticationSuccessHandler defaultHandler;

	public AjaxAuthenticationSuccessHandler(AuthenticationSuccessHandler defaultHandler) {
		this.defaultHandler = defaultHandler;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		
		if (X_AJAX_CALL.equals(request.getHeader(X_AJAX_CALL))) {
			response.getWriter().print("OK");
			response.getWriter().flush();
		} else {
			defaultHandler.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
