/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package basededatos;

import beans.Categoria;
import beans.Juego;
import beans.Materia;
import beans.Perfil;
import beans.Pista;
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

    public static boolean verificarCuenta(String user, String password) {
        boolean exito = false;

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT usuario, contraseña FROM usuarios where usuario='" + user + "' and contraseña='" + password + "'");

            if (results.next()) {
                exito = true;
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return exito;
    }

    public static Usuario getUsuario(String user) {
        Statement statement;

        try {
            statement = connection.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM usuarios WHERE usuario='" + user + "'");
            resultado.next();

            return new Usuario(resultado.getString(1), resultado.getString(2), resultado.getBoolean(3));

        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static int getIntentosFallidos(String user) {
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT intentosFallidos FROM usuarios where usuario='" + user + "'");
            int intentosFallidos = -1;

            if (results.next()) {
                intentosFallidos = results.getInt(1);
            }

            statement.close();

            return intentosFallidos;
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return -1;
    }

    public static void actualizarIntentosFallidos(String user, int intentos) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET intentosFallidos=" + intentos + " WHERE usuario='" + user + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void cambiarContrasena(String user, String nuevaContra) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET contraseña='" + nuevaContra + "' WHERE usuario='" + user + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void setCambioContra(String user) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE Usuarios SET cambioContraseña='1' WHERE usuario='" + user + "'");
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static boolean usuarioExistente(String usuario) {
        boolean existe = false;

        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT usuario FROM usuarios where usuario='" + usuario + "'");

            if (results.next()) {
                existe = true;
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return existe;
    }

    public static void agregaUsuario(String usuario, String password) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO usuarios (usuario, contraseña) VALUES ('" + usuario + "', '" + password + "')");

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Materia> getMaterias(String hint) {
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            if (hint.equals("")) {
                query = "SELECT * FROM materias ORDER BY nombre ASC";
            } else {
                query = "SELECT * FROM `materias` WHERE nombre LIKE '" + hint + "%' ORDER BY nombre ASC ";
            }

            results = statement.executeQuery(query);

            while (results.next()) {
                materias.add(new Materia(results.getInt(1), results.getString(2)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materias;
    }

    public static void agregarMateria(String nombre) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO materias (nombre) VALUES ('" + nombre + "')");

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarMateria(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM materias WHERE id =" + id);
            statement.executeUpdate("DELETE FROM categorias WHERE idMateria =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editarMateria(int id, String nuevoNombre) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE materias SET nombre = '" + nuevoNombre + "' WHERE id =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Materia obtenerMateria(int id) {
        Materia materia = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM materias where id = " + id);

            if (results.next()) {
                materia = new Materia(results.getInt(1), results.getString(2));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }

    public static ArrayList<Categoria> getCategorias(String hint, int id) {
        ArrayList<Categoria> categorias = new ArrayList<Categoria>();
        System.out.println("Hint: " + hint);
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            if (hint.equals("")) {
                query = "SELECT * FROM categorias WHERE idMateria = " + id + " ORDER BY nombre ASC";
            } else {
                query = "SELECT * FROM categorias WHERE idMateria = " + id + " and nombre LIKE '" + hint + "%' ORDER BY nombre ASC ";
            }

            results = statement.executeQuery(query);

            while (results.next()) {
                categorias.add(new Categoria(results.getInt(1), results.getString(2), results.getInt(3)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return categorias;
    }

    public static void agregarCategoria(String nombre, int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO categorias (nombre, idMateria) VALUES ('" + nombre + "', '" + id + "')");

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editarCategoria(int id, String nuevoNombre) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE categorias SET nombre = '" + nuevoNombre + "' WHERE id =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void eliminarCategoria(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM categorias WHERE id =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static ArrayList<Pista> getPreguntas(String hint, int id) {
        ArrayList<Pista> preguntas = new ArrayList<Pista>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            if (hint.equals("")) {
                query = "SELECT * FROM pistas WHERE idCategoria = " + id + " ORDER BY pregunta ASC";
            } else {
                query = "SELECT * FROM pistas WHERE idCategoria = " + id + " and pregunta LIKE '" + hint + "%' ORDER BY pregunta ASC ";
            }

            results = statement.executeQuery(query);

            while (results.next()) {
                preguntas.add(new Pista(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return preguntas;
    }

    public static ArrayList<Pista> getPreguntasPorPuntos(int id, int puntos) {
        ArrayList<Pista> preguntas = new ArrayList<Pista>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM pistas WHERE idCategoria = " + id + " and puntos = " + puntos + " ORDER BY pregunta ASC";

            results = statement.executeQuery(query);

            while (results.next()) {
                preguntas.add(new Pista(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return preguntas;
    }

    public static void agregarPregunta(String pregunta, String pista, int puntos, int idCategoria) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO pistas (pista, pregunta, puntos, idCategoria) VALUES ('" + pista + "', '" + pregunta + "', '" + puntos + "', '" + idCategoria + "')");

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Pista obtenerPista(int id) {
        Pista pista = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM pistas where id = " + id);

            if (results.next()) {
                pista = new Pista(results.getInt(1), results.getString(2), results.getString(3), results.getInt(4), results.getInt(5));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pista;
    }

    public static void eliminarPista(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM pistas WHERE id =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void editarPista(int id, String pregunta, String respuesta, int puntos) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("UPDATE pistas SET pista = '" + respuesta + "', pregunta = '" + pregunta + "', puntos = " + puntos + " WHERE id =" + id);

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Categoria obtenerCategoria(int id) {
        Categoria categoria = null;
        try {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM categorias where id = " + id);

            if (results.next()) {
                categoria = new Categoria(results.getInt(1), results.getString(2), results.getInt(3));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoria;
    }

    public static void agregarPerfil(Perfil perfil) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO perfil (nombre) VALUES ('" + perfil.getNombre() + "')");

            ResultSet keyResultSet = statement.getGeneratedKeys();
            int nuevoId = 0;
            if (keyResultSet.next()) {
                nuevoId = (int) keyResultSet.getInt(1);
            }

            for (Categoria c : perfil.getCategorias()) {
                statement.executeUpdate("INSERT INTO perfil_categoria (idperfil, idcategoria) VALUES (" + nuevoId + ", " + c.getId() + ")");
                for (Pista p : c.getPistas().get(0)) {
                    statement.executeUpdate("INSERT INTO perfil_pista (idperfil, idpista) VALUES (" + nuevoId + ", " + p.getId() + ")");
                }
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static ArrayList<Perfil> getPerfiles() {
        ArrayList<Perfil> perfiles = new ArrayList<Perfil>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM perfil ORDER BY nombre ASC";

            results = statement.executeQuery(query);

            while (results.next()) {
                perfiles.add(new Perfil(results.getInt(1), results.getString(2)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return perfiles;
    }

    public static Perfil obtenerPerfil(int id) {
        Perfil perfil = null;
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM perfil WHERE id = " + id + " ORDER BY nombre ASC";

            results = statement.executeQuery(query);

            if (results.next()) {
                String nombre = results.getString(2);
                ArrayList<Categoria> categorias = new ArrayList<>();

                ResultSet resultsCategorias = connection.createStatement().executeQuery("SELECT * FROM perfil_categoria WHERE idperfil = " + id);
                while (resultsCategorias.next()) {
                    Categoria cat = obtenerCategoria(resultsCategorias.getInt(2));
                    ArrayList<ArrayList<Pista>> pistas = new ArrayList<>();
                    ArrayList<Pista> pistasAux = new ArrayList<>();
                    ResultSet resultsP = connection.createStatement().executeQuery("SELECT idpista FROM perfil_pista, pistas WHERE perfil_pista.idpista = pistas.id and idperfil = " + id + " and idCategoria = " + cat.getId());
                    while (resultsP.next()) {
                        pistasAux.add(obtenerPista(resultsP.getInt(1)));
                    }

                    pistas.add(pistasAux);
                    cat.setPistas(pistas);
                    categorias.add(cat);

                }

                perfil = new Perfil(id, nombre, DBHandler.obtenerMateria(categorias.get(0).getMateriaID()), categorias);

            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return perfil;
    }

    public static int agregarJuego(String[] nombres, ArrayList<Integer> puntuaciones, int idperfil) {
        int nuevoId = 0;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO juego (fecha, idperfil) VALUES (now(), " + idperfil + ")");

            ResultSet keyResultSet = statement.getGeneratedKeys();
           
            if (keyResultSet.next()) {
                nuevoId = (int) keyResultSet.getInt(1);

            }

            for (int i = 0; i < nombres.length; i++) {
                statement.executeUpdate("INSERT INTO juego_jugador (nombre, puntos, idjuego) VALUES ('" + nombres[i] + "', " + puntuaciones.get(i) + ", " + nuevoId + ")");
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nuevoId;
        
    }

    public static ArrayList<Juego> getJuegos() {
        ArrayList<Juego> juegos = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM juego ORDER BY fecha ASC";

            results = statement.executeQuery(query);

            while (results.next()) {
                juegos.add(new Juego(results.getInt(1), results.getTimestamp(2), results.getInt(3)));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return juegos;
    }
    
    public static Juego obtenerJuego(int id) {
        Juego juego = null;
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM juego WHERE id = " + id + " ORDER BY fecha ASC";

            results = statement.executeQuery(query);

            if (results.next()) {
                juego = new Juego(results.getInt(1), results.getTimestamp(2), results.getInt(3));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return juego;
    }
    
    public static String[] obtenerJugadores(int id){
        ArrayList<String> jugadores = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM juego_jugador WHERE idjuego = "+ id + " ORDER BY nombre ASC";

            results = statement.executeQuery(query);
            while (results.next()) {
                jugadores.add(results.getString(2));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String []aux = new String[jugadores.size()];
        return jugadores.toArray(aux);
    }
    
    public static ArrayList<Integer> obtenerPuntuaciones(int id){
        ArrayList<Integer> puntuaciones = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query;
            ResultSet results;

            query = "SELECT * FROM juego_jugador WHERE idjuego = "+ id + " ORDER BY nombre ASC";

            results = statement.executeQuery(query);
            while (results.next()) {
                puntuaciones.add(results.getInt(3));
            }

            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

        return puntuaciones;
    }
    
    

}
