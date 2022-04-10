package com.gafahtec.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gafahtec.model.Animal;
import com.gafahtec.model.Raza;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IRazaRepository;
import com.gafahtec.service.IRazaService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class RazaServiceImpl  extends CRUDImpl<Raza, Integer>  implements IRazaService {

	
	private IRazaRepository repo;
	
	@Override
	protected IGenericRepository<Raza, Integer> getRepo() {
		
		return repo;
	}
	
	
	
	@Override
	public Page<Raza> listarPageable(Pageable pageable) {
		return repo.findAll(pageable);
	}



	@Override
	public List<Raza> listarPorAnimal(Animal animal) {
		// TODO Auto-generated method stub
		return repo.findByAnimal(animal);
	}
	
//	
//	@Override
//	public List<Cliente> listarOrderNombre() {		
//		return repo.findAll(Sort.by("apellidoPaterno"));
//	}
}

