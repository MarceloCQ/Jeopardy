/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import beans.Categoria;
import beans.Materia;
import beans.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcelo
 */
public class DBHandler {
       private static Connection connection;

    public DBHandler() {
        try {            
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/jeopardy", "root", "");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static boolean verificarCuenta (String user, String password){ 
        boolean exito = false;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT usuario, contraseña FROM usuarios where usuario='"+user+"' and contraseña='"+password+"'");
            
            if (results.next()){
                exito = true;
            } 
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return exito;
    }
    
    public static Usuario getUsuario(String user){
        Statement statement;
        
        try{
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios WHERE usuario='"+user+"'");
            resultado.next();
            
            return new Usuario(resultado.getString(1), resultado.getString(2), resultado.getBoolean(3));
                     
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }        
    }
    
    public static int getIntentosFallidos(String user){
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT intentosFallidos FROM usuarios where usuario='"+user+"'");
            int intentosFallidos = -1;
            
            if (results.next()) {
                intentosFallidos = results.getInt(1);
            }
            
            statement.close();
            
            return intentosFallidos;                        
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return -1;
    }
    
    public static void actualizarIntentosFallidos(String user, int intentos){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET intentosFallidos=" + intentos + " WHERE usuario='"+user+"'");
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
               
    }
    
    public static void cambiarContrasena(String user, String nuevaContra){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET contraseña='" + nuevaContra + "' WHERE usuario='"+user+"'");
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void setCambioContra(String user){
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET cambioContraseña='1' WHERE usuario='"+user+"'");
        }
        catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean usuarioExistente(String usuario) {
        boolean existe = false;
        
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT usuario FROM usuarios where usuario='"+usuario+"'");
            
            if (results.next()){
                existe = true;
            } 
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return existe;
    }
    
    public static void agregaUsuario(String usuario, String password) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usuarios (usuario, contraseña) VALUES ('" + usuario + "', '" + password +"')");
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Materia> getMaterias(String hint){
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;
            
            if (hint.equals("")){
                query = "SELECT * FROM materias ORDER BY nombre ASC";
            }
            else{
                query = "SELECT * FROM `materias` WHERE nombre LIKE '"+hint+"%' ORDER BY nombre ASC ";
            }
                       
            results = statement.executeQuery(query);
            
            while (results.next()){
                materias.add(new Materia(results.getInt(1), results.getString(2)));
            }
                      
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return materias;
    }
    
    public static void agregarMateria(String nombre){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO materias (nombre) VALUES ('" + nombre + "')");
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void eliminarMateria(int id){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM materias WHERE id =" + id);
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editarMateria(int id, String nuevoNombre){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE materias SET nombre = '" + nuevoNombre + "' WHERE id =" + id);
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ArrayList<Categoria> getCategorias(String hint, int id){
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        System.out.println("Hint: " + hint);
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;
            
            if (hint.equals("")){
                query = "SELECT * FROM categorias WHERE idMateria = "+ id +" ORDER BY nombre ASC";
            }
            else{
                query = "SELECT * FROM categorias WHERE idMateria = " + id + " and nombre LIKE '"+hint+"%' ORDER BY nombre ASC ";
            }
                       
            results = statement.executeQuery(query);
            
            while (results.next()){
                categorias.add(new Categoria(results.getInt(1), results.getString(2), results.getInt(3)));
            }
                      
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return categorias;
    }
    
    public static void agregarCategoria(String nombre, int id){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO categorias (nombre, idMateria) VALUES ('" + nombre + "', '" + id +"')");
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void editarCategoria(int id, String nuevoNombre){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE categorias SET nombre = '" + nuevoNombre + "' WHERE id =" + id);
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void eliminarCategoria(int id){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM categorias WHERE id =" + id);
            
            statement.close();                       
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
