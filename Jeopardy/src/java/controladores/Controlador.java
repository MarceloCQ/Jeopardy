/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import basededatos.DBHandler;
import beans.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcelo
 */
public class Controlador extends HttpServlet {

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
        
        //Se asigna la URL a la cual se va a redireccionar
        String url = "/login.jsp";
        
        //Si la operacion es login
        if (op.equals("login")){
            int intentosFallidos;
            
            //Se obtiene el user y password
            String user = request.getParameter("username");
            String password = request.getParameter("password");
            
            HttpSession session = request.getSession();
            
            //Si es la primera vez que se conecta, los intentos fallidos se ponen en cero
            if (session.getAttribute("intentosFallidos") == null){
                intentosFallidos = 0;
                session.setAttribute("intentosFallidos", 0);               
            }
            else{
                intentosFallidos = (Integer)session.getAttribute("intentosFallidos");
            }
            
            //Si el usuaro inicio sesión correctamente
            if (DBHandler.verificarCuenta(user, password)){
                Usuario usuario = DBHandler.getUsuario(user);
                
                //Si la cuenta del usuario esta bloqueda
                if (usuario.isBloqueada()){
                    //Se regresa a la pagina de login con mensaje
                    request.setAttribute("mensaje", "Tu cuenta esta bloqueda");
                    url = "/login.jsp";
                }
                //Si no esta bloqueada
                else{
                    //Si el usuario ya cambio su contraseña
                    if (usuario.isCambioContraseña()){
                        //Se manda a la pagina principal
                        url = "/inicio.jsp";
                        //Se guarda el objeto en la sesion
                        session.setAttribute("user", user);
                    }
                    else{
                        //Se manda al usuario a que cambie su contraseña
                        url = "/cambioConstrasena.jsp";
                    }
                }
                               
            }
            //Si el intento no fue existoso
            else{
                intentosFallidos++;
                request.setAttribute("mensaje", "Intento fallido, tu cuenta está a punto de bloquearse, quedan" + intentosFallidos + " intentos.");
                url = "/login.jsp";
            }

        }
        
        //Se redirecciona la pagina a la correcta
        ServletContext sc = this.getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);
        rd.forward(request, response);
        
        
        
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
