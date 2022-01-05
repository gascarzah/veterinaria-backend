package com.gafahtec.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEmpleado;
	private String dni;
	private String nombres;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String direccion;
	//perfil
	//departamento
	//provincia
	//distrito
	//fec.nac
	private LocalDateTime fechaIngreso;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fechaRegistro;
	private String fecha;
	private String email;
	private String telefono;
	private boolean activo;
}
