package es.albarregas.daofactory;

import es.albarregas.dao.IDentistaDAO;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IHistorialDAO;
import es.albarregas.dao.IPacienteDAO;
import es.albarregas.dao.IUsuarioDAO;

public abstract class DAOFactory {

    public abstract IGenericoDAO getGenericDAO();

    public abstract IUsuarioDAO getUsuarioDAO();

    public abstract IPacienteDAO getPacienteDAO();

    public abstract IDentistaDAO getDentistaDAO();
    
    public abstract IHistorialDAO getHistorialDAO();

    public static DAOFactory getDAOFactory() {
        DAOFactory daof = null;

        daof = new MySQLDAOFactory();

        return daof;
    }

}
