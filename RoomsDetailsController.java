package sms.first;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomsDetailsController {

    @FXML
    private TableView<Room> roomTable;

    @FXML
    private TableColumn<Room, String> roomIDColumn;

    @FXML
    private TableColumn<Room, String> roomTypeColumn;

    @FXML
    private TableColumn<Room, Integer> capacityColumn;

    @FXML
    private TableColumn<Room, Integer> scheduledPeriodsColumn;

    @FXML
    private Text heading;

    private ObservableList<Room> rooms = FXCollections.observableArrayList();

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    @FXML
    void toRooms(ActionEvent event) throws IOException {
        SceneManager.switchToRoomsScreenScene();
    }

    @FXML
    public void initialize() {
        loadRoomsFromDatabase();
        initializeTableColumns();
        heading.setText(initialButtonText);
    }

    String initialButtonText = SharedData.getButtonText();

    private void initializeTableColumns() {
        roomIDColumn.setCellValueFactory(new PropertyValueFactory<>("roomId"));
        roomTypeColumn.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        scheduledPeriodsColumn.setCellValueFactory(new PropertyValueFactory<>("scheduledPeriods"));
    }

    private void loadRoomsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT room_id, capacity, room_type, scheduled_periods FROM RoomView WHERE room_type = ?")) {

            // Set the room_type parameter value
            preparedStatement.setString(1, initialButtonText);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String roomId = resultSet.getString("room_id");
                int capacity = resultSet.getInt("capacity");
                String roomType = resultSet.getString("room_type");
                int scheduledPeriods = resultSet.getInt("scheduled_periods");
                rooms.add(new Room(roomId, capacity, roomType, scheduledPeriods));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        roomTable.setItems(rooms);
    }

}
