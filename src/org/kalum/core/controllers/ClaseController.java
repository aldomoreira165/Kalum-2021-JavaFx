package org.kalum.core.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Clase;
import org.kalum.core.reportes.GenerarReporte;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.*;

public class ClaseController implements Initializable {

    @FXML
    private TableView<Clase> tblClase;
    @FXML
    private TableColumn<Clase, String> colDescripcion;
    @FXML
    private TableColumn<Clase, Number> colCiclo;
    @FXML
    private TableColumn<Clase, String> colCarrera;
    @FXML
    private TableColumn<Clase, String> colHorario;
    @FXML
    private TableColumn<Clase, String> colInstructor;
    @FXML
    private TableColumn<Clase, String> colSalon;
    @FXML
    private TableColumn<Clase, String> colCupo;

    private ObservableList<Clase> listaClases;

    private Main escenarioPrincipal;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            listaClases = FXCollections.observableArrayList((List<Clase>) ConexionPU.getInstancia().findAll("Clase.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de clase", e.getMessage(), 50302);
        }
        tblClase.setItems(listaClases);
        this.colDescripcion.setCellValueFactory(cellDescripcion -> cellDescripcion.getValue().descripcion());
        this.colCiclo.setCellValueFactory(cellCiclo -> cellCiclo.getValue().ciclo());
        this.colCarrera.setCellValueFactory(cellCarrera -> cellCarrera.getValue().getCarreraTecnica().nombre());
        this.colHorario.setCellValueFactory(cellHorario -> new ReadOnlyStringWrapper(cellHorario.getValue().getHorario().getHorarioInicio() + ":"
                + cellHorario.getValue().getHorario().horarioFinal()));
        this.colInstructor.setCellValueFactory(cellInstructor ->
                new ReadOnlyStringWrapper(cellInstructor.getValue().getInstructor().getApellidos() + " " + cellInstructor.getValue().getInstructor().getNombres()));
        this.colSalon.setCellValueFactory(cellSalon -> cellSalon.getValue().getSalon().nombreSalon());
        this.colCupo.setCellValueFactory(cellCupo -> new ReadOnlyStringWrapper(cellCupo.getValue().getCupoMinimo() + "-" + cellCupo.getValue().getCupoMinimo()));

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void mostrarMenuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

    public void mostrarVentanaClaseCU() {
        this.escenarioPrincipal.mostrarEscenaClaseCU();
    }

    public void mostrarVentanaClaseCUActualizar() {
        if (this.tblClase.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Clases");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            this.escenarioPrincipal.mostrarEscenaClaseCU(this.tblClase.getSelectionModel().getSelectedItem());
        }
    }

    public void eliminarClase() {
        if (this.tblClase.getSelectionModel().getSelectedItem() == null) {
            Alert aviso = new Alert(Alert.AlertType.ERROR);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Clases");
            aviso.setContentText("Debe seleccionar un registro");
            aviso.show();
        } else {
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Kalum v1.0.0");
            aviso.setHeaderText("Administración de Clases");
            aviso.setHeaderText("¿Está seguro que desea eliminar el registro?");
            Optional<ButtonType> result = aviso.showAndWait();
            if (result.get() == ButtonType.OK) {
                int posicion = this.tblClase.getSelectionModel().getSelectedIndex();
                ConexionPU.getInstancia().eliminar(this.tblClase.getSelectionModel().getSelectedItem());
                this.listaClases.remove(posicion);
            }
        }
    }

    public void generarReporte() {
        Map parametros = new HashMap();
        GenerarReporte.getInstancia().mostrarReporte("Listado de Clases", "reporteClases.jasper",
                parametros);
    }

}
