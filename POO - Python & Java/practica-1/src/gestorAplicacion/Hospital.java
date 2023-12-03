package gestorAplicacion;

import java.util.Random;
import java.io.Serializable;	

import javax.swing.text.html.HTMLDocument.RunElement;

import java.util.ArrayList;

public class Hospital implements Serializable{
	
	/*	INSTANCE VARIABLES	*/
	private static final long serialVersionUID = 1L;
	private int denuncias = 0;
	private ArrayList<Cita> horario;
	private SalaDeEspera salaDeEspera;
	private Quirofano[] quirofanos;
	private Cuarto[] cuartos;
	private Doctor[] doctores;
	private int presupuesto;
	private boolean bioseguro;
	
	/*	CONSTRUCTORS	*/
	public Hospital(SalaDeEspera salaDeEspera, Quirofano[] quirofanos, Cuarto[] cuartos, Doctor[] doctores) {
		this.salaDeEspera = salaDeEspera;
		this.quirofanos = quirofanos;
		this.cuartos = cuartos;
		this.doctores = doctores;
	}
	
	
	/*	INSTANCE METHODS  */
	
	/*
	 * despedirMedico: Busca la cedula del medico a despedir
	 * lo "indemniza"(resta del presupuesto del hospital el salario del medico)
	 */
	public void despedirDoctor(int cedula,boolean justificado) {
		for (int i = 0; i < doctores.length; i++) {
			if(doctores[i] != null && cedula == doctores[i].getCedula()) {
				if(!justificado){
					this.presupuesto -= doctores[i].getEspecialidad().getSalario();
				}
				doctores[i] = null;
				break;
			}
		}
		
	}
		
	/*
	 *getDoctorQuirofano: hace un arreglo de los doctores que estan en los quirofanos
	 * es decir retorna un array de instancias de tipo doctores que estan en los quirofano, recuerde que los quirofanos 
	 * tambien son instacias el cual tiene un atributo referenciado de tipo doctor
	 */
	public Doctor[] getDoctorQuirofano(){
		Quirofano[] quirofanos= this.quirofanos;
		int contador=0;

		Doctor[] doctores= new Doctor[6];
		for (int i = 0; i < quirofanos.length; i++) {
            if(quirofanos[i]!=null){
				doctores[contador]=quirofanos[i].getDoctor();
                contador++;
            }
		}
		return doctores;


	}
	
	/*
	 * getMuestraDoctores: Retorna un array de 3 elementos de tipo Doctor
	 * Que pueden corresponder a una misma especialidad o no(sobrecarga)
	 */
	public Doctor[] getMuestraDoctores() {
		Doctor[] muestra = new Doctor[3];
		
		int i = 0;
		for (Doctor doctor : this.doctores) {
			if (doctor != null) {
				muestra[i++] = doctor;
			}
		}
		
		return muestra;
	}

	public Doctor[] getMuestraDoctores(Especialidad especialidad) {
		Doctor[] muestra = new Doctor[3];
		
		int i = 0;
		for (Doctor doctor : this.doctores) {
			if (doctor != null && doctor.getEspecialidad() == especialidad) {
				muestra[i++] = doctor;
			}
		}
		
		return muestra;
	}
	
	/*
	 * getNDoctoresEspecialidad: Retorna la cantidad de doctores que haya en dicha especialidad
	 */
	public int getNDoctoresEspecialidad(Especialidad especialidad) {
		int i=0;
		for (Doctor doctor : this.doctores) {
			if(doctor != null && doctor.getEspecialidad() == especialidad) {
				i++;
			}
		}
		return i;
	}
	
	/*
	 * implementarBioseguridad: implementa medidas de bioseguridad en el hospital
	 */
	public ArrayList<Persona> implementarBioseguridad() {
		
		// Guardando en un ArrayList una lista de las personas que se echaron
		ArrayList<Persona> personasHechadas = this.getSalaDeEspera().implementarBioseguridad();
		
		for (Persona persona : personasHechadas) {
			if (persona.isAgresivo()) {
				this.denuncias++;
			}
		}
		
		/* ITERANDO SOBRE CADA DOCTOR PARA VER SI ESTA DISPUESTO A TRABAJAR EN PANDEMIA*/
		for(Doctor doctor: this.getDoctores()) {
			if(doctor != null && !doctor.isVoluntadPandemia()) {
				// EL DOCTOR "RENUNCIA"
				this.despedirDoctor(doctor.getCedula(), false);
				personasHechadas.add(doctor);
			}
		}
		this.setBioseguro(true);
		return personasHechadas;
	}
	
	/*
	 * apartarQuirofano(int index, Paciente paciente, Doctor doctor)
	 * y retorna un boolean para saber si se pudo apartar el quirofano
	 */
	public boolean apartarQuirofano(int index, Paciente paciente, Doctor doctor) {
		boolean suceed = false;
		Quirofano quirofano = this.getQuirofanos()[index];
		if(quirofano == null) {
			this.getQuirofanos()[index] = new Quirofano(paciente, doctor);
			suceed = true;
		} else if (quirofano.getDoctor() == null) {
			quirofano.setDoctor(doctor);
			quirofano.setPaciente(paciente);
			suceed = true;
		}
		return suceed;
	}
	
	
	/*
	 * apartarQuirofanoUrgencia: aparta el quirofano que encuentre disponible el paciente
	 * que este en parametro
	 * @params: Paciente paciente
	 */
	public Quirofano apartarQuirofanoUrgencia(Paciente paciente){
		/* recorre la lista de quirofano verificando si tiene un paciente */ 
		Doctor cirujano = null;
		Quirofano[] quirofanos = this.getQuirofanos();
		for(int i = 0; i < quirofanos.length; i++){
			if (quirofanos[i]==null || quirofanos[i].getDoctor()==null){
				
				// Busca por cada problema medico un cirujano disponible y asigna el primero
				for (Especialidad tipoProblema : paciente.getTipoProblemasMedicos()) {
					cirujano = this.getcirujanoEspecialidad(tipoProblema);
					if(cirujano != null) { break; }
				};
				
				// Si no hay doctores disponibles, entonces conseguir un cirujano cualquiera
//				if(cirujano == null) { 
//					cirujano = this.getcirujanoEspecialidad(Especialidad.GENERAL); 
//				}
				
				// Si no hay cirujanos disponibles, entonces conseguir un doctor general para que opere
				// for(Especialidad)
				
				// Si definitiva no encontro doctor, entonces F
				if(cirujano == null) { return null; }
				
				// Si llego hasta aca es que si hay cirujano, por lo que creemos uno
				// para que ese elemento de la lista no apunte hacia un null sino hacia un Quirofano
				quirofanos[i] = new Quirofano(paciente, cirujano);
				return quirofanos[i];
			}
		}
		// Si se retorno null es porque no hay quirofanos disponibles
		return null;
	}
	
	/*
	 * getDoctorEspecialidad(Especialidad especialidad)
	 */
	public Doctor getcirujanoEspecialidad(Especialidad especialidad) {
		for (Doctor doctor : this.getDoctores()) {
				if(doctor != null && doctor.getEspecialidad() == especialidad && doctor.isCirujano()) {
				return doctor;
			}
		}
		return null;
	}

	/*
	 * esparcirVirus: se esparce el virus a los pacientes en la sala de espera
	 * puede ser el caso que el paciente se muera al ser poblacion de riesgo y por mala suerte(azar)
	 */
	public ArrayList<Persona> esparcirVirus() {
		ArrayList<Persona> personasHospital = getPersonasHospital();
		ArrayList<Persona> muertos = new ArrayList<Persona>();
		Random rand = new Random();
		
		for (Persona persona : personasHospital) {
			if(persona.isPoblacionDeRiesgo() && rand.nextBoolean()) {
				boolean muerto = persona.infectar();
				if(muerto) {
					muertos.add(persona);
					if(persona instanceof Paciente) {
						this.getSalaDeEspera().sacarPaciente(persona.getCedula());						
					} else if(persona instanceof Doctor) {
						this.despedirDoctor(persona.getCedula(), true);
					}
				}
			}
		}
		return muertos;
	}

	
	
	/*
	 * 
	 */
	public ArrayList<Persona> getPersonasHospital() {
		ArrayList<Persona> personasHospital = new ArrayList<Persona>();
		
		for(Persona persona : getSalaDeEspera().getPacientes()) {
			if(persona != null) {
				personasHospital.add(persona);
			}
		}
		
		for(Persona persona : getDoctores()) {
			if(persona != null) {
				personasHospital.add(persona);
			}
		}
		return personasHospital;
	}
	
	/*	GETTERS & SETTERS	*/
	public ArrayList<Cita> getHorario() { return this.horario; }
	public void delCita(Cita cita) {  horario.remove(cita); }
	public void addCita(Cita cita) { horario.add(cita); }
	
	public int getDenuncias() { return this.denuncias; }
	public void setDenuncias(int denuncias) { this.denuncias = denuncias; }
	
	public SalaDeEspera getSalaDeEspera() { return this.salaDeEspera; }
	public void setSalaDeEspera(SalaDeEspera salaDeEspera) { this.salaDeEspera = salaDeEspera; }
	
	public Quirofano[] getQuirofanos() { return this.quirofanos; }
	public void setQuirofanos(Quirofano[] quirofanos) { this.quirofanos = quirofanos;}
	
	public Cuarto[] getCuartos() { return this.cuartos; }
	public void setCuartos(Cuarto[] cuartos) { this.cuartos = cuartos; }
	
	public Doctor[] getDoctores() { return this.doctores; }
	public void setDoctores(Doctor[] doctores) { this.doctores = doctores; }
	
	public int getPresupuesto() { return this.presupuesto; }
	public void setPresupuesto(int presupuesto) { this.presupuesto = presupuesto; }

	public boolean isBioseguro() { return this.bioseguro; }
	public void setBioseguro(boolean bioseguro) { this.bioseguro = bioseguro; }
	
}
