package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.*;

public class NewAdmissionController {

    @FXML
    private TextField inputClass;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSession;

    @FXML
    private Text resultText;

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void toPrevScene(ActionEvent event) throws IOException {
        SceneManager.switchToStudentScreenScene();
    }

    @FXML
    void addStudent(ActionEvent event) {
        // Get values from input fields
        String id = inputId.getText();
        String name = inputName.getText();
        String sessionText = inputSession.getText();
        String classText = inputClass.getText();

        // Validate input fields
        if (id.isEmpty() || name.isEmpty() || sessionText.isEmpty() || classText.isEmpty()) {
            // Display error message or handle invalid input
            resultText.setFill(Color.DEEPPINK);
            resultText.setText("Please fill all the fields");
            return;
        }

        try {
            int session = Integer.parseInt(sessionText);
            int studentClass = Integer.parseInt(classText);

            // Insert the new student into the database
            insertStudentIntoDatabase(id, name, session, studentClass);

            // Clear input fields
            clearInputFields();
        } catch (NumberFormatException e) {
            // Handle invalid input format (non-integer session, attendance, or class)
            // Display an error message to the user
        }
    }

    private void insertStudentIntoDatabase(String id, String name, int session, int studentClass) {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            String query = "INSERT INTO students (student_id, first_name, last_name, session_number, class) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, id);
            // Split the name into first name and last name
            String[] names = name.split("\\s+");
            String firstName = "";
            String lastName = "";
            if (names.length >= 2) {
                firstName = names[0]; // First name
                lastName = names[1]; // Last name
            } else {
                firstName = name; // If split fails, use the entire name as the first name
                lastName = ""; // Set last name to empty string
            }
            statement.setString(2, firstName); // First name
            statement.setString(3, lastName); // Last name
            statement.setInt(4, session);
            statement.setInt(5, studentClass);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                // Student added successfully
                resultText.setFill(Color.GREEN);
                resultText.setText("Congrats, student "+name+" enrolled in class "+studentClass+"!");
            }
        } catch (SQLException e) {
            if (e instanceof SQLIntegrityConstraintViolationException) {
                // Handle unique constraint violation
                // Provide feedback to the user that the student with this ID already exists
                resultText.setFill(Color.RED);
                resultText.setText("Error: Student with ID " + id + " already exists.");
            } else {
                // Handle other SQL exceptions
                e.printStackTrace();
            }
        }
    }



    private void clearInputFields() {
        inputId.clear();
        inputName.clear();
        inputSession.clear();
        inputClass.clear();
    }
}
