package com.gspann.security;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.gspann.constant.Credential;


@Component
public class AuthFailureHandler extends SimpleUrlAuthenticationFailureHandler{
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {
		response.setStatus(HttpServletResponse.SC_NON_AUTHORITATIVE_INFORMATION);
		if(exception instanceof BadCredentialsException) {
			response.getWriter().print(Credential.BAD.name());
		}else if(exception instanceof LockedException) {
			response.getWriter().print(Credential.LOCKED.name());
		}else if(exception instanceof DisabledException) {
			response.getWriter().print(Credential.DISABLED.name());
		}else if(exception instanceof AccountExpiredException) {
			response.getWriter().print(Credential.EXPIRED.name());
		}else if(exception instanceof CredentialsExpiredException) {
			response.getWriter().print(Credential.CREDENTIAL_EXPIRED.name());
		}else {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);	
		}
		response.getWriter().flush();
		
	}
}
