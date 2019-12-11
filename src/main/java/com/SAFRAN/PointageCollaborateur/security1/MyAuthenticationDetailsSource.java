package com.SAFRAN.PointageCollaborateur.security1;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class MyAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails>{

	@Override
	public WebAuthenticationDetails buildDetails(final HttpServletRequest context) {
		// TODO Auto-generated method stub
		return new MyAuthenticationDetails(context);
	}

}
