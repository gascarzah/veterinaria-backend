package com.gafahtec.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDto {
	private String correo;
	private String password;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String rol;


}
