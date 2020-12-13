/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Historial;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class HistorialDAO extends GenericoDAO implements IHistorialDAO<Historial> {

    @Override
    public List<Historial> historialDeUnPaciente(int id) {
       List<Historial> listadoResultados = null;
        try {
            startTransaction();
            Query query = sesion.createQuery("SELECT h from Historial as h where h.paciente.idUsuario = :id ");
            query.setParameter("id", id);
            listadoResultados = query.list();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public void borrarHistorialPaciente(int id) {
         try {
            startTransaction();
            Query query = sesion.createQuery("DELETE FROM Historial as h where h.paciente.idUsuario = :id ");
            query.setParameter("id", id);
            int filasEliminadas = query.executeUpdate();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }
}
