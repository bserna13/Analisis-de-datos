package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Paciente;

/**
 * @author Brahian Serna
 * @author Daniel correa
 * @summary En esta clase, nosotros agregamos distintos metodos estaticos que distintas funcionalidades usan 
 * 			en conjunto, es decir para no estar creando el mismo metodo estatico en distintas clases
 * 			lo ponemos acá y lo llamamos cuando lo necesitemos. Los más usados por ejemplo son: sleep() para retrasar 
 * 			el tiempo de ejecucion de los programas y waitInput() para que el usuario precione alguna tecla para continuar
*/


public class Utilities {

	/* Pausar tiempo de ejecucion durante 2 segundos(2000 ms)
	 * para que no todo pase tan de una
	 * */
	public static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch(InterruptedException e) {
			System.out.println("Somethind bad is happening");
			e.printStackTrace();
		}
	}

	/* 
	* waitInput() para que el usuario precione alguna tecla para continuar
	*/
	public static void waitInput(Scanner in) {
		System.out.println("Ingrese [n] para continuar, o la letra que quiera\n");
		in.next();
	}


	//En este metodo se decide si un paciente muere o vive, por tal razon retorna un resutado de tipo boolean
	public static boolean decision(Paciente paci,Scanner in) {
		System.out.println("Presione un numero par de [n]'s si no quieres que el paciente muera");
		String x=in.next();
		int numero= x.length();

		if(numero%2==0){
			System.out.println("El paciente "+paci.getNombre() +" se ha salvado gracias a ti :))))\n");
			sleep(4000);
			return true;
		}else{
			System.out.println("El paciente "+paci.getNombre() +" acaba de fallecer debido a complicaciones cardiacas :( \n");
			sleep(4000);
			return false;
		}

	}

    //lo siquiente es un temporizador que se emplea en la funcionalidad 3, cuenta 3 segundos
	public static long midTime;
	public static void temporizador(){
			  
			midTime = 4;
		 //time1 (); // Método uno
		 	time2 (); // Método 2
	}
	private static void time2() {
		while (midTime > 0) {
			midTime--;	           
			long ss = midTime % 60;
			 System.out.println ("Fantasmon se va en "+ss + " segundos");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	/*
	 * En este metodo se imprime las pociones que contiene un ArrayList
	 */
	public static void imprimirOpciones(ArrayList<Object> objects) {
		int i = 0;
		for (Object obj : objects) {
			System.out.println("[" + i + "]" + " " + obj);
			i++;
		}
	}

	//JUEGO DEL AHORCADITO LOS SIGUIENTES 3 METODOS
	public static char[] desguaza(String palAzar){
        char[] letras;
        letras = new char[palAzar.length()];
        for(int i = 0; i < palAzar.length(); i++){
            letras[i] = palAzar.charAt(i);
        }
        return letras;
    }
    /**
     * Esto imprime la palabra con espacios
     * @param tusRespuestas el array de caracteres
     */
    public static void imprimeOculta(char[] tusRespuestas){
 
        for(int i = 0; i < tusRespuestas.length; i++){
            System.out.print(tusRespuestas[i] + " ");
        }
    }
 
  
}
