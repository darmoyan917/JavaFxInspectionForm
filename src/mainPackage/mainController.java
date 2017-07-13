package mainPackage;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class mainController {
    private static printPageController printController;
    private static ModelInterface modelDAO = null;
    private List<Node> InspectionmiddlePaneContent = new ArrayList<>();
    private List<Node> CustomermiddlePaneContent = new ArrayList<>();
    private AnchorPane printAnchorPane = new AnchorPane();
    public int selectedMakeIndex;
    private Stage aboutStage = new Stage();
    private Stage printStage = new Stage();
    @FXML
    private AnchorPane middlePane;
    @FXML
    private Label leftStatusLabel;
    @FXML
    private ChoiceBox<String> makeChoiceBox;
    @FXML
    private ChoiceBox<String> modelChoiceBox;
    ObservableList<String> make = FXCollections.observableArrayList("", "ACURA", "ASTON MARTIN" ,"AUDI" ,"BENTLEY" ,"BMW" ,"BUGATTI" ,"BUICK" ,"CADILLAC" ,"CHEVROLET" ,"CHRYSLER" ,"DODGE","FERRARI" ,"FIAT" ,
            "FORD" ,"GMC","HONDA" ,"HYUNDAI" ,"INFINITI" ,"ISUZU" ,"JAGUAR" ,"JEEP" ,"KIA" ,"LAMBORGHINI" ,"LAND ROVER" ,"LEXUS" ,"LINCOLN",
            "LOTUS" ,"MASERATI" ,"MAYBACH" ,"MAZDA" ,"MCLAREN" ,"MERCEDES BENZ" ,"MERCURY" ,"MINI" ,"MITSUBISHI" ,"NISSAN" ,"OLDSMOBILE" ,"PLYMOUTH" ,
            "PONTIAC", "PORSCHE","RANGE ROVER" ,"ROLLS ROYCE" ,"SAAB" ,"SATURN" ,"SCION" ,"SHELBY" ,"SMART CARS" ,"SUBARU" ,"SUZUKI" ,"TESLA",
            "TOYOTA" ,"VOLKSWAGEN" ,"VOLVO" );

    public void setMakeChoiceBox() {
    this.makeChoiceBox.setItems(make);
    }
    @FXML
    public void makeChoiceBoxActionEvent(){
        makeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedMakeIndex = (int) newValue;
                setModelChoiceBox();
            }
        });
    }
    public void setMiddlePaneContent(List<Node> node){
        CustomermiddlePaneContent = node;
    }

    @FXML
    private void setCustomerToMiddlePane(){
        if(!middlePane.getChildren().isEmpty()) {
            if(!middlePane.getChildren().equals(CustomermiddlePaneContent)) {
                InspectionmiddlePaneContent.clear();
                InspectionmiddlePaneContent.addAll(middlePane.getChildren());
            }
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(CustomermiddlePaneContent);
        }
    }
    @FXML
    private void setInspectionToMiddlePane(){
        if(middlePane.getChildren().equals(CustomermiddlePaneContent)) {
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(InspectionmiddlePaneContent);
            middlePane.setVisible(true);
        }
    }

    private void setModelChoiceBox(){
        if(selectedMakeIndex > 0) {
            String selectedMake = make.get(selectedMakeIndex);
            modelDAO = InterfaceFactory.getModelDAO(selectedMake);
            String[] ModelCombo = modelDAO.SetModelCombo();
            ObservableList<String> model = FXCollections.observableArrayList(ModelCombo);
            this.modelChoiceBox.getItems().removeAll();
            this.modelChoiceBox.setItems(model);
        }
    }

    @FXML
    private void exitButtonAction() {
        Platform.exit();
    }
    @FXML
    private void openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.showOpenDialog(leftStatusLabel.getScene().getWindow());
    }
    @FXML
    private void saveFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.showSaveDialog(leftStatusLabel.getScene().getWindow());
    }
    @FXML
    private void openAboutWindow (){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("aboutLayout.fxml"));
            aboutStage.setScene(new Scene(root, 600, 400));
            aboutStage.initStyle(StageStyle.UNDECORATED);
            aboutStage.setResizable(false);
            aboutStage.getIcons().add(new Image("/images/icon.png"));
            aboutStage.show();
        }catch (IOException e){
        }
    }

    @FXML
    private void printPage() throws Exception{
        FXMLLoader printLoader = new FXMLLoader(getClass().getResource("printPageLayout.fxml"));
        Parent root = printLoader.load();
        printController = printLoader.getController();
        printStage.setScene(new Scene(root, 1000, 800));
        printStage.show();
        printAnchorPane = printController.getNode();
        printPage(printAnchorPane);

    }
    public void printPage(Node node){
        Printer printer = Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.DEFAULT);
        double scaleX = pageLayout.getPrintableWidth() / node.getBoundsInParent().getWidth();
        double scaleY = pageLayout.getPrintableHeight() / node.getBoundsInParent().getHeight();
        node.getTransforms().add(new Scale(scaleX, scaleY));
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null) {
            boolean print = job.showPrintDialog(leftStatusLabel.getScene().getWindow());
            if (print) {
                boolean success = job.printPage(node);
                if (success) {
                    job.endJob();
                }
            }
        }

        printStage.hide();
        leftStatusLabel.setText("Print Completed");
    }
    @FXML
    private void runWorldpac() {
        Thread t1 = new Thread(new runWorldpac());

    }
    }
