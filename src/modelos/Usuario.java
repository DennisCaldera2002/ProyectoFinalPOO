package modelos;


/**
 * @author DELL
 * @version 1.0
 * @created 28-Nov-2021 2:33:28 PM
 */
public class Usuario {

	private String apellido;
	private int estado;
	private int id;
	private String nombre;
	private String contraseña;
        private boolean sexo;

    public Usuario() {
    }

    public Usuario(String apellido, int estado, int id, String nombre, String contraseña, boolean sexo) {
        this.apellido = apellido;
        this.estado = estado;
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.sexo = sexo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isSexo() {
        return sexo;
    }

    public void setSexo(boolean sexo) {
        this.sexo = sexo;
    }

    

    
}//end Usuario