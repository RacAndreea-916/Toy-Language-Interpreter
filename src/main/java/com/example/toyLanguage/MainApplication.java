package com.example.toyLanguage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        MainController mainCtrl = fxmlLoader.getController();

        FXMLLoader fxmlLoader1 = new FXMLLoader(MainApplication.class.getResource("second.fxml"));
        Scene scene2 = new Scene(fxmlLoader1.load(),600,600);

        SelectController ctrl2 = fxmlLoader1.getController();

        mainCtrl.setController(ctrl2);


        Stage stage2=new Stage();
        stage2.setTitle("Select window");
        stage2.setScene(scene2);
        stage2.show();

        stage.setTitle("program window");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}