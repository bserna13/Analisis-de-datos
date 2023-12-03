package gestorAplicacion;

public enum Sintomas {
	GRIPA(Especialidad.GENERAL),
	TOS(Especialidad.GENERAL),
	FIEBRE(Especialidad.GENERAL), 
	MALESTAR(Especialidad.GENERAL), 
	DOLORPECHO(Especialidad.CARDIOLOGIA),
	DEFORMACION(Especialidad.PLASTICA),
	PIELRARA(Especialidad.DERMATOLOGIA),
	DOLORESTOMAGO(Especialidad.GENERAL),
	DEMENCIA(Especialidad.NEUROCIRUJANO);
	
	
	private Especialidad especialidad;
	
	Sintomas(Especialidad especialidad) {
		this.especialidad = especialidad;
	}
	
	public Especialidad getEspecialidad() { return this.especialidad; }
}


