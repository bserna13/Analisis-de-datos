package baseDatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.Hospital;
import uiMain.Interfaz;

/**
 * @author Brahian Serna
 * @summary En esta clase, nosotros deserializamos el el programa con el metodo estatico leer()
*/


public class Leer {
    static File archivo = new File("");

    public static void leer(){
		try {
			FileInputStream fi = new FileInputStream(new File(archivo.getAbsolutePath()+"/src/baseDatos/temp/Objetos.txt"));
			ObjectInputStream oi = new ObjectInputStream(fi);
			// Leer objectos
			Interfaz.hospital= (Hospital) oi.readObject();    // ESTO SE LOGRA GRACIAS AQUE EL ATRIUBUTO LO PUSE PUBLIC, SI SE QUEIRE CONSERVAR LA PRIVACIDAD ENTONCES TOCA CREAR UN SET

			// Cerrar conexiones abiertas
			oi.close();
			fi.close();
			
			}
			catch (FileNotFoundException e) {
				System.out.println("No se encuentra archivo");
			}
			catch (IOException e) {
				System.out.println("Error flujo de inicialización");
			}
			
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
}
