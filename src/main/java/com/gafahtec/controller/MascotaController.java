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
import com.gafahtec.model.Mascota;
import com.gafahtec.service.IMascotaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/mascotas")
@AllArgsConstructor
public class MascotaController {

	private IMascotaService iMascotaService;
	
	@GetMapping
	public ResponseEntity<List<Mascota>> listar() throws Exception{
		List<Mascota> lista = iMascotaService.listar();
		return new ResponseEntity<List<Mascota>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Mascota> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Mascota obj = iMascotaService.listarPorId(id);
		
		if(obj.getIdMascota() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Mascota>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Mascota> registrar(@Valid @RequestBody Mascota p) throws Exception{
		Mascota obj = iMascotaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMascota()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Mascota> modificar(@Valid @RequestBody Mascota p) throws Exception{
//		System.out.println(p);
		Mascota obj = iMascotaService.modificar(p);
		return new ResponseEntity<Mascota>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Mascota obj = iMascotaService.listarPorId(id);
		
		if(obj.getIdMascota() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iMascotaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
//	@GetMapping("/pageable")
//	public ResponseEntity<Page<Mascota>> listarPageable(@PageableDefault(sort = "apellidoPaterno")Pageable pageable) throws Exception{			
//		Page<Mascota> Mascotas = iMascotaService.listarPageable(pageable);
//		return new ResponseEntity<Page<Mascota>>(Mascotas, HttpStatus.OK);
//	}
	
//	@GetMapping
//	public ResponseEntity<List<Mascota>> listarOrdenadoPorId() throws Exception{
//		List<Mascota> lista = iMascotaService.listarOrderById();
//		return new ResponseEntity<List<Mascota>>(lista, HttpStatus.OK);
//	}
	
}
