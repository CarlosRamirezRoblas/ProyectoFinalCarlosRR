/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

/**
 *
 * @author Carlos
 */
public interface IUsuarioDAO<Usuario> {

    public Usuario login(Usuario usuario);
    
    public Usuario checkEmail(String email);
    
    public Usuario checkDNI(String dni);
}
