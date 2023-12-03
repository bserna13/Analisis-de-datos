package uiMain.funcionalidades;

import java.util.Random;
import java.util.Scanner;
import java.util.HashMap;

import uiMain.Utilities;

import gestorAplicacion.Hospital;
import gestorAplicacion.Quirofano;
import gestorAplicacion.Paciente;
import gestorAplicacion.Doctor;
import gestorAplicacion.Sintomas;
import gestorAplicacion.Factura;

public class Urgencia {
	
	/*
	 * nuevaUrgencia: Crea una nueva instancia Paciente
	 * PD: Este es el metodo que se ejecuta desde interfaz
	 */
	public static void nuevaUrgencia(Hospital hospital, Scanner in) {
		Random rand = new Random();
		
		// DETERMINAR ALEATORIAMENTE EN QUE LLEGA EL PACIENTE
		String[] transporteOpciones = new String[]{"helicoptero", "ambulancia", "carro particular"};
		String transporte = transporteOpciones[rand.nextInt(transporteOpciones.length)];
		System.out.println("Ha llegado alguien en " + transporte);
		
		Utilities.waitInput(in);
		
		// SE LE DETERMINA ALEATORIAMENTE UNA SERIE DE SINTOMAS Y DE ACUERDO A ESO UNA TRAMA
		HashMap<String, Sintomas> opciones = new HashMap<String, Sintomas>(){{
			put("le dio un paro cardiaco mientras se comia una hamburguesa", Sintomas.DOLORPECHO);
			put("le envenenaron la comida", Sintomas.DOLORESTOMAGO);
			put("tuvo un shock electrico y le dio un paro cardiaco", Sintomas.DEFORMACION);
			put("le dio un paro cardia", Sintomas.DOLORPECHO);
			put("se puso un stricker y ahora la piel se ve bastante mal", Sintomas.PIELRARA);
			put("lamio la calle y ahora tiene una tos increible", Sintomas.TOS);
		}};
		String[] causasOpciones = opciones.keySet().toArray(new String[2]);
		String causa = causasOpciones[rand.nextInt(causasOpciones.length)];
		System.out.println("El paciente llego a urgencias porque " + causa);
		Sintomas sintoma = opciones.get(causa);

		// DETERMINAR 
		boolean identificado = rand.nextBoolean();
		String nombre;
		if(identificado) {
			
			// DETERMINAR ALEATORIAMENTE DE UNA LISTA DE OPCIONES, EL NOMBRE DEL PACIENTE
			String[] nombreOpciones = new String[]{"Juan", "Pedro", "Camilo",
					"Julian", "Daniel", "Sebastian", "Maria", "Juliana", "Natalia"};
			nombre = nombreOpciones[rand.nextInt(nombreOpciones.length)];
			
			int cedula = rand.nextInt(1000000,19999999); // DA ALEATORIAMENTE UNA CEDULA
			
			System.out.println("El paciente se llama " + nombre + " y esta identificado con"
					+ " cedula de ciudadania " + cedula);
			
			
			
			
			// SE DETERMINA SI EL PACIENTE ES MILITAR, DOCTOR O OTRO
			String[] profesionesOpciones = new String[]{"militar", "doctor", "otro"};
			String profesion = profesionesOpciones[rand.nextInt(profesionesOpciones.length)];
			
			// DEPENDIENDO DE SU PROFESION SE LE APLICA DESCUENTO
			double descuento;
			switch (profesion) {
			case "doctor":
				System.out.println("El paciente es un doctor y tiene un descuento del 15%");
				descuento = 0.15;
				break;
			case "militar":
				System.out.println("El paciente es militar y redimio el codigo MILITAR, que le da un 50% de descuento en su cirugia B)");
				descuento = 0.50;
				break;
			default:
				descuento = 0.0;
				break;
			}
			
			Utilities.waitInput(in);
			
			// SE CREA UNA INSTANCIA DEL PACIENTE PARA REFERENCIAR
			Paciente paciente = new Paciente(nombre, cedula, sintoma);
			
			// SE BUSCA UN QUIROFANO URGENTE
			Quirofano quirofano = hospital.apartarQuirofanoUrgencia(paciente);
			
			// SI NO SE CONSIGUIO
			if(quirofano == null) {
				System.out.println("F, el paciente murio porque no habia ciruganos/doctores disponibles X_X");
			} else {
				System.out.println("Ya lo estan operando");
				Factura factura = new Factura(sintoma.getEspecialidad().getCostoCirujia(), descuento);	// GENERAR FACTURA
				System.out.println("La factura ha llegado por " + factura.getValorPagar() + " la deuda ha sido generada y se espera el pago");
			}
		} else {
			System.out.println("El paciente no esta identificado :(, esto genera problemas");
			Doctor doctor = hospital.getcirujanoEspecialidad(sintoma.getEspecialidad());
			
			System.out.println("El doctor " +  doctor + " justo esta pasando y es muy buena gente");
			System.out.println("Segun las politicas del hospital, no podemos operar al paciente al no estar identificado");
			System.out.println("Pero... podriamos operarlo de todas maneras  ( ͡° ͜ʖ ͡°)");
			System.out.println("Puedes decir por el doctor y decidir si operar al paciente,"
					+ "\nINGRESA ;) para operar, de lo contrario, entedere tu señal como que no quieres operar al paciente\n");
			
			in.nextLine();
			String election = in.nextLine();
			if(election.equals(";)")) {
				// MUESTRA LOS QUIROFANOS QUE HAY EN EL HOSPITAL
				mostrarQuirofanos(hospital);
				System.out.println("Ingresa el numero del quirofano al que quieres entrar");
				int electionQuirofano = in.nextInt();
				boolean suceed = hospital.apartarQuirofano(electionQuirofano, null, doctor); // SI EL QUIROFANO ESTA DISPONIBLE RETORNA TRUE
				
				// DEPENDIENDO DE SI SE HIZO UNA BUENA ELECCION O NO (SI EL QUIROFANO QUE SE ELIJIO ESTABA DISPONIBLE)
				if(!suceed) { 
					System.out.println("Has entrado a donde estaba otro doctor y te ha sapeado :(, han despedido al doctor " + doctor);
				} else {
					System.out.println("Has elejido sabiamente, ya estan operando al paciente");
				}
			} 
			// SI NO SE QUISO ARRIESGAR A OPERAR AL PACIENTE INDOCUMENTADO
			else {
				System.out.println("El paciente ha muerto");
			}
		}
		
		System.out.println("La emergencia ha finalizado, volviendo a menu");
		Utilities.waitInput(in);
	}
	
	public static void mostrarQuirofanos(Hospital hospital) {
		System.out.println("Vamos a conseguir ");
		int  i = 0;
		for (Quirofano quirofano : hospital.getQuirofanos()) {
			System.out.println("[" + i + "] " + quirofano);
			i++;
		}
	}
}
