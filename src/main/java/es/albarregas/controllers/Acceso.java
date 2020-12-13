/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Paciente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IUsuarioDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "Acceso", urlPatterns = {"/Acceso"})
public class Acceso extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        HttpSession session = request.getSession();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IUsuarioDAO<Usuario> usuarioUsuarioDAO = daof.getUsuarioDAO();
        IGenericoDAO<Usuario> usuarioGenericDAO = daof.getGenericDAO();
        IGenericoDAO<Paciente> pacienteGenericDAO = daof.getGenericDAO();

        Paciente paciente = new Paciente();
        /*
        Asi genero el administrador de la aplicacion.
          
        IGenericoDAO<Administrador> adao = daof.getGenericDAO();
        String digest = DigestUtils.md5Hex("123");
        Administrador admin = new Administrador("admin@admin.com", digest, "admin", "admin", "ADMINISTRADOR", "76266549k","D");
        adao.insertOrUpdate(admin);
         */
        /*
         Preguntamos si es un login o logout.
         */
        if (request.getParameter("login").equalsIgnoreCase("Iniciar Sesion")) {
            /*
        * Comprobamos que haya introducido datos.
        * Encriptamos la contraseña a md5 para compararla con la que estan guardadas a la base de datos.
        * Asiganmos los valores introducidos a un usuario y llamamos al metodo login pasandole este usuario.
             */
            if (request.getParameter("email") != null && request.getParameter("password") != null) {
                String pass = DigestUtils.md5Hex(request.getParameter("password"));
                Usuario user = new Usuario();
                user.setEmail(request.getParameter("email"));
                user.setPassword(pass.toUpperCase());
                Boolean primerAcceso = false;

                user = usuarioUsuarioDAO.login(user);
                /*
            * Comprobamos que el usuario este guardado en la base de datos preguntado si es null.
                 */
                if (user != null) {
                    session.setAttribute("userConectado", user);
                    /*
                * Redirigimos hacia la siguiente pagina dependiendo de que rol tenga el usuario que ha logueado.
                     */
                    switch (user.getRol()) {
                        case "ADMINISTRADOR":
                            url = "/jsp/Administrador/menuAd.jsp";
                            break;
                        case "DENTISTA":
                        case "PACIENTE":
                            if (user.getUltimoAcceso() == null) {
                                primerAcceso = true;

                            }
                            /*
                            Controlamos si es la primera vez que accede a la aplicacion para que rellene los datos.
                             */
                            if (user.getRol().equalsIgnoreCase("DENTISTA")) {
                                if (primerAcceso) {
                                    url = "jsp/Dentista/cambiarDatos.jsp";
                                } else {
                                    Date actual = new Date();
                                    user.setUltimoAcceso(actual);
                                    usuarioGenericDAO.insertOrUpdate(user);
                                    url = "/jsp/Dentista/menuDentista.jsp";
                                }
                            } else if (primerAcceso) {
                                url = "jsp/Paciente/cambiarDatosPaciente.jsp";
                            } else {
                                Date actual = new Date();
                                paciente = pacienteGenericDAO.getById(user.getIdUsuario(), Paciente.class);
                                paciente.setUltimoAcceso(actual);
                                pacienteGenericDAO.insertOrUpdate(paciente);
                                url = "/jsp/Paciente/menuPaciente.jsp";
                            }
                            break;

                    }

                } else {
                    /*
                * Usuario incorrecto, volvemos a index y mostramos el mensaje de error.
                     */
                    request.setAttribute("mensaje", "Email o password incorrectos.");
                    url = "index.jsp";
                }

            }
            /*
            Hacemos logout
             */
        } else {
            session.invalidate();
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
        String acceso = "Introduce tus datos para acceder a la pagina.";
        request.setAttribute("login", acceso);
        request.getRequestDispatcher(url).forward(request, response);
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
