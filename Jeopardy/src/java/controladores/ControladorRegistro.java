/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import basededatos.DBHandler;
import utilities.EmailUtility;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author eduardocristerna
 */
public class ControladorRegistro extends HttpServlet {

    /**
     * Metodo que genera passwords
     */
    private String generarPassword() {
        char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        Random random = new Random();
        int numCaracteres = random.nextInt((10 - 6) + 1) + 6;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < numCaracteres; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }

        return sb.toString();
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
            throws ServletException, IOException, MessagingException {

        //Se saca la operacion que se quiere ejecutar
        String op = request.getParameter("operacion");

        if (op.equals("registrar")) {
            String usuario = request.getParameter("username");
            String email = request.getParameter("email");
            String url = "/registrado.html";

            if (!DBHandler.usuarioExistente(usuario)) {
                String password = generarPassword();
                DBHandler.agregaUsuario(usuario, password);
                EmailUtility.sendEmail("smtp.gmail.com", "587", "jeopardystaff@gmail.com", "w3bisc00l", email, "JEOPARDY - Completa tu registro",
                        "Tu registro ha sido exitoso.\n\n Usuario: " + usuario + "\n Contraseña: " + password + "\n\nSe te pedirá que cambies la contraseña una vez que ingreses al sitio por primera vez.");
            } else {
                request.setAttribute("mensaje", "Nombre de usuario no disponible");
                url = "/registrar.jsp";
            }
            
               //Se redirecciona la pagina a la correcta
                ServletContext sc = this.getServletContext();
                RequestDispatcher rd = sc.getRequestDispatcher(url);
                rd.forward(request, response);
            
        }
        
        else if (op.equals("verificar")){
            String userName = request.getParameter("userName");
            String respuesta = "si";
            
            if (DBHandler.usuarioExistente(userName)){
                respuesta = "no";
            }
            
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (MessagingException ex) {
            Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
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
