package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Empleado;

public interface IEmpleadoService extends ICRUD<Empleado, Integer>{

	Page<Empleado> listarPageable(Pageable pageable);

}
