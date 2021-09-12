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
import org.kalum.core.models.Instructor;
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class InstructorController implements Initializable {

    @FXML
    private TableView<Instructor> tblInstructor;
    @FXML
    private TableColumn<Instructor, String> colIdInstructor;
    @FXML
    private TableColumn<Instructor, String> colApellidos;
    @FXML
    private TableColumn<Instructor, String> colComentario;
    @FXML
    private TableColumn<Instructor, String> colDireccion;
    @FXML
    private TableColumn<Instructor, String> colEstatus;
    @FXML
    private TableColumn<Instructor, String> colFoto;
    @FXML
    private TableColumn<Instructor, String> colNombres;
    @FXML
    private TableColumn<Instructor, String> colTelefono;

    private ObservableList<Instructor> listaInstructor;
    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            this.listaInstructor = FXCollections.observableArrayList((List<Instructor>) ConexionPU.getInstancia().findAll("Instructor.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Carreras", e.getMessage(), 50302);
        }

        this.tblInstructor.setItems(this.listaInstructor);
        this.colIdInstructor.setCellValueFactory(cellData -> cellData.getValue().idInstructor());
        this.colApellidos.setCellValueFactory(cellData -> cellData.getValue().apellidos());
        this.colComentario.setCellValueFactory(cellData -> cellData.getValue().comentario());
        this.colDireccion.setCellValueFactory(cellData -> cellData.getValue().direccion());
        this.colEstatus.setCellValueFactory(cellData -> cellData.getValue().estatus());
        this.colFoto.setCellValueFactory(cellData -> cellData.getValue().foto());
        this.colNombres.setCellValueFactory(cellData -> cellData.getValue().nombres());
        this.colTelefono.setCellValueFactory(cellData -> cellData.getValue().telefono());

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void ventanaPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarEscenaInstructoresCUView() {
        this.escenarioPrincipal.mostrarEscenaInstructoresCU();
    }

    public void mostrarVentanaInstructorUpdate() {
        if (this.tblInstructor.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Instructores");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaInstructoresCU(this.tblInstructor.getSelectionModel().getSelectedItem());
        }
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de Instructores", "ReporteInstructores.jasper",
                parametros);
    }

    public void eliminarInstructor() {
        if (this.tblInstructor.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Instructores");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setContentText("¿Está seguro de eliminar el registro?");
            aviso.setTitle("Kalum v1.0.0");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblInstructor.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblInstructor.getSelectionModel().getSelectedItem());
                this.listaInstructor.remove(posicion);
            }
        }
    }

}
