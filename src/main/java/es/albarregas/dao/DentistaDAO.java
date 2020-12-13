/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Dentista;
import java.util.List;
import org.hibernate.HibernateException;


/**
 *
 * @author Carlos
 */
public class DentistaDAO extends GenericoDAO implements IDentistaDAO<Dentista> {

    @Override
    public List<Dentista> dentistaSinPaciente() {
        List<Dentista> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery("SELECT t from Dentista as t where t.idUsuario not in (select p.dentista from Paciente as p) ").list();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }

        return listadoResultados;
    }
}
