package mainPackage;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class mainController {
    private Stage myStage;
    public void setStage(Stage stage) {
        myStage = stage;
    }

    @FXML
    private void exitButtonAction() {
        Platform.exit();

    }
    @FXML
    private void openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.showOpenDialog(myStage);
    }
    @FXML
    private void saveFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.showSaveDialog(myStage);
    }
    @FXML
    private void openAboutWindow (){

    try{
        Stage aboutStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("aboutLayout.fxml"));
        aboutStage.setScene(new Scene(root, 600, 400));
        aboutStage.initStyle(StageStyle.UNDECORATED);
        aboutStage.setResizable(false);
        aboutStage.getIcons().add(new Image("/images/icon.png"));
        aboutStage.show();
    }
    catch (IOException e){

    }

    }

}
