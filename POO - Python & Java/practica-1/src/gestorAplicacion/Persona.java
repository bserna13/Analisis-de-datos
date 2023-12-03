package gestorAplicacion;

import java.io.Serializable;
import java.lang.Math;

public abstract class Persona implements Serializable {
	
	// INSTANCE ATTRIBUTES
	private static final long serialVersionUID = 1L;
	protected String nombre;
	protected int cedula;
	private int edad;
	private boolean poblacionDeRiesgo;
	private boolean agresivo;
	private int strikes;
	private char genero;
	
	
	// CONSTRUCTORS
	public Persona(String nombre, int cedula) {
		this(nombre, cedula, false, 0);
	}
	
	public Persona(String nombre, int cedula, boolean poblacionDeRiesgo) {
		this(nombre, cedula, poblacionDeRiesgo, 0);
	}
	
	public Persona(String nombre, int cedula, int strikes) { 
		this(nombre, cedula, false, strikes); 
	}
	
	public Persona(String nombre, int cedula, boolean poblacionDeRiesgo, int strikes) {
		this.nombre = nombre;
		this.cedula = cedula;
		this.poblacionDeRiesgo = poblacionDeRiesgo;
		this.strikes = strikes;
	}
	
	
	/*	INSTANCE METHODS	*/
	/*
	 * presentarQueja: se presenta una queja hacia una persona(ya sea doctor o paciente)
	 */
	public void presentarQueja(Hospital hospital, Persona persona) {
		persona.setStrikes(persona.getStrikes() + 1);
		if(persona.getStrikes() >= 3) {
			try {
				Doctor doctor = (Doctor) persona;
				hospital.despedirDoctor(doctor.getCedula(), true);
			} finally {
				// TODO: handle finally clause
				Cita.vetados.add(persona.cedula);
			}
		}
	}
	
	/*
	 * enfermar: El paciente se enferma
	 */
	public boolean infectar() { 
		int nuevaEdad = (int) Math.floor(getEdad() * 1.10);
		setEdad(nuevaEdad);
		return isPoblacionDeRiesgo();
	}
	
	/*
	 * ABSTRACT METHODS
	 */
	
	public abstract String toString();
	
	
	/* GETTERS & SETTERS */
	public String getNombre() { return this.nombre; }
	public void setNombre(String nombre) { this.nombre = nombre; }
	
	public int getCedula() { return this.cedula; }
	public void setCedula(int cedula) { this.cedula = cedula; }
	
	public int getEdad() { return this.edad; }
	public void setEdad(int edad) { this.edad = edad; }
	
	public boolean isPoblacionDeRiesgo() { return this.poblacionDeRiesgo; }
	public void setPoblacionDeRiesgo(boolean poblacionDeRiesgo) { this.poblacionDeRiesgo = poblacionDeRiesgo; }
	
	public int getStrikes() { return this.strikes; }
	public void setStrikes(int strikes) { this.strikes = strikes; }
	
	public char getGenero() { return this.genero; }
	public void setGenero(char genero) { this.genero = genero; }
	
	public boolean isAgresivo() { return this.agresivo; }
	public void setAgresivo(boolean agresivo) { this.agresivo = agresivo; }
	
}
