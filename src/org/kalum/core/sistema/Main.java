package org.kalum.core.sistema;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.kalum.core.controllers.*;
import org.kalum.core.db.Conexion;
import org.kalum.core.models.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class Main extends Application {

    private Stage escenarioPrincipal;
    private final String PAQUETE_VIEW = "../views/";

    @Override
    public void start(Stage escenarioPrincipal) throws Exception {
        this.escenarioPrincipal = escenarioPrincipal;
        this.escenarioPrincipal.setTitle("kalum v1.0.0");
        this.escenarioPrincipal.initStyle(StageStyle.UNDECORATED);
        this.mostrarVentanaLogin();
        this.escenarioPrincipal.show();
    }

    public void mostrarAlumnos() {
        try {
            AlumnoController controlador = (AlumnoController) this.cambiarEscena("AlumnoView.fxml", 699, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaPrincipal() {
        try {
            MainController controlador = (MainController) this.cambiarEscena("MainView.fxml", 600, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaHorarioCU() {
        try {
            HorarioCUController controlador = (HorarioCUController) this.cambiarEscena("HorarioCUView.fxml", 600, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaHorarioCU(Horario elemento){
        try {
            HorarioCUController controlador = (HorarioCUController) this.cambiarEscena("HorarioCUView.fxml",600,400);
            controlador.setHorario(elemento);
            controlador.setEscenarioPrincipal(this);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void mostrarEscenaAlumnoCU(Alumno elemento) {
        try {
            AlumnoCUController controlador = (AlumnoCUController) this.cambiarEscena("AlumnoCUView.fxml", 600, 400);
            controlador.setAlumno(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaAlumnoCU() {
        try {
            AlumnoCUController controlador = (AlumnoCUController) this.cambiarEscena("AlumnoCUView.fxml", 600, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaClases() {
        try {
            ClaseController controlador = (ClaseController) this.cambiarEscena("ClaseView.fxml", 770, 420);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaCarreraTecnicaCU() {
        try {
            CarreraTecnicaCUController controlador = (CarreraTecnicaCUController) this.cambiarEscena("CarreraTecnicaCUView.fxml", 600, 304);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaCarreraTecnicaCU(CarreraTecnica elemento) {
        try {
            CarreraTecnicaCUController controlador = (CarreraTecnicaCUController) this.cambiarEscena("CarreraTecnicaCUView.fxml", 429, 304);
            controlador.setCarreraTecnica(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarInstructores() {
        try {
            InstructorController controlador = (InstructorController) this.cambiarEscena("InstructorView.fxml", 1085, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarContacto() {
        try {
            ContactoController controlador = (ContactoController) this.cambiarEscena("contactoView.fxml", 600, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaInstructoresCU() {
        try {
            InstructorCUController controlador = (InstructorCUController) this.cambiarEscena("InstructorCUView.fxml", 625, 500);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaInstructoresCU(Instructor elemento) {
        try {
            InstructorCUController controlador = (InstructorCUController) this.cambiarEscena("InstructorCUView.fxml", 625, 500);
            controlador.setInstructor(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSalonesCU() {
        try {
            SalonCUController controlador = (SalonCUController) this.cambiarEscena("SalonCUView.fxml", 600, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaSalonesCU(Salon elemento) {
        try {
            SalonCUController controlador = (SalonCUController) this.cambiarEscena("SalonCUView.fxml", 600, 400);
            controlador.setSalon(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarCarreras() {
        try {
            CarreraTecnicaController controlador = (CarreraTecnicaController) this.cambiarEscena("CarreraTecnicaView.fxml", 710, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarSalones() {
        try {
            SalonController controlador = (SalonController) this.cambiarEscena("SalonView.fxml", 722, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarHorarios() {
        try {
            HorarioController controlador = (HorarioController) this.cambiarEscena("HorarioView.fxml", 731, 400);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaClaseCU() {
        try {
            ClaseCUController controlador = (ClaseCUController) this.cambiarEscena("ClaseCUView.fxml", 587, 486);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarEscenaClaseCU(Clase elemento) {
        try {
            ClaseCUController controlador = (ClaseCUController) this.cambiarEscena("ClaseCUView.fxml", 587, 486);
            controlador.setClase(elemento);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostrarVentanaLogin() {
        try {
            LoginController controlador = (LoginController) this.cambiarEscena("login.fxml", 476, 305);
            controlador.setEscenarioPrincipal(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*//Método para cambiar la escena del escenario principal
    public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
            Initializable resultado  = null;
        //cargador del archivo XML
        FXMLLoader cargadorFXML = new FXMLLoader();
        //Aignación lógica del archivo
        InputStream archivo = Main.class.getResourceAsStream(this.PAQUETE_VIEW + escena);
        //Cargador de fábrica para cargar el archivo FXML
        cargadorFXML.setBuilderFactory(new JavaFXBuilderFactory());
        //Ubicación del archivo FXML que se pintará en el escenario
        cargadorFXML.setLocation(PrintStream.class.getResource(this.PAQUETE_VIEW + escena));
        //Creación de la escena
        Scene nuevaEscena = new Scene((AnchorPane) cargadorFXML.load(archivo), ancho, alto);
        //Agregando hojas de estilos
        nuevaEscena.getStylesheets().addAll("org/kalum/core/resources/styles/estilo.css");
        //Mostrar la escena en el escenario principal
        this.escenarioPrincipal.setScene(nuevaEscena);
        //Ajustar el tamaño del escenario a la escena que se desea mostrar
        this.escenarioPrincipal.sizeToScene();
        //obtener el controlador de la vista que se está mostrando
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }*/

    public Initializable cambiarEscena(String escena, int ancho, int alto) throws IOException {
        Initializable resultado = null;
        FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource(this.PAQUETE_VIEW + escena));
        AnchorPane root = (AnchorPane) cargadorFXML.load();
        Scene scene = new Scene(root, ancho, alto);
        scene.getStylesheets().add("org/kalum/core/resources/styles/estilo.css");
        this.escenarioPrincipal.setScene(scene);
        this.escenarioPrincipal.sizeToScene();
        resultado = (Initializable) cargadorFXML.getController();
        return resultado;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
