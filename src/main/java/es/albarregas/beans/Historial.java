/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "historiales")
public class Historial {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHistorial;
    
    @Column(length = 150)
    private String descripcion;
    
    @Column(name = "fechaHistorial")
    @Temporal(TemporalType.DATE)
    private Date fechaHistorial;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "paciente", foreignKey = @ForeignKey(name = "FK_historial_paciente"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Paciente paciente;

    public Historial(int idHistorial, String descripcion, Date fechaHistorial, Paciente paciente) {
        this.idHistorial = idHistorial;
        this.descripcion = descripcion;
        this.fechaHistorial = fechaHistorial;
        this.paciente = paciente;
    }

    public Historial(){
        
    }

    public int getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(int idHistorial) {
        this.idHistorial = idHistorial;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaHistorial() {
        return fechaHistorial;
    }

    public void setFechaHistorial(Date fechaHistorial) {
        this.fechaHistorial = fechaHistorial;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    
    
}
