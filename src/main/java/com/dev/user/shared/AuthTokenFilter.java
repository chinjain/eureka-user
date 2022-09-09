//package com.dev.user.shared;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import com.dev.user.service.UserServiceImpl;
//
//public class AuthTokenFilter extends OncePerRequestFilter {
//
//	@Autowired
//	UserDetailsService userDetailsService;
//
//	@Autowired
//	jwtUtils jwtUtils;
//	
//	@Autowired
//	UserServiceImpl serviceImpl;
//
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		try {
//
//			String jwt = parseJwt(request);
//			if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//				String username = jwtUtils.getUsernameFromToken(jwt);
//				UserDto userDetails   = (UserDto) serviceImpl.loadUserByUsername(username);
//				
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, new ArrayList<>());
//				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//			}
//
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//
//	}
//
//	private String parseJwt(HttpServletRequest request) {
//		String headerAuth = request.getHeader("Authorization");
//
//		if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
//			return headerAuth.substring(7, headerAuth.length());
//		}
//		return null;
//	}
//
//}
