package com.gafahtec.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.MimeTypeUtils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	
	
//	private String clientSecret="[a-zA-Z0-9._]^+$Guidelines89797987forAlphabeticalArraNumeralsandOtherSymbo$";
	
	private  AuthenticationManager authenticationManager;
	



	@SuppressWarnings("unchecked")
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		Map<String, String> requestMap = new HashMap<>();
		try {
			requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
			log.info(""+requestMap);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		String username = requestMap.get("username");
		String password = requestMap.get("password");
		
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
		
	
		log.info("Username is: {}",username);
		log.info("Password is: {}",password);
		
//		printRequest(request);
		
//		log.info("Username2 is: {}",obtainUsername(request));
//		log.info("Password2 is: {}",obtainPassword(request));
		
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
//		Authentication authentication = null;
//		try {
//			authentication =  authenticationManager.authenticate(authenticationToken);	
//		}catch (Exception e) {
//			log.error("Error ==>>> ",e);
//		}
//		
		
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	
//	private void printRequest(HttpServletRequest httpRequest) {
//	    System.out.println(" \n\n Headers");
//
//	    Enumeration headerNames = httpRequest.getHeaderNames();
//	    while(headerNames.hasMoreElements()) {
//	        String headerName = (String)headerNames.nextElement();
//	        System.out.println(headerName + " = " + httpRequest.getHeader(headerName));
//	    }
//
//	    System.out.println("\n\nParameters");
//
//	    Enumeration params = httpRequest.getParameterNames();
//	    while(params.hasMoreElements()){
//	        String paramName = (String)params.nextElement();
//	        System.out.println(paramName + " = " + httpRequest.getParameter(paramName));
//	    }
//
//	    System.out.println("\n\n Row data");
//	    System.out.println(extractPostRequestBody(httpRequest));
//	}
//
//	static String extractPostRequestBody(HttpServletRequest request) {
//	    if ("POST".equalsIgnoreCase(request.getMethod())) {
//	        Scanner s = null;
//	        try {
//	            s = new Scanner(request.getInputStream(), "UTF-8").useDelimiter("\\A");
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }
//	        return s.hasNext() ? s.next() : "";
//	    }
//	    return "";
//	}
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) throws IOException, ServletException {
		
		User user = (User)authentication.getPrincipal();
//		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
		
		String access_token = JWT.create()
				.withSubject(user.getUsername())
				.withExpiresAt(new Date(System.currentTimeMillis()+ 1 * 60 * 1000))
//				.withExpiresAt(new Date(System.currentTimeMillis()+ 20000))
				.withIssuer(request.getRequestURL().toString())
				.withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.sign(algorithm);
		
		
//		String jwtToken = JWT.create()
//				.withIssuer(key)
//				.withIssuedAt(issureDate)
//				.withExpiresAt(expiryDate)
//				.sign(Algorithm.HMAC256(secreateKey));
		
		
		String refresh_token = JWT.create()
		.withSubject(user.getUsername())
		.withExpiresAt(new Date(System.currentTimeMillis() + 2 * 60 * 1000))
//		.withExpiresAt(new Date(System.currentTimeMillis() + 30000))
		.withIssuer(request.getRequestURL().toString())
		.sign(algorithm);
		
//		response.setHeader("access_token", access_token);
//		response.setHeader("refresh_token", refresh_token);
		
		var tokens = new HashMap();
		tokens.put("access_token", access_token);
		tokens.put("refresh_token", refresh_token);
		System.out.println(tokens);
//		usuarioService.grabarToken(AuthToken.builder().accessToken(access_token).refreshToken(refresh_token).username(user.getUsername()).build());
		response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
		new ObjectMapper().writeValue(response.getOutputStream(), tokens);
	}






}
