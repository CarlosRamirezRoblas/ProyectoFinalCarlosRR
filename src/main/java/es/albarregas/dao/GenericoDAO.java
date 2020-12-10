package es.albarregas.dao;



import es.albarregas.persistencia.HibernateUtil;

import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.resource.transaction.spi.TransactionStatus;


public class GenericoDAO<T> implements IGenericoDAO<T> {
    
    public Session sesion;
    
    public void startTransaction(){
        sesion = HibernateUtil.getSessionFactory().openSession();
        sesion.getTransaction().begin();
    }
    
    public void endTransaction(){
        if (sesion.getTransaction().getStatus().equals(TransactionStatus.ACTIVE)) {
            sesion.getTransaction().commit();
        }
        sesion.close();
    }
    
    public void handleExcepcion(HibernateException he) throws HibernateException {
        if (sesion.getTransaction().isActive()) {
            sesion.getTransaction().rollback();
        }
        throw he;
    } 

    @Override
    public void insertOrUpdate(T objeto) {
        try {
            startTransaction();
            sesion.saveOrUpdate(objeto);
            sesion.flush();
            
        } catch (HibernateException he){
            handleExcepcion(he);
        } finally {
            endTransaction();
        }
    }

    @Override
    public <T> List<T> get(Class<T> claseEntidad) {
        List<T> listadoResultados = null;
        try {
            startTransaction();
            listadoResultados = sesion.createQuery(" from " + claseEntidad.getSimpleName()).list();
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        return listadoResultados;
    }

    @Override
    public <T> T getById(Serializable pk, Class<T> claseEntidad) {
        T objetoRecuperado = null;
        
        try {
            startTransaction();
            objetoRecuperado = sesion.get(claseEntidad, pk);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
        
        return objetoRecuperado;
    }

    @Override
    public void delete(T objeto) {
        try {
            startTransaction();
            sesion.delete(objeto);
        } catch(HibernateException he){
            this.handleExcepcion(he);
        } finally {
            this.endTransaction();
        }
    }

    
 }
