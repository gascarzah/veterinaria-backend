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
import com.gafahtec.model.Venta;
import com.gafahtec.service.IVentaService;

import lombok.AllArgsConstructor;
@RestController
@RequestMapping("/ventas")
@AllArgsConstructor
public class VentaController {

	private IVentaService iVentaService;
	
	@GetMapping
	public ResponseEntity<List<Venta>> listar() throws Exception{
//		System.out.println("1====>>>2 ");
		List<Venta> lista = iVentaService.listar();
		return new ResponseEntity<List<Venta>>(lista, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Venta> listarPorId(@PathVariable("id") Integer id) throws Exception{
//		System.out.println("3");
		Venta obj = iVentaService.listarPorId(id);
		
		if(obj.getIdVenta() == null) {
			throw new ModeloNotFoundException("Id no encontrado " + id );
		}
		
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Venta> registrar(@Valid @RequestBody Venta p) throws Exception{
//		System.out.println("1====>>> "+ p);
		Venta obj = iVentaService.registrarTransaccion(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdVenta()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Venta> modificar(@Valid @RequestBody Venta p) throws Exception{
//		System.out.println("4 ");
		Venta obj = iVentaService.modificar(p);
		return new ResponseEntity<Venta>(obj, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws Exception{
//		System.out.println("5");
		Venta obj = iVentaService.listarPorId(id);
		
		if(obj.getIdVenta() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO "+id);
		}
			
		iVentaService.eliminar(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
