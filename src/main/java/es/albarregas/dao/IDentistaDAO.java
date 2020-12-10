/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import java.util.List;

/**
 *
 * @author Carlos
 */
public interface IDentistaDAO<Dentista> {
    
     public List<Dentista> dentistaSinPaciente ();
    
}
