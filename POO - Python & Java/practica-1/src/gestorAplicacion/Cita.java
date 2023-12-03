package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cita  implements Serializable{
	
	/*	CLASS VARIABLES	*/
	private static final long serialVersionUID = 1L;
	public static ArrayList<Integer> vetados;
	
	/*	INSTANCE VARIABLES	*/
	private Date fecha;
	private Paciente paciente;
	private Doctor doctor;
	private Quirofano quirofano;
	
	/*	CONSTRUCTORS	*/
	public Cita(Date fecha, Paciente paciente, Doctor doctor) {
		this(fecha, paciente, doctor, null);
	}
	
	public Cita(Date fecha, Paciente paciente, Doctor doctor, Quirofano quirofano) {
		this.fecha = fecha;
		this.paciente = paciente;
		this.doctor = doctor;
		this.quirofano = quirofano;
	}
	
	
	/*	GETTERS & SETTERS	*/
	public Date getFecha() { return this.fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	
	public Paciente getPaciente() { return this.paciente; }
	public void setPaciente(Paciente paciente) { this.paciente = paciente; }
	
	public Doctor getDoctor() { return this.doctor; }
	public void setDoctor(Doctor doctor) { this.doctor = doctor; }
	
	public Quirofano getQuirofano() { return this.quirofano; }
	public void setQuirofano(Quirofano quirofano) { this.quirofano = quirofano; }
}
