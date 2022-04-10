package com.gafahtec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.gafahtec.dto.UsuarioDto;
import com.gafahtec.service.UsuarioService;

@SpringBootApplication
public class VeterinariaApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(VeterinariaApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEnconder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		// TODO Auto-generated method stub
		return builder.sources(VeterinariaApplication.class);
	}
	
	@Bean
	CommandLineRunner run(UsuarioService userService) {
		String correo = "karina@correo.com";
		return args -> {
//			userService.registrarRol(Rol.builder().nombre("ROL_SUPER_ADMIN").build());
			
//			userService.registrar(Usuario.builder().username("user@gmail.com").password("123").roles(new HashSet<Rol>()).build());
			
			userService.registrarUsuarioDto(UsuarioDto.builder().nombres("karina").apellidoMaterno("Hinostroza").apellidoPaterno("Ascarza").correo(correo).password("123456")
					.rol("ROL_SUPER_ADMIN").build());
			
//			userService.addRoleToUser(correo, "ROL_SUPER_ADMIN");
		};
	}
}
