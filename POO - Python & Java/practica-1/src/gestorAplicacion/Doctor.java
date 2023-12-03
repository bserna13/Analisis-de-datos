package gestorAplicacion;

import java.io.Serializable;

public class Doctor extends Persona implements Serializable {
	
	/*	INSTANCE ATTRIBUTES	*/
	private static final long serialVersionUID = 1L;
	private Especialidad especialidad;
	private boolean cirujano;
	public Jerarquia jerarquia;
	private boolean voluntadPandemia;
	
	
	
	/*	CONSTRUCTORS	*/
	public Doctor(String nombre, int cedula) {
		this(nombre, cedula, false, Especialidad.GENERAL, true, Jerarquia.INTERNO, true);
	}

	public Doctor(String nombre, int cedula, Especialidad especialidad) {
		this(nombre, cedula, false, especialidad, true, Jerarquia.INTERNO, true);
	}
	
	public Doctor(String nombre, int cedula, Jerarquia jerarquia) {
		this(nombre, cedula, false, Especialidad.GENERAL, true, jerarquia, true);
	}

	public Doctor(String nombre, int cedula, Especialidad especialidad, boolean voluntadPandemia) {
		this(nombre, cedula, false, especialidad, true, Jerarquia.INTERNO, voluntadPandemia);
	}
	
	public Doctor(String nombre, int cedula, boolean poblacionDeRiesgo, Especialidad especialidad, boolean cirujano, Jerarquia jerarquia, boolean voluntadPandemia) {
		super(nombre, cedula, poblacionDeRiesgo, 0);
		this.especialidad = especialidad;
		this.cirujano = cirujano;
		this.jerarquia = jerarquia;
		this.voluntadPandemia = voluntadPandemia;
	}

	/* METODOS DE INSTANCIA*/
	
	/*
	 * toString: <Jerarquia> <Nombre> de la especialidad <Especialidad>
	 */
	public String toString() {
		String output = "";
		
		if(this.getJerarquia() != null ) {
			output += this.getJerarquia().getTitulo() + " ";
		}
		
		output += "" + this.getNombre() + " de la especialidad " + this.getEspecialidad().getTitulo();		
		return output;
	}
	
	/*
	 * infectar
	 */
	public boolean infectar() {
		boolean retorna = super.infectar();
		if(this.isPoblacionDeRiesgo()) {
			setVoluntadPandemia(false);
		}
		return retorna;
	}
	
	/*	GETTERS & SETTERS	*/
	public Especialidad getEspecialidad() { return this.especialidad; }
	public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }
	
	public boolean isCirujano() { return this.cirujano; }
	public void setCirujano(boolean cirujano) { this.cirujano = cirujano; }
	
	public Jerarquia getJerarquia() { return this.jerarquia; }
	public void setJerarquia(Jerarquia jerarquia) { this.jerarquia = jerarquia; }

	public boolean isVoluntadPandemia() { return this.voluntadPandemia; }
	public void setVoluntadPandemia(Boolean voluntadPandemia) { this.voluntadPandemia = voluntadPandemia; }

	
}
