package com.gafahtec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Cliente;

public interface IClienteService extends ICRUD<Cliente, Integer>{

	Page<Cliente> listarPageable(Pageable pageable);
	
	public List<Cliente> listarOrderNombre();

}
