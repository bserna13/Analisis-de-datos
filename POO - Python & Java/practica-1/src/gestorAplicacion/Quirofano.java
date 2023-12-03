package gestorAplicacion;

import java.io.Serializable;

public class Quirofano implements Cuarto, Serializable {
	
	/*	INSTANCE VARIABLES	*/
	private static final long serialVersionUID = 1L;
	private Paciente paciente;
	private Doctor doctor;
	
	/*	CONSTRUCTORS	*/
	public Quirofano() {}
	
	public Quirofano(Paciente paciente, Doctor doctor) {
		this.paciente = paciente;
		this.doctor = doctor;
	}
	
	/*	INSTANCE METHODS	*/
	
	/*
	 * despejar: Actualiza el paciente y el doctor a null
	 */
	public void despejar() {
		this.setPaciente(null);
		this.setDoctor(null);
	}
	
	
	/*
	 * apartar: actualiza los atributos de instancia al doctor y pacinete que se pasen como paremetros
	 * @ params: Doctor, Paciente
	 */

	@Override
	public void apartar(Doctor doctor, Paciente paciente) {
		this.setDoctor(doctor);
		this.setPaciente(paciente);
	}
	
	/*
	 * toString: 
	 */
	public String toString() {
		String output;
		if(this.getDoctor() == null) {
			output = "Quirofano vacio";
		} else {
			output = "Quirofano con el doctor " + this.getDoctor().getNombre() + " que esta operando al paciente " + this.getPaciente().getNombre();
		}
		return output;
	}
	
	/*	SETTERS & GETTERS	*/
	@Override
	public Paciente getPaciente() { return this.paciente; }
	@Override
	public void setPaciente(Paciente paciente) { this.paciente = paciente; }
	
	public Doctor getDoctor() { return this.doctor; }
	public void setDoctor(Doctor doctor) { this.doctor = doctor; }
}
