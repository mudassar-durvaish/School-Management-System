package sms.first;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class StudentScreenController {


    @FXML
    private Button btn1;

    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private Button button4;

    @FXML
    private Button button5;

    @FXML
    private Button button6;

    @FXML
    private Button button7;

    @FXML
    private Button button8;
    @FXML
    void toNewAdmission(ActionEvent event)throws IOException {
        SceneManager.switchToNewAdmissionScreenScene();
    }

    // Inject the shared service

    @FXML
    public void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        SharedData.setButtonText(buttonText);
    }


    @FXML
    void toClassXScene(MouseEvent event) throws IOException {
        SceneManager.switchToClassXStudentScreenScene();
    }

    @FXML
    void toMainScene(MouseEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

}
