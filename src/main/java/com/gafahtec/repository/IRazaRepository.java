package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Animal;
import com.gafahtec.model.Raza;

public interface IRazaRepository extends IGenericRepository<Raza, Integer>{
	
	
	List<Raza> findByAnimal(Animal animal);

	
}
