package sms.first;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.scene.control.cell.PropertyValueFactory;

public class EventXController implements Initializable {

    @FXML
    private TableColumn<Event, String> auditoriumName;

    @FXML
    private TableView<Event> eventTable;

    @FXML
    private TableColumn<Event, String> eventTypeColumn;

    @FXML
    private Text heading;

    @FXML
    private TableColumn<Event, Integer> idColumn;

    @FXML
    private TableColumn<Event, String> nameColumn;

    @FXML
    private TableColumn<Event, Integer> noOfSeats;

    private ObservableList<Event> events = FXCollections.observableArrayList();

    @FXML
    void toEventsScene(ActionEvent event) throws IOException {
        SceneManager.switchToAuditoriumScreenScene();
    }

    @FXML
    void toHomeScene(ActionEvent event) throws IOException {
        SceneManager.switchToMainScene();
    }

    String initialButtonText = SharedData.getButtonText();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setHeading();
        initializeTableColumns();
        loadEventsFromDatabase();
    }

    private void initializeTableColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("registrationId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        noOfSeats.setCellValueFactory(new PropertyValueFactory<>("numberOfSeats"));
        auditoriumName.setCellValueFactory(new PropertyValueFactory<>("auditoriumName"));
        eventTypeColumn.setCellValueFactory(new PropertyValueFactory<>("eventType"));
    }

    private void setHeading() {
        this.heading.setText(initialButtonText + " EVENT");
    }

    private void loadEventsFromDatabase() {
        try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "Oracle_1");
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM EventDetails WHERE event_type = ?")) {
            preparedStatement.setString(1, initialButtonText);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int registrationId = resultSet.getInt("registration_id");
                String name = resultSet.getString("name");
                String eventType = resultSet.getString("event_type");
                String auditoriumName = resultSet.getString("auditorium_name");
                int numberOfSeats = resultSet.getInt("no_of_seats");
                events.add(new Event(registrationId, name, eventType, auditoriumName, numberOfSeats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        eventTable.setItems(events);
    }
}
