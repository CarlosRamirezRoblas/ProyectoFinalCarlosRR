/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuario;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;

/**
 *
 * @author Carlos
 */
public class UsuarioDAO extends GenericoDAO implements IUsuarioDAO<Usuario> {


    @Override
    public Usuario login(Usuario user) {

        Usuario usuario = null;
        try {
            startTransaction();
            Query query = sesion.createQuery("SELECT u from Usuario as u where u.email = :email and u.password = :password");
            query.setParameter("email", user.getEmail());
            query.setParameter("password", user.getPassword());
            usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return usuario;
    }

    @Override
    public Usuario checkEmail(String email) {
       Usuario usuario = null;
        try {
            startTransaction();
            Query query = sesion.createQuery("SELECT u from Usuario as u where u.email = :email");
            query.setParameter("email", email);
            usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return usuario;
    }
    
    @Override
    public Usuario checkDNI(String dni) {
       Usuario usuario = null;
        try {
            startTransaction();
            Query query = sesion.createQuery("SELECT u from Usuario as u where u.dni = :dni");
            query.setParameter("dni", dni);
            usuario = (Usuario) query.uniqueResult();
        } catch (HibernateException he) {
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return usuario;
    }

}
