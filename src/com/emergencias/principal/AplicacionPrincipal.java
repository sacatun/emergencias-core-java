package com.emergencias.principal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AplicacionPrincipal extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader cargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/emergencias/resources/vista_emergencias.fxml")
        );

        Scene escena = new Scene(cargador.load(), 1000, 700);
        escena.getStylesheets().add(
                getClass().getClassLoader().getResource("com/emergencias/resources/estilos.css").toExternalForm()
        );
        stage.setTitle("Sistema de Gestión de Emergencias");
        stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}