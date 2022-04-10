package com.gafahtec.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = {"mascotas" })
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Raza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idRaza;
	private String nombre;
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "id_animal", nullable = false, foreignKey = @ForeignKey(name = "FK_raza_animal"))
	private Animal animal;
	

	
	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "raza", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Mascota> mascotas = new ArrayList<>();
}
