/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import basededatos.DBHandler;
import beans.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
public class ControladorJugar extends HttpServlet {

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

        String op = request.getParameter("operacion");
        String url = "/seleccionarPerfil.jsp";

        switch (op) {
            case "inicio": {
                ArrayList<Perfil> perfiles = DBHandler.getPerfiles();
                request.setAttribute("perfiles", perfiles);
                break;
            }
                
            case "nuevoPerfil":
                ArrayList<Materia> materias = DBHandler.getMaterias("");
                request.setAttribute("materias", materias);
                url = "/seleccionarMateria.jsp";
                break;
            case "matSelecc": {
                int id = Integer.parseInt(request.getParameter("materia"));
                String nombrePerfil = request.getParameter("nombrePerfil");
                ArrayList<Categoria> categorias = DBHandler.getCategorias("", id);
                request.setAttribute("categorias", categorias);
                request.getSession().setAttribute("nombrePerfil", nombrePerfil);
                url = "/seleccionarCategorias.jsp";
                break;
            }
            case "catSelecc": {
                String[] categorias = request.getParameterValues("categorias");
                ArrayList<Categoria> categoriasLista = new ArrayList<>();
                for (String categoria : categorias) {
                    int id = Integer.parseInt(categoria);
                    Categoria cat = DBHandler.obtenerCategoria(id);
                    ArrayList<ArrayList<Pista>> pistas = new ArrayList<>();
                    for (int i = 100; i <= 500; i += 100) {
                        pistas.add(DBHandler.getPreguntasPorPuntos(id, i));
                    }
                    cat.setPistas(pistas);
                    categoriasLista.add(cat);

                }
                request.setAttribute("categorias", categoriasLista);
                url = "/seleccionarPistas.jsp";
                break;
            }
            case "pistaSelecc": {
                String[] pistas = request.getParameterValues("pistas");
                String nombre;
                Materia materia;
                ArrayList<Categoria> categorias = new ArrayList<>();
                
                nombre = (String) request.getSession().getAttribute("nombrePerfil");
                
                for (int i = 0; i < 6; i++) {
                    int id = i * 5;
                    Pista p = DBHandler.obtenerPista(Integer.parseInt(pistas[id]));
                    categorias.add(DBHandler.obtenerCategoria(p.getIdCategoria()));
                }
                
                materia = DBHandler.obtenerMateria(categorias.get(0).getMateriaID());
                int ind = 0;
                for (Categoria c : categorias) {
                    ArrayList<ArrayList<Pista>> aux = new ArrayList<>();
                    ArrayList<Pista> pistasLista = new ArrayList<>();
                    for (int i = 0; i < 5; i++) {
                        pistasLista.add(DBHandler.obtenerPista(Integer.parseInt(pistas[ind])));
                        ind++;
                    }
                    aux.add(pistasLista);
                    c.setPistas(aux);
                }
                Perfil perfil = new Perfil(0, nombre, materia, categorias);
                HttpSession session = request.getSession();
               
                int id = DBHandler.agregarPerfil(perfil);
                perfil.setId(id);
                session.setAttribute("perfil", perfil);
                url = "/seleccionarJugadores.jsp";
                break;
            }
            
            case "jugadoresSelecc": {
                String[] jugadores = request.getParameterValues("jugador");               
                request.getSession().setAttribute("jugadores", jugadores);
                ArrayList<Integer> scores = new ArrayList<Integer>();
                for (String s : jugadores){
                    scores.add(0);
                }
                request.getSession().setAttribute("scores", scores);
                url = "/juego.jsp";
                break;
                
            }
            case "perfilSelecc": {
                int id = Integer.parseInt(request.getParameter("perfil"));
                Perfil p = DBHandler.obtenerPerfil(id);
                request.getSession().setAttribute("perfil", p);
                url = "/seleccionarJugadores.jsp";
                break;
            }
            case "juegoTerminado": {
                String[] jugadores = (String[])request.getSession().getAttribute("jugadores");
                ArrayList<Integer> scores = (ArrayList<Integer>)request.getSession().getAttribute("scores");
                Perfil p = (Perfil)request.getSession().getAttribute("perfil");
                
                request.setAttribute("jugadores", jugadores);
                request.setAttribute("scores", scores);
                request.setAttribute("perfil", p);
                
                request.getSession().removeAttribute("jugadores");
                request.getSession().removeAttribute("scores");
                request.getSession().removeAttribute("perfil");
                
                int id = DBHandler.agregarJuego(jugadores, scores, p.getId());
                request.setAttribute("juego", DBHandler.obtenerJuego(id));
                url = "/resumenJuego.jsp";
                break;
                
                
            }
            
            case "historial": {
                ArrayList<Juego> juegos = DBHandler.getJuegos();
                request.setAttribute("juegos", juegos);
                url = "/seleccionarJuego.jsp";
                break;
            }
            
            case "juegoSelecc": {
                int id = Integer.parseInt(request.getParameter("id"));
                Juego j = DBHandler.obtenerJuego(id);
                Perfil p = DBHandler.obtenerPerfil(j.getIdPerfil());
                
                String[] jugadores = DBHandler.obtenerJugadores(j.getId());
                ArrayList<Integer> scores = DBHandler.obtenerPuntuaciones(j.getId());
                
                
                request.setAttribute("jugadores", jugadores);
                request.setAttribute("scores", scores);
                request.setAttribute("perfil", p);
                request.setAttribute("juego", j);
                url = "/resumenJuego.jsp";
                break;
            }
            

        }

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
