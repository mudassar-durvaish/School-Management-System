package sms.first;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.sql.*;

public class ResultController {

    @FXML
    private Text classTxt;

    @FXML
    private Text fnameTxt;

    @FXML
    private Text lnameTxt;

    @FXML
    private Button print;

    @FXML
    private Text marksTxt;

    @FXML
    private Text sessionTxt;

    @FXML
    private Text stuIDTxt;

    @FXML
    private Button home;

    @FXML
    void toHome(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void initialize() {
        String stuID=SharedData.getButtonText();
        // Fetch data from the database when the controller is initialized
        fetchDataFromDatabase(stuID);
    }

    @FXML
    void printScreen(ActionEvent event) {
        home.setVisible(false);
        print.setVisible(false);

        // Get the stage
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Get the scene
        Scene scene = stage.getScene();

        // Create a FileChooser
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (.png)", "*.png"));

        // Call the method to save the screen
        PrintUtil.saveScreen(fileChooser, scene);
        home.setVisible(true);
        print.setVisible(true);
    }

    private void fetchDataFromDatabase(String studentId) {
        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");

            System.out.println("Connected to database");
            // Prepare the SQL query
            String query = "SELECT student_id,first_name, last_name, session_number, Marks_Percentage, class FROM students WHERE student_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, studentId);
            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            // If there's a result, update the text fields
            if (resultSet.next()) {
                stuIDTxt.setText(resultSet.getString("student_id"));
                fnameTxt.setText(resultSet.getString("first_name"));
                lnameTxt.setText(resultSet.getString("last_name"));
                sessionTxt.setText(resultSet.getString("session_number"));
                marksTxt.setText(resultSet.getString("Marks_Percentage"));
                classTxt.setText(resultSet.getString("class"));
            }

            // Close the resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any exceptions
        }
    }
}
