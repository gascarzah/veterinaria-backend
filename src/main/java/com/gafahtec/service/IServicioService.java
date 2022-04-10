package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Servicio;

public interface IServicioService extends ICRUD<Servicio, Integer>{

	Page<Servicio> listarPageable(Pageable pageable);

//	Venta registrarTransaccion(@Valid Venta p);

}
