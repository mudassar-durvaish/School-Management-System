package sms.first;

import javafx.application.Preloader;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class MainController {

    @FXML
    private ImageView btnStudent;

    @FXML
    void studentScreenScene(MouseEvent event) throws IOException {
        SceneManager.switchToStudentScreenScene();
    }
    @FXML
    void toEmployees(MouseEvent event) throws IOException {
        SceneManager.switchToEmployeesScreenScene();
    }

    @FXML
    void toRooms(MouseEvent event) throws IOException {
        SceneManager.switchToRoomsScreenScene();
    }

    @FXML
    void toAuditorium(MouseEvent event) throws IOException {
        SceneManager.switchToAuditoriumScreenScene();
    }

    @FXML
    void toResults(MouseEvent event) throws IOException {
        SceneManager.switchToResultIDScreenScene();
    }

    @FXML
    void toTimeSchedule(MouseEvent event) throws IOException {
        SceneManager.switchToTimeScheduleScreenScene();
    }

}


