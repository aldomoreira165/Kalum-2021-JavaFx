package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.Clase;
import org.kalum.core.models.Salon;
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class SalonController implements Initializable {

    @FXML
    private TableView<Salon> tblSalon;
    @FXML
    private TableColumn<Salon, String> colIDSalon;
    @FXML
    private TableColumn<Salon, Number> colCapacidad;
    @FXML
    private TableColumn<Salon, String> colDescripcion;
    @FXML
    private TableColumn<Salon, String> colNombreSalon;
    private Main escenarioPrincipal;

    private ObservableList<Salon> listaSalon;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.listaSalon = FXCollections.observableArrayList((List<Salon>) ConexionPU.getInstancia().findAll("Salon.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de clase", e.getMessage(), 50302);
        }
        this.tblSalon.setItems(this.listaSalon);
        this.colIDSalon.setCellValueFactory(cellData -> cellData.getValue().idSalon());
        this.colCapacidad.setCellValueFactory(cellData -> cellData.getValue().capacidad());
        this.colDescripcion.setCellValueFactory(cellData -> cellData.getValue().descripcion());
        this.colNombreSalon.setCellValueFactory(cellData -> cellData.getValue().nombreSalon());

    }

    public void eliminarSalon() {
        if (this.tblSalon.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Salones");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            aviso.setTitle("Kalum v1.0.0");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblSalon.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblSalon.getSelectionModel().getSelectedItem());
                this.listaSalon.remove(posicion);
            }
        }
    }

    public void mostrarVentanaSalonUpdate() {
        if (this.tblSalon.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Salones");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaSalonesCU(this.tblSalon.getSelectionModel().getSelectedItem());
        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarSalonCUView() {
        this.escenarioPrincipal.mostrarEscenaSalonesCU();
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de Salones", "ReporteSalones.jasper",
                parametros);
    }

}
