/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import beans.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        try
        {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT usuario, contraseña FROM usuarios where usuario='"+user+"' and contraseña='"+password+"'");
            statement.close();
            
            if (results.next()){
                return true;
            }           
                                   
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Usuario getUsuario(String user){
        Statement statement;
        try{
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios WHERE usuario='"+user);
            resultado.next();
            
            return new Usuario(resultado.getString(0), resultado.getString(1), resultado.getBoolean(2), resultado.getBoolean(3));
                     
        }
        catch (SQLException ex){
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        
        
    }
    
    
}
