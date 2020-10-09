package ar.edu.teclab.prueba.entity;

public class Carrera {

	private String nombre;
	private String descripcion;
	private String semestresDuracion;
	private String cargaHoraria;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getSemestresDuracion() {
		return semestresDuracion;
	}

	public void setSemestresDuracion(String semestresDuracion) {
		this.semestresDuracion = semestresDuracion;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return "Carrera{" +
				"nombre='" + nombre + '\'' +
				", descripcion='" + descripcion + '\'' +
				", semestresDuracion='" + semestresDuracion + '\'' +
				", cargaHoraria='" + cargaHoraria + '\'' +
				 '}';
	}
}
