package gestorAplicacion;

public enum Especialidad {
	/*
	 * ESPECIALIDAD(SALARIO)
	 * */
	CARDIOLOGIA(15000000, "cardiolog@", 1000000),
	PLASTICA(30000000, "cirugan@ plastic@", 3000000),
	GENERAL(45000000, "medic@ general", 1000000),
	DERMATOLOGIA(10000000, "dermatolog@", 500000),
	NEUROCIRUJANO(10000000, "neurocirujano", 5000000);
	
	private int salario;
	private String titulo;
	private int costoCirujia;
	
	Especialidad(int salario, String titulo, int costoCirujia) {
		this.salario = salario;
		this.titulo = titulo;
		this.costoCirujia = costoCirujia;
	}
	
	public int getSalario() { return this.salario; }
	public String getTitulo() { return this.titulo; }
	public int getCostoCirujia() { return this.costoCirujia; }
}
