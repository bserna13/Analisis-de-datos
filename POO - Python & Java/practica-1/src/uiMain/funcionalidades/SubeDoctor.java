package uiMain.funcionalidades;

import java.util.Scanner;

import java.util.Random;

import gestorAplicacion.Doctor;
import gestorAplicacion.Hospital;
import gestorAplicacion.Jerarquia;
import gestorAplicacion.Quirofano;
import uiMain.Utilities;

/**
 * @author Brahian Serna
 * @summary En esta funcionalidad ascendemos a un doctor que lo seleccionará el usuario, hay unos rangos establecidos
*/


public class SubeDoctor {
    public static void SubirDoctor(Hospital hospital, Scanner in) {
        
        System.out.println( "Antes de que el mundo apareciera tal y como lo conocemos, se creó este hospital donde ciertos doctores tenian una jeraquia establecida");

        System.out.println( "A continuacion ayudaras a un doctor para que suba de rango");
        System.out.println("El orden es el siguiente:");
        Utilities.sleep(5000);
        System.out.println(
                          "                           ,......,                                      \n"
                        + "                          ,        ,                                    \n"
                        + "                         ,   JEFE   ,                                   \n"
                        + "                       ,..............,                                 \n"
                        + "                      ,                ,                                \n"
                        + "                     ,      DIRECTOR    ,                              \n"
                        + "                    ,        MEDICO      ,                             \n"
                        + "                   ,......................,                            \n"
                        + "                  ,                        ,                          \n"
                        + "                 ,      DOCTOR DE PLANTA    ,                         \n"
                        + "                ,                            ,                       \n"
                        + "               ,..............................,                      \n"
                        + "              ,                                ,                    \n"
                        + "             ,           DOCTOR INTERNO         ,                   \n"
                        + "            ,....................................,                  \n");

        Utilities.waitInput(in);

        System.out.println("En el hospital hay doctores ocupados que están en los quirofanos y otros que estan disponibles\n"
                          +"seleccione a cuales quieres ayudar: ");

        System.out.println("[1] Doctores que estan en los quirofanos");
        System.out.println("[2] Doctores que estan disponibles en el hospital\n");

        int caso=in.nextInt();

        //CODICIONAL PARA EL USUARIO SI ESCOGE LOS MEDICOS QUE ESTAN EN LOS QUIROFANOS
        if(caso==1){
            System.out.println("Haz seleccionado a los doctores que están trabajando en los quirofanos\n"
            +"Estos son los doctores:");

            DoctoresEnQuirofanos(hospital);
            Doctor[] array= hospital.getDoctorQuirofano(); //DOCTORES
            Utilities.sleep(2000);

            System.out.print("Seleccione el doctor/a que quieras ayudar:");
            int indice= in.nextInt();
            if(array[indice-1]==null){
                System.out.println("Este doctor ya no hace parte del hospital");
                Utilities.waitInput(in);
                return;
            }
            System.out.println("\nHaz seleccionado al doctor/a "+array[indice-1].getNombre()+" y el cargo que ocupa es de "+array[indice-1].getJerarquia());
            
            System.out.println("Si pierdes el juego el/la doctor/a se mantiene en el cargo y si ganas el/la doctor/a sube de rango (en caso de ser jefe se mantiene), recuerda la jerarquia\nEl siguiente juego se llama ahorcadito");
            Utilities.sleep(15000);
            boolean resultado= juego();
            //CODICIONAL POR SI GANA O PIERDE EL JUEGO
            if (resultado==true){
                Utilities.sleep(2000);
                //SI ES JEFE SE MANTIENE COMO JEFE
                if(array[indice-1].getJerarquia()==Jerarquia.JEFE){
                    System.out.println("\nEl/la doctor/a "+array[indice-1].getNombre()+ " ya es JEFE y no va a subir más de rango");
                    Utilities.waitInput(in);
                    return;
                }

                int presupuest = hospital.getPresupuesto()-1000000;
                hospital.setPresupuesto(presupuest);
                if(array[indice-1].getJerarquia()==Jerarquia.INTERNO){
                    array[indice-1].setJerarquia(Jerarquia.PLANTA);
                }
                else if(array[indice-1].getJerarquia()==Jerarquia.PLANTA){
                    array[indice-1].setJerarquia(Jerarquia.DIRECTORMEDICO);
                }
                else if(array[indice-1].getJerarquia()==Jerarquia.DIRECTORMEDICO){
                    array[indice-1].setJerarquia(Jerarquia.JEFE);
                }
                Utilities.sleep(2000);
                System.out.println("\nEl/la doctor/a "+array[indice-1].getNombre()+ " ahora ocupará el cargo de "+array[indice-1].getJerarquia()+" y tendrá un aumento");
                Utilities.waitInput(in);
            }else{
                Utilities.sleep(2000);
                System.out.println("\n Haz perdido y el doctor/a "+array[indice-1].getNombre()+" se mantendra en su cargo");
                Utilities.waitInput(in);
                return;
            }
        }
        //CODICIONAL PARA EL USUARIO SI ESCOGE LOS MEDICOS QUE ESTAN EN EL HOSPITAL DISPONIBLES
        else if(caso==2){
            System.out.println("Haz seleccionado a los doctores que están disponibles en el hospital\n"
            +"Estos son los doctores:");

            printDoctores(hospital);
            Doctor[] array = hospital.getDoctores();  //DOCTORES
            Utilities.sleep(2000);

            System.out.print("Seleccione el doctor/a que quieras ayudar:");
            int indice= in.nextInt();
            if(array[indice-1]==null){
                System.out.println("Este doctor ya no hace parte del hospital");
                Utilities.waitInput(in);
                return;
            }
            System.out.println("\nHaz seleccionado al doctor/a "+array[indice-1].getNombre()+" y el cargo que ocupa es de "+array[indice-1].getJerarquia());
            
            System.out.println("Si pierdes el juego el/la doctor/a se mantiene en el cargo y si ganas el/la doctor/a sube de rango (en caso de ser jefe se mantiene), recuerda la jerarquia\nEl siguiente juego se llama ahorcadito");
            Utilities.sleep(15000);
            boolean resultado= juego();
            //CODICIONAL POR SI GANA O PIERDE EL JUEGO
            if (resultado==true){
                Utilities.sleep(2000);
                if(array[indice-1].getJerarquia()==Jerarquia.JEFE){
                    System.out.println("\nEl/la doctor/a "+array[indice-1].getNombre()+ " ya es JEFE y no va a subir más de rango");
                    Utilities.waitInput(in);
                    return;
                }

                int presupuest = hospital.getPresupuesto()-1000000;
                hospital.setPresupuesto(presupuest);
                if(array[indice-1].getJerarquia()==Jerarquia.INTERNO){
                    array[indice-1].setJerarquia(Jerarquia.PLANTA);
                }
                else if(array[indice-1].getJerarquia()==Jerarquia.PLANTA){
                    array[indice-1].setJerarquia(Jerarquia.DIRECTORMEDICO);
                }
                else if(array[indice-1].getJerarquia()==Jerarquia.DIRECTORMEDICO){
                    array[indice-1].setJerarquia(Jerarquia.JEFE);
                }
                Utilities.sleep(2000);
                System.out.println("\nEl/la doctor/a "+array[indice-1].getNombre()+ " ahora ocupará el cargo de "+array[indice-1].getJerarquia()+" y tendrá un aumento");
                Utilities.waitInput(in);
            }else{
                Utilities.sleep(2000);
                System.out.println("\n Haz perdido y el doctor/a "+array[indice-1].getNombre()+" se mantendra en su cargo");
                Utilities.waitInput(in);
                return;
            }
        }
    }

    /*
    En este metodo DoctoresEnQuirofanos es un metodos donde recibe a un hospital de tipo Hospital como parametro
    y nos imprime en pantalla los doctores que estan en los quirofanos
    */
    public static void DoctoresEnQuirofanos(Hospital hospital) {
		Quirofano[] quirofanos = hospital.getQuirofanos();
        int contador=0;
		for (int i = 0; i < quirofanos.length; i++) {

            if(quirofanos[i]!=null){
                contador++;
                System.out.print("["+contador+"] "+"Doctor/a "+quirofanos[i].getDoctor().getNombre() + " \t");
            }
			
			
			if((contador) % 10 == 0 || quirofanos.length==i+1) {
				System.out.println("\n");
			}
		}
	}
    /*
    En este metodo prinDoctores es un metodos donde recibe a hospital de tipo Hospital como parametro
    y nos imprime en pantalla los doctores que estan disponibles en el hospital, es decir los que no estan en los
    quirofanos trabajando
    */
    public static void printDoctores(Hospital hospital) {
		Doctor[] doctores = hospital.getDoctores();

		for (int i = 0; i < doctores.length; i++) {

                if(doctores[i]!=null){
                    System.out.print("["+(i+1)+"] Doctor/a "+doctores[i].getNombre()+ " \t");
                }else{
                    System.out.println("["+(i+1)+"] Doctor/a No Registrado \t");
                }

			if((i+1) % 10 == 0 || doctores.length==i+1) {
				System.out.println("\n");
			}
		}

	}

    /*
    En este metodo estatico de juego() retorna un boolean porque dependiendo de si el usuario gana el juego (Ahorcadito)
    el doctor que se selecciono sube de rango, por ende aquí mismo es donde se ejecuta el mismo juego donde arriba en
    el codigo basta con solo llamarlo para ejecutarlo
    */
    public static boolean juego(){
        final int INTENTOS_TOTALES = 7; // Constante con el limite de fallos
        int intentos = 0;
        int aciertos = 0;
        
        Scanner teclado = new Scanner(System.in);
        teclado.useDelimiter("\n");
        char resp;
        // Random para pillar una palabra al azar
        Random rnd = new Random();
        // Creamos unas palabras y le asignamos una aleatoria a una varibale
        String arrayPalabras[] = new String[3];
        arrayPalabras[0] = "poo";
        arrayPalabras[1] = "hola";
        arrayPalabras[2] = "clinical";
 
        
 
        // Desguazamos la palabra y la guardamos en un array de caracteres
        int alea = rnd.nextInt(3);
        char[] desguazada = Utilities.desguaza(arrayPalabras[alea]);
        char[] copia = Utilities.desguaza(arrayPalabras[alea]); // Algo auxiliar para mas tarde
        // Array para pintar mierdecillas en pantalla(Guiones o letras vamos)
        char[] tusRespuestas = new char[desguazada.length];
 
        // Rellenamos palabras ocn guiones
        for(int i = 0; i < tusRespuestas.length; i++){
            tusRespuestas[i] = '_';
        }
 
        // Empezamos a pintar mierdas en pantalla
        System.out.println("Adivina la palabra!");
 
        // Mientras que no nos pasemos con los intentos y no la acertemos...
        while(intentos < INTENTOS_TOTALES && aciertos != tusRespuestas.length){
            Utilities.imprimeOculta(tusRespuestas);
            // Preguntamos mierdas por teclado
            System.out.println("\nIntroduce una letra: ");
            resp = teclado.next().toLowerCase().charAt(0);
            // Recorremos el array y comprobamos si se ha producido un acierto
            for(int i = 0; i < desguazada.length; i++){
                if(desguazada[i]==resp){
                    tusRespuestas[i] = desguazada[i];
                    desguazada[i] = ' ';
                    aciertos++;
                }
            }
            intentos++;
        }
        // Si hemos acertado todas imprimimos un mensahe
        if(aciertos == tusRespuestas.length){
            System.out.print("\nFelicidades!! has acertado la palabra: ");
            Utilities.imprimeOculta(tusRespuestas);
            return true;
        }
        // Si no otro
        
        System.out.print("\nHas fallado en este intento! la palabra era: ");
        for(int i = 0; i < copia.length; i++){
        System.out.print(copia[i] + " ");    
        }
        return false;
    }
}
