package com.gafahtec.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gafahtec.dto.UsuarioDto;
import com.gafahtec.model.Empleado;
import com.gafahtec.model.Rol;
import com.gafahtec.model.Usuario;
import com.gafahtec.repository.IEmpleadoRepository;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IRolRepository;
import com.gafahtec.repository.IUsuarioRepository;
import com.gafahtec.service.UsuarioService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@AllArgsConstructor
@Service 
 
@Slf4j
public class UsuarioServiceImpl extends CRUDImpl<Usuario, Integer> implements UsuarioService, UserDetailsService {

	private IUsuarioRepository repo;
	private IEmpleadoRepository empleadoRepo;

	private IRolRepository rolRepo;
	
	private final PasswordEncoder passwordEncoder;
	
//	private AuthTokenRepository authTokenRepository;
	
	@Override
	protected IGenericRepository<Usuario, Integer> getRepo() {

		return repo;
	}

	
	@Transactional	
	@Override
	public Usuario registrarUsuarioDto(UsuarioDto usuarioDto) {

		var usuarioRegistrado = repo.findByUsername(usuarioDto.getCorreo());

		if (usuarioRegistrado.isEmpty()) {

			var rolSearched = rolRepo.findByNombre(usuarioDto.getRol());
			
			if(rolSearched == null ) {
				rolSearched = rolRepo.save(Rol.builder().nombre(usuarioDto.getRol()).build());
			}
			
			var persona = Empleado.builder().nombres(usuarioDto.getNombres())
										   .apellidoPaterno(usuarioDto.getApellidoPaterno())
										   .apellidoMaterno(usuarioDto.getApellidoMaterno())
										   .correo(usuarioDto.getCorreo()).build();

			var outputPersona = empleadoRepo.save(persona);

			System.out.println("id grabado " + outputPersona);

			Set<Rol> hashRoles = new HashSet<Rol>();
			hashRoles.add(rolSearched);
			var usuario = Usuario.builder().username(usuarioDto.getCorreo())
										   .password(passwordEncoder.encode(usuarioDto.getPassword()))
										   .empleado(outputPersona)
										   .roles(hashRoles).build();

			return repo.save(usuario);
		} else {
			return null;
		}
	}


	public Rol registrarRol(Rol rol) {
		
		return rolRepo.save(rol);
	}

	@Override
	public void addRoleToUser(String correo, String rol) {
		
		log.info("Agrega rol {} a usuario {}", rol, correo);
		var list = repo.findByUsername(correo);
		var objUsuario =list.get(0);
		var objRol = rolRepo.findByNombre(rol);
		
		objUsuario.getRoles().add(objRol);
		
	}



	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
		var list = repo.findByUsername(correo);
		
		
		if(list.isEmpty()) {
			log.error("Usuario no se encuentra en base de datos");
			throw new UsernameNotFoundException("Usuario no se encuentra en base de datos");
		}else {
			log.error("Usuario encontrado en  base de datos: {}", correo);
		}
		var usuario = list.get(0);
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		usuario.getRoles().forEach(rol -> {
			authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		});
		
		return new User(usuario.getUsername(), usuario.getPassword(), authorities);
	}


	@Override
	public Usuario getUsuario(String username) {
		var list = repo.findByUsername(username);
		return list != null && !list.isEmpty() ? list.get(0) : null ;
	}


//	@Override
//	public void grabarToken(AuthToken token) {
//		AuthToken saved = authTokenRepository.save(token);
//		System.out.println("token grabado "+saved);
//		
//	}
	
	
}
