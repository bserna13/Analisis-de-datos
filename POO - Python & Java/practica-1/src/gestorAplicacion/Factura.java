package gestorAplicacion;

import java.io.Serializable;

public class Factura  implements Serializable{
	
	/*	CLASS VARIABLES	*/
	private static final long serialVersionUID = 1L;
	private static final int NIT = 12;
	private static final String direccion = "Calle 1 #23";
	private static final String telefono = "#444 4444 (444)";
	
	/*	INSTANCE VARIABLES	*/
	private double valorPagar;
	
	/*	CONSTRUCTORS	*/
	public Factura(double valorPagar) {
		this(valorPagar, 0);
	}
	
	public Factura(double valorPagar, double descuento) {
		this.valorPagar = (1-descuento)*valorPagar;
	}
	
	/*	INSTANCE METHODS	*/
	public double devolucionDinero(Factura factura) {
		double valorDevolver = factura.getValorPagar();
		factura.setValorPagar(0);
		return valorDevolver;
	}
	
	/* GETTERS & SETTERS */
	public int getNIT() { return NIT; }
	
	public String getDireccion() { return direccion; }
	
	public String telefono() { return telefono; }
	
	public double getValorPagar() { return this.valorPagar; }
	public void setValorPagar(int valorPagar) { this.valorPagar = valorPagar; }
	
}
