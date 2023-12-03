package uiMain;

import java.util.Scanner;

import baseDatos.Actualizar;
import baseDatos.Leer;
import uiMain.funcionalidades.Cuarentena;
import uiMain.funcionalidades.Urgencia;
import uiMain.funcionalidades.Minigame;
import uiMain.funcionalidades.ProyectoExperimental;
import uiMain.funcionalidades.QuejasReclamos;
import uiMain.funcionalidades.SubeDoctor;

import gestorAplicacion.Hospital;
import gestorAplicacion.Jerarquia;
import gestorAplicacion.SalaDeEspera;
import gestorAplicacion.Sintomas;
import gestorAplicacion.Quirofano;
import gestorAplicacion.Cuarto;
import gestorAplicacion.Doctor;
import gestorAplicacion.Paciente;
import gestorAplicacion.Sintomas;
import gestorAplicacion.Especialidad;

/**
 * @author Daniel correa
 * @summary En esta clase Interfaz es donde contiene el metodo main, por ende es donde es donde iniciamos 
 * 			el recorrido del programa mostrandonos las distintas opciones 
*/

public class Interfaz {
	
	private static boolean running = true;
	 /*En este atributo estatico "hospital" tiene como atributo tambien las instancias con las que se van a trabajar en
	 el programa
	 */
	public static Hospital hospital;

	private static int election = -1;
	
	public static void main(String[] args) {
		/*
	 * en inicializar() note que nos lleva a un metodos donde leemos el un archivo donde tenemos los 
	 * objetos guardados lo que se llamaria deserializacion, y serializamos cuando el usuario salga del programa
	 * en la opcion 7
	 */
		inicializar();  

		Scanner in = new Scanner(System.in);
		
		showLogo();
		System.out.println("\n".repeat(3));

		while(running) {
			showMenu();
			while(election < 0 || election > 7) {
				try {
					election = in.nextInt();
					if (election < 0 || election > 7) {
						System.out.println("Opcion invalida..., probemos otra vez");
						System.out.println("Recuerda, elije una de las opciones [1] [2] [3] [4] [5] [6]");
					}
				} catch (Exception InputMismatchException) {
					// TODO: handle exception
					System.out.println("No te entiendo..., probemos otra vez");
					System.out.println("Recuerda, elije una de las opciones [1] [2] [3] [4] [5] [6]");
				}
			}
			System.out.println("");
			executeFunctionality(election, in);	
			election = -1;
		}
		in.close();
	}

	public static void showLogo() {
		System.out.println("       _..._                                   _..._                    \n"
				+ "    .-'_..._''. .---.                       .-'_..._''.           .---. \n"
				+ "  .' .'      '.\\|   |.--.   _..._   .--.  .' .'      '.\\          |   | \n"
				+ " / .'           |   ||__| .'     '. |__| / .'                     |   | \n"
				+ ". '             |   |.--..   .-.   ..--.. '                       |   | \n"
				+ "| |             |   ||  ||  '   '  ||  || |                 __    |   | \n"
				+ "| |             |   ||  ||  |   |  ||  || |              .:--.'.  |   | \n"
				+ ". '             |   ||  ||  |   |  ||  |. '             / |   \\ | |   | \n"
				+ " \\ '.          .|   ||  ||  |   |  ||  | \\ '.          .`\" __ | | |   | \n"
				+ "  '. `._____.-'/|   ||__||  |   |  ||__|  '. `._____.-'/ .'.''| | |   | \n"
				+ "    `-.______ / '---'    |  |   |  |        `-.______ / / /   | |_'---' \n"
				+ "             `           |  |   |  |                 `  \\ \\._,\\ '/      \n"
				+ "                         '--'   '--'                     `--'  `\"       ");
	}
	
	public static void showMenu() {
		if(hospital.isBioseguro()) {
			System.out.println("        .----. \n"
					+ "       ===(_)==   Este hospital es bioseguro, 9 de 10 medicos lo recomiendan...\n"
					+ "      // 6  6 \\\\  /\n"
					+ "      (    7   )\n"
					+ "       \\ '--' /\n"
					+ "        \\_ ._/\n"
					+ "       __)  (__\n"
					+ "    /\"`/`\\`V/`\\`\\\n"
					+ "   /   \\  `Y _/_ \\\n"
					+ "  / [DR]\\_ |/ / /\\\n"
					+ "  |     ( \\/ / / /\n"
					+ "   \\  \\  \\      /\n"
					+ "    \\  `-/`  _.`\n"
					+ "     `=._`=./");
		} else {
		System.out.println("  __  __                  \n"
				+ " |  \\/  |                 \n"
				+ " | \\  / | ___ _ __  _   _ \n"
				+ " | |\\/| |/ _ \\ '_ \\| | | |\n"
				+ " | |  | |  __/ | | | |_| |\n"
				+ " |_|  |_|\\___|_| |_|\\__,_|\n"
				+ "                          \n"
				+ "                          ");
		}
		
		System.out.println("[1] Experimento en laboratorio sale mal");
		System.out.println("[2] Paciente urgente necesita ayuda");
		System.out.println("[3] ¡Peligro! Aquí duerme fantasmon");
		System.out.println("[4] Proyecto Experimental");
		System.out.println("[5] Ayuda a un Doctor en apuros");
		System.out.println("[6] Presentar Queja/Reclamo");
		System.out.println("[7] Salir\n");
		
	}
	
	public static void executeFunctionality(int election, Scanner in) {
		switch (election) {
		case 1:
			Cuarentena.decretarCuarentena(hospital, in);
			break;
		case 2:
			Urgencia.nuevaUrgencia(hospital, in);
			break;
		case 3:
			Minigame.fantasmon(hospital, in);
			break;
		case 4:
			ProyectoExperimental.experimentar(hospital, in);;
			break;
		case 5:
			SubeDoctor.SubirDoctor(hospital, in);
			break;
		case 6:
			QuejasReclamos.presentarQueja(hospital, in);
			break;
		case 7:
			running = false;
			                                 //IMPORTANTE
			//AQUI SERIALIZAMOS LAS INSTANCIAS DEL PROGRAMA CUANDO EL USIARIO QUIERA SALIR DEL PROGRAMA
			Actualizar.actualizar(hospital);
			break;
		}
	}

	//inicializar() es para dar inicio con los objetos que son atributos referenciado de hospital
	public static void inicializar() {
		/* 
		Paciente[] pacientes = {
				new Paciente("Daniel", 1001251),
				new Paciente("Maria", 1230124),
				new Paciente("Juan", 124012512, true),
				new Paciente("Julian", 12414912, true),
				new Paciente("Beatriz", 12041241, true),
				new Paciente("Gabriel", 12401295, true),
				new Paciente("Oscar", 1240124, Sintomas.DEFORMACION),
				new Paciente("Fernando", 125125, Sintomas.FIEBRE),
				new Paciente("Guillermo", 124124, Sintomas.DOLORPECHO),
				new Paciente("David", 1249124, true, Sintomas.DOLORPECHO),
				new Paciente("Ana", 124141412, true, Sintomas.DOLORESTOMAGO)
				};

		Doctor[] doctores = {
				new Doctor("Juana", 12412414, false, Especialidad.CARDIOLOGIA, true, Jerarquia.PLANTA, true),
				new Doctor("Rebeca", 1241514, false, Especialidad.PLASTICA, true, Jerarquia.PLANTA, true),
				new Doctor("Hugo", 2151241, false, Especialidad.DERMATOLOGIA, false, Jerarquia.INTERNO, false),
				new Doctor("Derek", 1241241241, false, Especialidad.NEUROCIRUJANO, true, Jerarquia.JEFE, true),
				new Doctor("Francisco", 124124124, true, Especialidad.CARDIOLOGIA, true, Jerarquia.JEFE, false),
				new Doctor("Cristina", 1241214124, false, Especialidad.CARDIOLOGIA, true, Jerarquia.PLANTA, true),
				new Doctor("Leonel", 12412412, true, Especialidad.DERMATOLOGIA, true, Jerarquia.JEFE, false),
		};

		Quirofano[] quirofanos = new Quirofano[40];
		quirofanos[0] = new Quirofano(new Paciente("Esteban", 102452, true,Sintomas.DOLORPECHO),new Doctor("Jaime", 123624));
		quirofanos[3] = new Quirofano(new Paciente("Juanito", 133452, false,Sintomas.DEMENCIA),new Doctor("Felipe", 1231624,Jerarquia.JEFE));
		
		Cuarto[] cuartos = new Cuarto[40];
		Interfaz.hospital = new Hospital(new SalaDeEspera(pacientes), quirofanos, cuartos, doctores);
		Actualizar.actualizar(hospital); 
		*/

		                                   //IMPORTANTE:
		Leer.leer();  //AQUI DESERIALIZAMOS LOS OBJETOS CON LOS QUE VA A FUNCIONAR NUESTRO PROGRAMA
	}
}
