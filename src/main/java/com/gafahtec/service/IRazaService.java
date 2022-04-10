package com.gafahtec.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Animal;
import com.gafahtec.model.Raza;

public interface IRazaService extends ICRUD<Raza, Integer>{

	Page<Raza> listarPageable(Pageable pageable);

	List<Raza> listarPorAnimal(Animal animal);

//	Venta registrarTransaccion(@Valid Venta p);

}
