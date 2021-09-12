package org.kalum.core.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "clase")
@NamedQueries({@NamedQuery(name = "Clase.findAll", query = "from Clase")})
public class Clase implements Serializable {
    private final StringProperty claseId;
    private final StringProperty descripcion;
    private final IntegerProperty ciclo;
    private final IntegerProperty cupoMaximo;
    private final IntegerProperty cupoMinimo;
    private Horario horario;
    private Instructor instructor;
    private Salon salon;
    private CarreraTecnica carreraTecnica;

    public Clase() {
        this.claseId = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.ciclo = new SimpleIntegerProperty();
        this.cupoMaximo = new SimpleIntegerProperty();
        this.cupoMinimo = new SimpleIntegerProperty();
    }

    @Id
    @Column(name = "clase_id")
    public String getClaseId() {
        return claseId.get();
    }

    public StringProperty claseId() {
        return claseId;
    }

    public void setClaseId(String claseId) {
        this.claseId.set(claseId);
    }

    @Column(name = "descripcion")
    public String getDescripcion() {
        return descripcion.get();
    }

    public StringProperty descripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    @Column(name = "ciclo")
    public int getCiclo() {
        return ciclo.get();
    }

    public IntegerProperty ciclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo.set(ciclo);
    }

    @Column(name = "cupo_maximo")
    public int getCupoMaximo() {
        return cupoMaximo.get();
    }

    public IntegerProperty cupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo.set(cupoMaximo);
    }

    @Column(name = "cupo_minimo")
    public int getCupoMinimo() {
        return cupoMinimo.get();
    }

    public IntegerProperty cupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo.set(cupoMinimo);
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "horario_id")
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "salon_id")
    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_carrera")
    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
    }


}






