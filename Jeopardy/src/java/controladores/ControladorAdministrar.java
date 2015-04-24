/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import basededatos.DBHandler;
import beans.Categoria;
import beans.Materia;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marcelo
 */
public class ControladorAdministrar extends HttpServlet {

    private String obtenerMateriasenXML(String hint) {
        String respuesta = "";

        respuesta += "<?xml version='1.0' encoding='ISO-8859-1'?><Materias>";

        ArrayList<Materia> materias = DBHandler.getMaterias(hint);

        for (Materia m : materias) {
            respuesta += "<Nombre>" + m.getNombre() + "</Nombre>";
            respuesta += "<Id>" + m.getId() + "</Id>";
        }

        respuesta += "</Materias>";
        
        return respuesta;
    }
    
    private String obtenerCategoriasenXML(String hint, int id) {
        String respuesta = "";

        respuesta += "<?xml version='1.0' encoding='ISO-8859-1'?><Categorias>";

        ArrayList<Categoria> categorias = DBHandler.getCategorias(hint, id);

        for (Categoria c : categorias) {
            respuesta += "<Nombre>" + c.getNombre() + "</Nombre>";
            respuesta += "<Id>" + c.getId() + "</Id>";
        }

        respuesta += "</Categorias>";
        
        
        return respuesta;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Se saca la operacion que se quiere ejecutar
        String op = request.getParameter("operacion");

        String url = "/administrar.jsp";

        if (op.equals("inicializacion")) {
            ArrayList<Materia> materias = DBHandler.getMaterias("");
            request.setAttribute("materias", materias);

            //Se redirecciona la pagina a la correcta
            ServletContext sc = this.getServletContext();
            RequestDispatcher rd = sc.getRequestDispatcher(url);
            rd.forward(request, response);

        } else if (op.equals("buscar")) {
            String hint = request.getParameter("hint");
            String respuesta = obtenerMateriasenXML(hint);

            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("agregar")) {
            String nombre = request.getParameter("materiaNombre");
            DBHandler.agregarMateria(nombre);

            String respuesta = obtenerMateriasenXML(nombre);

            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.

        } else if (op.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("materiaNombre");
            
            DBHandler.eliminarMateria(id);
            
            String respuesta = obtenerMateriasenXML(nombre);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("editarMateria")){
            int id = Integer.parseInt(request.getParameter("id"));
            String nombre = request.getParameter("nombre");
            DBHandler.editarMateria(id, nombre);
            
            String respuesta = obtenerMateriasenXML(nombre);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("buscarCategorias")){
            int id = Integer.parseInt(request.getParameter("id"));
            String hint = request.getParameter("hint");
            
            String respuesta = obtenerCategoriasenXML(hint, id);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("agregarCategoria")){
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            String nombre = request.getParameter("nombre");
            DBHandler.agregarCategoria(nombre, idMateria);
            
            String respuesta = obtenerCategoriasenXML(nombre, idMateria);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("editarCategoria")){
            int id = Integer.parseInt(request.getParameter("id"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            String nombre = request.getParameter("nombre");
            
            DBHandler.editarCategoria(id, nombre);
            
            String respuesta = obtenerCategoriasenXML(nombre, idMateria);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        } else if (op.equals("eliminarCategoria")){
            int id = Integer.parseInt(request.getParameter("id"));
            int idMateria = Integer.parseInt(request.getParameter("idMateria"));
            String nombre = request.getParameter("nombre");
            
            DBHandler.eliminarCategoria(id);
            
            String respuesta = obtenerCategoriasenXML(nombre, idMateria);
            
            response.setContentType("text/xml");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(respuesta);       // Write response body.
            
        }
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
