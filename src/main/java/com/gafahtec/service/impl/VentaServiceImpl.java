package com.gafahtec.service.impl;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Venta;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IVentaRepository;
import com.gafahtec.service.IVentaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class VentaServiceImpl extends CRUDImpl<Venta, Integer> implements IVentaService {

	private IVentaRepository repo;


	@Override
	protected IGenericRepository<Venta, Integer> getRepo() {

		return repo;
	}

	@Transactional
	@Override
	public Venta registrarTransaccion(@Valid Venta p) {
		repo.save(p);


		

		return p;
	}
}
