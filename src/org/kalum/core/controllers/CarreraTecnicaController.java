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
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Horario;
import org.kalum.core.models.Instructor;
import org.kalum.core.models.Salon;
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class CarreraTecnicaController implements Initializable {

    @FXML
    private TableView<CarreraTecnica> tblCarreraTecnica;
    @FXML
    private TableColumn<CarreraTecnica, String> colIdCarrera;
    @FXML
    private TableColumn<CarreraTecnica, String> colNombre;
    private Main escenarioPrincipal;

    private ObservableList<CarreraTecnica> listaCarreras;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.listaCarreras = FXCollections.observableArrayList((List<CarreraTecnica>) ConexionPU.getInstancia().findAll("CarreraTecnica.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Carreras", e.getMessage(), 50302);
        }
        this.tblCarreraTecnica.setItems(this.listaCarreras);
        this.colIdCarrera.setCellValueFactory(cellData -> cellData.getValue().codigoCarrera());
        this.colNombre.setCellValueFactory(cellData -> cellData.getValue().nombre());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void eliminarCarrera() {
        if (this.tblCarreraTecnica.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Carreras Técnicas");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            aviso.setTitle("Kalum v1.0.0");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblCarreraTecnica.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblCarreraTecnica.getSelectionModel().getSelectedItem());
                this.listaCarreras.remove(posicion);
            }
        }
    }

    public void mostrarVentanaCarreraUpdate() {
        if (this.tblCarreraTecnica.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Carreras Técnicas");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaCarreraTecnicaCU(this.tblCarreraTecnica.getSelectionModel().getSelectedItem());
        }
    }

    public void mostrarVentanaCarreraCU() {
        this.escenarioPrincipal.mostrarEscenaCarreraTecnicaCU();
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de Carrera Técnica", "ReporteCarreraTecnica.jasper",
                parametros);
    }

}
