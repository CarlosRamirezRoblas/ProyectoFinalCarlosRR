/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "dentistas")
@PrimaryKeyJoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "FK_dentistas_usuarios"))
public class Dentista extends Usuario implements Serializable{
    
    
    @Column(name = "horario", length = 45)
    private String horario;
    
   @OneToMany(cascade = CascadeType.ALL)//, mappedBy = "dentista")
    private List<Paciente> pacientes;

    public Dentista(String horario, int idUsuario, String email, String password, String nombre, String apellidos, Date ultimoAcceso, String rol,String dni) {
        super(idUsuario, email, password, nombre, apellidos, ultimoAcceso, rol,dni);
        this.horario = horario;
    }

    public Dentista(String horario, String email, String password, String nombre, String apellidos, String rol,String dni) {
        super(email, password, nombre, apellidos, rol,dni);
        this.horario = horario;
    }

    public Dentista(String horario) {
        this.horario = horario;
    }
    
    public Dentista(){
        
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public List<Paciente> getPacientes() {
        return pacientes;
    }

    public void setPacientes(List<Paciente> pacientes) {
        this.pacientes = pacientes;
    }
    
    
    
}
