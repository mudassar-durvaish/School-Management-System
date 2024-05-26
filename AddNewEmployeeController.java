package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddNewEmployeeController {

    @FXML
    private ToggleGroup EmployeeType;

    @FXML
    private ToggleGroup Gender;

    @FXML
    private TextField empIDinput;

    @FXML
    private TextField firstNameInput;

    @FXML
    private TextField lastNameInput;

    @FXML
    private Text resultText;

    @FXML
    private TextField salaryInput;

    @FXML
    void addEmployee(ActionEvent event) {
        String empID = empIDinput.getText();
        String firstName = firstNameInput.getText();
        String lastName = lastNameInput.getText();
        String gender = ((RadioButton) Gender.getSelectedToggle()).getText();
        String salary = salaryInput.getText();
        String empType = ((RadioButton) EmployeeType.getSelectedToggle()).getText();

        // Validate input fields
        if (empID.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || salary.isEmpty() || empType.isEmpty()) {
            // Display error message or handle invalid input
            resultText.setFill(Color.DEEPPINK);
            resultText.setText("Please fill all the fields");
            return;
        }

        // Here, you can perform further processing like adding the employee to the database
        try {
            insertEmployeeIntoDatabase(empID, firstName, lastName, gender, salary, empType);
        } catch (SQLException e) {
            // Handle database insertion error
            resultText.setFill(Color.RED);
            resultText.setText("Error! Employee with ID: " + empID + " already exists.");
            return;
        }

        // If insertion was successful, display success message
        resultText.setFill(Color.GREEN);
        resultText.setText("Employee added successfully! ID: " + empID);
    }

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    private void insertEmployeeIntoDatabase(String empID, String firstName, String lastName, String gender, String salary, String empType) throws SQLException {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            String query = "INSERT INTO Employees (employee_Id, first_name, last_name, gender, salary, employeeType) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, empID);
            statement.setString(2, firstName);
            statement.setString(3, lastName);
            statement.setString(4, gender);
            statement.setString(5, salary);
            statement.setString(6, empType);
            statement.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
