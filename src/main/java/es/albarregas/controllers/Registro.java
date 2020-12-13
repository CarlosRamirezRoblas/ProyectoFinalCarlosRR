/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;


import es.albarregas.beans.Paciente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "Registro", urlPatterns = {"/Registro"})
public class Registro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = "/jsp/registro.jsp";
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO<Usuario> pdao = daof.getGenericDAO();

        Paciente paciente = new Paciente();
        /*
        Pagina donde llegan los datos del registro de pacientes.
        Se encripta la contraseña, se le asigna el rol de paciente y se 
        guarda en la base de datos.
        */
        if (request.getParameter("registro") != null) {
            String digest = DigestUtils.md5Hex(request.getParameter("password"));
            paciente.setPassword(digest.toUpperCase());
            paciente.setEmail(request.getParameter("email"));
            paciente.setRol("PACIENTE");
            pdao.insertOrUpdate(paciente);
            url = "index.jsp";
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
        String url = "/index.jsp";
        HttpSession session = request.getSession();
        Usuario usuario = new Usuario();
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
            request.getRequestDispatcher(url).forward(request, response);
        }
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
