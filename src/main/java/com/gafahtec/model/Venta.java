package com.gafahtec.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
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
public class Venta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idVenta;
	private String numVenta;
	 @CreationTimestamp
	 @Column(updatable = false)
	private LocalDateTime fecha;
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false, foreignKey = @ForeignKey(name = "FK_venta_cliente"))
	private Cliente cliente;


	private Float efectivo;
	private Float visa;
	private Float mastercard;
	private Float total;
	
	
	
}
