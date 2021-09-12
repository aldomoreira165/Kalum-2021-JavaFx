package org.kalum.core.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "salon")
@NamedQueries({@NamedQuery(name = "Salon.findAll", query = "from Salon")})

public class Salon implements Serializable {

    private final StringProperty idSalon;
    private final IntegerProperty capacidad;
    private final StringProperty descripcion;
    private final StringProperty nombreSalon;
    private List<Clase> clases;

    public Salon() {
        this.idSalon = new SimpleStringProperty();
        this.capacidad = new SimpleIntegerProperty();
        this.descripcion = new SimpleStringProperty();
        this.nombreSalon = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return this.getNombreSalon();
    }

    @Id
    @Column(name = "salon_id")
    public String getIdSalon() {
        return this.idSalon.get();
    }

    public void setIdSalon(String idSalon) {
        this.idSalon.set(idSalon);
    }

    public StringProperty idSalon() {
        return this.idSalon;
    }

    @Id
    @Column(name = "capacidad")
    public void setCapacidad(Integer capacidad) {
        this.capacidad.set(capacidad);
    }

    public Integer getCapacidad() {
        return this.capacidad.get();
    }

    public IntegerProperty capacidad() {
        return this.capacidad;
    }

    @Id
    @Column(name = "descripcion")
    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }

    public String getDescripcion() {
        return this.descripcion.get();
    }

    public StringProperty descripcion() {
        return this.descripcion;
    }

    @Id
    @Column(name = "nombre_salon")
    public void setNombreSalon(String nombreSalon) {
        this.nombreSalon.set(nombreSalon);
    }

    public String getNombreSalon() {
        return this.nombreSalon.get();
    }

    public StringProperty nombreSalon() {
        return this.nombreSalon;
    }

    @OneToMany(mappedBy = "salon", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
