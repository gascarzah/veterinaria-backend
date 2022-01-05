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
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idCliente;
	private String nombres;
	private String dni;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String imagen;
	private boolean activo;
	private String tipoDocumento;
	private String telefono;
	private String email;
	private String direccion;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fechaRegistro;

	
}
