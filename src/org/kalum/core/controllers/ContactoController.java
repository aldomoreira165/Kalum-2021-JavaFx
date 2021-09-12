package org.kalum.core.controllers;
import javafx.fxml.Initializable;
import org.kalum.core.sistema.Main;
import java.net.URL;
import java.util.ResourceBundle;

public class ContactoController implements Initializable {

    private Main escenarioPrincipal;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setEscenarioPrincipal(Main escenarioPrincipal) {
        this.escenarioPrincipal = escenarioPrincipal;
    }

    public void menuPrincipal() {
        this.escenarioPrincipal.mostrarEscenaPrincipal();
    }

}
