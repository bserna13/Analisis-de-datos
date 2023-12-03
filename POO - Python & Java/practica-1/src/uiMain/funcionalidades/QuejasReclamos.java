package uiMain.funcionalidades;

import uiMain.Utilities;

import gestorAplicacion.Persona;
import gestorAplicacion.Doctor;
import gestorAplicacion.Hospital;
import gestorAplicacion.Paciente;

import java.util.ArrayList;
import java.util.Scanner;

public class QuejasReclamos {
	public static void presentarQueja(Hospital hospital, Scanner in) {
		
		System.out.println("Quieres presentar la denuncia ante");
		System.out.println("[1] Personal Medico" );
		System.out.println("[2] Paciente en la Sala de Espera");
		
		int eleccion = in.nextInt();
		Persona acusado;
		
		switch (eleccion) {
		case 1:
			acusado = escogerAcusado(hospital.getDoctores(), in);
			break;
		case 2:
			acusado = escogerAcusado(hospital.getSalaDeEspera().getPacientes(), in);
			break;
		default:
			System.out.println("Intentemos de nuevo");
			presentarQueja(hospital ,in);
			return;
		}		
		
		
		acusado.setStrikes(acusado.getStrikes() + 1);
		System.out.println(acusado + " ahora tiene " + acusado.getStrikes() + " strikes");
		if(acusado.getStrikes() >= 3) {
			if(acusado instanceof Doctor) {
				hospital.despedirDoctor(acusado.getCedula(), true);
				System.out.println("Se ha despedido a " + acusado);
			} else if (acusado instanceof Paciente) {
				hospital.getSalaDeEspera().sacarPaciente(acusado.getCedula());
				System.out.println("Se ha hechado a " + acusado);
			}
		}  
	}
	
	public static Persona escogerAcusado(Persona[] personas, Scanner in) {
		
		// FILTER NULL OBJECTS
		ArrayList<Object> listPersonas = new ArrayList<Object>();
		for (Persona persona : personas) {
			if(persona != null) {
				listPersonas.add(persona);
			}
		}
		
		// PRINT
		Utilities.imprimirOpciones(listPersonas);
		
		// ELECCION
		System.out.print("Tu eleccion es : ");
		int eleccion = in.nextInt();
		
		return (Persona) listPersonas.get(eleccion);
		
	}
}
