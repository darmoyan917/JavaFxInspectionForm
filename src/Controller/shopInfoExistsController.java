package Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class shopInfoExistsController {
    @FXML private Button noButton;
    public boolean choice;
    @FXML private Label shopInfoLabel;

    @FXML private void noButtonAction(){
        choice = false;
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }
    @FXML private void yesButtonAction(){
        choice = true;
        Stage stage = (Stage) noButton.getScene().getWindow();
        stage.close();
    }
    public Boolean getChoice(){
        return choice;
    }

    public void openStoredShopInfo(){
        File shopInfoFile = new File("../JavaFxInspectionForm/src/Resources/shopInfo.txt");
        String[] fileReading = new String[100];
        try (BufferedReader in = new BufferedReader(new FileReader(shopInfoFile)))
        {
            String line = in.readLine();
            System.out.println(line);
            int i = 0;
            while(line != null)
            {
                fileReading[i] = line;
                line = in.readLine();
                shopInfoLabel.setText(shopInfoLabel.getText() + fileReading[i]+"\n");
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
