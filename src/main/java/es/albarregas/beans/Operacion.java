/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "operaciones")
public class Operacion implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOperacion;
    
    @Column(length = 3)
    private int revision;
    
    @Column(length = 3)
    private int ortodoncia; 
    
    @Column(length = 3)
    private int extraccion;
    
    @Column(length = 3)
    private int endodoncia;
    
    @Column(length = 3)
    private int limpiezaDental;

    public Operacion(int idOperacion, int revision, int ortodoncia, int extraccion, int endodoncia, int limpiezaDental) {
        this.idOperacion = idOperacion;
        this.revision = revision;
        this.ortodoncia = ortodoncia;
        this.extraccion = extraccion;
        this.endodoncia = endodoncia;
        this.limpiezaDental = limpiezaDental;
    }
    
    public Operacion( int revision, int ortodoncia, int extraccion, int endodoncia, int limpiezaDental) {
        this.revision = revision;
        this.ortodoncia = ortodoncia;
        this.extraccion = extraccion;
        this.endodoncia = endodoncia;
        this.limpiezaDental = limpiezaDental;
    }
    
    public Operacion(){
        
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public int getOrtodoncia() {
        return ortodoncia;
    }

    public void setOrtodoncia(int ortodoncia) {
        this.ortodoncia = ortodoncia;
    }

    public int getExtraccion() {
        return extraccion;
    }

    public void setExtraccion(int extraccion) {
        this.extraccion = extraccion;
    }

    public int getEndodoncia() {
        return endodoncia;
    }

    public void setEndodoncia(int endodoncia) {
        this.endodoncia = endodoncia;
    }

    public int getLimpiezaDental() {
        return limpiezaDental;
    }

    public void setLimpiezaDental(int limpiezaDental) {
        this.limpiezaDental = limpiezaDental;
    }
    
    
    
    
}
