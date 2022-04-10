package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Cliente;

public interface IClienteRepository extends IGenericRepository<Cliente, Integer>{

	List<Cliente> findByNumeroDocumento(String numeroDocumento);
}
