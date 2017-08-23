package Model;

import Controller.CustomerController;
import Controller.mainController;
import Controller.printPageController;
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
    static CustomerController custContoller;
    static printPageController printController;
    public List<Node> middlePaneContent = new ArrayList<>();
    public List<Node> printAnchorPane = new ArrayList<>();
    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/mainLayout.fxml"));
        FXMLLoader custLoader = new FXMLLoader(getClass().getResource("../View/CustomerLayout.fxml"));
        Parent root = loader.load();
        custLoader.load();

        primaryStage.setTitle("Inspection Form");
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
        controller = loader.getController();
        controller.setMakeChoiceBox();
        custContoller = custLoader.getController();
        middlePaneContent = custContoller.getmiddlePane();
        controller.setMiddlePaneContent(middlePaneContent);
        controller.setChangeListeners();
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
