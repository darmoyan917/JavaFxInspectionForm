package Controller;


import Model.customersDAO;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import Model.customers;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class customerController {
    @FXML private TextField customerLastName;
    @FXML private TextField customerFirstName;
    @FXML private TextField customerAddress;
    @FXML private TextField customerCity;
    @FXML private TextField customerState;
    @FXML private TextField customerZip;
    @FXML private TextField customerVin;
    @FXML private TextField customerEmail;
    @FXML private Label searchStatus;
    @FXML private TableView<customers> customersTable;
    @FXML private AnchorPane customerMiddlePane;
    @FXML private TableColumn<customers, String> firstName;
    @FXML private TableColumn<customers, String> lastName;
    @FXML private TableColumn<customers, String> address;
    @FXML private TableColumn<customers, String> city;
    @FXML private TableColumn<customers, String> state;
    @FXML private TableColumn<customers, Integer> zipCode;
    @FXML private TableColumn<customers, String> vinNumber;
    @FXML private TableColumn<customers, String> email;
    static mainController controller;
    FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/mainLayout.fxml"));
    public List<Node> vehiclesMiddlePaneContent = new ArrayList<>();
    private customers temp;
    public List<Node> middlePaneContent = new ArrayList<>();

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(customerMiddlePane.getChildren());
        return  middlePaneContent;
    }
    private void setSearchStatus(String status){
        searchStatus.setText(status + "\n");
        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event -> searchStatus.setText(""));
        pause.playFromStart();
    }
    // Search one customer
    @FXML
    private void searchCustomer(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        try {
            //Get Customer information
            customers emp = customersDAO.searchCustomers(customerFirstName.getText().toLowerCase(),customerLastName.getText().toLowerCase());
            //Populate Customer on TableView
            populateAndShowCustomer(emp);
        } catch (SQLException e) {
            e.printStackTrace();
            setSearchStatus("Error occurred while getting customer information from DB");
            throw e;
        }
    }
    //Search all customers
    @FXML
    private void searchAllCustomers(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            //Get all Customers information
            ObservableList<customers> empData = customersDAO.searchCustomers();
            //Populate Customers on TableView
            populateCustomers(empData);
        } catch (SQLException e){
            setSearchStatus("Error occurred while getting customer information from DB");
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
    private void populateCustomer(customers emp) throws ClassNotFoundException {
        //Declare and ObservableList for table view
        ObservableList<customers> empData = FXCollections.observableArrayList();
        //Add employee to the ObservableList
        empData.add(emp);
        //Set items to the customer table
        customersTable.setItems(empData);
    }
    @FXML
    private void populateAndShowCustomer(customers emp) throws ClassNotFoundException {
        if (emp != null) {
            populateCustomer(emp);
        } else {
            setSearchStatus("This customer does not exist!");
        }
    }
    //Populate Vehicles for TableView
    @FXML
    private void populateCustomers(ObservableList<customers> empData) throws ClassNotFoundException {
        //Set items to the employeeTable
        customersTable.setItems(empData);
    }
    //Update employee's email with the email which is written on newEmailText field
    @FXML
    private void updateCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            customersDAO.updateVehicleModel(customerVin.getText(),"new Model");
            setSearchStatus("Email has been updated for ");
        } catch (SQLException e) {
            setSearchStatus("Problem occurred while updating email: " + e);
        }
    }

    //Insert an employee to the DB
    @FXML
    private void insertCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            customersDAO.insertCustomer(customerFirstName.getText(),customerLastName.getText(), customerAddress.getText(), customerCity.getText(),
                    customerState.getText(), customerZip.getText(), customerVin.getText(), customerEmail.getText());
            setSearchStatus("Employee inserted!");
        } catch (SQLException e) {
            setSearchStatus("Problem occurred while inserting employee " + e);
            throw e;
        }
    }

    //Delete an employee with a given employee Id from DB
    @FXML
    private void deleteCustomer(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            customersDAO.deleteCustomerWithName(customerFirstName.getText(),customerLastName.getText());
            setSearchStatus("Employee deleted! ");
        } catch (SQLException e) {
            setSearchStatus("Problem occurred while deleting employee " + e);
            throw e;
        }
    }

    @FXML
    private void handleRowSelect() throws IOException {
        customers row = customersTable.getSelectionModel().getSelectedItem();
        if (row==null) return;
        if(row!=temp){
            temp=row;
        } else if(row==temp) {
        String FirstName = firstName.getCellData(customersTable.getSelectionModel().getSelectedIndex());
        String LastName = lastName.getCellData(customersTable.getSelectionModel().getSelectedIndex());
        loader.load();
        controller = loader.getController();
        controller.setVehiclesToMiddlePane(customerMiddlePane);
        }
    }
    public void setVehiclesMiddlePaneContent(List<Node> node){
        vehiclesMiddlePaneContent = node;
    }

}
