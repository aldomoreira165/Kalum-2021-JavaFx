package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.Salon;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class SalonCUController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCapacidad;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtNombre;

    private Main escenarioPrincipal;
    private Salon salon;

    public void guardar() {
        try {
            if (salon == null) {
                Salon salon = new Salon();
                salon.setIdSalon(UUID.randomUUID().toString());
                salon.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                salon.setDescripcion(txtDescripcion.getText());
                salon.setNombreSalon(txtNombre.getText());
                ConexionPU.getInstancia().agregar(salon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Salones");
                aviso.setHeaderText("Administración de Salones");
                aviso.setContentText("Registro almacenado correctamente");
                aviso.show();
                this.escenarioPrincipal.mostrarSalones();
            } else {
                salon.setCapacidad(Integer.parseInt(txtCapacidad.getText()));
                salon.setDescripcion(txtDescripcion.getText());
                salon.setNombreSalon(txtNombre.getText());
                ConexionPU.getInstancia().modificar(salon);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Salones");
                aviso.setHeaderText("Administración de Salones");
                aviso.setContentText("Registro modificado correctamente.");
                aviso.show();
                this.escenarioPrincipal.mostrarSalones();
            }
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Horarios", e.getMessage(), 50301);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarSalones();
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
        this.txtId.setEditable(false);
        this.txtId.setText(salon.getIdSalon());
        this.txtCapacidad.setText(Integer.toString(salon.getCapacidad()));
        this.txtDescripcion.setText(salon.getDescripcion());
        this.txtNombre.setText(salon.getNombreSalon());
    }
}
