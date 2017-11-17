package Controller;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class shopInfoController {
    @FXML private ImageView companyLogo;
    public List<Node> middlePaneContent = new ArrayList<>();
    @FXML public AnchorPane shopInfoMiddlePane;
    private Image shopLogo;
    @FXML private GridPane textFieldPane;
    private File shopInfoFile = null;

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(shopInfoMiddlePane.getChildren());
        return  middlePaneContent;
    }

   @FXML private void setCompanyLogo()  {
       FileChooser fc = new FileChooser();
       fc.setTitle("Choose Shop Logo");
       fc.getExtensionFilters().addAll(
               new FileChooser.ExtensionFilter("JPG", "*.jpg"),
               new FileChooser.ExtensionFilter("GIF", "*.gif"),
               new FileChooser.ExtensionFilter("BMP", "*.bmp"),
               new FileChooser.ExtensionFilter("PNG", "*.png")
       );
       File shopLogoFile =  fc.showOpenDialog(companyLogo.getScene().getWindow());
       if(shopLogoFile != null) {
           try {
               BufferedImage bufferedImage = ImageIO.read(shopLogoFile);
               shopLogo = SwingFXUtils.toFXImage(bufferedImage, null);
               companyLogo.setImage(shopLogo);
           } catch (IOException ex) {
               dialogMessageBox("Image not Found.", "Please check your file, something went wrong");
           }
       }
    }
    private void dialogMessageBox(String headerText,String content){
        Dialog dialog = new Dialog();
        dialog.setHeaderText(headerText);
        dialog.setContentText(content);
        dialog.showAndWait();
    }
    @FXML private void saveButton(){
        if(shopLogo!=null)
        saveFile(companyLogo.getImage());
        setShopInfoToFile(textFieldPane);
    }
    private void saveFile(Image image) {
        Boolean choice =false;
        File outputFile = new File("../JavaFxInspectionForm/src/images/companyLogo.png");
        if(outputFile.exists() && !outputFile.isDirectory() && image !=null){
             choice = fileExistsWindow("../View/fileExistsWindow.fxml", new fileExistsController());
        }
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        if (choice) {
            try {
                ImageIO.write(bImage, "png", outputFile);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    // Opens confirmation window for overwriting Company logo file.
    @FXML private Boolean fileExistsWindow (String viewFXML, Object fileController){
        Stage fileExistsStage = new Stage();
        Boolean choice = false;
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(viewFXML));
            Parent root = loader.load();
            fileExistsStage.setScene(new Scene(root, 450, 250));
            fileExistsStage.initStyle(StageStyle.UNDECORATED);
            fileExistsStage.setResizable(false);
            fileExistsStage.initOwner(companyLogo.getScene().getWindow());
            fileExistsStage.initModality(Modality.APPLICATION_MODAL);
            fileExistsStage.getIcons().add(new Image("/images/icon.png"));
            fileController = loader.getController();
            if(fileController instanceof fileExistsController){
                ((fileExistsController)fileController).openStoredLogo();
                fileExistsStage.showAndWait();
                choice = ((fileExistsController)fileController).getChoice();
            }
            else if (fileController instanceof shopInfoExistsController){
                ((shopInfoExistsController)fileController).openStoredShopInfo();
                fileExistsStage.showAndWait();
                choice = ((shopInfoExistsController)fileController).getChoice();
            }
            else
                System.out.println("None of the above");

            fileExistsStage.close();
        }catch (IOException e){
        }
        return choice;
    }
    // Clear all fields in shop info page.
    @FXML private void clearButtonAction(){
        clearFields(textFieldPane);
    }
    private void clearFields(GridPane textGroup) {
        for (Node textNode : textGroup.getChildren()) {
              if(textNode instanceof GridPane){
                    for(Node Text: ((GridPane) textNode).getChildren())
                        ((TextField) Text).clear();
              }else if (textNode instanceof TextArea){
                  ((TextArea) textNode).clear();
              }else if(textNode instanceof ImageView){
                  ((ImageView) textNode).setImage(null);
            }
        }
    }
    public void setShopInfoToFile(GridPane shopInfoGroup){
            shopInfoFile = new File( "../JavaFxInspectionForm/src/Resources/shopInfo.txt");

            try{
                if(!shopInfoFile.exists()){
                    shopInfoFile.createNewFile();
                }
                else if (shopInfoFile.exists()){
                    if(fileExistsWindow("../View/shopInfoExistsWindow.fxml", new shopInfoExistsController())) {
                        shopInfoFile.delete();
                        shopInfoFile.createNewFile();
                    }
                }
                try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(shopInfoFile,true)))){
                    for (Node textNode : shopInfoGroup.getChildren()) {
                        if(textNode instanceof GridPane){
                            for(Node Text: ((GridPane) textNode).getChildren())
                               out.println( ((TextField) Text).getText());
                        }else if (textNode instanceof TextArea){
                            out.println(((TextArea) textNode).getText());
                        }
                    }
                } catch (IOException ex) {

            }
            }catch(IOException x){

            }
    }
}
