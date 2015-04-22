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
public class ControladorLogIn extends HttpServlet {

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
        
        HttpSession session = request.getSession();
        
        //Si la operacion es login
        if (op.equals("login")){
            
            //Se obtiene el user y password
            String user = request.getParameter("username");
            String password = request.getParameter("password");
 
            //Sacar los intentos fallidos de la cuenta
            int intentosFallidos = DBHandler.getIntentosFallidos(user);
            
            //Si el usuario no se encontro
            if (intentosFallidos == -1){
                request.setAttribute("mensaje", "La cuenta que ingresaste no existe.");
            }
            else{
                //Si los intentos fallidos son 3, es decir la cuenta está bloqueada
                if (intentosFallidos == 3){
                    request.setAttribute("mensaje", "La cuenta que ingresaste esta bloqueada.");
                }
                else{
                    //Si los datos ingresados fueron correctos
                    if (DBHandler.verificarCuenta(user, password)){
                        
                        Usuario usuario = DBHandler.getUsuario(user);
                        DBHandler.actualizarIntentosFallidos(user, 0);
                        
                        //Checar si el usuario ya cambió su contraseña
                        if (usuario.isCambioContraseña()){
                            session.setAttribute("user", usuario);
                            url = "/inicio.jsp";
                        }
                        else{
                            session.setAttribute("userName", user);
                            url = "/cambioContrasena.jsp";
                        }

                    }
                    else{
                        intentosFallidos++;
                        //Si la cuenta se bloquea por pasar el numero de intentos fallidos
                        if (intentosFallidos == 3){
                            request.setAttribute("mensaje", "La cuenta que ingresaste se ha bloqueado.");

                        }
                        else{
                            boolean singular = intentosFallidos == 2;
                            request.setAttribute("mensaje", "Contraseña incorrecta, te queda" + (singular? " " : "n ") + (3 - intentosFallidos) + " intento" + (singular? "" : "s") +  " para que la cuenta se bloquee.");
                        }
                        
                        DBHandler.actualizarIntentosFallidos(user, intentosFallidos);
                    }
                }
            }
           

        } else if (op.equals("cambioContra")){
            
            String nombreUsuario = (String) session.getAttribute("userName");
            String passwordViejo = request.getParameter("oldpassword");
            String passwordNuevo = request.getParameter("newpassword");
            
            if (DBHandler.verificarCuenta(nombreUsuario, passwordViejo)){
                 DBHandler.cambiarContrasena(nombreUsuario, passwordNuevo);
                 DBHandler.setCambioContra(nombreUsuario);
                 
                 Usuario usuario = DBHandler.getUsuario(nombreUsuario);
                 
                 session.setAttribute("user", usuario);
                 url = "/inicio.jsp";
            }
            else{
                request.setAttribute("mensaje", "Contraseña incorrecta");
                url = "/cambioContrasena.jsp";
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
