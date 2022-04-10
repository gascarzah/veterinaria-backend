package com.gafahtec.service;

import com.gafahtec.dto.UsuarioDto;
import com.gafahtec.model.Rol;
import com.gafahtec.model.Usuario;

public interface UsuarioService extends ICRUD<Usuario, Integer>{
	

	Usuario getUsuario(String username);
	
	Usuario registrarUsuarioDto(UsuarioDto usuario);

	void addRoleToUser(String correo, String rol);
	
	public Rol registrarRol(Rol rol);
	
//	void grabarToken(AuthToken token);

}
