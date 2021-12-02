package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import modelos.Estudiante;


/**
 * @author Freedman
 * @version 1.0
 * @created 28-Nov-2021 2:33:29 PM
 */
public class DaoEstudiante {
 private ArrayList<Estudiante> listaEstudiante = new ArrayList();
    private final conexion conexion = new conexion();
    private Connection conn;
    private PreparedStatement mostrarRegistros;

    
    public DaoEstudiante(){
        try{

            conn = conexion.obtenerConexion();
            mostrarRegistros = conn.prepareStatement("Select * from Estudiante");           
            listaEstudiante = new ArrayList<>();
            listaEstudiante = listarRegistro();
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR", 
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private ArrayList<Estudiante> listarRegistro(){
    ArrayList<Estudiante> result = null;
    ResultSet rs = null;

    try{
        rs = mostrarRegistros.executeQuery();
        result = new ArrayList<>();
        while(rs.next()){
            result.add(new Estudiante(
                    rs.getString("apellido"),
                    1,
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("contraseña"),
                    rs.getBoolean ("sexo")
            ));
        }

    }catch(SQLException ex){
        JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", 
                JOptionPane.ERROR_MESSAGE);
    }finally{
        try{
            rs.close();
        }catch(Exception ex){
            conexion.close(conn);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", 
                JOptionPane.ERROR_MESSAGE);  
        }
    }

    return result;
    }
    
    public ArrayList<Estudiante> getListaEstudiante(){
        return listaEstudiante;
    }
    
    public int buscarID(int id){
        try{
            for(Estudiante d: listaEstudiante){
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
            for(Estudiante d: listaEstudiante){ // vas a cambiar esto para estudiante
                if( d.getContraseña().equals(pw)){
                    return d.getContraseña();
                }
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return "";
    }
    public Estudiante buscarEstudiante(int id){
        try{
            for(Estudiante d : listaEstudiante){
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


	 

