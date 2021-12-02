package modelos;



/**
 * @author DELL
 * @version 1.0
 * @created 28-Nov-2021 2:33:28 PM
 */
public class Estudiante extends Usuario {

	

	public Estudiante(){

	}

    public Estudiante(String apellido, int estado, int id, String nombre, String contraseña, boolean sexo) {
        super(apellido, estado, id, nombre, contraseña, sexo);
        
        
    }

    
}//end Estudiante