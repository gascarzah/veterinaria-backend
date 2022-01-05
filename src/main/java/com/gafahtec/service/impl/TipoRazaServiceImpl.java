package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.TipoRaza;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.ITipoRazaRepository;
import com.gafahtec.service.ITipoRazaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TipoRazaServiceImpl  extends CRUDImpl<TipoRaza, Integer>  implements ITipoRazaService {

	
	private ITipoRazaRepository repo;
	
	@Override
	protected IGenericRepository<TipoRaza, Integer> getRepo() {
		
		return repo;
	}
	
//	@Override
//	public Page<Cliente> listarPageable(Pageable pageable) {
//		return repo.findAll(pageable);
//	}
//	
//	
//	@Override
//	public List<Cliente> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

