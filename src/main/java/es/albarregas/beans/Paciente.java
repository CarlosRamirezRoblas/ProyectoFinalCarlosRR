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
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import org.hibernate.annotations.Cascade;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "pacientes")
@PrimaryKeyJoinColumn(name = "idUsuario", foreignKey = @ForeignKey(name = "FK_paciente_usuarios"))
public class Paciente extends Usuario implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentista", foreignKey = @ForeignKey(name = "FK_paciente_dentista"))
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Dentista dentista;

    @Column(length = 150)
    private String tratamiento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private List<Historial> historiales;

    @OneToOne(cascade = {CascadeType.ALL})
    @PrimaryKeyJoinColumn
    private Cita cita;

    public Paciente(Dentista dentista, String tratamiento, List<Historial> historiales,Cita cita, int idUsuario, String email, String password, String nombre, String apellidos, Date ultimoAcceso, String rol,String dni) {
        super(idUsuario, email, password, nombre, apellidos, ultimoAcceso, rol,dni);
        this.dentista = dentista;
        this.tratamiento = tratamiento;
        this.historiales = historiales;
        this.cita = cita;
    }

    public Paciente(Dentista dentista, String tratamiento, List<Historial> historiales, Cita cita) {
        this.dentista = dentista;
        this.tratamiento = tratamiento;
        this.historiales = historiales;
        this.cita = cita;
    }

    public Paciente() {
    }

    public Dentista getDentista() {
        return dentista;
    }

    public void setDentista(Dentista dentista) {
        this.dentista = dentista;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public List<Historial> getHistoriales() {
        return historiales;
    }

    public void setHistoriales(List<Historial> historiales) {
        this.historiales = historiales;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    
    
}
