package uiMain.funcionalidades;

import uiMain.Utilities;
import gestorAplicacion.Hospital;
import gestorAplicacion.SalaDeEspera;
import gestorAplicacion.Paciente;
import gestorAplicacion.Persona;
import gestorAplicacion.Doctor;
import java.util.ArrayList;
import java.util.Scanner;

import baseDatos.Actualizar;


public class Cuarentena {
	/**
	 * 
	 * @param hospital
	 * @return void
	 * Trama: El hospital es muy particular, puesto que tiene un laboratorio
	 * donde se experimentan con virus muy raros, justamente, hay un mal diseño
	 * tal laboratorio esta al lado de la sala de espera!
	 * Esta funcion esparce el virus y decreta cuarente en el hospital
	 * Algunos pacientes pueden fallecer
	 */
	public static void decretarCuarentena(Hospital hospital, Scanner in) {
		
		/************************************
		 * INICIAR HISTORIA
		 * */
		System.out.println("Hoy es un dia normal y corriente");
		/*
		 * Muestra en pantalla como esta organizada la sala de espera
		 * ANTES de la "re ubicacion" que se genera por medidas de
		 * bioseguridad
		 * */
		System.out.println("***********************************");
		System.out.println("Estado actual de la sala de espera");
		printSalaDeEspera(hospital.getSalaDeEspera());
		Utilities.waitInput(in);
		
		/*********************************
		 * Se esparce el virus
		 */
		System.out.println("Se esta trabajando con un nuevo virus muy raro");
		System.out.println("Tal laboratorio se encuentra al lado de la sala de espera");
		System.out.println("Oh no, se ha derramado la muestra, y la puerta esta abierta");
		System.out.println("⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣷⣶⣶⣶⣻⣷⢶⣶⣤⣤⣤⣄⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⠀⠀⠀⠀⢀⣠⣶⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣶⣤⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⠀⠀⣠⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣤⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⣠⣾⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣦⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⣴⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠿⠟⠛⠋⠙⠉⠉⠁⠀⠀⠈⠙⢿⣿⣄⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⣰⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⠛⠛⠛⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢿⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⢠⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠘⣷⡄⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⣼⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢻⣆⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⣤⣤⣴⣤⣤⣤⣤⣀⣀⣀⠀⠘⢻⡄⠀⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡿⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⣠⣤⣤⣭⣍⡙⠛⠛⠛⠛⠿⠻⢷⣤⣜⣻⡄⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⡿⠋⣉⣀⣤⣤⣶⣶⣶⣾⣿⣿⣧⣤⣄⡀⠀⠀⠀⠠⣿⣿⣿⣿⣿⣿⠛⠛⠻⠶⠶⣤⣀⣀⢀⡙⢿⣷⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣷⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⠀⠻⣿⢿⣿⣿⣿⣿⣿⣤⣀⣀⣀⠀⢉⣻⣿⣿⣾⣿⡄⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠿⠿⠛⣿⣿⣿⠛⢿⣿⣿⣿⡿⠁⠀⠀⠀⠐⢳⠀⡟⠂⢹⣧⠀⠀⢹⣿⡟⣿⣿⣿⣿⣿⠇⢹⡇⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⡟⣀⣤⣶⠾⠿⣿⡿⣳⣆⣿⣿⠟⠁⡀⠀⠀⡄⠀⠀⠇⢻⣶⣶⣿⡀⠀⢸⣿⣿⣿⡟⣸⣿⠏⢀⣿⠃⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣹⣿⠀⣰⠋⠀⣿⠙⡿⠁⠀⣼⡇⠀⠀⢿⠀⠀⠘⠈⡿⣿⣿⣷⣤⣀⠻⠿⢟⣼⣿⡟⠀⢈⡟⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⡋⠉⣿⣿⣿⣿⡾⢡⠆⢰⡏⠀⠀⣄⣸⣿⡇⠀⠀⠀⠳⠀⠀⡄⠀⠈⢿⣿⣿⣿⣿⣶⣼⡿⠛⠃⢀⣿⠃⠀⠀⠀⠀⠀\n"
				+ "⡟⢿⣿⣿⣿⣿⣿⣿⣬⡛⠟⢋⣴⣏⠀⣾⣧⣤⣾⣿⣿⠿⠀⠀⠀⠀⠀⠀⠀⠈⢢⠀⠀⠈⠻⣯⡥⠾⠛⠀⠀⠄⣰⡏⠀⠀⠀⠀⠀⠀\n"
				+ "⣷⡀⠙⣿⡿⢿⣿⣿⣿⣿⣿⣿⣿⠁⢸⠏⢹⣿⣿⣿⣿⠇⠀⠀⠀⠀⠀⠀⠀⠀⠈⠀⠀⠀⠀⠀⣀⣴⡀⢀⡴⠚⣿⣰⢏⣡⠄⠀⠀⠀\n"
				+ "⠹⣿⣷⡈⢻⣧⠀⠉⠙⠻⠿⠟⠉⠀⠀⠀⠰⣿⣿⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⣤⡾⠿⠛⣋⣥⠴⠚⣫⡿⢛⡅⠀⠀⠀⠀\n"
				+ "⠄⠟⣿⣿⣄⠙⢧⡀⡀⠀⠀⠀⠀⠀⠀⠀⢠⣿⣿⣯⣥⣤⣄⡀⠐⠀⠀⣀⣤⣴⠟⠚⠉⠉⣀⣤⠶⠛⠉⣠⣴⠟⣉⡷⠋⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠈⠻⣿⣧⠀⠻⣿⣶⣤⡀⠀⣀⣠⣴⣿⣿⣿⣿⣿⠿⣿⡿⠿⠟⠛⠋⠁⠀⣀⣤⡶⠟⠋⠀⣠⡴⠛⢉⡤⠞⠁⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⠀⠀⠀⠀⠛⣷⡄⠀⠈⠻⣿⣿⣿⣿⣿⣿⣷⡶⠶⣶⡾⠟⠃⠀⠀⢀⣀⣤⠶⠛⠋⠁⢀⣤⠶⠛⠁⣠⠖⢋⣤⠖⢢⠆⠀⠀⠀⠀⠀⠀\n"
				+ "⣀⣤⣤⣤⣤⣽⣿⣆⠤⠔⠈⠉⠀⠀⠀⠀⠀⠀⠀⠋⠀⠀⠀⠀⠾⠉⠁⠀⠀⢀⣤⠞⠋⠀⣠⡴⢋⣡⣶⣿⣷⠖⠁⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠐⠋⠀⢀⣤⣾⡿⠟⣋⣥⣾⣿⣥⡀⠀⠀⠀⠀⠀⠀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣴⠟⢋⣹⣶⣿⣿⢛⣥⣼⣿⣿⣿⣿⣶⣦⣄⣀⠀⠀\n"
				+ "⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠀⠀⢶⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣬⣴⣾⠟⣿⣿⣿⣿⣾⣛⣿⣿⣿⣿⣿⣿⣿⣿⣋⣙⣲");		
		System.out.println("El virus se ha esparcido");
		Utilities.waitInput(in);
		ArrayList<Persona> muertos = hospital.esparcirVirus();
		despedirMuertos(muertos);
		System.out.println("La cuarentena ha empezado");
		System.out.println("*Comprando antibacteriales* \n");
		System.out.println("Implementado Bioseguridad...");
		Utilities.waitInput(in);
		/*
		 * Muestra en pantalla como esta organizada la sala de espera
		 * DESPUES de la "re ubicacion" que se genera por medidas de bioseguridad
		 */
		ArrayList<Persona> personasFuera = hospital.implementarBioseguridad();
		if(personasFuera.size() > 0 && hospital.getDenuncias() > 0) {
			System.out.println("Se recibieron " + hospital.getDenuncias() + " denuncias D:");
		} else {
			System.out.println("No se recibieron denuncias :)");
		}
		
		System.out.println("******************************************");
		System.out.println("Estado despues de implementar bioseguridad");
		Utilities.waitInput(in);
		printSalaDeEspera(hospital.getSalaDeEspera());
		
		
		/*
		 * Formalidades para terminar la funcionalidad
		 */
		System.out.println("Volviendo a menu");
		Utilities.waitInput(in);
	}

	/**
	 * 
	 * @param salaDeEspera
	 * Imprime informacion sobre como estan las "sillas"(elementos del atributo
	 * pacientes de la sala de espera) en un formato como el siguiente
	 * <key>	<key>	<key>	<key>	
	 * <key>	<key>	<key>	<key>
	 * ...
	 * donde <key> puede ser igual a: -nombre del paciente que esta en la silla
	 * 									- "libre" si no hay nadie
	 * 									- "***" si esta reservado por bioseguridad
	 */
	public static void printSalaDeEspera(SalaDeEspera salaDeEspera) {
		Paciente[] pacientes = salaDeEspera.getPacientes();
		for (int i = 0; i < pacientes.length; i++) {
			
			if(salaDeEspera.isBioseguro() && pacientes[i] == null) {
				System.out.print("*** \t");
			} else if(pacientes[i] != null) {
				System.out.print(pacientes[i].getNombre() + " \t");
			} else {
				System.out.print("libre \t");
			}

			
			if(i % 10 == 0 && i != 0) {
				System.out.println("\n");
			}
		}
		System.out.println("\n");
	}
	
	/*
	 * recibe un ArrayList de personas que estan muertas
	 * le informa al usuario de la siguiente manera por cada muerto
	 * <nombre> ha fallecido :(
	 */
	public static void despedirMuertos(ArrayList<Persona> personas) {
		if(personas.size() > 0) {
			for (Persona persona : personas) {
				if(persona instanceof Paciente) {					
					System.out.println(persona.getNombre() + " ha fallecido :(, sentido pesame");
				}
			}
		} else {
			System.out.println("Nadie ha fallecido :)");
		}
	}
	
	/*
	 * 
	 */
	public static void anunciarHechados(ArrayList<Persona> personas) {
		for (Persona persona : personas) {
			try {
				Doctor doc = (Doctor) persona;
				System.out.println("El " + doc.getEspecialidad().getTitulo() + " " + doc.getNombre() + ", no esta dispuesto a trabajar en pandemia, ha renunciado :(");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("La persona " + persona.getNombre() + " ha sido echada de la sala de espera :(");
			}
		}
	}

}
