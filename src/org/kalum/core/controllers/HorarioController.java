package org.kalum.core.controllers;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableObjectValue;
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
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class HorarioController implements Initializable {


    @FXML
    private TableView<Horario> tblHorario;
    @FXML
    private TableColumn<Horario, String> colIdHorario;
    @FXML
    private TableColumn<Horario, Date> colHorarioInicio;
    @FXML
    private TableColumn<Horario, Date> colHorarioFinal;
    private Main escenarioPrincipal;

    private ObservableList<Horario> listaHorario;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        try {
            this.listaHorario = FXCollections.observableArrayList((List<Horario>) ConexionPU.getInstancia().findAll("Horario.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Carreras", e.getMessage(), 50302);
        }
        this.tblHorario.setItems(this.listaHorario);
        this.colIdHorario.setCellValueFactory(cellData -> cellData.getValue().idHorario());
        this.colHorarioInicio.setCellValueFactory(cellData -> cellData.getValue().horarioInicio());
        this.colHorarioFinal.setCellValueFactory(cellData -> cellData.getValue().horarioFinal());

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarHorarioCUView() {
        this.escenarioPrincipal.mostrarEscenaHorarioCU();
    }

    public void eliminarHorario() {
        if (this.tblHorario.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de horarios");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            aviso.setTitle("Kalum v1.0.0");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblHorario.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblHorario.getSelectionModel().getSelectedItem());
                this.listaHorario.remove(posicion);
            }
        }
    }

    public void mostrarVentanaHorarioUpdate() {
        if (this.tblHorario.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de horarios");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaHorarioCU(this.tblHorario.getSelectionModel().getSelectedItem());
        }
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de Horarios", "ReporteHorarios.jasper",
                parametros);
    }

}
