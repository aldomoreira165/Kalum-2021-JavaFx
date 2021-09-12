package org.kalum.core.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.*;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.UUID;

public class ClaseCUController implements Initializable {

    private Main escenarioPrincipal;
    private Clase clase;
    private SpinnerValueFactory<Integer> valueFactoryCiclo = new SpinnerValueFactory.IntegerSpinnerValueFactory(2020, 2040, 2021);
    private SpinnerValueFactory<Integer> valueFactoryMaximo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 1);
    private SpinnerValueFactory<Integer> valueFactoryMinimo = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);

    private ObservableList<Instructor> instructores;
    private ObservableList<Horario> horarios;
    private ObservableList<CarreraTecnica> carreras;
    private ObservableList<Salon> salones;

    @FXML
    private TextField txtDescripcion;
    @FXML
    private Spinner<Integer> numCiclo;
    @FXML
    private Spinner<Integer> numMaximo;
    @FXML
    private Spinner<Integer> numMinimo;
    @FXML
    private ComboBox<CarreraTecnica> cmbCarrera;
    @FXML
    private ComboBox<Horario> cmbHorario;
    @FXML
    private ComboBox<Instructor> cmbInstructor;
    @FXML
    private ComboBox<Salon> cmbSalon;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.numCiclo.setValueFactory(this.valueFactoryCiclo);
        this.numMaximo.setValueFactory(this.valueFactoryMaximo);
        this.numMinimo.setValueFactory(this.valueFactoryMinimo);

        try {
            this.instructores = FXCollections.observableArrayList((List<Instructor>) ConexionPU.getInstancia().findAll("Instructor.findAll"));
            this.horarios = FXCollections.observableArrayList((List<Horario>) ConexionPU.getInstancia().findAll("Horario.findAll"));
            this.carreras = FXCollections.observableArrayList((List<CarreraTecnica>) ConexionPU.getInstancia().findAll("CarreraTecnica.findAll"));
            this.salones = FXCollections.observableArrayList((List<Salon>) ConexionPU.getInstancia().findAll("Salon.findAll"));
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Clases", e.getMessage(), 50302);
        }

        this.cmbInstructor.setItems(this.instructores);
        this.cmbHorario.setItems(this.horarios);
        this.cmbCarrera.setItems(this.carreras);
        this.cmbSalon.setItems(this.salones);
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
        this.txtDescripcion.setText(clase.getDescripcion());
        this.cmbCarrera.getSelectionModel().select(clase.getCarreraTecnica());
        this.cmbHorario.getSelectionModel().select(clase.getHorario());
        this.cmbInstructor.getSelectionModel().select(clase.getInstructor());
        this.cmbSalon.getSelectionModel().select(clase.getSalon());
        this.valueFactoryMaximo.setValue(clase.getCupoMaximo());
        this.valueFactoryMinimo.setValue(clase.getCupoMinimo());
        this.valueFactoryCiclo.setValue(clase.getCiclo());
    }

    public void guardar() {
        try {
            if (this.clase == null) {
                Clase nuevaClase = new Clase();
                nuevaClase.setClaseId(UUID.randomUUID().toString());
                nuevaClase.setDescripcion(this.txtDescripcion.getText());
                nuevaClase.setCiclo(this.numCiclo.getValue());
                nuevaClase.setCupoMaximo(this.numMaximo.getValue());
                nuevaClase.setCupoMinimo(this.numMinimo.getValue());
                nuevaClase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                nuevaClase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                nuevaClase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                nuevaClase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                ConexionPU.getInstancia().agregar(nuevaClase);
            } else if (this.clase != null) {
                this.clase.setDescripcion(txtDescripcion.getText());
                this.clase.setCiclo(numCiclo.getValue());
                this.clase.setCupoMinimo(numMinimo.getValue());
                this.clase.setCupoMaximo(numMaximo.getValue());
                this.clase.setSalon(this.cmbSalon.getSelectionModel().getSelectedItem());
                this.clase.setInstructor(this.cmbInstructor.getSelectionModel().getSelectedItem());
                this.clase.setHorario(this.cmbHorario.getSelectionModel().getSelectedItem());
                this.clase.setCarreraTecnica(this.cmbCarrera.getSelectionModel().getSelectedItem());
                ConexionPU.getInstancia().modificar(this.clase);
            }
            Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
            aviso.setTitle("Administración de Clases");
            aviso.setHeaderText("Administración de Clases");
            aviso.setContentText("Registro almacenado correctamente");
            aviso.show();
            this.escenarioPrincipal.mostrarEscenaClases();
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de clases", e.getMessage(), 50301);
        }

    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarEscenaClases();
    }

}
