package mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLayout.fxml"));
        FXMLLoader custLoader = new FXMLLoader(getClass().getResource("CustomerLayout.fxml"));
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

    }

    public static void main(String[] args) {
        launch(args);
    }
}
