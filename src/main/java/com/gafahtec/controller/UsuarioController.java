package com.gafahtec.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gafahtec.dto.UsuarioDto;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Rol;
import com.gafahtec.model.Usuario;
import com.gafahtec.service.UsuarioService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@Slf4j
public class UsuarioController {

	private UsuarioService iUsuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listar() throws Exception {
		List<Usuario> lista = iUsuarioService.listar();
		return new ResponseEntity<List<Usuario>>(lista, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarPorId(@PathVariable("id") Integer id) throws Exception {
		Usuario obj = iUsuarioService.listarPorId(id);

		if (obj.getUsername() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id);
		}

		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Usuario> registrar(@RequestBody UsuarioDto p1) throws Exception {
		URI location = null;
		Usuario obj = iUsuarioService.registrarUsuarioDto(p1);
		if(obj == null) {
			
				throw new ModeloNotFoundException("Correo ya se encuentra registrado" );
			
		}else {
			location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getUsername())
					.toUri();	
		}
		
		return ResponseEntity.created(location).build();
	}

	@PutMapping
	public ResponseEntity<Usuario> modificar(@Valid @RequestBody Usuario p) throws Exception {
		Usuario obj = iUsuarioService.modificar(p);
		return new ResponseEntity<Usuario>(obj, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception {
		Usuario obj = iUsuarioService.listarPorId(id);

		if (obj.getUsername() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO " + id);
		}

		iUsuarioService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/token/refresh")
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			try {
				String refresh_token = authorizationHeader.substring("Bearer ".length());
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build();
				DecodedJWT decodedJWT = verifier.verify(refresh_token);
				String username = decodedJWT.getSubject();
				Usuario user = iUsuarioService.getUsuario(username);

				String access_token = JWT.create()
						.withSubject(user.getUsername())
						.withExpiresAt(new Date(System.currentTimeMillis()+ 1 * 60 * 1000))
//						.withExpiresAt(new Date(System.currentTimeMillis()+ 30000))
						.withIssuer(request.getRequestURL().toString())
						.withClaim("roles", user.getRoles().stream().map(Rol::getNombre).collect(Collectors.toList()))
						.sign(algorithm);
				
			
				

				var tokens = new HashMap();
				tokens.put("access_token", access_token);
				tokens.put("refresh_token", refresh_token);
				System.out.println(tokens);
				response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
				
				

			} catch (Exception e) {
				log.error("Error loggin in {}", e.getMessage());
				response.setHeader("error", e.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				var error = new HashMap();
				error.put("error_message", e.getMessage());
				response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), error);
			}
		} else {
			throw new RuntimeException("Refresh token perdido");

		}
	}

}
