package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class printPageController {

    @FXML
    private AnchorPane TopPane;
    public AnchorPane printPane;


    public AnchorPane setTopPane(Collection<? extends Node> middlepane){
        TopPane.getChildren().clear();
        TopPane.getChildren().addAll(middlepane);
        printPane = TopPane;
        return printPane;

    }

}
