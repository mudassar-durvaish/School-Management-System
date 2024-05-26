package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.io.IOException;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class EmployeeDetailsController {

    @FXML
    private TableColumn<Employee, String> empIDInput;

    @FXML
    private TableView<Employee> employeeTable;

    @FXML
    private TableColumn<Employee, String> genderInput;

    @FXML
    private TableColumn<Employee, String> levelInput;

    @FXML
    private TableColumn<Employee, String> nameInput;

    @FXML
    private TableColumn<Employee, Integer> salaryInput;

    @FXML
    private Text heading;

    private ObservableList<Employee> employees = FXCollections.observableArrayList();

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void toEmployeeScene(ActionEvent event) throws IOException {
        SceneManager.switchToEmployeesScreenScene();
    }

    @FXML
    public void initialize() {
        loadEmployeesFromDatabase();
        initializeTableColumns();
        heading.setText(initialButtonText);
    }
    String initialButtonText = SharedData.getButtonText();

    private void initializeTableColumns() {
        empIDInput.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameInput.setCellValueFactory(new PropertyValueFactory<>("name"));
        genderInput.setCellValueFactory(new PropertyValueFactory<>("gender"));
        levelInput.setCellValueFactory(new PropertyValueFactory<>("level"));
        salaryInput.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void loadEmployeesFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT employee_Id,concat(concat(first_name,' '),last_name) as name, gender, salary,level_t,employeeType FROM Employees WHERE employeeType = ?")) {
            preparedStatement.setString(1, initialButtonText);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String employeeId = resultSet.getString("employee_Id");
                String name = resultSet.getString("name");
                String gender = resultSet.getString("gender");
                String level = resultSet.getString("level_t");
                int salary = resultSet.getInt("salary");
                employees.add(new Employee(employeeId, name, gender, level, salary));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        employeeTable.setItems(employees);
    }
}
