/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Paciente;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class PacienteDAO extends GenericoDAO implements IPacienteDAO<Paciente> {

    @Override
    public List<Paciente> obtenerPacientesDeUnDentista(int id) {
        List<Paciente> listadoResultados = null;
        try {
            startTransaction();
            Query query = sesion.createQuery("SELECT a from Paciente as a where a.dentista.idUsuario = :id ");
            query.setParameter("id", id);
            listadoResultados = query.list();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }
    

}
