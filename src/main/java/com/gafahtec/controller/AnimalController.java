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

import com.gafahtec.dto.ApiResponse;
import com.gafahtec.exception.ModeloNotFoundException;
import com.gafahtec.model.Animal;
import com.gafahtec.service.IAnimalService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/animales")
@AllArgsConstructor
public class AnimalController {

	private IAnimalService iTipoMascotaService;
	
//	@GetMapping("/alumnos/{anio}/{seccion}")
//	public ApiResponse<List<Persona>> getDecadas(
//			@PathVariable int anio,
//			@PathVariable String seccion){
//		
//        return new ApiResponse<>(HttpStatus.OK.value(), "Alumnos obtenido satisfactoriamente", personaRepositoryDao.getAlumnos(anio, seccion));
//    }
	
	@GetMapping
	public ResponseEntity<List<Animal>> listar() throws Exception{
		List<Animal> lista = iTipoMascotaService.listar();
		return new ResponseEntity<List<Animal>>(lista, HttpStatus.OK);
	}
	
//	@GetMapping
//	public ApiResponse<List<Animal>> listar() throws Exception{
//		
//        return new ApiResponse<>(HttpStatus.OK.value(), "Animales obtenido satisfactoriamente", iTipoMascotaService.listar());
//    }
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Animal> listarPorId(@PathVariable("id") Integer id) throws Exception{
		Animal obj = iTipoMascotaService.listarPorId(id);
		
		if(obj.getIdAnimal() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Animal>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Animal> registrar(@Valid @RequestBody Animal p) throws Exception{
		Animal obj = iTipoMascotaService.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdAnimal()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Animal> modificar(@Valid @RequestBody Animal p) throws Exception{
//		System.out.println(p);
		Animal obj = iTipoMascotaService.modificar(p);
		return new ResponseEntity<Animal>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
		Animal obj = iTipoMascotaService.listarPorId(id);
		
		if(obj.getIdAnimal() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iTipoMascotaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<Page<Animal>> listarPageable(@PageableDefault(sort = "nombre")Pageable pageable) throws Exception{			
		Page<Animal> TipoMascotas = iTipoMascotaService.listarPageable(pageable);
		return new ResponseEntity<Page<Animal>>(TipoMascotas, HttpStatus.OK);
	}
	
	
	
//	@GetMapping
//	public ResponseEntity<List<TipoMascota>> listarOrdenadoPorId() throws Exception{
//		List<TipoMascota> lista = iTipoMascotaService.listarOrderById();
//		return new ResponseEntity<List<TipoMascota>>(lista, HttpStatus.OK);
//	}
	
}
