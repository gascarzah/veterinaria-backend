package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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
import com.gafahtec.model.TipoRaza;
import com.gafahtec.service.ITipoRazaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/tipoRazas")
@AllArgsConstructor
public class TipoRazaController {

	private ITipoRazaService iTipoRazaService;
	
	@GetMapping
	public ResponseEntity<List<TipoRaza>> listar() throws Exception{
		List<TipoRaza> lista = iTipoRazaService.listar();
		return new ResponseEntity<List<TipoRaza>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoRaza> listarPorId(@PathVariable("id") Integer id) throws Exception{
		TipoRaza obj = iTipoRazaService.listarPorId(id);
		
		if(obj.getIdTipoRaza() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<TipoRaza>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TipoRaza> registrar(@Valid @RequestBody TipoRaza p) throws Exception{
		TipoRaza obj = iTipoRazaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoRaza()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<TipoRaza> modificar(@Valid @RequestBody TipoRaza p) throws Exception{
//		System.out.println(p);
		TipoRaza obj = iTipoRazaService.modificar(p);
		return new ResponseEntity<TipoRaza>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		TipoRaza obj = iTipoRazaService.listarPorId(id);
		
		if(obj.getIdTipoRaza() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoRazaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/pageable")
//	public ResponseEntity<Page<TipoRaza>> listarPageable(@PageableDefault(sort = "apellidoPaterno")Pageable pageable) throws Exception{			
//		Page<TipoRaza> TipoRazas = iTipoRazaService.listarPageable(pageable);
//		return new ResponseEntity<Page<TipoRaza>>(TipoRazas, HttpStatus.OK);
//	}
	
//	@GetMapping
//	public ResponseEntity<List<TipoRaza>> listarOrdenadoPorId() throws Exception{
//		List<TipoRaza> lista = iTipoRazaService.listarOrderById();
//		return new ResponseEntity<List<TipoRaza>>(lista, HttpStatus.OK);
//	}
	
}
