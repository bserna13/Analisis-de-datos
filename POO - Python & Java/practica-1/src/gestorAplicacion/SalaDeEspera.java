package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class SalaDeEspera implements Serializable {
	
	/*	INSTANCE VARIABLES	*/
	private static final long serialVersionUID = 1L;
	private Paciente[] pacientes = new Paciente[40];
	private boolean bioseguro;
	
	/*	CONSTRUCTORS	*/
	public SalaDeEspera() {
	}
	
	public SalaDeEspera(Paciente[] pacientes) {
		for (int i = 0; i < pacientes.length; i++) {
			this.pacientes[i] = pacientes[i];
		}
	}
	
	/* INSTANCE METHODS */
	
	
	/*
	 * @param Paciente
	 * @return void
	 * 
	 * agregarPaciente: agrega la referencia del paciente/s al atributo pacientes[]s
	 */
	public void agregarPaciente(Paciente pacienteNuevo) {
		for (int i = 0; i < this.pacientes.length; i++) {
			if(this.pacientes[i] == null ) {
				this.pacientes[i] = pacienteNuevo;
			}
		}
	}
	
	public void agregarPaciente(Paciente[] pacientesNuevos) {

		for (int i = 0; i < pacientesNuevos.length; i++) {
			for (int j = 0; j < this.pacientes.length; j++) {
				 if(this.pacientes[j] == null) {
					 pacientes[j] = pacientesNuevos[i];
					 break;
				 }
			}
		}
	}
	
	/* 
	 * getSiguientePaciente
	 */
	public Paciente getSiguientePaciente() {
		Paciente siguientePaciente = null;
		
		for (int i = 0; i < this.pacientes.length; i++) {
			if(pacientes[i].getTurno() == 1) {
				siguientePaciente = pacientes[i];
				quitarPaciente(pacientes[i]);
			}
		}
		return siguientePaciente;
	}
	
	/*
	 * quitarPaciente: quita el paciente de la sala de espera
	 * actualiza los fichos de las personas que venian detras de el
	 */
	public void quitarPaciente(Paciente paciente) {
		int turnoPacienteQuitar = paciente.getTurno();
		for (int i = 0; i < this.pacientes.length; i++) {
			
			if(this.pacientes == null) {
				continue;
			}
			
			int turnoiPaciente = this.pacientes[i].getTurno();
			
			if(this.pacientes[i] == paciente) {
				this.pacientes[i] = null;
			} 
			else if(turnoiPaciente > turnoPacienteQuitar) {
				this.pacientes[i].setTurno(turnoiPaciente - 1);
			}
		}
	}
	
	/*
	 * implementarBioseguridad: se deja una silla en medio en la sala de espera
	 */
	public ArrayList<Persona> implementarBioseguridad() {
		// LA GENTE EN POBLACION DE RIESGO PRIMERO EN LA FILA
		this.sortByRiesgo();
		
		ArrayList<Persona> pacientesHechados = new ArrayList<Persona>();
		// SI LA SALA DE ESPERA YA ES BIOSEGURA, ENTONCES NO HAGA NADA
		if(this.isBioseguro()) {
			return pacientesHechados;
		}
		
		// DEJAR UNA SILLA EN MEDIO PARA LOS PACIENTES
		int i = 0;
		Paciente[] nuevoOrden = new Paciente[40];
		for (; (2*i) < this.pacientes.length; i++) {
			nuevoOrden[2*i] = this.pacientes[i];
		}
		// LOS QUE SOBRARON, AGREGARLOS AL ARRAYLIST PACIENTESHECHADOS
		for (; i < this.pacientes.length; i++) {
			if(this.pacientes[i] != null) {
				pacientesHechados.add(this.pacientes[i]);
			}
		}

		// UPDATE VARIABLES
		this.pacientes = nuevoOrden;
		this.setBioseguro(true);
		
		// retorna un ArrayList con los pacientes que toco hechar porque no habia puesto para ellos
		return pacientesHechados;
	}
	
	
	/*
	 * sortByRiesgo: Ordena la lista pacientes(atributo de instancia)
	 * donde primero estan los que sean poblacion de riesgo
	 */
	private void sortByRiesgo() {
		for (int i = 0; i < this.pacientes.length; i++) {
			Paciente key = this.pacientes[i];
			int j = i - 1;
			
			while(j >= 0 && this.pacientes[j] != null &&this.pacientes[j].isPoblacionDeRiesgo() && key != null &&!key.isPoblacionDeRiesgo()) {
				this.pacientes[j+1] = this.pacientes[j];
				j = j - 1;
			}
			this.pacientes[j+1] = key;
		}
	}
	
	/*
	 * sacarPaciente: saca al paciente de la sala de espera
	 */
	public void sacarPaciente(int cedula) {
		
		for (int i = 0; i < this.pacientes.length; i++) {
			if(this.pacientes[i] != null && pacientes[i].getCedula() == cedula) {
				this.pacientes[i] = null;
			}
		}
	}
	
	
	/*	GETTERS & SETTERS*/
	public Paciente[] getPacientes() { return this.pacientes; }
	public void setPacientes(Paciente[] pacientes) { this.pacientes = pacientes; }
	
	public boolean isBioseguro() { return this.bioseguro; }
	public void setBioseguro(boolean bioseguro) { this.bioseguro = bioseguro; }
}
