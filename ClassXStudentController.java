package sms.first;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.GestureEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class ClassXStudentController implements Initializable {

    @FXML
    private TableColumn<Student, Integer> classColumn;

    @FXML
    private TableColumn<Student, Integer> attendanceColumn;

    @FXML
    private TextField inputClass;

    @FXML
    private TextField inputId;

    @FXML
    private TextField inputName;

    @FXML
    private TextField inputSession;

    @FXML
    private TableColumn<Student, String> nameColumn;

    @FXML
    private TableColumn<Student, Integer> sessionColumn;

    @FXML
    private TableColumn<Student, Integer> studentIdColumn;

    @FXML
    private TableView<Student> studentTable;

    @FXML
    public Text heading;

    public void setHeading() {
        this.heading.setText("CLASS " + initialButtonText + " STUDENTS");
    }


    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void toPrevScene(ActionEvent event)throws IOException {
        SceneManager.switchToStudentScreenScene();
    }

    // External
    private ObservableList<Student> students = FXCollections.observableArrayList();

    String initialButtonText = SharedData.getButtonText();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initial Button Text: " + initialButtonText);
        setHeading();
        // Set cell value factories
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        sessionColumn.setCellValueFactory(new PropertyValueFactory<>("session"));
        classColumn.setCellValueFactory(new PropertyValueFactory<>("studentClass"));
        attendanceColumn.setCellValueFactory(new PropertyValueFactory<>("studentAttendance"));

        // Fetch lastClickedButtonText

        // Load data from database
        loadStudentsFromDatabase();

        // Setting tables on views
        studentTable.setItems(students);
    }
    private void loadStudentsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");
             PreparedStatement statement = connection.prepareStatement("SELECT student_id, concat(concat(first_name, ' '), last_name) AS name, session_number, class, attendance FROM students where class=?")) {
            // Set parameter for the prepared statement
            String lastClickedButtonText="2";
            statement.setString(1, initialButtonText);
            System.out.println(lastClickedButtonText);

            try (ResultSet resultSet = statement.executeQuery()) {
                // Clear existing data in students list
                students.clear();

                // Process results
                while (resultSet.next()) {
                    String id = resultSet.getString("student_id");
                    String studentName = resultSet.getString("name");
                    int sessionYear = resultSet.getInt("session_number");
                    int studentClass = resultSet.getInt("class");
                    int studentAttendance = resultSet.getInt("attendance");
                    students.add(new Student(id, studentName, sessionYear, studentClass, studentAttendance));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            return;
        }

        try {
            int session = Integer.parseInt(sessionText);
            int studentClass = Integer.parseInt(classText);

            // Create a new Student object
            Student newStudent = new Student(id, name, session, studentClass,0);

            // Add the new student to the list
            students.add(newStudent);

            // Insert the new student into the database
            insertStudentIntoDatabase(newStudent);

            // Update the TableView
            studentTable.setItems(students);

            // Clear input fields
            clearInputFields();
        } catch (NumberFormatException e) {
            // Handle invalid input format (non-integer session, attendance, or class)
            // Display an error message to the user
        }
    }

    private void insertStudentIntoDatabase(Student student) {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1")) {
            String query = "INSERT INTO students (student_id, first_name, last_name, session_number, class) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student.getStudentId());
            // Split the name into first name and last name
            String[] names = student.getName().split("\\s+");
            String firstName = "";
            String lastName = "";
            if (names.length >= 2) {
                firstName = names[0]; // First name
                lastName = names[1]; // Last name
            } else {
                firstName = student.getName(); // If split fails, use the entire name as the first name
                lastName = ""; // Set last name to empty string
            }
            statement.setString(2, firstName); // First name
            statement.setString(3, lastName); // Last name
            statement.setInt(4, student.getSession());
            statement.setInt(5, student.getStudentClass());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearInputFields() {
        inputId.clear();
        inputName.clear();
        inputSession.clear();
        inputClass.clear();
    }
}
