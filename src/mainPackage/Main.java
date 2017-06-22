package mainPackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("mainLayout.fxml"));
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLayout.fxml"));

        primaryStage.setTitle("Inspection Form");
        primaryStage.getIcons().add(new Image("/images/icon.png"));
        primaryStage.setScene(new Scene(root, 1200, 900));
        primaryStage.show();
        //mainController controller1 = loader.getController();
        //controller1.setStage(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
