package uiMain.funcionalidades;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Hospital;
import gestorAplicacion.Persona;
import gestorAplicacion.Doctor;
import gestorAplicacion.Jerarquia;

import uiMain.Utilities;

public class ProyectoExperimental {
	public static void experimentar(Hospital hospital, Scanner in) {
		// INTRO
		System.out.println("Este hospital es pionero en investigacion!");
		System.out.println("Se ha investigado de una manera en la que se puede curar la gripa");
		System.out.println("Para ello vas a tener que elegir a ciertos doctores para que hagan una operacion");
		
		System.out.println("Escoje cuales doctores van a hacer la operacion experimental");
		System.out.println("Son 3 maximo, si hay menos de 3 doctores, entonces seran esos");
		ArrayList<Persona> voluntarios = escogerPersonas(hospital, in);
		
		for (Persona persona : voluntarios) {
			System.out.println("\n"+ persona + " va a comenzar su operacion");
			System.out.println("Recuerda que operar a alguien es como jugar buscaminas, solo no debes dar donde no es");
			
			
			int dificultad;
			if(persona instanceof Doctor && ((Doctor) persona).getJerarquia() == Jerarquia.JEFE) {
				dificultad = 2;
			} else if (persona instanceof Doctor) {
				dificultad = 1;
			}else {
				dificultad = 0;
			}
			boolean operacionExitosa = miniGame(persona, in, dificultad);
			if(!operacionExitosa && persona instanceof Doctor) {
				System.out.println("Se va a despedir a " + persona + " por su mal desempeño");
				hospital.despedirDoctor(persona.getCedula(), true);
			}
		}
	}
	
	
	public static ArrayList<Persona> escogerPersonas(Hospital hospital, Scanner in) {
		ArrayList<Persona> personas = new ArrayList<Persona>();
		
		ArrayList<Object> doctores = new ArrayList<Object>();
	
		for (Persona doctor : hospital.getDoctores()) {
			if(doctor != null) {
				doctores.add(doctor);
			}
		}
		
		Utilities.imprimirOpciones(doctores);	
		
		System.out.println("");
		int elegidos = 0;
		int eleccion;
		while(elegidos < 3 && elegidos < doctores.size()) {
			System.out.print("Escojo a : ");
			eleccion = in.nextInt();
			personas.add((Persona) doctores.get(eleccion));
			elegidos++;
		}
		
		return personas;
	} 
	
	public static boolean miniGame(Persona persona, Scanner in, int dificultad) {
		boolean asesino = false;
		boolean gameOver = false;
		
		int dim;
		int mines;
		switch (dificultad) {
		case 0:
			dim = 10;
			mines = 5;
			break;
		case 1:
			dim = 10;
			mines = 2;
			break;
		case 2:
			dim= 5;
			mines = 1;
			break;
		default:
			dim = 20;
			mines = 15;
			break;
		}
		System.out.println("El tablero es de " + dim + "x" + dim);
		System.out.println("Por lo que las filas y columnas van desde 1 hasta " + dim);
		System.out.println("Si escoges fuera de este rango puedes dañar el sistema!");
		while(!gameOver) {
			int i, j, k, l;
			char[][] solboard = new char[dim+2][dim+2];
			int c = 0, p, q;
			while(c < mines) {
				p = 1 + (int) (Math.random() *dim);
				q = 1 + (int) (Math.random() *dim);
				if(solboard[p][q] != '*') {
					solboard[p][q] = '*';
					c++;
				}
			}
			for(i = 1; i < dim +1; i++) {
				for(j = 1; j < dim + 1; j++) {
					if(solboard[i][j] != '*') {
						solboard[i][j] = '0';
						for(k = -1; k < 2; k++) {
							for(l = -1; l < 2; l++) {
								if(solboard[i+k][j+l] == '*') {
									solboard[i][j]+=1;
								}
							}
						}
					}
				}
			}
			char[][] playboard = new char[dim+2][dim+2];
			for(i = 1; i < dim +1; i++) {
				for(j = 1; j < dim + 1; j++) {
					playboard[i][j] = ' ';
				}
			}
			int stats = 1;
			int minasDescubiertas = 0;
			while (stats == 1) {
				for(i = 1; i < dim +1; i++) {
					for(j = 1; j < dim + 1; j++) {
						System.out.print("|"+ playboard[i][j]);
					}
					System.out.println("|");
				}
				
				System.out.println("Bisturi o succion?");
				System.out.println("[1] Succion | [2] Bisturi");
				System.out.println("Elije instrumento: ");
				int movimiento = in.nextInt();
				
				System.out.println("Donde vas a proceder?");
				System.out.print("Fila" + "(min:" + 1 + ",max:"+ dim + ") : ");
				int coi = in.nextInt();				
				System.out.print("Columna" + "(min:" + 1 + ",max:"+ dim + ") : ");
				int coj = in.nextInt();

				if(playboard[coi][coj] == ' ' || playboard[coi][coj] == '0') {
					switch (solboard[coi][coj]) {
					case '*':
						if(movimiento == 1) {							
							System.out.println("Mataste al paciente :(");
							asesino = true;
							stats=0;
						} else if(movimiento == 2){						
							playboard[coi][coj] = 'X';
							minasDescubiertas++;
							if(minasDescubiertas == mines) {
								System.out.println("Has operado con exito al paciente B) \n\n");
								stats = 0;
								gameOver = true;
							}
						}
						break;
					case '0':
						open(coi, coj, playboard, solboard);
						for(i = 1; i < dim +1; i++) {
							for(j = 1; j < dim + 1; j++) {
								if(playboard[i][j] == '0') {
									for(k = -1; k < 2; k++) {
										for(l = -1; l < 2; l++) {
											playboard[i+k][j+l] = solboard[i+k][j+l];
										}
									}
								}
							}
						}
						break;
					default:
						playboard[coi][coj] = solboard[coi][coj];
					}
				}
			}
			System.out.println("Aqui esta la posicion de todas las celulas malas");
			for(i = 1; i < dim +1; i++) {
				for(j = 1; j < dim + 1; j++) {
					System.out.print("|"+solboard[i][j]);
				}
				System.out.print("|\n");
			}
			gameOver = true;
		}
		return !asesino;
	}
	public static void open(int coi, int coj, char[][] playboard, char[][] solboard) {
		int k, l;
		if (solboard[coi][coj] == '0' && playboard[coi][coj] == ' ')
		{
			playboard[coi][coj] = '0';
			for(k = -1; k < 2; k++) {
				for(l = -1; l < 2; l++) {
					open(coi + k, coj + l, playboard,solboard);
				}
			}
		}
	}
}
