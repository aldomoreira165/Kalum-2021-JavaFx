package org.kalum.core.controllers;

import javafx.fxml.Initializable;
import org.kalum.core.sistema.Main;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void ventanaAlumnos() {
        this.escenarioPrincipal.mostrarAlumnos();
    }

    public void ventanaInstructor() {
        this.escenarioPrincipal.mostrarInstructores();
    }

    public void ventanaCarreras() {
        this.escenarioPrincipal.mostrarCarreras();
    }

    public void mostrarSalones() {
        this.escenarioPrincipal.mostrarSalones();
    }

    public void mostrarHorarios() {
        this.escenarioPrincipal.mostrarHorarios();
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void ventanaClases() {
        this.escenarioPrincipal.mostrarEscenaClases();
    }

    public void cerrarSesion() {
        this.escenarioPrincipal.mostrarVentanaLogin();
    }

    public void ventanaContacto(){this.escenarioPrincipal.mostrarContacto();}

}

