package com.dev.user.jwtConfig;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.dev.user.service.UserService;

public class AuthJwtTokenFilter extends OncePerRequestFilter {

	Logger log = LoggerFactory.getLogger(AuthJwtTokenFilter.class);

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils jwtUtils;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		log.info("<-----Inside Jwt filter-------->");
		try {
			String token = jwtUtils.parseJwt(request);
			if (token != null) {
				String username = jwtUtils.getUsername(token);
				UserDetails userDetails = userService.loadUserByUsername(username);
				if (jwtUtils.validateToken(token, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails.getUsername(), null, Collections.emptyList());

					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}
		}

		catch (Exception e) {
			log.error("Can't set find the user");
		}

		filterChain.doFilter(request, response);

	}

}
