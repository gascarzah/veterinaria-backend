package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Mascota;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IMascotaRepository;
import com.gafahtec.service.IMascotaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class MascotaServiceImpl  extends CRUDImpl<Mascota, Integer>  implements IMascotaService {

	
	private IMascotaRepository repo;
	
	@Override
	protected IGenericRepository<Mascota, Integer> getRepo() {
		
		return repo;
	}
	
//	@Override
//	public Page<Mascota> listarPageable(Pageable pageable) {
//		return repo.findAll(pageable);
//	}
	
	
//	@Override
//	public List<Mascota> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

