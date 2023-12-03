package gestorAplicacion;

public enum Jerarquia {
	INTERNO("Interno"),
	PLANTA("Medico de planta"), 
	DIRECTORMEDICO("Director medico"),    //ESTO LO PUSO BRAHIAN
	JEFE("Jefe");
	
	private String titulo;
	
	Jerarquia(String titulo) {
		this.titulo = titulo;
	}
	
	public String getTitulo() { return this.titulo; }
}
