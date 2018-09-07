package co.udea.heroes.api.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Controller
@RestController
@RequestMapping("/tourofheroes")
public class HeroController {
	
	private static Logger log = LoggerFactory.getLogger(HeroController.class);
	
	private HeroService heroService;
	
	public HeroController(HeroService heroService) {
		this.heroService = heroService;
	}
	
	@GetMapping("listar")
	@ApiOperation(value = "Buscar todos", response = Page.class)
	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron buscados", response = Page.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<List<Hero>> getHeros(){
		log.debug("REST request listar todos los heroes");
		return ResponseEntity.ok(heroService.getHeroes());		
	}
	
	@GetMapping(value = "consultar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> getHero( @PathVariable("id") int id){
		 log.debug("REST request getHero id : {}", id);
		return ResponseEntity.ok(heroService.getHero(id));
	}
	
	
	@GetMapping(value = "buscar/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Consultar heroe por termino", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe buscacp", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no encontrado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> getHero( @PathVariable("name") String name){
		 log.debug("REST request getHero name : {}", name);
		return ResponseEntity.ok(heroService.searchHeroes(name));
	}
	
	
	@PutMapping(value = "actualizar/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "actualizar heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe actualizado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no actualizado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> getHero( @PathVariable("id") int id, @PathVariable("name") String name){
		log.debug("REST request getHero id : {}", id);
		log.debug("REST request getHero name : {}", name);
		Hero hero=new Hero(id, name);
		return ResponseEntity.ok(heroService.updateHero(hero));
	}
	
	@PostMapping(value = "crear/{id}/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "crea un heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe creado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no actualizado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public ResponseEntity<Hero> addHero( @PathVariable("id") int id, @PathVariable("name") String name){
		log.debug("REST request getHero id : {}", id);
		log.debug("REST request getHero name : {}", name);
		Hero hero=new Hero(id, name);
		return ResponseEntity.ok(heroService.addHero(hero));
	}
	
	@DeleteMapping(value = "borrar/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "elimina un heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe eliminado", response = Hero.class),
            @ApiResponse(code = 400, message = "La petición es invalida"),
            @ApiResponse(code = 404, message = "Recurso no actualizado"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
	public void deleteHero( @PathVariable("id") int id){
		log.debug("REST request getHero id : {}", id);
		Hero hero = heroService.getHero(id);
		heroService.deleteHero(hero);
	}
	
	}
	
	
