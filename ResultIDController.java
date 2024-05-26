package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultIDController {

    @FXML
    private TextField idInput;

    @FXML
    private Text idNotExist;


    @FXML
    void toHome(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void checkResult(ActionEvent event) throws IOException {
        // Retrieve the ID entered by the user
        String studentID = idInput.getText().trim();

        // Connect to the database
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            // Prepare the SQL statement to select the student with the given ID
            String sql = "SELECT student_id FROM students WHERE student_id=?";
            SharedData.setButtonText(studentID);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, studentID);

                // Execute the query
                ResultSet resultSet = statement.executeQuery();

                // Check if a student with the given ID exists
                if (resultSet.next()) {
                    SceneManager.switchToResultScreenScene();
                } else {
                    idNotExist.setText("Error ID: " + studentID+ " does not exist");
                    idNotExist.setVisible(true);
                    System.out.println("ID Does not exist");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }
    }
}
