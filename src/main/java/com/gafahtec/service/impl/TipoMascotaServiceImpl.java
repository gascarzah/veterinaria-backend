package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.TipoMascota;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.ITipoMascotaRepository;
import com.gafahtec.service.ITipoMascotaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class TipoMascotaServiceImpl  extends CRUDImpl<TipoMascota, Integer>  implements ITipoMascotaService {

	
	private ITipoMascotaRepository repo;
	
	@Override
	protected IGenericRepository<TipoMascota, Integer> getRepo() {
		
		return repo;
	}
	
//	@Override
//	public Page<TipoMascota> listarPageable(Pageable pageable) {
//		return repo.findAll(pageable);
//	}
//	
//	
//	@Override
//	public List<TipoMascota> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

