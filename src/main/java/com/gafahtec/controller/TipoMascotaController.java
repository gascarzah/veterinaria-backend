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
import com.gafahtec.model.TipoMascota;
import com.gafahtec.service.ITipoMascotaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/tipoMascotas")
@AllArgsConstructor
public class TipoMascotaController {

	private ITipoMascotaService iTipoMascotaService;
	
	@GetMapping
	public ResponseEntity<List<TipoMascota>> listar() throws Exception{
		List<TipoMascota> lista = iTipoMascotaService.listar();
		return new ResponseEntity<List<TipoMascota>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TipoMascota> listarPorId(@PathVariable("id") Integer id) throws Exception{
		TipoMascota obj = iTipoMascotaService.listarPorId(id);
		
		if(obj.getIdTipoMascota() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<TipoMascota>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<TipoMascota> registrar(@Valid @RequestBody TipoMascota p) throws Exception{
		TipoMascota obj = iTipoMascotaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdTipoMascota()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<TipoMascota> modificar(@Valid @RequestBody TipoMascota p) throws Exception{
//		System.out.println(p);
		TipoMascota obj = iTipoMascotaService.modificar(p);
		return new ResponseEntity<TipoMascota>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		TipoMascota obj = iTipoMascotaService.listarPorId(id);
		
		if(obj.getIdTipoMascota() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoMascotaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/pageable")
//	public ResponseEntity<Page<TipoMascota>> listarPageable(@PageableDefault(sort = "apellidoPaterno")Pageable pageable) throws Exception{			
//		Page<TipoMascota> TipoMascotas = iTipoMascotaService.listarPageable(pageable);
//		return new ResponseEntity<Page<TipoMascota>>(TipoMascotas, HttpStatus.OK);
//	}
	
//	@GetMapping
//	public ResponseEntity<List<TipoMascota>> listarOrdenadoPorId() throws Exception{
//		List<TipoMascota> lista = iTipoMascotaService.listarOrderById();
//		return new ResponseEntity<List<TipoMascota>>(lista, HttpStatus.OK);
//	}
	
}
