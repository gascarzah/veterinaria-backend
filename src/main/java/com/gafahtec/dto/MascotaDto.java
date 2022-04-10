package com.gafahtec.dto;

import java.io.File;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MascotaDto {

	private Integer idMascota;
	private String nombre;
	private String peso;
	private String sexo;
	private Integer idRaza;
	private Integer idCliente;
	private File image;
	
}
