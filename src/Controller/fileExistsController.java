package Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javafx.scene.image.ImageView;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class fileExistsController {
    @FXML private Button noButton;
    public boolean choice;
    @FXML private ImageView companyLogo;
    private Image shopLogo;

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

    public void openStoredLogo(){
        File shopLogoFile = new File("../JavaFxInspectionForm/src/images/companyLogo.png");
        BufferedImage bufferedImage = null;
        try {
            bufferedImage = ImageIO.read(shopLogoFile);
            shopLogo = SwingFXUtils.toFXImage(bufferedImage, null);
            companyLogo.setImage(shopLogo);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
