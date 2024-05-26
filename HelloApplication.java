package sms.first;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneManager.setStage(primaryStage);
        SceneManager.switchToLoginScene();
    }


    public static void main(String[] args) {
        launch();
    }
}
