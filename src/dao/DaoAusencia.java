package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelos.Ausencia;
import java.util.ArrayList;
import javax.swing.JOptionPane;


/**
 * @author Freedman
 * @version 1.0
 * @created 28-Nov-2021 2:33:29 PM
 */
public class DaoAusencia {
        private final conexion conexion = new conexion();
    private Connection conn;
    private PreparedStatement eliminarAusencia;
    private PreparedStatement insertarAusencia;
    private PreparedStatement modificarAusencia;
    private PreparedStatement mostrarAusencia;
    private ArrayList<Ausencia> listaAusencia = new ArrayList();

	public DaoAusencia(){
      try{
            conn = conexion.obtenerConexion();
            mostrarAusencia = conn.prepareStatement("Select * from "
                    + "Ausencia");
            insertarAusencia = conn.prepareStatement("Insert into Ausencia"
                    + "(idEstudiante, idAusencia, clase, fecha, mes, year)" +
                    "Values(?, ?, ?, ? , ?, ?)");
            modificarAusencia = conn.prepareStatement("Update Asistencia set"
                    + "  idEstudiante = ?"
                    + "clase = ?"
                    + "fecha = ?"
                    + " mes = ?"
                    + " year = ?"
                    + " where idAusencia = ?");
            eliminarAusencia = conn.prepareStatement("Delete Ausencia where"
                    + " idAusencia = ?");
            listaAusencia = new ArrayList <>();
          
            listaAusencia = this.listarAusencia();

        }catch(SQLException ex){
            ex.printStackTrace();
            conexion.close(conn);
        } 

	}

	public void finalize() throws Throwable {

	}
	/**
	 * 
	 * @param year
	 * @param mes
	 * @param fecha
	 * @param idAsuencia
	 * @param idEstudiante
	 */
	public int agregarAusencia(String year, String mes, String fecha, int idAusencia, int idEstudiante, String clase){
int b = 0;
        try{
            listaAusencia.add(new Ausencia(clase,
                    fecha,
                    idAusencia,
                    idEstudiante,
                    mes,
                    year,
                    4
    ));
            b = 1;
            return b;
        }catch(Exception ex){
            ex.printStackTrace();
        }
            return b;
	}

	public String BDactualizar(){
		 String msn ="";
        String msnError = "Errores en:";
        int nuevo = 0, modificado = 0, eliminados = 0;
        int errorN = 0, errorM = 0, errorE = 0;

        for(Ausencia pro: listaAusencia){
            switch(pro.getEstado()){
                case 1:
                    break;
                case 2:
                    if(this.BDmodificarAusencia(pro) != 2) modificado++;
                    else{
                        errorM++;
                        msnError += "-Error al modificar registro " +
                                pro.getIdAusencia() + "\n";
                    }
                    break;
                case 3:
                    if(this.BDeliminarAusencia(pro) != 3) eliminados++;
                    else{
                        errorE++;
                        msnError += "-Error al eliminar registro " +
                                pro.getIdAusencia()+ "\n";
                    }
                    break;
                case 4:
                    if(this.BDagregarAusencia(pro) != 4) nuevo++;
                    else{
                        errorN++;
                        msnError += "-Error al agregar proyecto " +
                                pro.getIdAusencia() + "\n";
                    }
                    break;
                default:
                    msnError += "Revise el registro " +
                            pro.getIdAusencia();
                    break;
            }
        }
        msn = "Registros Guardados " + nuevo + "\n" +
            "Registros Modificados " + modificado + "\n" +
            "Registros Eliminados " + eliminados + "\n";
        if(!msnError.equals("Errores en:")){
            msn += "\n" + msnError;
        }
        return msn;
    }
	

	/**
	 * 
	 * @param ausencia
	 */
    public int BDagregarAusencia(Ausencia ausencia){
        int r = 0;
        try{
            insertarAusencia.setInt(1, ausencia.getIdEstudiante());
            insertarAusencia.setInt(2, ausencia.getIdAusencia());
            insertarAusencia.setString(3, ausencia.getClase ());
            insertarAusencia.setString(4, ausencia.getFecha());
            insertarAusencia.setString(5, ausencia.getMes());
            insertarAusencia.setString (6, ausencia.getYear());

            r = insertarAusencia.executeUpdate();

        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Error al agregar registro", JOptionPane.ERROR_MESSAGE);
            conexion.close(conn);
        }
        return r;
    }

	/**
	 * 
	 * @param ausencia
	 */
	public int BDeliminarAusencia(Ausencia ausencia){
         int r = 0;
        try{
            eliminarAusencia.setInt(1, ausencia.getIdAusencia());
            r = eliminarAusencia.executeUpdate();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), 
                    "Error al eliminar registro", JOptionPane.ERROR_MESSAGE)
                    ;
            conexion.close(conn);
        }
            return r;
    }

	/**
	 * 
	 * @param ausencia
	 */
	public int BDmodificarAusencia(Ausencia ausencia){
          int b = 0;
        try{
            /*
            modificarAusencia = conn.prepareStatement("Update Asistencia set"
                    + "  idEstudiante = ?"
                    + "clase = ?"
                    + "fecha = ?"
                    + " mes = ?"
                    + " year = ?"
                    + " where idAusencia = ?");
            
            */
            modificarAusencia.setInt(1,ausencia.getIdEstudiante());
            modificarAusencia.setString(2, ausencia.getClase());
            modificarAusencia.setString(3, ausencia.getFecha());
            modificarAusencia.setString(4, ausencia.getMes());
            modificarAusencia.setString(5, ausencia.getYear());
            modificarAusencia.setInt(6, ausencia.getIdAusencia());
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
            return b;
    }
	

	/**
	 * 
	 * @param id
     * @return 
	 */
	public Ausencia buscarAusencia(int id){
         try{
            for(Ausencia pro: listaAusencia){
                if(pro.getIdAusencia() == id){
                    return pro;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
            return null;
    }

	

	/**
	 * 
	 * @param id
	 */
	public int eliminarAusencia(int id){
          int b = 0;
        try{
            for(Ausencia pro: listaAusencia){
                if(pro.getIdAusencia() == id){
                    pro.setEstado(3);
                    b = 1;
                    return b;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
            return b;
    }
	

	public ArrayList<Ausencia> getListaAusencia(){
		return listaAusencia;
	}

	private ArrayList<Ausencia> listarAusencia(){
	ArrayList<Ausencia> listado = null;
        ResultSet rs = null;
        try{
            rs = mostrarAusencia.executeQuery();
            listado = new ArrayList<>();
            while(rs.next()){
                listado.add(new Ausencia(
                        rs.getString("clase"),
                        rs.getString("fecha"),
                        rs.getInt("idAusencia"),
                        rs.getInt("idEstudiante"),
                        rs.getString("mes"),
                        rs.getString("year"),
                        4
                ));
            }
        }catch(SQLException ex){
            ex.printStackTrace();

        }finally{
            try{
                rs.close();

            }catch(SQLException ex){
                conexion.close(conn);
                ex.printStackTrace();


            }
        }
            return listado;

    }	
	

    /**
     * 
     * @param id
     * @param year
     * @param mes
     * @param fecha
     * @param idEstudiante
     * @param clase
     */
    public void modificarAusencia(int id, String year, String mes, String fecha, int idEstudiante, String clase){
        for(Ausencia a: listaAusencia){
            if(a.getIdEstudiante() == idEstudiante && a.getClase().equals(clase)){
                a.setFecha(fecha);
                a.setMes(mes);
                a.setYear(year);
                a.setClase(clase);
                a.setEstado(2);
            }
        }
    }
        
        //public buscar ausencia o algo asi terminar
    public int getGreaterID(){
        int temp = 0;
        try{
            for(Ausencia a: listaAusencia){
                if(temp < a.getIdAusencia()){
                    temp = a.getIdAusencia();
                    return temp;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
}//end DaoAusencia