package com.gafahtec.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Mascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMascota;
	private String nombre;
	private String imagen;
	private String peso;
	private String tamanio;
	private String email;
	private String sexo;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_mascota", nullable = false, foreignKey = @ForeignKey(name = "FK_tipo_mascota"))
	private TipoMascota tipoMascota;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_raza", nullable = false, foreignKey = @ForeignKey(name = "FK_tipo_raza"))
	private TipoRaza tipoRaza;
	private LocalDate fechaNacimiento;
	
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fechaRegistro;
}
