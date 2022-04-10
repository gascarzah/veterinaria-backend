package com.gafahtec.service.impl;

import org.springframework.stereotype.Service;

import com.gafahtec.model.Rol;
import com.gafahtec.repository.IGenericRepository;
import com.gafahtec.repository.IRolRepository;
import com.gafahtec.service.IRolService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class RolServiceImpl extends CRUDImpl<Rol, Integer>  implements IRolService {

	
	private IRolRepository repo;
	
	@Override
	protected IGenericRepository<Rol, Integer> getRepo() {
		
		return repo;
	}
}
