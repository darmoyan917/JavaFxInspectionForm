package Model;

import Controller.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    static mainController controller;
    static customerController custContoller;
    static vehiclesController vehController;
    static shopInfoController shopController;
    static printPageController printController;
    public List<Node> customerMiddlePaneContent = new ArrayList<>();
    public List<Node> vehiclesMiddlePaneContent = new ArrayList<>();
    public List<Node> inspectionMiddlePaneContent = new ArrayList<>();
    public List<Node> shopInfoMiddlePaneContent = new ArrayList<>();
    public List<Node> printAnchorPane = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/mainLayout.fxml"));
        FXMLLoader custLoader = new FXMLLoader(getClass().getResource("../View/customerLayout.fxml"));
        FXMLLoader vehLoader = new FXMLLoader(getClass().getResource("../View/vehicleLayout.fxml"));
        FXMLLoader shopLoader = new FXMLLoader(getClass().getResource("../View/shopInfoLayout.fxml"));
        Parent root = loader.load();
        custLoader.load();
        vehLoader.load();
        shopLoader.load();

        primaryStage.setTitle("Inspection Form");
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(850);
        primaryStage.show();
        controller = loader.getController();
        controller.setMakeChoiceBox();
        custContoller = custLoader.getController();
        vehController = vehLoader.getController();
        shopController = shopLoader.getController();
        customerMiddlePaneContent = custContoller.getmiddlePane();
        vehiclesMiddlePaneContent = vehController.getmiddlePane();
        inspectionMiddlePaneContent = controller.getmiddlePane();
        shopInfoMiddlePaneContent = shopController.getmiddlePane();
        controller.setInspectionmiddlePaneContent(inspectionMiddlePaneContent);
        controller.setCustomerMiddlePaneContent(customerMiddlePaneContent);
        controller.setVehiclesMiddlePaneContent(vehiclesMiddlePaneContent);
        controller.setShopInfoMiddlePaneContent(shopInfoMiddlePaneContent);
        custContoller.setVehiclesMiddlePaneContent(vehiclesMiddlePaneContent);
        custContoller.setChangeListeners();
        controller.setChangeListeners();
        controller.setUpperDefaultListener();
        PauseTransition pause = new PauseTransition(Duration.seconds(0.2));
        primaryStage.maximizedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> ov, Boolean t, Boolean t1) {
                if(t1)
                    pause.setOnFinished(event -> controller.showDrivetrainComments());
                    pause.playFromStart();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
