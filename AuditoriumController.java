package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AuditoriumController {

    @FXML
    void toEventXView(MouseEvent event) throws IOException {
        Node source = (Node) event.getSource();
        if (source instanceof ImageView) {
            ImageView clickedImageView = (ImageView) source;
            String accessibleText = clickedImageView.getAccessibleText();
            System.out.println(accessibleText);
            SharedData.setButtonText(accessibleText);
            // Do something with accessibleText
        }
        SceneManager.switchToXEventScreenScene();
    }

    @FXML
    void toHomeScreen(ActionEvent event) {

    }

    @FXML
    void toMainScene(MouseEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void toRegisterEvent(ActionEvent event) throws IOException {
        SceneManager.switchToRegisterEventScreenScene();
    }



}
