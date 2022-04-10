package com.gafahtec.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idUsuario;
	private String username;
	private String password;
	
	
	@ManyToOne
	@JoinColumn(name = "empleado_id", foreignKey = @ForeignKey(name = "FK_empleado_usuario"))
	private Empleado empleado;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_rol", 
		joinColumns = @JoinColumn(name = "id_usuario", referencedColumnName = "idUsuario"), 
		inverseJoinColumns = @JoinColumn(name = "id_rol", referencedColumnName = "idRol"))
	private Set<Rol> roles;	
	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime fechaRegistro;
	

}
