/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Cita;
import es.albarregas.beans.Dentista;
import es.albarregas.beans.Historial;
import es.albarregas.beans.Paciente;
import es.albarregas.dao.IDentistaDAO;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IHistorialDAO;
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
        IGenericoDAO<Paciente> pacienteGenericDAO = daof.getGenericDAO();
        IGenericoDAO<Dentista> dentistaGenericDAO = daof.getGenericDAO();
        IDentistaDAO<Dentista> dentistaDentistaDAO = daof.getDentistaDAO();
        IPacienteDAO<Paciente> pacientePacienteDAO = daof.getPacienteDAO();
        IHistorialDAO<Historial> historialHistorialDAO = daof.getHistorialDAO();

        Dentista dentista = new Dentista();
        Paciente paciente = new Paciente();
        List<Dentista> dentistas = new ArrayList();
        List<Paciente> pacientes = new ArrayList();
        List<Paciente> addPacientes = new ArrayList();
        List<Dentista> dentistasYSusPacientes = new ArrayList();
        List<Historial> historiales = new ArrayList();

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
                dentistas = dentistaDentistaDAO.dentistaSinPaciente();
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
                dentistas = dentistaGenericDAO.get(Dentista.class);
                if (dentistas.isEmpty()) {
                    request.setAttribute("mensaje", "No existen dentistas en la base de datos.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("dentistas", dentistas);
                    url = "/jsp/Administrador/listDentistas.jsp";
                }
                break;
                /*
                Obtenemos todos los pacientes y dentistas.
                Luego vamos preguntando a cada paciente si es su dentista y asi
                vamos creando una lista que posteriormente la la enviamos por peticion
                si hay algun dentista añadido o redireccionamos a la pagina de advertencias
                si no hay ningun dentista con paciente.
                */
            case "Listar los dentistas y pacientes asignados":
                pacientes = pacienteGenericDAO.get(Paciente.class);
                dentistas = dentistaGenericDAO.get(Dentista.class);

                for (Dentista dent : dentistas) {
                    for (Paciente paci : pacientes) {
                        if (paci.getDentista() != null) {
                            if (dent.getIdUsuario() == paci.getDentista().getIdUsuario()) {
                                addPacientes.add(paci);
                            }
                        }
                    }
                    if (!addPacientes.isEmpty()) {
                        Dentista nuevo = new Dentista();
                        nuevo = dent;
                        nuevo.setPacientes(addPacientes);
                        dentistasYSusPacientes.add(nuevo);

                    }
                    addPacientes = new ArrayList();
                }
                if (dentistasYSusPacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay dentistas con pacientes asignados.");
                    url = "/jsp/advertencias.jsp";
                } else {

                    request.setAttribute("dentistas", dentistasYSusPacientes);
                    url = "/jsp/Administrador/listDentistasYSusPacientes.jsp";
                }

                break;
            /*
                Obtenemos una lista de nuestros pacientes sin dentista asignado
                y los mostramos en una pagina jsp para elegir al que queramos borrar.
             */
            case "Eliminar paciente":
                pacientes = pacientePacienteDAO.pacientesSinDentista();
                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes sin asignar.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Administrador/listEliminarPaciente.jsp";
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
                Obtenemos una lista con los pacientes sin dentista. Comprobamos que haya
                alguno y los mostramos en una vista.
             */
            case "Asignar paciente":
                pacientes = pacientePacienteDAO.pacientesSinDentistaNiDatos();
                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes sin asignar.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Dentista/listAsignarPaciente.jsp";
                }
                break;
            /*
                Obtenemos una lista con los pacientes del dentista en sesion. Comprobamos que haya
                alguno y los mostramos en una vista.
             */
            case "Desasignar paciente":
                dentista = (Dentista) session.getAttribute("userConectado");
                pacientes = pacientePacienteDAO.obtenerPacientesDeUnDentista(dentista.getIdUsuario());
                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes asignados.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Dentista/listDesasignarPaciente.jsp";
                }
                break;
            /*
                Redireccion a la pagina de cambiar datos.
             */
            case "Cambiar datos":
                url = "/jsp/Dentista/cambiarDatos.jsp";
                break;
            case "Asignar tratamiento":
                dentista = (Dentista) session.getAttribute("userConectado");
                pacientes = pacientePacienteDAO.obtenerPacientesDeUnDentista(dentista.getIdUsuario());
                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes asignados.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Dentista/listTratamientoPaciente.jsp";
                }

                break;
            /*
                Nos envia a la pagina donde se muestra nuestro historial
             */
            case "Ver historiales":
                dentista = (Dentista) session.getAttribute("userConectado");
                pacientes = pacientePacienteDAO.obtenerPacientesDeUnDentista(dentista.getIdUsuario());

                for (Paciente p : pacientes) {
                    historiales = historialHistorialDAO.historialDeUnPaciente(p.getIdUsuario());
                    p.setHistoriales(historiales);
                }

                if (pacientes.isEmpty()) {
                    request.setAttribute("mensaje", "No hay pacientes asignados.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("pacientes", pacientes);
                    url = "/jsp/Dentista/listHistorialesPaciente.jsp";
                }
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
                if (paciente.getTratamiento() == null) {
                    request.setAttribute("mensaje", "No hay tratamiento asignado.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("paciente", paciente);
                    url = "/jsp/Paciente/verTratamiento.jsp";
                }

                break;
            /*
                Nos envia a la pagina donde se muestra nuestro historial
             */
            case "Ver historial":
                paciente = (Paciente) session.getAttribute("userConectado");
                historiales = historialHistorialDAO.historialDeUnPaciente(paciente.getIdUsuario());
                paciente.setHistoriales(historiales);

                if (paciente.getHistoriales().isEmpty()) {
                    request.setAttribute("mensaje", "No hay historial.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    request.setAttribute("paciente", paciente);
                    url = "/jsp/Paciente/verHistorial.jsp";
                }
                break;
                /*
                Redireccionamos a la pagina donde se mostraran las opciones
                para pedir su cita.
                */
                case "Pedir cita":
                if (paciente == null) {
                    request.setAttribute("mensaje", "Ha ocurrido un error.");
                    url = "/jsp/advertencias.jsp";
                } else {
                    url = "/jsp/Paciente/pedirCita.jsp";
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
         String url = "/index.jsp";
        String acceso = "Introduce tus datos para acceder a la pagina." ;
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
