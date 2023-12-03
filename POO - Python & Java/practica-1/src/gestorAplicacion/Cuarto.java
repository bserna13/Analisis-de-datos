package gestorAplicacion;

public interface Cuarto {
	

	/*	INSTANCE METHODS	*/
	public void despejar();

	public void apartar(Doctor doctor, Paciente paciente);

	public String toString();
	

	/*	GETTERS & SETTERS	*/
	public Paciente getPaciente();
	public void setPaciente(Paciente paciente);
}
