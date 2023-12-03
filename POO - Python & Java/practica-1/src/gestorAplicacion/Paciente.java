package gestorAplicacion;

import java.io.Serializable;
import java.util.ArrayList;

public class Paciente extends Persona implements Serializable{
	
	/*	INSTANCE METHODS	*/
	private static final long serialVersionUID = 1L;
	private Cita cita;
	private int turno = -1;
	private ArrayList<Sintomas> sintomas = new ArrayList<Sintomas>();
	private ArrayList<Especialidad> tipoProblemasMedicos = new ArrayList<Especialidad>();
	private static int numeroPacientes;
	
	
	/*	CONSTRUCTORS	*/
	public Paciente(String nombre, int cedula) {
		this(nombre, cedula, false, null, null);
	}
	public Paciente(String nombre, int cedula, boolean poblacionDeRiesgo) {
		this(nombre, cedula, poblacionDeRiesgo, null, null);
	}
	public Paciente(String nombre, int cedula, boolean poblacionDeRiesgo,Sintomas sintoma) {
		super(nombre, cedula, poblacionDeRiesgo);
		this.sintomas.add(sintoma);
		Paciente.numeroPacientes++;
	}
	
	public Paciente(String nombre, int cedula, Sintomas sintoma) {
		this(nombre, cedula, false, new Sintomas[]{sintoma}, -1);
	}

	public Paciente(String nombre, int cedula, boolean poblacionDeRiesgo, Sintomas[] sintomas, int turno) {
		super(nombre, cedula, poblacionDeRiesgo, 0);
		Paciente.numeroPacientes++;
		
		for (int i = 0; i < sintomas.length; i++) {
			this.sintomas.add(sintomas[i]);
		}
		
		this.turno = turno;
		this.diagnosticar();
	}
	
	
	public Paciente(String nombre, int cedula, boolean poblacionDeRiesgo, Sintomas[] sintomas, Cita cita) {
		super(nombre, cedula, poblacionDeRiesgo, 0);
		
		if(sintomas != null) {
			for (int i = 0; i < sintomas.length; i++) {
				this.sintomas.add(sintomas[i]);
			}
		}
		
		this.cita = cita;
	}
	
	/*
	 * diagnosticar: El sistema le asigna a que especialista busca el paciente
	 */
	public void diagnosticar() {
		for (Sintomas sintoma : this.getSintomas()) {
			if(sintoma == Sintomas.DOLORPECHO) {
				this.addTipoProblemasMedicos(Especialidad.CARDIOLOGIA);
			} else if(sintoma == Sintomas.DEMENCIA) {
				this.addTipoProblemasMedicos(Especialidad.NEUROCIRUJANO);
			} else if(sintoma == Sintomas.PIELRARA) {
				this.addTipoProblemasMedicos(Especialidad.DERMATOLOGIA);
			}
		}
		
		if(this.getTipoProblemasMedicos().size() == 0) {
			this.addTipoProblemasMedicos(Especialidad.GENERAL);
		}
	}
	/*
	 * 
	 */
	public String toString() {
		return "Paciente " + nombre + " identificado con documento " + cedula;
	}
	
	public boolean infectar() {
		boolean retorno = super.infectar();
		addSintomas(Sintomas.DEMENCIA);
		return retorno;
	}
	
	/*	GETTERS & SETTERS	*/
	public ArrayList<Sintomas> getSintomas() { return this.sintomas; }
	public void addSintomas(Sintomas[] sintomas) {
		for (int i = 0; i < sintomas.length; i++) {
			this.sintomas.add(sintomas[i]);
		}
	}
	public void addSintomas(Sintomas sintoma) {
		this.sintomas.add(sintoma);
	}

	public String getNombre() { return super.getNombre();} 

	public Cita getCita() { return this.cita; }
	public void setCita(Cita cita) { this.cita = cita; }
	
	public int getTurno() { return this.turno; }
	public void setTurno(int turno) { this.turno = turno; }
	
	public ArrayList<Especialidad> getTipoProblemasMedicos() { return this.tipoProblemasMedicos; }
	public void setTipoProblemasMedicos(ArrayList<Especialidad> tipoProblemasMedicos) { this.tipoProblemasMedicos = tipoProblemasMedicos; }
	public void addTipoProblemasMedicos(Especialidad tipoProblemasMedicos) { this.tipoProblemasMedicos.add(tipoProblemasMedicos); }
}
