package Controller;


import Model.customersDAO;
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
import Model.customers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    @FXML private TextField customerLastName;
    @FXML private TextField customerFirstName;
    @FXML private Label searchStatus;
    @FXML private TableView customersTable;
    @FXML private AnchorPane customerMiddlePane;
    @FXML private TableColumn<customers, String> firstName;
    @FXML private TableColumn<customers, String> lastName;
    @FXML private TableColumn<customers, String> address;
    @FXML private TableColumn<customers, String> city;
    @FXML private TableColumn<customers, String> state;
    @FXML private TableColumn<customers, Integer> zipCode;
    @FXML private TableColumn<customers, String> vinNumber;
    @FXML private TableColumn<customers, String> email;

    public List<Node> middlePaneContent = new ArrayList<>();

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(customerMiddlePane.getChildren());
        return  middlePaneContent;
    }

    @FXML
    private void searchCustomer(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Customer information
            customers emp = customersDAO.searchVehicles(customerFirstName.getText().toLowerCase()+customerLastName.getText().toLowerCase());
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
            ObservableList<customers> empData = customersDAO.searchVehicles();
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
        firstName.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastName.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        address.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        city.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        state.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        zipCode.setCellValueFactory(cellData -> cellData.getValue().zipCodeProperty().asObject());
        vinNumber.setCellValueFactory(cellData -> cellData.getValue().vinNumberProperty());
        email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
    }
    //Populate Vehicle
    @FXML
    private void populateEmployee (customers emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<customers> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the employeeTable
        customersTable.setItems(empData);
    }
    @FXML
    private void populateAndShowEmployee(customers emp) throws ClassNotFoundException {
        if (emp != null) {
            populateEmployee(emp);
        } else {
            searchStatus.setText("This customer does not exist!\n");
        }
    }
    //Populate Vehicles for TableView
    @FXML
    private void populateVehicles(ObservableList<customers> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        customersTable.setItems(empData);
    }

}
