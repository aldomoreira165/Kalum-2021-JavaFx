package org.kalum.core.controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.kalum.core.db.ConexionPU;
import org.kalum.core.models.Alumno;
import org.kalum.core.models.Horario;
import org.kalum.core.sistema.Main;
import org.kalum.core.utils.KalumException;
import org.kalum.core.utils.KalumViewMessage;

import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class HorarioCUController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private ComboBox<String> cmbInicio;
    @FXML
    private ComboBox<String> cmbFinal;
    private Main escenarioPrincipal;
    private Horario horario;

    Date date1 = null;
    Date date2 = null;

    public void guardar() throws ParseException {
        try{
            TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
            DateFormat formato = new SimpleDateFormat("HH:mm");
            date1 = (Date)formato.parse(String.valueOf(cmbInicio.getSelectionModel().getSelectedItem()));
            date2 = (Date)formato.parse(String.valueOf(cmbFinal.getSelectionModel().getSelectedItem()));
            if (horario == null){
                Horario horario = new Horario();
                horario.setIdHorario(UUID.randomUUID().toString());
                horario.setHorarioInicio(date1);
                horario.setHorarioFinal(date2);
                ConexionPU.getInstancia().agregar(horario);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Horarios");
                aviso.setHeaderText("Administración de Horarios");
                aviso.setContentText("Registro almacenado correctamente");
                aviso.show();
                this.escenarioPrincipal.mostrarHorarios();
            }else{
                horario.setHorarioInicio(date1);
                horario.setHorarioFinal(date2);
                ConexionPU.getInstancia().modificar(horario);
                Alert aviso = new Alert(Alert.AlertType.CONFIRMATION);
                aviso.setTitle("Administración de Horarios");
                aviso.setHeaderText("Administración de Horarios");
                aviso.setContentText("Registro modificado correctamente.");
                aviso.show();
                this.escenarioPrincipal.mostrarHorarios();
            }
        }catch (KalumException | ParseException e) {
            KalumViewMessage.getInstancia().mostrarMensaje("Administración de horarios", e.getMessage(), 50303);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> list = new ArrayList<>();
        Collections.addAll(list, new String[]{"7:10:00", "8:00:00", "8:50:00","9:00:00","9:50:00","10:40:00","11:30:00","12:20:00","13:10:00"});
        cmbInicio.getItems().addAll(list);
        cmbFinal.getItems().addAll(list);
    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void cancelar() {
        this.escenarioPrincipal.mostrarHorarios();
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
        this.txtId.setEditable(false);
        this.cmbInicio.getSelectionModel().select(horario.getHorarioInicio().toString());
        this.cmbFinal.getSelectionModel().select(horario.getHorarioFinal().toString());
    }
}
