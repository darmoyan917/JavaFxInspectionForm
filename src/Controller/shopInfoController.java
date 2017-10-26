package Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Dialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;



import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class shopInfoController {
    @FXML private ImageView companyLogo;
    public List<Node> middlePaneContent = new ArrayList<>();
    @FXML public AnchorPane shopInfoMiddlePane;

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(shopInfoMiddlePane.getChildren());
        return  middlePaneContent;
    }

   @FXML public void setCompanyLogo()  {
       FileChooser fc = new FileChooser();
       fc.setTitle("Choose Shop Logo");
       fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("JPG", "*.jpg"),
               new FileChooser.ExtensionFilter("GIF", "*.gif"),
               new FileChooser.ExtensionFilter("BMP", "*.bmp"),
               new FileChooser.ExtensionFilter("PNG", "*.png")
       );
       File shopLogoFile =  fc.showOpenDialog(companyLogo.getScene().getWindow());
       try {
           BufferedImage bufferedImage = ImageIO.read(shopLogoFile);
           Image image = SwingFXUtils.toFXImage(bufferedImage, null);
           companyLogo.setImage(image);
       } catch (IOException ex) {
           dialogMessageBox("Image not Found.");
       }
    }
    private void dialogMessageBox(String message){
        Dialog dialog = new Dialog();
        dialog.setHeaderText(message);
        dialog.setContentText("Please check your file, something went wrong");
        dialog.show();
    }
}
