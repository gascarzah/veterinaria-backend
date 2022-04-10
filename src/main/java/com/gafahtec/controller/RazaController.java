package com.gafahtec.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
import com.gafahtec.model.Animal;
import com.gafahtec.model.Raza;
import com.gafahtec.service.IRazaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/razas")
@AllArgsConstructor
public class RazaController {

	private IRazaService iTipoRazaService;
	
	@GetMapping
	public ResponseEntity<List<Raza>> listar() throws Exception{
		List<Raza> lista = iTipoRazaService.listar();
		return new ResponseEntity<List<Raza>>(lista, HttpStatus.OK);
	}

//	@GetMapping("/{idTipoMascota}")
	@RequestMapping(value = "getByIdAnimal/{idAnimal}")
	public ResponseEntity<List<Raza>> listarPorAnimal(@PathVariable("idAnimal") Integer idAnimal) throws Exception{
		System.out.println(idAnimal);
		List<Raza> lista = iTipoRazaService.listarPorAnimal(Animal.builder().idAnimal(idAnimal).build());
		System.out.println(lista);
		return new ResponseEntity<List<Raza>>(lista, HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Raza> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Raza obj = iTipoRazaService.listarPorId(id);
		
		if(obj.getIdRaza() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Raza>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Raza> registrar(@Valid @RequestBody Raza p) throws Exception{
		System.out.println("Registrar");
		System.out.println(p);
		Raza obj = iTipoRazaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdRaza()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Raza> modificar(@Valid @RequestBody Raza p) throws Exception{
//		System.out.println(p);
		Raza obj = iTipoRazaService.modificar(p);
		return new ResponseEntity<Raza>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Raza obj = iTipoRazaService.listarPorId(id);
		
		if(obj.getIdRaza() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoRazaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Raza>> listarPageable(Pageable pageable) throws Exception{			
		System.out.println("pageable "+pageable);
		Page<Raza> paginas = iTipoRazaService.listarPageable(pageable);
		System.out.println("paginas "+paginas);
		return new ResponseEntity<Page<Raza>>(paginas, HttpStatus.OK);
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
