package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.*;

public class TimeScheduleController{

    @FXML
    private TableColumn<TimeSchedule, String> classPeriodColumn;

    @FXML
    private TableColumn<TimeSchedule, String> classSubjectColumn;

    @FXML
    private TableColumn<TimeSchedule, Integer> roomCapacityColumn;

    @FXML
    private TableColumn<TimeSchedule, String> roomIDColumn;

    @FXML
    private TableColumn<TimeSchedule, String> teacherNameColumn;

    @FXML
    private TableView<TimeSchedule> timeScheduleTable;

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    public void initialize() {
        // Initialize table columns
        classPeriodColumn.setCellValueFactory(new PropertyValueFactory<>("classPeriod"));
        classSubjectColumn.setCellValueFactory(new PropertyValueFactory<>("classSubject"));
        roomCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("roomCapacity"));
        roomIDColumn.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        teacherNameColumn.setCellValueFactory(new PropertyValueFactory<>("teacherName"));

        // Load data into the table
        loadTimeScheduleFromDatabase();
    }

    private void loadTimeScheduleFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT teacher_name, class_period, class_subject, room_capacity, room_id FROM schedule")) {

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String teacherName = resultSet.getString("teacher_name");
                String classPeriod = resultSet.getString("class_period");
                String classSubject = resultSet.getString("class_subject");
                int roomCapacity = resultSet.getInt("room_capacity");
                String roomID = resultSet.getString("room_id");

                TimeSchedule timeSchedule = new TimeSchedule(classPeriod, classSubject, roomCapacity, roomID, teacherName);
                timeScheduleTable.getItems().add(timeSchedule);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
