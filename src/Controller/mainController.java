package Controller;

import Model.*;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.scene.paint.Color.*;

public class mainController {
    //................................................Variable Delcaration...................................................
        private static printPageController printController;
        private static ModelInterface modelDAO = null;
        public int selectedMakeIndex;
    private List<Node> InspectionmiddlePaneContent = new ArrayList<>();
    private List<Node> inspectionMiddlePaneContent = new ArrayList<>();
    private List<Node> shopInfoMiddlePaneContent = new ArrayList<>();
    private List<Node> CustomermiddlePaneContent = new ArrayList<>();
    private List<Node> vehiclesMiddlePaneContent = new ArrayList<>();
    private List<Node> printMiddlePaneContent = new ArrayList<>();
        private Stage printStage = new Stage();
    private AnchorPane printAnchorPane = new AnchorPane();
    @FXML private AnchorPane middlePane;
    @FXML private GridPane upperGrid1;
    @FXML private GridPane upperGrid2;
    @FXML private SplitPane splitPane;
        @FXML private ChoiceBox<String> makeChoiceBox;
        @FXML private ChoiceBox<String> modelChoiceBox;
    @FXML private TextField oilConditionText;
    @FXML private TextField transFluidText;
    @FXML private TextField airFilterText;
    @FXML private TextField beltsText;
    @FXML private TextArea drivetrainTextArea;
    @FXML private TextField yearText;
    @FXML private TextField vinText;
    @FXML private TextField mileageText;
    @FXML private TextField techNameText;
        @FXML private ToggleButton oilConditionBad;
        @FXML private ToggleButton airFilterBad;
        @FXML private ToggleButton beltsBad;
        @FXML private ToggleButton transFluidBad;
        @FXML private ToggleButton goodToggle;
    @FXML private Slider valveCoverSlider;
    @FXML private Slider oilPanSlider;
    @FXML private Slider timingCoverSlider;
    @FXML private Slider filterHousingSlider;
    @FXML private Slider oilCoolerSlider;
        @FXML private Label valveCoverLabel;
        @FXML private Label leftStatusLabel;
        @FXML private Label oilPanLabel;
        @FXML private Label timingCoverLabel;
        @FXML private Label filterHousingLabel;
        @FXML private Label oilCoolerLabel;
        @FXML private Label upperDetailsLabel;
    @FXML private ImageView lowerDetailsImage;
        @FXML private DatePicker inspectionDate;
    @FXML private ColorPicker colorPick;
    private Image valveCoverImage = new Image("images/valve_Cover.jpg");

    ObservableList<String> make = observableArrayList("", "ACURA", "ASTON MARTIN" ,"AUDI" ,"BENTLEY" ,"BMW" ,"BUGATTI" ,"BUICK" ,"CADILLAC" ,
            "CHEVROLET" ,"CHRYSLER" ,"DODGE","FERRARI" ,"FIAT" , "FORD" ,"GMC","HONDA" ,"HYUNDAI" ,"INFINITI" ,"ISUZU" ,"JAGUAR" ,"JEEP" ,"KIA" ,
            "LAMBORGHINI" ,"LAND ROVER" ,"LEXUS" ,"LINCOLN", "LOTUS" ,"MASERATI" ,"MAYBACH" ,"MAZDA" ,"MCLAREN" ,"MERCEDES BENZ" ,"MERCURY" ,
            "MINI" ,"MITSUBISHI" ,"NISSAN" ,"OLDSMOBILE" ,"PLYMOUTH" , "PONTIAC", "PORSCHE","RANGE ROVER" ,"ROLLS ROYCE" ,"SAAB" ,"SATURN" ,
            "SCION" ,"SHELBY" ,"SMART CARS" ,"SUBARU" ,"SUZUKI" ,"TESLA", "TOYOTA" ,"VOLKSWAGEN" ,"VOLVO" );
    public List<Node> getmiddlePane(){
        InspectionmiddlePaneContent.addAll(middlePane.getChildren());
        return  InspectionmiddlePaneContent;
    }

    //............................Upper Details label text..............................................

    public void setUpperDefaultListener(){
        for (Node node: upperGrid1.getChildren()){
            setFocusLostListeners(node);
        }
        for (Node node: upperGrid2.getChildren()){
            setFocusLostListeners(node);
        }
    }

    @FXML private void setUpperDetailsLabelYear(){
        upperDetailsLabel.setText("Enter the vehicle year");
    }
    @FXML private void setUpperDetailsLabelColor(){
        upperDetailsLabel.setText("Select the Color");
    }
    @FXML private void setUpperDetailsLabelMake(){
        upperDetailsLabel.setText("Select the manufacturer of the vehicle");
    }
    @FXML private void setUpperDetailsLabelModel(){
        upperDetailsLabel.setText("Select vehicle model from the drop down menu");
    }
    @FXML private void setUpperDetailsLabelDate(){
        upperDetailsLabel.setText("Select the inspection date");
    }
    @FXML private void setUpperDetailsLabelVin(){
        upperDetailsLabel.setText("Enter a valid 17 digit VIN number");
    }
    @FXML private void setUpperDetailsLabelMileage(){
        upperDetailsLabel.setText("Enter vehicle's current mileage");
    }
    @FXML private void setUpperDetailsLabelTechnician(){
        upperDetailsLabel.setText("Enter your name");
    }
    @FXML private void setUpperDetailsLabelDefault(){
       upperDetailsLabel.setText("Select one of the options to the left in order to get more details about it.");
    }

    // .............................CHANGE LISTENERS......................................................
    public void setChangeListeners(){
        //............................DividerResizeListeners..............................................
        splitPane.getDividers().get(1).positionProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                showDrivetrainComments();
            }
        });
        splitPane.getDividers().get(0).positionProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                showDrivetrainComments();
            }
        });
        //....................................makeChoiceBoxListener......................
        makeChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                selectedMakeIndex = (int) newValue;
                setModelChoiceBox();
            }
        });
        //....................................Text Field Listener..........................................
        yearText.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    // Check if the new character is greater than LIMIT
                    if (yearText.getText().length() >= 4) {

                        yearText.setText(yearText.getText().substring(0, 4));
                    }
                }
            }
        });
        yearText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    yearText.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
        vinText.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (vinText.getText().length() >= 17) {
                        vinText.setText(vinText.getText().substring(0, 17));
                    }
                }
            }
        });
        vinText.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                vinText.setText(newValue.toUpperCase());
            }
        });
        mileageText.lengthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                         if (mileageText.getText().length() >= 6) {
                             mileageText.setText(mileageText.getText().substring(0, 6));
                    }
                }
            }
        });
        mileageText.textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed (ObservableValue < ? extends String > observable, String oldValue, String newValue){
                    if (!newValue.matches("\\d*"))
                        mileageText.setText(newValue.replaceAll("[^\\d]", ""));
                     /* if (newValue != "") {
                            if (newValue != oldValue) {
                                try {
                                     Number formatted = NumberFormat.getNumberInstance(Locale.US).parse(newValue);
                                     mileageText.setText(String.format("%,d", formatted));
                                } catch (ParseException e) {
                                }
                            }
                        }*/
            }
        });
        //.......................UpperDetailLabelFocusLost...............................
    }

    public void setFocusLostListeners(Node node){
        node.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> arg0, Boolean focusLost, Boolean focusGained) {
                if(focusLost)
                    setUpperDetailsLabelDefault();
            }
        });
    }
    //..............................DRIVETRAIN....AND....OIL LEAKS...................................
    @FXML private void setOilConditionBad(){
        setBadToggle(oilConditionBad,oilConditionText, goodToggle);
    }
    @FXML private void setAirFilterBad(){
        setBadToggle(airFilterBad,airFilterText, goodToggle);
    }
    @FXML private void setBeltsBad(){
        setBadToggle(beltsBad,beltsText, goodToggle);
    }
    @FXML private void setTransFluidBad(){
        setBadToggle(transFluidBad,transFluidText,goodToggle);
    }
    @FXML private void setBadToggle(ToggleButton button, TextField text,ToggleButton goodButton){
        if (button.isSelected()){
            text.setVisible(true);
        }
        else if(goodButton.isSelected()){
            text.setVisible(false);
        }
        else {
            text.setVisible(false);
        }
    }
    @FXML private void setValveCoverSlider(){
        setOilLeaks(valveCoverSlider,valveCoverLabel);
            lowerDetailsImage.setImage(valveCoverImage);
        }
    @FXML private void setOilPanSlider(){
        setOilLeaks(oilPanSlider, oilPanLabel);
    }
    @FXML private void setTimingCoverSlider(){
        setOilLeaks(timingCoverSlider,timingCoverLabel);
    }
    @FXML private void setFilterHousingSlider(){
        setOilLeaks(filterHousingSlider,filterHousingLabel);
    }
    @FXML private void setOilCoolerSlider(){
        setOilLeaks(oilCoolerSlider,oilCoolerLabel);
    }
    private void setOilLeaks(Slider oilLeakSlider, Label oilLeakLabel){
        if(oilLeakSlider.getValue()>= 5 && oilLeakSlider.getValue() <= 9 ){
            oilLeakLabel.setTextFill(ORANGE);
            oilLeakLabel.setText(" Moderate");
        }
        else if(oilLeakSlider.getValue() > 9){
            oilLeakLabel.setTextFill(RED);
            oilLeakLabel.setText(" Heavy");
        }
        else if(oilLeakSlider.getValue() <5 && oilLeakSlider.getValue() >2 ){
            oilLeakLabel.setTextFill(BLACK);
            oilLeakLabel.setText(" Slight");
        }
        else if(oilLeakSlider.getValue() <=2 ){
            oilLeakLabel.setTextFill(GREEN);
            oilLeakLabel.setText(" None");
        }
    }
    @FXML public void showDrivetrainComments(){
        if(middlePane.getWidth() > 1095){
            drivetrainTextArea.setVisible(true);
        }
        else {
           drivetrainTextArea.setVisible(false);
        }
    }

    //..........................END    DRIVETRAIN    AND   OIL LEAKS....................................
    public void setMakeChoiceBox() {
    this.makeChoiceBox.setItems(make);
    }
    public void setCustomerMiddlePaneContent(List<Node> node){
        CustomermiddlePaneContent = node;
    }
    public void setVehiclesMiddlePaneContent(List<Node> node){
        vehiclesMiddlePaneContent = node;
    }
    public void setInspectionmiddlePaneContent(List<Node> node){
        inspectionMiddlePaneContent = node;
    }
    public void setShopInfoMiddlePaneContent(List<Node> node){
        shopInfoMiddlePaneContent = node;
    }
    //................................Set the Middle Pane Main View.....................................

    @FXML public void setCustomersToMiddlePane(){
        if(!middlePane.getChildren().isEmpty()) {
            if(!middlePane.getChildren().equals(CustomermiddlePaneContent)) {
                if(middlePane.getChildren().equals(inspectionMiddlePaneContent)) {
                    inspectionMiddlePaneContent.clear();
                    inspectionMiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(vehiclesMiddlePaneContent)){
                    vehiclesMiddlePaneContent.clear();
                    vehiclesMiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(shopInfoMiddlePaneContent)){
                    shopInfoMiddlePaneContent.clear();
                    shopInfoMiddlePaneContent.addAll(middlePane.getChildren());
                }
            }
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(CustomermiddlePaneContent);
        }
    }

    @FXML public void setInspectionToMiddlePane(){
        if(!middlePane.getChildren().isEmpty()) {
            if(!middlePane.getChildren().equals(inspectionMiddlePaneContent)){
                if (middlePane.getChildren().equals(CustomermiddlePaneContent)) {
                    CustomermiddlePaneContent.clear();
                    CustomermiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if (middlePane.getChildren().equals(vehiclesMiddlePaneContent)) {
                    vehiclesMiddlePaneContent.clear();
                    vehiclesMiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(shopInfoMiddlePaneContent)){
                    shopInfoMiddlePaneContent.clear();
                    shopInfoMiddlePaneContent.addAll(middlePane.getChildren());
                }
            }
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(inspectionMiddlePaneContent);
        }
    }

    @FXML public void setVehiclesToMiddlePane() {
        if(!middlePane.getChildren().isEmpty()) {
            if(!middlePane.getChildren().equals(vehiclesMiddlePaneContent)) {
                if(middlePane.getChildren().equals(inspectionMiddlePaneContent)) {
                    inspectionMiddlePaneContent.clear();
                    inspectionMiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(CustomermiddlePaneContent)){
                    CustomermiddlePaneContent.clear();
                    CustomermiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(shopInfoMiddlePaneContent)){
                    shopInfoMiddlePaneContent.clear();
                    shopInfoMiddlePaneContent.addAll(middlePane.getChildren());
                }
            }
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(vehiclesMiddlePaneContent);
        }
    }

    @FXML public void setShopInfoToMiddlePane() {
        if(!middlePane.getChildren().isEmpty()) {
            if(!middlePane.getChildren().equals(shopInfoMiddlePaneContent)) {
                if(middlePane.getChildren().equals(inspectionMiddlePaneContent)) {
                    inspectionMiddlePaneContent.clear();
                    inspectionMiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if(middlePane.getChildren().equals(CustomermiddlePaneContent)){
                    CustomermiddlePaneContent.clear();
                    CustomermiddlePaneContent.addAll(middlePane.getChildren());
                }
                else if (middlePane.getChildren().equals(vehiclesMiddlePaneContent)) {
                    vehiclesMiddlePaneContent.clear();
                    vehiclesMiddlePaneContent.addAll(middlePane.getChildren());
                }
            }
            middlePane.getChildren().clear();
            middlePane.getChildren().addAll(shopInfoMiddlePaneContent);
        }
    }
    //.........................................................................................................
    @FXML private void setModelChoiceBox(){
        if(selectedMakeIndex > 0) {
            String selectedMake = make.get(selectedMakeIndex);
            modelDAO = InterfaceFactory.getModelDAO(selectedMake);
            String[] ModelCombo = modelDAO.SetModelCombo();
            ObservableList<String> model = observableArrayList(ModelCombo);
            this.modelChoiceBox.getItems().removeAll();
            this.modelChoiceBox.setItems(model);
        }
        else if(selectedMakeIndex <=0){
            this.modelChoiceBox.getItems().removeAll();
        }
    }
    @FXML private void exitButtonAction() {
        Platform.exit();
    }
    @FXML private void openFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.showOpenDialog(leftStatusLabel.getScene().getWindow());
    }
    @FXML private void saveFileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        fileChooser.showSaveDialog(leftStatusLabel.getScene().getWindow());
    }
    @FXML private void openAboutWindow (){
        Stage aboutStage = new Stage();
        try{
            Parent root = FXMLLoader.load(getClass().getResource("../View/aboutLayout.fxml"));
            aboutStage.setScene(new Scene(root, 600, 400));
            aboutStage.initStyle(StageStyle.UNDECORATED);
            aboutStage.setResizable(false);
            aboutStage.initOwner(middlePane.getScene().getWindow());
            aboutStage.initModality(Modality.APPLICATION_MODAL);
            aboutStage.getIcons().add(new Image("/images/icon.png"));
            aboutStage.showAndWait();
            aboutStage.close();
        }catch (IOException e){
        }
    }
    @FXML private void printPage() throws Exception{
        FXMLLoader printLoader = new FXMLLoader(getClass().getResource("../View/printPageLayout.fxml"));
        Parent root = printLoader.load();
        printController = printLoader.getController();
        printStage.setScene(new Scene(root, 800, 600));
        printMiddlePaneContent.clear();
        printMiddlePaneContent.addAll( middlePane.getChildren());
        printStage.show();
        printAnchorPane = printController.setTopPane(printMiddlePaneContent);
        printStage.hide();
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
    @FXML private void runWorldpac() {
        Thread t1 = new Thread(new runWorldpac());

    }
}
