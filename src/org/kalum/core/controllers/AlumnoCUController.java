package org.kalum.core.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.UUID;

public class AlumnoCUController implements Initializable {

    @FXML
    private TextField txtCarne;
    @FXML
    private TextField txtApellidos;
    @FXML
    private TextField txtNombres;
    @FXML
    private TextField txtExpediente;
    @FXML
    private TextField txtEmail;

    private Main escenarioPrincipal;
    private Alumno alumno;

    public void guardar() {
        try {
            if (alumno == null) {
                Alumno alumno = new Alumno();
                alumno.setCarne(UUID.randomUUID().toString());
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setNoExpediente(txtExpediente.getText());
                alumno.setEmail(txtEmail.getText());
                ConexionPU.getInstancia().agregar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de alumnos");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("Registro almacenado correctamente");
                aviso.show();
                this.escenarioPrincipal.mostrarAlumnos();
            } else {
                alumno.setApellidos(txtApellidos.getText());
                alumno.setNombres(txtNombres.getText());
                alumno.setNoExpediente(txtExpediente.getText());
                alumno.setEmail(txtEmail.getText());
                ConexionPU.getInstancia().modificar(alumno);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de alumnos");
                aviso.setHeaderText("Administración de alumnos");
                aviso.setContentText("Registro modificado correctamente.");
                aviso.show();
                this.escenarioPrincipal.mostrarAlumnos();
            }
        } catch (KalumException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de alumnos", e.getMessage(), 50301);
        }


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (alumno != null) {

        }
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarAlumnos();
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
        this.txtCarne.setEditable(false);
        this.txtCarne.setText(alumno.getCarne());
        this.txtApellidos.setText(alumno.getApellidos());
        this.txtNombres.setText(alumno.getNombres());
        this.txtExpediente.setText(alumno.getNoExpediente());
        this.txtEmail.setText(alumno.getEmail());
    }
}
