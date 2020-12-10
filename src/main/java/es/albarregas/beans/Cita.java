/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "citas")
public class Cita {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCita;
    
    @Column(name = "cita")
    @Temporal(TemporalType.DATE)
    private Date cita;
    
    
    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Operacion tipoOperacion;

    public Cita(int idCita, Date cita) {
        this.idCita = idCita;
        this.cita = cita;
    }

    
    public Cita(){
        
    }
    
    
    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getCita() {
        return cita;
    }

    public void setCita(Date cita) {
        this.cita = cita;
    }

    public Operacion getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(Operacion tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }
    
    
    
    
}
