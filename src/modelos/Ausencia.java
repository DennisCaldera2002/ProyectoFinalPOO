package modelos;

import dao.DaoAusencia;


/**
 * @author DELL
 * @version 1.0
 * @created 28-Nov-2021 2:33:28 PM
 */
public class Ausencia {

   
    
    

	private String clase;
	private String fecha;
	private int idAusencia;
	private int idEstudiante;
	private String mes;
	private String year;
        private int estado;

    public Ausencia(String clase, String fecha, int idAusencia, int idEstudiante, String mes, String year, int estado) {
        this.clase = clase;
        this.fecha = fecha;
        this.idAusencia = idAusencia;
        this.idEstudiante = idEstudiante;
        this.mes = mes;
        this.year = year;
        this.estado = estado;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getIdAusencia() {
        return idAusencia;
    }

    public void setIdAusencia(int idAusencia) {
        this.idAusencia = idAusencia;
    }

    public int getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(int idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    
    
       



}//end Ausenci