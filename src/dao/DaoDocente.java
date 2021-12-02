package dao;

import modelos.Docente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Freedman
 */
public class DaoDocente {
    private ArrayList<Docente> listaDoc = new ArrayList();
    private final conexion conexion = new conexion();
    private Connection conn;
    private PreparedStatement mostrarRegistros;

    
    public DaoDocente() {
        try{
            
            conn = conexion.obtenerConexion();
            mostrarRegistros = conn.prepareStatement("Select * from Docente");           
            listaDoc = new ArrayList<>();
            listaDoc = listarRegistro();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private ArrayList<Docente> listarRegistro(){
        ArrayList<Docente> result = null;
        ResultSet rs = null;
        
        try{
            rs = mostrarRegistros.executeQuery();
            result = new ArrayList<>();
            while(rs.next()){
                result.add(new Docente(
                        rs.getString("claseImpart"),
                        rs.getString("apellido"),
                        1,
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("contraseña"),
                        rs.getBoolean("sexo")
                ));
            }
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", 
                    JOptionPane.ERROR_MESSAGE);
        }finally{
            try{
                rs.close();
            }catch(SQLException ex){
                conexion.close(conn);
                JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", 
                    JOptionPane.ERROR_MESSAGE);  
            }
        }
        
        return result;
    }
    public int buscarID(int id){
        try{
            for(Docente d: listaDoc){
                if( d.getId() == id){
                    return d.getId();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return 0;
    }
    
    public String buscarContraseña(String pw){
        try{
            for(Docente d: listaDoc){
                if( d.getContraseña().equals(pw)){
                    return d.getContraseña();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    
    public Docente buscarDocente(int id){
        try{
            for(Docente d : listaDoc){
                if(d.getId() == id){
                    return d;
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}