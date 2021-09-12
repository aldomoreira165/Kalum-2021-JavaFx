package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.CarreraTecnica;
import org.kalum.core.models.Instructor;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class InstructorCUController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtComentario;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEstatus;
    @FXML
    private TextField txtFoto;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;

    private Main escenarioPrincipal;
    private Instructor instructor;

    public void guardar() {
        try {
            if (instructor == null) {
                Instructor instructor = new Instructor();
                instructor.setIdInstructor(UUID.randomUUID().toString());
                instructor.setApellidos(txtApellidos.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setEstatus(txtEstatus.getText());
                instructor.setFoto(txtFoto.getText());
                instructor.setNombres(txtNombre.getText());
                instructor.setTelefono(txtTelefono.getText());
                ConexionPU.getInstancia().agregar(instructor);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Instructores");
                aviso.setHeaderText("Administración de Instructores");
                aviso.setContentText("Registro almacenado correctamente");
                aviso.show();
                this.escenarioPrincipal.mostrarInstructores();
            } else {
                instructor.setApellidos(txtApellidos.getText());
                instructor.setComentario(txtComentario.getText());
                instructor.setDireccion(txtDireccion.getText());
                instructor.setEstatus(txtEstatus.getText());
                instructor.setFoto(txtFoto.getText());
                instructor.setNombres(txtNombre.getText());
                instructor.setTelefono(txtTelefono.getText());
                ConexionPU.getInstancia().modificar(instructor);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Instructores");
                aviso.setHeaderText("Administración de Instructores");
                aviso.setContentText("Registro modificado correctamente.");
                aviso.show();
                this.escenarioPrincipal.mostrarInstructores();
            }
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de Instructores", e.getMessage(), 50301);
        }

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarInstructores();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
        this.txtId.setEditable(false);
        this.txtId.setText(instructor.getIdInstructor());
        this.txtApellidos.setText(instructor.getApellidos());
        this.txtComentario.setText(instructor.getComentario());
        this.txtDireccion.setText(instructor.getDireccion());
        this.txtEstatus.setText(instructor.getEstatus());
        this.txtFoto.setText(instructor.getFoto());
        this.txtNombre.setText(instructor.getNombres());
        this.txtTelefono.setText(instructor.getTelefono());
    }
}
