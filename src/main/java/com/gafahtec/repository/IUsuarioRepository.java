package com.gafahtec.repository;

import java.util.List;

import com.gafahtec.model.Usuario;

public interface IUsuarioRepository extends IGenericRepository<Usuario,Integer>{

	 List<Usuario> findByUsername(String username);

//	 Usuario findOneByUsername(String username);	
}
