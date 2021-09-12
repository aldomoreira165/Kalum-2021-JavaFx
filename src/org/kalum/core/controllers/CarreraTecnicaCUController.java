package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.sistema.Main;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

import javafx.scene.control.TextField;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

public class CarreraTecnicaCUController implements Initializable {

    @FXML
    private TextField txtCodigoCarrera;
    @FXML
    private TextField txtNombre;

    private Main escenarioPrincipal;
    private CarreraTecnica carreraTecnica;

    public void guardar() {
        try {
            if (carreraTecnica == null) {
                CarreraTecnica carreraTecnica = new CarreraTecnica();
                carreraTecnica.setCodigoCarrera(UUID.randomUUID().toString());
                carreraTecnica.setNombre(txtNombre.getText());
                ConexionPU.getInstancia().agregar(carreraTecnica);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Carreras Técnicas");
                aviso.setHeaderText("Administración de Carreras Técnicas");
                aviso.setContentText("Registro almacenado correctamente");
                aviso.show();
                this.escenarioPrincipal.mostrarCarreras();
            } else {
                carreraTecnica.setNombre(txtNombre.getText());
                ConexionPU.getInstancia().modificar(carreraTecnica);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Carreras Técnicas");
                aviso.setHeaderText("Administración de Carreras Técnicas");
                aviso.setContentText("Registro modificado correctamente.");
                aviso.show();
                this.escenarioPrincipal.mostrarCarreras();
            }
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Carreras", e.getMessage(), 50301);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarCarreras();
    }

    public CarreraTecnica getCarreraTecnica() {
        return carreraTecnica;
    }

    public void setCarreraTecnica(CarreraTecnica carreraTecnica) {
        this.carreraTecnica = carreraTecnica;
        this.txtCodigoCarrera.setEditable(false);
        this.txtCodigoCarrera.setText(carreraTecnica.getCodigoCarrera());
        this.txtNombre.setText(carreraTecnica.getNombre());
    }
}
