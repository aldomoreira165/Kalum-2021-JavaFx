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
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class AlumnoController implements Initializable {

    @FXML
    private TableView<Alumno> tblAlumno;
    @FXML
    private TableColumn<Alumno, String> colCarne;
    @FXML
    private TableColumn<Alumno, String> colApellidos;
    @FXML
    private TableColumn<Alumno, String> colNombres;
    @FXML
    private TableColumn<Alumno, String> colNoExpediente;
    @FXML
    private TableColumn<Alumno, String> colEmail;

    private ObservableList<Alumno> listaAlumnos;
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.listaAlumnos = FXCollections.observableArrayList((List<Alumno>) ConexionPU.getInstancia().findAll("Alumno.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Alumnos", e.getMessage(), 50301);
        }
        this.tblAlumno.setItems(this.listaAlumnos);
        this.colCarne.setCellValueFactory(cellData -> cellData.getValue().carne());
        this.colApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidos());
        this.colNombres.setCellValueFactory(cellData -> cellData.getValue().nombres());
        this.colNoExpediente.setCellValueFactory(cellData -> cellData.getValue().noExpediente());
        this.colEmail.setCellValueFactory(cellData -> cellData.getValue().email());
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void mostrarMenuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarAlumnoCUView() {
        this.escenarioPrincipal.mostrarEscenaAlumnoCU();
    }

    public void mostrarVentanaAlumnnoUpdate() {
        if (this.tblAlumno.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaAlumnoCU(this.tblAlumno.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminarAlumno() {
        if (this.tblAlumno.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de alumnos");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            aviso.setTitle("Kalum v1.0.0");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblAlumno.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblAlumno.getSelectionModel().getSelectedItem());
                this.listaAlumnos.remove(posicion);
            }
        }
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de alumnos", "ReporteAlumnos.jasper",
                parametros);
    }
}




