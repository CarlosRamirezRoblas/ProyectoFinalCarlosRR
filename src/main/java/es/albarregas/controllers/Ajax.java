/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.IUsuarioDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Ajax", urlPatterns = {"/Ajax"})
public class Ajax extends HttpServlet {

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
        String accion = request.getParameter("accion");

        HttpSession session = request.getSession();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuarioDAO<Usuario> udao = daof.getUsuarioDAO();

        
        JSONObject objeto = null;
        JSONArray arrayJSON = null;

        Usuario usuario = null;

        switch (accion) {
            /*
            Comprobamos si el email ya esta introducido en la base de datos y devolvemos un mensaje si esta en uso
            o la repuesta a null si no esta en la base de datos.
            */
            case "checkEmail":
                objeto = null;
                usuario = udao.checkEmail(request.getParameter("email"));
                if (usuario != null) {
                    objeto = new JSONObject();
                    objeto.put("mensaje", "El email ya esta en uso.");
                }
                response.setContentType("application/json");
                response.getWriter().print(objeto);
                break;
                /*
                Comprobamos que las contraseñas sean iguales y devolvemos un mensaje si no coinciden o null si es correcto.
                */
            case "checkPassword":
                objeto = null;
                
                if (!request.getParameter("password").equals(request.getParameter("repite"))) {  
                    objeto = new JSONObject();
                    objeto.put("mensaje", "Las password deben coincidir.");
                }
                
                response.setContentType("application/json");
                response.getWriter().print(objeto);
                break;
            case "checkDNI":
                objeto = null;
                Usuario userActual = (Usuario) session.getAttribute("userConectado");
                usuario = udao.checkDNI(request.getParameter("dni"));
                if (usuario != null && usuario.getIdUsuario() != userActual.getIdUsuario()) {
                    objeto = new JSONObject();
                    objeto.put("mensaje", "El Dni ya esta en uso.");
                }
                response.setContentType("application/json");
                response.getWriter().print(objeto);
                break;
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
