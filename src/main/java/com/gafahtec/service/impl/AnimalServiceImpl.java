package com.gafahtec.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Animal;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IAnimalRepository;
import com.gafahtec.service.IAnimalService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class AnimalServiceImpl  extends CRUDImpl<Animal, Integer>  implements IAnimalService {

	
	private IAnimalRepository repo;
	
	@Override
	protected IGenericRepository<Animal, Integer> getRepo() {
		
		return repo;
	}
	
	@Override
	public Page<Animal> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}
	
	
//	@Override
//	public List<TipoMascota> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

