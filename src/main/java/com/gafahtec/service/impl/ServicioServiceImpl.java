package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Servicio;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IServicioRepository;
import com.gafahtec.service.IServicioService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class ServicioServiceImpl  extends CRUDImpl<Servicio, Integer>  implements IServicioService {

	
	private IServicioRepository repo;

	@Override
	protected IGenericRepository<Servicio, Integer> getRepo() {
	
		return repo;
	}
	

	
//	@Override
//	public Page<Servicio> listarPageable(Pageable pageable) {
//		return repo.findAll(pageable);
//	}
//	
//	
//	@Override
//	public List<Servicio> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

