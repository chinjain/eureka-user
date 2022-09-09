//package com.dev.user.shared;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//
//import com.dev.user.model.User;
//
//import io.jsonwebtoken.ExpiredJwtException;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.SignatureException;
//import io.jsonwebtoken.UnsupportedJwtException;
//
//@Component
//public class jwtUtils {
//
//	private Logger logger = LoggerFactory.getLogger(jwtUtils.class);
//
//	private String jwtSecret = "secret";
//
//	private int jwtExpirationMs = 3600000;
//
//	public String generateJwtToken(Authentication authentication) {
//
//		UserDto user = (UserDto) authentication.getPrincipal();
//		return Jwts.builder().setSubject(user.getEmail()).setIssuedAt(new Date())
//				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
//				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
//
//	}
//
//	public String getUsernameFromToken(String token) {
//		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
//	}
//
//	public boolean validateJwtToken(String authToken) {
//		try {
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//			return true;
//		} catch (SignatureException e) {
//			logger.error("Invalid JWT signature: {}", e.getMessage());
//		} catch (MalformedJwtException e) {
//			logger.error("Invalid JWT token: {}", e.getMessage());
//		} catch (ExpiredJwtException e) {
//			logger.error("JWT token is expired: {}", e.getMessage());
//		} catch (UnsupportedJwtException e) {
//			logger.error("JWT token is unsupported: {}", e.getMessage());
//		} catch (IllegalArgumentException e) {
//			logger.error("JWT claims string is empty: {}", e.getMessage());
//		}
//
//		return false;
//	}
//
//}
