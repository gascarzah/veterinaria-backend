package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Empleado;
import com.gafahtec.service.IEmpleadoService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/empleados")
@AllArgsConstructor
public class EmpleadoController {

	private IEmpleadoService iEmpleadoService;
	
	@GetMapping
	public ResponseEntity<List<Empleado>> listar() throws Exception{
		List<Empleado> lista = iEmpleadoService.listar();
		return new ResponseEntity<List<Empleado>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Empleado> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Empleado obj = iEmpleadoService.listarPorId(id);
		
		if(obj.getIdEmpleado() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Empleado>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Empleado> registrar(@Valid @RequestBody Empleado p) throws Exception{
		Empleado obj = iEmpleadoService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdEmpleado()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Empleado> modificar(@Valid @RequestBody Empleado p) throws Exception{
		Empleado obj = iEmpleadoService.modificar(p);
		return new ResponseEntity<Empleado>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Empleado obj = iEmpleadoService.listarPorId(id);
		
		if(obj.getIdEmpleado() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iEmpleadoService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Empleado>> listarPageable(@PageableDefault(sort = "apellidoPaterno")Pageable pageable) throws Exception{			
		Page<Empleado> paginas = iEmpleadoService.listarPageable(pageable);
		return new ResponseEntity<Page<Empleado>>(paginas, HttpStatus.OK);
	}
}
