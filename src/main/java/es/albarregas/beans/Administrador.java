/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "administradores")
@PrimaryKeyJoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "FK_administrador_usuarios"))
public class Administrador extends Usuario implements Serializable {
    
    @Column(name="modoDios", length=1, columnDefinition="CHAR")
    private String modoDios;

    public Administrador(String modoDios, int idUsuario, String email, String password, String nombre, String apellidos, Date ultimoAcceso, String rol,String dni) {
        super(idUsuario, email, password, nombre, apellidos, ultimoAcceso,rol,dni);
        this.modoDios = modoDios;
    }

    public Administrador(String modoDios, String email, String password, String nombre, String apellidos, String rol,String dni) {
        super(email, password, nombre, apellidos,rol,dni);
        this.modoDios = modoDios;
    }

    public Administrador(String modoDios) {
        this.modoDios = modoDios;
    }

    public Administrador(){
        
    }
    

    public String getModoDios() {
        return modoDios;
    }

    public void setModoDios(String modoDios) {
        this.modoDios = modoDios;
    }
    
    
    
}
