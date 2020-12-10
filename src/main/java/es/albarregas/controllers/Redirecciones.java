/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Dentista;
import es.albarregas.beans.Paciente;
import es.albarregas.dao.IDentistaDAO;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IPacienteDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "Redirecciones", urlPatterns = {"/Redirecciones"})
public class Redirecciones extends HttpServlet {

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
        String url = null;
        HttpSession session = request.getSession();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO<Paciente> adao = daof.getGenericDAO();
        IGenericoDAO<Dentista> gdao = daof.getGenericDAO();
        IDentistaDAO<Dentista> ddao = daof.getDentistaDAO();
        IPacienteDAO<Paciente> pdao = daof.getPacienteDAO();

        Dentista dentista = new Dentista();
        Paciente paciente = new Paciente();
        List<Dentista> dentistas = new ArrayList();
        List<Paciente> pacientes = new ArrayList();


        switch (request.getParameter("enviar")) {
            /*
            * Opciones de Administrador.
             */
            //Redireccion a al formulario para crear un nuevo dentista.
            case "Dar de alta a nuevos dentistas":
                url = "/jsp/Administrador/createDentista.jsp";
                break;
            /*
                Obtenemos una lista de dentistas que no tengan pacientes pero
                preguntamos si la lista esta vacia ya que puede ser que no haya
                dentistas sin pacientes.
             */
            case "Eliminar dentistas sin pacientes":
                dentistas = ddao.dentistaSinPaciente();
                if (dentistas.isEmpty()) {
                    request.setAttribute("mensaje", "No hay dentistas sin pacientes.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("dentistas", dentistas);
                    url = "/jsp/Administrador/dentistasSinPacientes.jsp";
                }
                break;
            /*
                Obtenemos una lista con todos los dentistas,
                la guardamos en un atributo de peticion y 
                redirigimos el flujo al formulario que los muestra
             */
            case "Listar los dentistas existentes":
                dentistas = gdao.get(Dentista.class);
                if (dentistas.isEmpty()) {
                    request.setAttribute("mensaje", "No hay dentista.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("dentistas", dentistas);
                    url = "/jsp/Administrador/listDentistas.jsp";
                }
                break;
            /*
                *A partir de aqui opciones de los dentistas.
             */
            //Redireccion al formulario para crear un nuevo paciente
            case "Registro pacientes":
                url = "/jsp/Tutor/createAlumno.jsp";
                break;
            /*
                Obtenemos una lista de nuestros pacientes y los mostramos en una 
                pagina jsp para elegir al que queramos borrar.
             */
            case "Eliminar paciente":
                dentista = (Dentista) session.getAttribute("userConectado");
                pacientes = pdao.obtenerPacientesDeUnDentista(dentista.getIdUsuario());
                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes que eliminar.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Tutor/eliminarPacientes.jsp";
                }
                break;
            /*
                Redireccion a la pagina de cambiar datos.
             */
            case "Cambiar datos":
                url = "/jsp/Dentista/cambiarDatos.jsp";
                break;

            /*
                Y por ultimo pacientes.
             */
            //Avanzamos a la pagina jsp donde se cambian los datos de paciente.
            case "Cambiar datos propios":
                url = "/jsp/Paciente/cambiarDatosPaciente.jsp";
                break;
            /*
                Nos envia a la pagina donde se muestra nuestro tratamiento en caso de tener uno
                activo.
             */
            case "Ver tratamiento":
                paciente = (Paciente) session.getAttribute("userConectado");
                if (paciente.getTratamiento().isEmpty()) {
                    request.setAttribute("mensaje", "No hay tratamiento asignados.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("tratamiento", paciente);
                    url = "/jsp/Paciente/verTratamiento.jsp";
                }

                break;
            /*
                Nos envia a la pagina donde se muestra nuestro historial
             */
            case "Ver historial":
                paciente = (Paciente) session.getAttribute("userConectado");
                if (paciente.getHistoriales().isEmpty()) {
                    request.setAttribute("mensaje", "No hay historial.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("historiales", paciente);
                    url = "/jsp/Paciente/verHistorial.jsp";
                }
                break;

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
