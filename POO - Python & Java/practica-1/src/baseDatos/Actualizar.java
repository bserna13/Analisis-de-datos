package baseDatos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import gestorAplicacion.Hospital;

import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * @author Brahian Serna
 * @summary En esta clase, nosotros serializamos el el programa con el metodo estatico actualizar()
*/


public class Actualizar {
    static File archivo = new File("");
	public static void actualizar(Hospital hospital){
		try {
			FileOutputStream f = new FileOutputStream(new File(archivo.getAbsolutePath()+"/src/baseDatos/temp/Objetos.txt"));
			ObjectOutputStream o = new ObjectOutputStream(f);
			// Escribir objectos a un Archivo
			o.writeObject(hospital);
			// Cerrar conexiones abiertas
			o.close();
			
			}
			catch (FileNotFoundException e) {
				System.out.println("No se encuentra archivo");
			}
			catch (IOException e) {
				System.out.println("Error flujo de inicialización");
			}

	}
    
}
