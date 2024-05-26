package sms.first;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController{

    @FXML
    private TextField emailInput;

    @FXML
    private PasswordField passwordInput;

    @FXML
    void doLogin(ActionEvent event) throws IOException {
        String email = emailInput.getText();
        String password = passwordInput.getText();

        // Establish connection to the Oracle database
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            // Prepare the SQL statement
            String sql = "SELECT password FROM users WHERE email = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);

                // Execute the query
                try (ResultSet resultSet = statement.executeQuery()) {
                    // Check if there is a matching user
                    if (resultSet.next()) {
                        String dbPassword = resultSet.getString("password");
                        // Compare the passwords
                        if (password.equals(dbPassword)) {
                            SceneManager.switchToMainScene();
                            System.out.println("Login successful");
                            // Here you can navigate to another scene or perform other actions
                        } else {
                            System.out.println("Incorrect password");
                            showAlert("Incorrect Password", "The password you entered is incorrect.");
                        }
                    } else {
                        System.out.println("User not found");
                        showAlert("User Not Found", "There is no user with the provided email.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "An error occurred while accessing the database.");
        }
    }

    // Method to show an alert dialog
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
