package ar.edu.teclab.prueba.persistence;

import ar.edu.teclab.prueba.entity.Carrera;
import ar.edu.teclab.prueba.exceptions.CarreraException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*****************
Por cuestiones de tiempo, no se realiza una conexion con una BD,
pero en este caso se podria crear una conexion a la base usando Hibernate para mayor facilidad y control;
*******************/

@Component
public class CarreraDao {
    private Map<String, Carrera> carreras = new HashMap<String, Carrera>();

    public Boolean containsCarrera(String id_carrera){
            return carreras.containsKey(id_carrera);
    }
    public void addNewCarrera(String id_carrera, Carrera carrera){
        this.carreras.put(id_carrera, carrera);
    }
    public Optional<Carrera> getCarrera(String id_carrera) throws NullPointerException {
            return Optional.ofNullable(carreras.get(id_carrera));
    }

    public void putCarrera(String id_carrera, String key, String value) throws CarreraException {
        Carrera carrera = getCarrera(id_carrera).get();
        if (key.equals("nombre")) {
            carrera.setNombre(value);
        } else if (key.equals("descripcion")) {
            carrera.setDescripcion(value);
        } else if (key.equals("semestresDuracion")) {
            carrera.setSemestresDuracion(value);
        } else if (key.equals("cargaHoraria")) {
            carrera.setCargaHoraria(value);
        } else {
            throw new CarreraException("El valor: " + key + " no coincide con ningun campo.", "-1");
        }
    }

    public void deleteCarrera(String id_carrera){
        carreras.remove(id_carrera);
    }
}
