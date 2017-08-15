package Controller;


import Model.vehiclesDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import Model.vehicles;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    @FXML
    private TableView vehiclesTable;
    @FXML
    private AnchorPane customerMiddlePane;
    @FXML
    private TableColumn<vehicles, Integer> vin;
    @FXML
    private TableColumn<vehicles, Integer> year;
    @FXML
    private TableColumn<vehicles, String> make;
    @FXML
    private TableColumn<vehicles, String> model;
    @FXML
    private TableColumn<vehicles, String> bodyType;
    @FXML
    private TableColumn<vehicles, String> color;

    public List<Node> middlePaneContent = new ArrayList<>();

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(customerMiddlePane.getChildren());
        return  middlePaneContent;
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
        vin.setCellValueFactory(cellData -> cellData.getValue().vehidleidProperty().asObject());
        year.setCellValueFactory(cellData -> cellData.getValue().yearProperty().asObject());
        make.setCellValueFactory(cellData -> cellData.getValue().makeProperty());
        model.setCellValueFactory(cellData -> cellData.getValue().modelProperty());
        bodyType.setCellValueFactory(cellData -> cellData.getValue().bodyTypeProperty());
        color.setCellValueFactory(cellData -> cellData.getValue().colorProperty());
    }

}
