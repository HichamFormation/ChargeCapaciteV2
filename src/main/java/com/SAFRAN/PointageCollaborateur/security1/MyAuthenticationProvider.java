package com.SAFRAN.PointageCollaborateur.security1;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.SAFRAN.PointageCollaborateur.Service.PointageService;
import com.SAFRAN.PointageCollaborateur.model.Collaborateur;

public class MyAuthenticationProvider implements AuthenticationProvider {

	private PointageService pointageService;

	public void setPointageService(PointageService pointageService) {
		this.pointageService = pointageService;
	}

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException { 
				
		Authentication res = isValid(authentication);
		
		if (!res.isAuthenticated()) {
			
			throw new BadCredentialsException("Bad credentials");
		}
		
		return res;
	}

	private Authentication isValid(final Authentication authentication) {
		Authentication res = authentication;
		System.out.println("Selected method: " + ((MyAuthenticationDetails) authentication.getDetails()).getMethod());

		// TODO: Appeler le service permettant de retourner l'utilisateur à partir de son matricule & pass
		Collaborateur collab = pointageService.findCollab(authentication.getPrincipal().toString(), authentication.getCredentials().toString());
		if (null != collab) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();

			// si on garde un seul Role par utilisateur
			// authorities.add(new SimpleGrantedAuthority(collab.getRole()));

			// deuxieme alternative: un collaborateur peut avoir plusieurs roles: on boucle
			// sur ses roles
			//for (String role : collab.getRole()) {
				authorities.add(new SimpleGrantedAuthority(collab.getRole()));
			//}

			// intouchable
			
			final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);

			result.setDetails(authentication.getDetails());

			res = result;

		}

		/*
		 * A retirer car en dur (seulement pour test)
		 * 
		 * if ("Admin".equals(authentication.getPrincipal())&&"Password".equals(
		 * authentication.getCredentials())) { //ancien mecanisme //res =
		 * createSuccessAuthentication(authentication); List<SimpleGrantedAuthority>
		 * authorities = new ArrayList<>(); authorities.add(new
		 * SimpleGrantedAuthority("ROLE_HAMID"));
		 * 
		 * final UsernamePasswordAuthenticationToken result = new
		 * UsernamePasswordAuthenticationToken(authentication.getPrincipal(),
		 * authentication.getCredentials(), authorities);
		 * 
		 * 
		 * result.setDetails(authentication.getDetails());
		 * 
		 * res = result; }
		 */
		return res;
	}

//	    private Authentication isValid(final Authentication authentication) {
//	        Authentication res = authentication;
//	        System.out.println("Selected methode: "+((MyAuthenticationDetails)authentication.getDetails()).getMethod());
//	        if ("Admin".equals(authentication.getPrincipal())&&"Password".equals(authentication.getCredentials())) {
//	            res = createSuccessAuthentication(authentication);
//	        }
//	        return res;
//	    }

	@Override
	public boolean supports(final Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	protected Authentication createSuccessAuthentication(final Authentication authentication) {

		final UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(
				authentication.getPrincipal(), authentication.getCredentials(), authentication.getAuthorities());
		result.setDetails(authentication.getDetails());

		return result;
	}
}