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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "usuarios", uniqueConstraints = {
    @UniqueConstraint(columnNames = "email", name = "UK_usuarios_email")})
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;
    @Column(name = "email", length = 60)
    private String email;
    @Column(name = "password", length = 128)
    private String password;
    @Column(name = "nombre",  length = 45)
    private String nombre;
    @Column(name = "apellidos", length = 45)
    private String apellidos;
    @Column(name = "ultimoAcceso")
    @Temporal(TemporalType.DATE)
    private Date ultimoAcceso;
    @Column(name = "rol", length = 6)
    private String rol;
    @Column(name = "dni", length = 60)
    private String dni;
    

    public Usuario(int idUsuario, String email, String password, String nombre, String apellidos, Date ultimoAcceso,String rol, String dni) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ultimoAcceso = ultimoAcceso;
        this.rol = rol;
        this.dni = dni;
    }
    public Usuario( String email, String password, String nombre, String apellidos,String rol,String dni) {
        
        this.email = email;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.rol = rol;
        this.dni = dni;

    }

    public Usuario() {

    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(Date ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    
    
}
