package uiMain.funcionalidades;

import java.util.ArrayList;
import java.util.Scanner;

import gestorAplicacion.Hospital;
import gestorAplicacion.Paciente;
import gestorAplicacion.Quirofano;
import gestorAplicacion.Sintomas;

/**
 * @author Brahian Serna
 * @summary Esta funcionalidad hace que ciertas instancias de tipo pacientes que
 *          ya fueron iniciadas dejen de existir dependiendo de si poseen problemas cardiacos
*/

public class Minigame{
    public static void fantasmon(Hospital hospital,Scanner in){


        System.out.println("Alguien ha despertado a fantasmon, un paciente que murio en el siglo XVIII que busca venganza");
        uiMain.Utilities.sleep(5000);
        System.out.println("Tienes 3 segundos para correr o fantasmon irá por ti");
        uiMain.Utilities.sleep(2000);
        uiMain.Utilities.temporizador();
        
        System.out.println(
        "\n&&&&&&&&#######BBGPP5B@@@&5!!~~~^^::.....:::^~!7?J5PPPGGPGP5YJJ?777777??JY5PPPGGBGGGBBBBB###&&&&&&&&\n"
        +"&&&&&&&#######BBGGP5P&@@@B7!~~~^^::............:::^^~!77?Y55PPPP55YYJJ???7?JJJYYYY5PPGBBB###&&&&&&&&\n"
        +"&&&&&&######BBBGGPP5B@@@&Y~!~~^^^::......:.............:::^^~~!!7???JY55?!!!7777Y55PPGBBB###&&&&&&&&\n"
        +"&&&&&&######BBBGPP5P&@@@B!~~~^^^~J^.....^~~^^:::................:::::^^~~^~~~!7?P55PPGBBB###&&&&&&@@\n"
        +"&&&&&&#####BBBBGPPP#@@@&J~~~^^^:?Y:....:~?YJ?777!~~^^::.................:^^~~!!7?Y5PPGBBB###&&&&&@@@\n"
        +"&&&&&&######BBBGPPG@@@@G7!~~^^:~P!.....^7Y5YYYY5YYJJ??7!!~^^^::::.......:^^~~!JJ555PPGBBB###&&#&@@@@\n"
        +"&&&&&&######BBGGPP#@@@&J!~~~^^:JY:..:.:~?Y7^^^~!!7??JJJJJJJ??7!^:......::^^~~J#GP55PPGGBBB##&&&&@@@@\n"
        +"&&&&&&####BBBBGGPB@@@@G!!!~~^:!P!...:.^7JJ^~~^^^^^:::^^~~!7JY?!^:......:^^~~!B@B55PPPGGBBB##&&&&@@@@\n"
        +"&&&&&#####BBBBGGP&@@@#J!!~~~^^YY:...::~?Y!!5P55YJ?77^::::^!JJ?!::^:...::^~~~Y@&G55PPPGBBBB##&&&@@@@@\n"
        +"&&&&&&######BGGPG&@@@G7!!~~^^!P!...::^7J?~JP55PPPPGBG5J7~!Y5J?~:^:...::^^~~7B@BY55PPGBBBB###&&@@@@@@\n"
        +"&&&&&&####B#BGGG#@@@#J!!!~~^^Y5:....:~?J!~5PPPP5PG#@@@#7!JPY?7:::....:^^~~~5@&PY5PPPGBBB###&&&@@@@@@\n"
        +"&&&&&&&###B#BBGB@@@@G7!!~~^^!B7...:.^7J?^JPPPP5PB#@@@@&Y?PGJ?^:::...:^^^~~7#@BY5PPPGBBBB###&&@@@@@@@\n"
        +"&&&&&&####B#BGG&@@@&Y7!!~^^^5P:...::~?J~!PP5PGB#@@@@@@@BPB5J7::^....:^^~~!P@@P5PPPGGBBB###&&&@@@@@@@\n"
        +"&&&&&&#####BBGB@@@@G?7!~~^:7B!....:^7J?^JPPPG#@@@@@@@@@@&#PY~:^:...::^~~~?#@#55PPGGB#B###&&&@@@@@@@@\n"
        +"&&&&&&#####BBB&@@@#Y77!~^^^P5:...::~JJ!!PPPG&@@@@@@@@@@@@@#?^~^....:^~~~!G@@B5PGGGB##B###&#&@@@@@@@@\n"
        +"&&&&&######BG#@@@@P?7!!~~:7B!...^:^7Y?^YGPG&@@@@@@@@@@@@@@#7^!^...:^^^~~J&@&GPGGGGB#B#####&@@@@@@@@@\n"
        +"&&&&&######BB&@@@&Y?7!~~^^Y?:..:::!JY~7GGG#@@@@@@@@@@@@@@@&?!!....:^~~!7G@@BPGGGGB#######&&@@@@@@@@&\n"
        +"&&&&&######B#@@@@GJ?!!~^^7B7.::::^75J~5BG#&@@@@@@@@@@@@@@@&5?:...:^^~~!Y&@&BPGGBBB#######&@@@@@@@@@&\n"
        +"&&&&&&####B#&@@@#YJ?!!~^^G5:..::^!J57!YPG#&@@@@@@@@@@@@@@@#5!...:^^~~!7B@@#GGGBBB#######&@@@@@@@@@@&\n"
        +"&&&&&&####B&@@@@GJJ7!~~^?B!..:::~?PP?JY55#&@@@@@@@@@@@@@@@&5:..::^~~!!5&@&BGGBBBB#######&@@@@@@@@@@@\n"
        +"&&&&&&#####&@@@&PJ?7!~^^G5:..::^!P&5YGB##&@@@@@@@@@@@@@@@@&7::::^^~~!?#@@#BGBBB########&@@@@@@@@@@@@\n"
        +"&&&&&&#####@@@@&PJ?!~~^?B~...::!5#GYPBBB##&@@@@@@@@@@@@@&&P~::::^~~!7P@@&BGBB#########&&@@@@@@@@@&&&\n"
        +"&&&&&&####@@@@&GY?7!~^~PY:..::~YBGYYPBBBB#&@@@@@@@@@@@@@B57:::::^~!7J#@@#BBBB#######&&&@@@@@@@@@@&&@\n"
        +"&&&&&&&##&@@@&GYJ?!!~^?B~..:^~7Y55YYPPGGB#&@@@@@@@@@@@&GYJ^::::^~!!?P@@&BBBB######&&&&&@@@@@@@@@@&@@\n"
        +"&&&&&&&##&@@@&PYJ7~~^^GY...~~!77???JYY55PG#&@@@@@@@@@#P?7~::::^^~!7Y&@@#GBBB#####&&&&&@@@@@@@@@@@@@@\n"
        +"&&&&&&&#&@@@@B5J7!!~^?B~..:~~!!!!777????JY5#@&@@@@@@&P?!!::::^^~!7JG@@&BBBB#####&&&&&&@@@@@@@@@@@&@@\n"
        +"&&&&&&&&@@@@&PYJ7!~^~PY..:^~~~~!!!777777???B@@@@@@&#BJ~!^::^^^^~!?5&@@#BBB######&&&&&@@@@@@@@@@@@@@@\n"
        +"&&&&&&&&@@@@B5Y7!!~^JG~::^^^^~~~~~~!!!!!777B@@@@@#G5J7!!:::^^^~!7JB@@&BBBB#####&&&&&@@@@@@@@@@@@@@@@\n"
        +"&&&&&&&@@@@&P5J7!~^~G5^^^^^^~~~~~~~~~!!!!!!G@@@@B5YJ!!7^.::^^~~775&@@#BB#######&&&&&@@@@@@@@@@@@@@@@\n"
        +"&&&&&&@@@@@B5Y?!!~^?G!^^^^^~~~~~~~~~~~!!!!!G@@@BYJ7!~~^:::^^^~!7J#@@&BBB#####&&&&&&@@@@@@@@@@@@@@@@@\n"
        +"&&&&&&@@@@&G5?7!~~~?7~^^^~~~~~!!!~~~~~!!!!J&@@#J??7!~^^^::^^~!7?P@@@#B#######&&&&&&@@@@@@@@@@@@@@@@@\n"
        +"&&&&&@@@@@#PY?7!!~!7~~^~~~!!!!!!!!!!!!!!7!P@@&Y777!~^^^^^^^~!!7Y#@@#BB######&&&&&&@@@@@@@@@@@@@@@@@@\n"
        +"@@&&&@@@@&G5J77!!!!!~~~~~!!!77!!!!!!!!777?G@@BJ77!!~~^^^^^^~!7?G@@@#B######&&&&&&&@@@@@@@@@@@@@@@@@@\n"
        +"@&&&&@@@@#PY?77!!!!!~~~~!!!77777777777!7?5#@&P??77!~^^^^~~~~!?5&@@&BB#####&&&&&&&@@@@@@@@@@@@@@@@@@@\n"
        +"@&&&@@@@&BPJ???77!!!~~~!!!!!77777777777?JG@&#PJ?77~~^~~~~!~!?JB@@&BB####&#&&&&&&&@@@@@@@@@@@@@@@@@@@\n"
        +"@@&@@@@@#G5YYJ??77!!~!~!!!7777!7!!!!777?JP@@#G?77!~~~~~~~!!7?P&@@#B#####&&&&&&&&@@@@@@@@@@@@@@@@@@@@\n"
        );  
        uiMain.Utilities.sleep(4000);
        
        System.out.println("Eres muy valiente por permanecer aun aquí, selecciona a donde va a ir fantasmon pero cuidado, esto puede traer consecuencia negativas");
        uiMain.Utilities.sleep(6000);
        System.out.println(
        "[1] SALA DE ESPERA\n"
        +"[2] QUIROFANOS"
        );
        int x= in.nextInt();

        //CODICIONAL PARA ENTRAR A LA SALA DE ESPERA O AUN QUIROFANO
        if(x==1){

            //AQUI SE ENTRO A LA SALA DE ESPERA Y LOS PACIENTES QUE ESTEN ALLI SERÁN ASUSTADOS
            System.out.println("Fantasmon entrando a la sala de espera...");
            Paciente[] pacientes = hospital.getSalaDeEspera().getPacientes();

            uiMain.Utilities.sleep(3000);

            //BUCLE QUE RECORRE LA LISTA DE PACIENTES PARA VER QUIEN POSEE PROBLEMAS CARDIACOS
            int contador=-1;
            int pp=0;
            for(Paciente paci :pacientes){
                contador++;
                if(paci==null){
                    continue;
                }
                
                ArrayList<Sintomas> sintomasPaciente = paci.getSintomas();

                //BUCLE QUE RECORRE LOS SINTOMAS DE DETERMINADO PACIENTE Y SI TIENE DOLORPECHO ENTRA AL CONDICIONAL
                for(Sintomas sintoma: sintomasPaciente ){
                    if(sintoma == Sintomas.DOLORPECHO) {   
                        pp++; 
                        if(pp>1){
                            System.out.println("\nOh no! el paciente "+paci.getNombre()+" tambien posee problemas cardiacos y está muy asustado al ver el fantasma, ¿Se salvará?\n");
                        }else{
                            System.out.println("\nOh no! el paciente "+paci.getNombre()+" posee problemas cardiacos y está muy asustado al ver el fantasma, ¿Se salvará?\n");
                        }
                
                        uiMain.Utilities.sleep(4000);

                        //AQUI SE DECIDE SI EL PACIENTE VIVE O MUERE
                        Boolean resultado = uiMain.Utilities.decision(paci,in);
                        if(resultado){
                            break;
                        }else{
                            pacientes[contador]=null;
                            break;
                        }
                    }
                        
                }
            }
            if(pp==0){
                System.out.println("Fantasmon entró, todo el mundo lo vio y se asusto pero afortunadamente no paso nada");
            }
            uiMain.Utilities.waitInput(in);
            
        }else if(x==2){

            //AQUÍ ENTRO A LOS QUIROFANOs
            System.out.println("");
            
            printQuirofanos(hospital);
            uiMain.Utilities.sleep(2000);

            System.out.println("Fantasmon entrará solo a 3 quirofanos segun el numero, seleccione cuales\n");
            uiMain.Utilities.sleep(2000);

            Quirofano[] quirofanos = hospital.getQuirofanos();
            uiMain.Utilities.sleep(5000);

            //NUMERO DE INTENTOS 
            for(int i=0;i<3;i++){
                System.out.println("Intento #"+(i+1));
                System.out.print("Quirofano numero:");

                int num= in.nextInt();

                System.out.println("Haz seleccionado el quirofano numero "+num);
                System.out.println("Fantasmon entrando...");
                uiMain.Utilities.sleep(3000);

                //QUIROFANO SELECCIONADO
                Quirofano quirofano= quirofanos[num-1];

                //HAY DOS OPCIONES, O EL QUIROFANO ESTA VACIDO O HAY ALQUIEN ADENTRO
                if(quirofano==null){
                    System.out.println("En este quirofano no hay nadie\n");
                    uiMain.Utilities.sleep(2000);
                    continue;

                }else{
                    Paciente paci = quirofano.getPaciente();
                    ArrayList<Sintomas> sintomasPaciente = paci.getSintomas();

                    int contador2=0;

                    //BUCLE QUE RECORRE LOS SINTOMAS DEL PACIENTE REVISANDO SI TIENE DOLOR DE PECHO 
                    for(Sintomas sintoma: sintomasPaciente ){

                        if(sintoma == Sintomas.DOLORPECHO) { 
                            contador2++;
                            System.out.println(""); 
                            System.out.println("Oh no! aquí se encuentra el paciente "+paci.getNombre()+" y posee problemas cardiacos, está muy asustado al ver el fantasma, ¿Se salvará?\n");
                            uiMain.Utilities.sleep(4000);
                            Boolean resultado = uiMain.Utilities.decision(paci,in);

                            if(resultado){
                                break;
                            }else{
                                quirofanos[num-1]=null;
                                break;
                            }
                        }
                    }

                    if (contador2==0){
                        System.out.println("\nEn este quirofano está "+paci.getNombre()+", y apesar de ver a fantasmon fue muy fuerte no le pasó absolutamente nada\n");
                        uiMain.Utilities.sleep(5000);
                    }
                }
            }

        }
    }
    /*
    En este metodo printQuirofanos muestra los quirofanos que tiene el hospital (Recordemos que es un atributo estatico)
    los muestra en filas dando saltos por cada 10
    */
    public static void printQuirofanos(Hospital hospital) {
		Quirofano[] quirofanos = hospital.getQuirofanos();

		for (int i = 0; i < quirofanos.length; i++) {

			System.out.print("Quirofano["+(i+1)+"]" + " \t");
			
			if((i+1) % 10 == 0 || quirofanos.length==i+1) {
				System.out.println("\n");
			}
		}
		//System.out.println("\n");
	}
}
		
        






    
    


