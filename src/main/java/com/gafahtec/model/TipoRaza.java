package com.gafahtec.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class TipoRaza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idTipoRaza;
	private String descripcion;

	@JsonIgnore
	@Builder.Default
	@OneToMany(mappedBy = "tipoRaza", cascade = { CascadeType.ALL }, orphanRemoval = true)
	private List<Mascota> mascotas = new ArrayList<>();
}
