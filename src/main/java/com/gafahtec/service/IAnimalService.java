package com.gafahtec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.gafahtec.model.Animal;

public interface IAnimalService extends ICRUD<Animal, Integer>{

	Page<Animal> listarPageable(Pageable pageable);

//	Venta registrarTransaccion(@Valid Venta p);

}
