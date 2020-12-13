/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controllers;

import es.albarregas.beans.Cita;
import es.albarregas.beans.Dentista;
import es.albarregas.beans.Historial;
import es.albarregas.beans.Operacion;
import es.albarregas.beans.Paciente;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IHistorialDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Carlos
 */
@MultipartConfig
@WebServlet(name = "Operaciones", urlPatterns = {"/Operaciones"})
public class Operaciones extends HttpServlet {

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
        String pass = null;
        String cap = null;
        String letraCapitalizada = null;
        HttpSession session = request.getSession();
        DAOFactory daof = DAOFactory.getDAOFactory();
        IGenericoDAO<Dentista> dentistaGenericDAO = daof.getGenericDAO();
        IGenericoDAO<Paciente> pacienteGenericDAO = daof.getGenericDAO();
        IHistorialDAO<Historial> historialHistorialDAO = daof.getHistorialDAO();
        List<Historial> historiales = new ArrayList();
        IGenericoDAO<Operacion> operacionGenericoDAO = daof.getGenericDAO();
        Date actual = new Date();
        Paciente paciente = new Paciente();
        Dentista dentista = new Dentista();
        Historial historial = new Historial();
        Operacion operacion = new Operacion();
        Cita cita = new Cita();


        /*
        Empezamos con la parte de administrador
         */
        switch (request.getParameter("enviar")) {
            /*
            Creamos un dentista con con la contraseña encriptada con md5,
            el email del dentista sera el introducido por el administrador en el formulario.
            Por ultimo mostramos un mensaje y volvemos al menu de administrador.
             */
            case "Crear dentista":
                String digest = DigestUtils.md5Hex(request.getParameter("password"));
                dentista.setPassword(digest.toUpperCase());
                dentista.setEmail(request.getParameter("email"));
                dentista.setRol("DENTISTA");
                dentistaGenericDAO.insertOrUpdate(dentista);
                request.setAttribute("mensaje", "Se ha insertado correctamente un nuevo dentista.");
                url = "/jsp/Administrador/menuAd.jsp";
                break;

            /*
                Borramos el dentista que el administrador haya seleccionado en el formulario anterior.
                Mostramos un mensaje y volvemos al menu de administrador.
             */
            case "Borrar dentista":
                dentista.setIdUsuario(Integer.parseInt(request.getParameter("confirmar")));
                dentistaGenericDAO.delete(dentista);

                request.setAttribute("mensaje", "Se ha eliminado correctamente el dentista.");
                url = "/jsp/Administrador/menuAd.jsp";
                break;
            /*
                Obtenemos el dentista seleccionado previamente, lo asignamos a un peticion y
                avanzamos a la pagina de confirmacion donde mostramos que dentista se eliminara.
             */
            case "Eliminar dentista":
                dentista = dentistaGenericDAO.getById(Integer.parseInt(request.getParameter("dentista")), Dentista.class);
                request.setAttribute("dentista", dentista);
                url = "/jsp/Administrador/confirmarEliminarDentista.jsp";
                break;
            /*
                Obtenemos el paciente seleccionado previamente, lo asignamos a un peticion y
                avanzamos a la pagina de confirmacion donde mostramos que paciente se eliminara.
             */

            case "Eliminar paciente":
                paciente = pacienteGenericDAO.getById(Integer.parseInt(request.getParameter("paciente")), Paciente.class);
                request.setAttribute("paciente", paciente);
                url = "/jsp/Administrador/confirmarBorrarPaciente.jsp";
                break;
            /*
                Borramos el paciente seleccionado en el formulario anterior.
                Mostramos un mensaje y volvemos al menu de dentista.
             */
            case "Borrar paciente":
                paciente.setIdUsuario(Integer.parseInt(request.getParameter("confirmar")));
                historialHistorialDAO.borrarHistorialPaciente(paciente.getIdUsuario());
                pacienteGenericDAO.delete(paciente);
                request.setAttribute("mensaje", "Se ha eliminado correctamente el paciente.");
                url = "/jsp/Administrador/menuAd.jsp";
                break;

            /*
         A partir de aqui opciones de los dentistas.
             */
            /*
                Asigamos un el paciente previamente seleccionado al dentista que esta
                en sesion actualemte.
             */
            case "Asignar":
                paciente = pacienteGenericDAO.getById(Integer.parseInt(request.getParameter("paciente")), Paciente.class);
                dentista = (Dentista) session.getAttribute("userConectado");
                paciente.setDentista(dentista);
                pacienteGenericDAO.insertOrUpdate(paciente);
                request.setAttribute("mensaje", "Asignado paciente " + paciente.getNombre() + " correctamente.");
                url = "/jsp/Dentista/menuDentista.jsp";
                break;
            /*
                Desasignamos el paciente previamente seleccionado al dentista que esta
                en sesion actualmente.
             */
            case "Desasignar":
                paciente = pacienteGenericDAO.getById(Integer.parseInt(request.getParameter("paciente")), Paciente.class);
                paciente.setDentista(null);
                pacienteGenericDAO.insertOrUpdate(paciente);
                request.setAttribute("mensaje", "Desasignado paciente " + paciente.getNombre() + " correctamente.");
                url = "/jsp/Dentista/menuDentista.jsp";
                break;
            /*
                Asignamos tratamiento al paciente que el dentista haya seleccionado.
                Si es el primer tratamiento no hace nada mas. En caso contrario,
                guarda el tratamiento anterior y su fecha en el historial.
             */
            case "Asignar tratamiento":
                paciente = pacienteGenericDAO.getById(Integer.parseInt(request.getParameter("paciente")), Paciente.class);
                if (paciente.getTratamiento() != null) {
                    historial.setFechaHistorial(actual);
                    historial.setDescripcion(paciente.getTratamiento());
                    historial.setPaciente(paciente);
                    historiales.add(historial);
                    paciente.setHistoriales(historiales);
                }
                paciente.setTratamiento(request.getParameter("tratamiento"));
                pacienteGenericDAO.insertOrUpdate(paciente);
                request.setAttribute("mensaje", "Tratamiento asignado al paciente " + paciente.getNombre() + " correctamente.");
                url = "/jsp/Dentista/menuDentista.jsp";
                break;
            /*
                Cambiamos los datos de dentista, si es la primera vez que ingresa
                tiene que actualizar sus datos.Capitalizamos tanto el nombre como el apellido al guardar.
                Luego preguntamos por la contraseña, si es la misma que esta guardada,
                no la encriptamos ya que se volveria a encriptar y  cambiaria la contraseña,
                si es nueva la encriptamos y asignamos a dentista.
                Asignamos el resto de campos y renovamos el usuario dentista de la sesion con los 
                datos actualizados, guardamos los datos en la base de datos.
                Mostramos un mensaje y volvemos al menu de dentista.
             */
            case "Cambiar datos":
                dentista = (Dentista) session.getAttribute("userConectado");
                if (dentista.getUltimoAcceso() == null) {
                    dentista.setUltimoAcceso(actual);
                }
                if (dentista.getPassword().equals(request.getParameter("password"))) {
                    pass = "correcta";
                }
                try {
                    BeanUtils.populate(dentista, request.getParameterMap());
                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                cap = dentista.getNombre();
                letraCapitalizada = cap.substring(0, 1).toUpperCase();
                dentista.setNombre(letraCapitalizada + cap.substring(1));
                cap = dentista.getApellidos();
                letraCapitalizada = cap.substring(0, 1).toUpperCase();
                dentista.setApellidos(letraCapitalizada + cap.substring(1));

                if (pass == null) {
                    pass = DigestUtils.md5Hex(request.getParameter("password"));
                    dentista.setPassword(pass.toUpperCase());
                }
                session.removeAttribute("userConectado");
                session.setAttribute("userConectado", dentista);

                dentistaGenericDAO.insertOrUpdate(dentista);

                request.setAttribute("mensaje", "Se han modificado correctamente tus datos.");
                url = "/jsp/Dentista/menuDentista.jsp";
                break;
            /*
        Estas son las opciones de alumnos.
             */
 /*
                Cambiamos los datos de paciente, si es la primera vez que ingresa
                tiene que actualizar sus datos. Capitalizamos tanto el nombre como el apellido.
                Luego preguntamos por la contraseña, si es la misma que esta guardada,
                no la encriptamos ya que se volveria a encriptar y  cambiaria la contraseña,
                si es nueva la encriptamos y asignamos a paciente.
                Asignamos el resto de campos y renovamos el usuario paciente de la sesion con los 
                datos actualizados, guardamos los datos en la base de datos.
                Mostramos un mensaje y volvemos al menu de paciente.
             */
            case "Guardar":
                paciente = (Paciente) session.getAttribute("userConectado");
                if (paciente.getUltimoAcceso() == null) {
                    paciente.setUltimoAcceso(actual);
                }
                if (paciente.getPassword().equals(request.getParameter("password"))) {
                    pass = "correcta";
                }
                try {
                    DateConverter converter = new DateConverter();
                    converter.setPattern("yyyy-MM-dd");
                    ConvertUtils.register(converter, Date.class);

                    BeanUtils.populate(paciente, request.getParameterMap());

                } catch (IllegalAccessException | InvocationTargetException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                cap = paciente.getNombre();
                letraCapitalizada = cap.substring(0, 1).toUpperCase();
                paciente.setNombre(letraCapitalizada + cap.substring(1));
                cap = paciente.getApellidos();
                letraCapitalizada = cap.substring(0, 1).toUpperCase();
                paciente.setApellidos(letraCapitalizada + cap.substring(1));
                if (pass == null) {
                    pass = DigestUtils.md5Hex(request.getParameter("password"));
                    paciente.setPassword(pass.toUpperCase());
                }

                session.removeAttribute("userConectado");
                session.setAttribute("userConectado", paciente);

                pacienteGenericDAO.insertOrUpdate(paciente);

                request.setAttribute("mensaje", "Se han modificado correctamente tus datos.");
                url = "/jsp/Paciente/menuPaciente.jsp";

                break;
                /*
                Obtiene la fecha seleccionada previamente, la asgina al paciente
                que este en sesion, lo añade a la base de datos y actualiza sesion.
                */
            case "Confirmar cita":
                operacion = operacionGenericoDAO.getById(1, Operacion.class);
                String fecha = request.getParameter("cita");
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                } catch (ParseException ex) {
                    Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
                }
                cita.setCita(date);
                cita.setTipoOperacion(operacion);
                paciente = (Paciente) session.getAttribute("userConectado");
                paciente.setCita(cita);
                session.setAttribute("userConectado", paciente);
                pacienteGenericDAO.insertOrUpdate(paciente);

                url = "/jsp/Paciente/menuPaciente.jsp";
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
