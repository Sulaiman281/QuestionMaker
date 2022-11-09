package org.witshells;

import org.witshells.filehandling.FileHandling;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.witshells.models.Question;
import org.witshells.views.HomePage;

import java.util.ArrayList;

public class App extends Application {
    public static ArrayList<Question> questions = new ArrayList<>();

    public  static Stage stage;
    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;
        new FileHandling().fileReading();
        primaryStage.setScene(new Scene(new HomePage().getContent()));
        primaryStage.setWidth(700);
        primaryStage.setHeight(500);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}