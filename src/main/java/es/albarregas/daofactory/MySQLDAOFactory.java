package es.albarregas.daofactory;

import es.albarregas.dao.DentistaDAO;
import es.albarregas.dao.GenericoDAO;
import es.albarregas.dao.IDentistaDAO;
import es.albarregas.dao.IGenericoDAO;
import es.albarregas.dao.IPacienteDAO;
import es.albarregas.dao.IUsuarioDAO;
import es.albarregas.dao.PacienteDAO;
import es.albarregas.dao.UsuarioDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IGenericoDAO getGenericDAO() {
        return new GenericoDAO();
    }

    @Override
    public IUsuarioDAO getUsuarioDAO() {
        return new UsuarioDAO();
    }

    @Override
    public IPacienteDAO getPacienteDAO() {
        return new PacienteDAO();
    }

    @Override
    public IDentistaDAO getDentistaDAO() {
        return new DentistaDAO();
    }

}
