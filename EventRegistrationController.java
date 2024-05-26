package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventRegistrationController {

    @FXML
    private ToggleGroup auditoriumName;

    @FXML
    private ToggleGroup eventType;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputName;

    @FXML
    private Text resultText;

    @FXML
    void addRegistration(ActionEvent event) {
        String id = inputId.getText();
        String name = inputName.getText();

        // Validate input fields
        if (id.isEmpty() || name.isEmpty() || eventType.getSelectedToggle() == null || auditoriumName.getSelectedToggle() == null) {
            // Display error message or handle invalid input
            resultText.setFill(Color.DEEPPINK);
            resultText.setText("Please fill all the fields");
            return;
        }

        String eventTypeText = ((RadioButton) eventType.getSelectedToggle()).getText();
        String auditoriumNameText = ((RadioButton) auditoriumName.getSelectedToggle()).getText();

        // Here, you can perform further processing like adding the registration to a database
        try {
            insertRegistrationIntoDatabase(id, name, eventTypeText, auditoriumNameText);
        } catch (SQLException e) {
            // Handle database insertion error
            resultText.setFill(Color.RED);
            resultText.setText("Error! id: "+id+" already exists.");
            return;
        }

        // If insertion was successful, display success message
        resultText.setFill(Color.GREEN);
        resultText.setText("Registration successful! for ID: " + id + "Name: ");
    }

    @FXML
    void toEvents(ActionEvent event) throws IOException {
        SceneManager.switchToAuditoriumScreenScene();
    }

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    private void insertRegistrationIntoDatabase(String id, String name, String eventType, String auditoriumName) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            String query = "INSERT INTO EventRegistration (registration_id, name, event_type, auditorium_name, registration_date) " +
                    "VALUES (?, ?, ?, ?, CURRENT_TIMESTAMP)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, eventType);
            statement.setString(4, auditoriumName);
            statement.executeUpdate();
        }
    }
}
