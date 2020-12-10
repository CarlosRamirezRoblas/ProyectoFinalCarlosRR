/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Inicio", urlPatterns = {"/Inicio"})
public class Inicio extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        String url = "index.jsp";
        HttpSession session = request.getSession();
        Usuario usuario = new Usuario();
        /*
        Boton de vuelta a los distintos menus dependiendo de que usuario
        este logueado o este sin loguear
         */
        if (session.getAttribute("userConectado") != null) {
            Object clase = session.getAttribute("userConectado").getClass();
            usuario = (Usuario) session.getAttribute("userConectado");
            switch (clase.toString()) {
                case "class es.albarregas.beans.Administrador":
                    url = "jsp/Administrador/menuAd.jsp";
                    break;
                case "class es.albarregas.beans.Dentista":
                    if (usuario.getUltimoAcceso() == null) {
                        url = "jsp/Dentista/cambiarDatos.jsp";
                    } else {
                        url = "jsp/Dentista/menuDentista.jsp";
                    }
                    break;
                case "class es.albarregas.beans.Paciente":
                    if (usuario.getUltimoAcceso() == null) {
                        url = "jsp/Paciente/cambiarDatosPaciente.jsp";
                    } else {
                        url = "jsp/Paciente/menuPaciente.jsp";
                    }
                    break;
            }
        }

        request.getRequestDispatcher(url).forward(request, response);

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
