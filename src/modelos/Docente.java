package modelos;

 


/**
 * @author DELL
 * @version 1.0
 * @created 28-Nov-2021 2:33:28 PM
 */
public class Docente extends Usuario {

	private String claseImpartida;

    public Docente(String claseImpartida, String apellido, int estado, int id, String nombre, String contraseña, boolean sexo) {
        super(apellido, estado, id, nombre, contraseña, sexo );
        this.claseImpartida = claseImpartida;
    }

    public Docente() {
        
    }

    public String getClaseImpartida() {
        return claseImpartida;
    }

    public void setClaseImpartida(String claseImpartida) {
        this.claseImpartida = claseImpartida;
    }
	
}//end Docente