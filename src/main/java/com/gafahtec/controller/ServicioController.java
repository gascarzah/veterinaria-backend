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
import com.gafahtec.model.Servicio;
import com.gafahtec.service.IServicioService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/servicios")
@AllArgsConstructor
public class ServicioController {

	private IServicioService iServicioService;
	
	@GetMapping
	public ResponseEntity<List<Servicio>> listar() throws Exception{
		List<Servicio> lista = iServicioService.listar();
		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Servicio> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Servicio obj = iServicioService.listarPorId(id);
		
		if(obj.getIdServicio() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Servicio>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Servicio> registrar(@Valid @RequestBody Servicio p) throws Exception{
		Servicio obj = iServicioService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdServicio()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Servicio> modificar(@Valid @RequestBody Servicio p) throws Exception{
//		System.out.println(p);
		Servicio obj = iServicioService.modificar(p);
		return new ResponseEntity<Servicio>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Servicio obj = iServicioService.listarPorId(id);
		
		if(obj.getIdServicio() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iServicioService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Servicio>> listarPageable(@PageableDefault(sort = "nombre")Pageable pageable) throws Exception{			
		Page<Servicio> Servicios = iServicioService.listarPageable(pageable);
		return new ResponseEntity<Page<Servicio>>(Servicios, HttpStatus.OK);
	}
	
//	@GetMapping
//	public ResponseEntity<List<Servicio>> listarOrdenadoPorId() throws Exception{
//		List<Servicio> lista = iServicioService.listarOrderById();
//		return new ResponseEntity<List<Servicio>>(lista, HttpStatus.OK);
//	}
	
}
