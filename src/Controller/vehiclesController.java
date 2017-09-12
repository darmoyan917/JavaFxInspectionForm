package Controller;

import Model.vehicles;
import Model.vehiclesDAO;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class vehiclesController {
    public List<Node> middlePaneContent = new ArrayList<>();
    @FXML private AnchorPane vehicleMiddlePane;
    @FXML private TextField customerLastName;
    @FXML private TextField customerFirstName;
    @FXML private Label searchStatus;
    @FXML private TableView vehiclesTable;
    @FXML private TableColumn<vehicles, String> vin;
    @FXML private TableColumn<vehicles, Integer> year;
    @FXML private TableColumn<vehicles, String> make;
    @FXML private TableColumn<vehicles, String> model;
    @FXML private TableColumn<vehicles, String> bodyType;
    @FXML private TableColumn<vehicles, String> mileage;
    @FXML private TableColumn<vehicles, String> date;

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(vehicleMiddlePane.getChildren());
        return  middlePaneContent;
    }

    @FXML
    private void searchCustomer(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Customer information
            vehicles emp = vehiclesDAO.searchVehicles(customerFirstName.getText().toLowerCase()+customerLastName.getText().toLowerCase());
            //Populate Vehicle on TableView
            populateAndShowEmployee(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            searchStatus.setText("Error occurred while getting customer information from DB.\n" + e);
            throw e;
        }
    }
    //Search all employees
    @FXML
    private void searchAllCustomers(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Customers information
            ObservableList<vehicles> empData = vehiclesDAO.searchVehicles();
            //Populate Vehicles on TableView
            populateVehicles(empData);
        } catch (SQLException e){
            System.out.println("Error occurred while getting customer information from DB.\n" + e);
            throw e;
        }
    }

    @FXML
    private void initialize () {
        /*
        The setCellValueFactory(...) that we set on the table columns are used to determine
        which field inside the Employee objects should be used for the particular column.
        The arrow -> indicates that we're using a Java 8 feature called Lambdas.
        (Another option would be to use a PropertyValueFactory, but this is not type-safe

        We're only using StringProperty values for our table columns in this example.
        When you want to use IntegerProperty or DoubleProperty, the setCellValueFactory(...)
        must have an additional asObject():
        */
        vin.setCellValueFactory(cellData -> cellData.getValue().vehidleidProperty());
        year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        make.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        model.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        bodyType.setCellValueFactory(cellData -> cellData.getValue().bodyTypeProperty());
        mileage.setCellValueFactory(cellData -> cellData.getValue().mileageProperty());
        date.setCellValueFactory(cellData -> cellData.getValue().dateProperty());

    }
    //Populate Vehicle
    @FXML
    private void populateEmployee (vehicles emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<vehicles> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        vehiclesTable.setItems(empData);
    }
    @FXML
    private void populateAndShowEmployee(vehicles emp) throws ClassNotFoundException {
        if (emp != null) {
            populateEmployee(emp);
        } else {
            searchStatus.setText("This customer does not exist!\n");
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(event -> searchStatus.setText(""));
            pause.playFromStart();
        }
    }
    //Populate Vehicles for TableView
    @FXML
    private void populateVehicles(ObservableList<vehicles> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        vehiclesTable.setItems(empData);
    }
}
