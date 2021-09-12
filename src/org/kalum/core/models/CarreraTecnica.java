package org.kalum.core.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "carrera_tecnica")
@NamedQueries({@NamedQuery(name = "CarreraTecnica.findAll", query = "from CarreraTecnica")})

public class CarreraTecnica implements Serializable {

    private final StringProperty codigoCarrera;
    private final StringProperty nombre;
    private List<Clase> clases;

    public CarreraTecnica() {
        this.codigoCarrera = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
    }

    @Override
    public String toString() {
        return this.getNombre();
    }

    @Id
    @Column(name = "codigo_carrera")
    public String getCodigoCarrera() {
        return this.codigoCarrera.get();
    }

    public void setCodigoCarrera(String codigoCarrera) {
        this.codigoCarrera.set(codigoCarrera);
    }

    public StringProperty codigoCarrera() {
        return this.codigoCarrera;
    }

    @Column(name = "nombre")
    public String getNombre() {
        return this.nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombre() {
        return this.nombre;
    }

    @OneToMany(mappedBy = "carreraTecnica", fetch = FetchType.LAZY)
    public List<Clase> getClases() {
        return clases;
    }

    public void setClases(List<Clase> clases) {
        this.clases = clases;
    }
}
