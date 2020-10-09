package ar.edu.teclab.prueba.controller;

import java.util.Map;
import java.util.Optional;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.exceptions.CarreraException;
import ar.edu.teclab.prueba.persistence.CarreraDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import ar.edu.teclab.prueba.dto.Ejemplo;
import ar.edu.teclab.prueba.service.PruebaService;

@RestController
@RequestMapping("/test")
@CrossOrigin(origins = "*")
public class PruebaController {

	private static final Log LOG = LogFactory.getLog(PruebaController.class);

	@Autowired
	protected PruebaService pruebaService;
	@Autowired
	private CarreraDao carreras;

	@GetMapping("/ejemplo")
	public ResponseEntity<Ejemplo> getMessageStatus(@RequestParam(value ="nombre") String nombre) {
		try {
			Ejemplo ejemplo = new Ejemplo();
			ejemplo.setNombre(nombre);
			return ResponseEntity.ok(ejemplo);
		}catch (Exception e){
			LOG.error("Error", e);
		}
		return null;
	}

	@GetMapping("/mostrarcarrera/{id_carrera}")
	public ResponseEntity<String> getCarrera(@PathVariable String id_carrera){
		Optional<Carrera> carrera = null;
		if (carreras.containsCarrera(id_carrera)){
				carrera = carreras.getCarrera(id_carrera);
		} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la carrera asociada al ID");
		}
		return ResponseEntity.ok().body(carrera.toString());
	}

	/**
	 *Ejemplo de request:
	 * {
	 *     "nombre":"carrera1",
	 *     "descripcion":"carrera",
	 *     "semestresDuracion":"12",
	 *     "cargaHoraria":"1500",
	 * }
	 *
	 */
	@PostMapping("/addcarrera/{id_carrera}")
	public ResponseEntity<String> addCarrera(@PathVariable String id_carrera, @RequestBody Carrera carrera){
		try {
		if(carreras.containsCarrera(id_carrera)){
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Ya existe una carrera con ese id");
		}
			carreras.addNewCarrera(id_carrera, carrera);
			return ResponseEntity.status(HttpStatus.CREATED).body("Carrera: " + carrera.getNombre() + " creada correctamente");
		} catch (Exception e){
			LOG.error("Error al crear una nueva carrera: " + e.getCause());
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}


	/****
	 * ejemplo de url: http://localhost:8093/test/modificarcarrera/1?nombre=nuevonombre&cargaHoraria=20
	 */
	@PutMapping("/modificarcarrera/{id_carrera}")
	public ResponseEntity<String> putCarrera(@PathVariable String id_carrera,
											 @RequestParam Map<String, String> allParams) {
		Optional<Carrera> carrera = null;
		try {
			carrera = carreras.getCarrera(id_carrera);
			if (carrera.isPresent()) {
				for (Map.Entry<String, String> entry : allParams.entrySet())
					carreras.putCarrera(id_carrera, entry.getKey(), entry.getValue());
			} else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("No existe una carrera con el id " + id_carrera);
			}
		} catch (CarreraException e) {
			carreras.addNewCarrera(id_carrera, carrera.get());
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().body("Carrera modificada correctamente");
	}

	@DeleteMapping("/eliminarcarrera/{id_carrera}")
	public ResponseEntity<String> deleteCarrera(@PathVariable String id_carrera){
		if (carreras.containsCarrera(id_carrera)){
			carreras.deleteCarrera(id_carrera);
			return ResponseEntity.ok().body("Carrera " + id_carrera + " eliminada correctamente");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe la carrera con id " + id_carrera);
		}
	}

}


