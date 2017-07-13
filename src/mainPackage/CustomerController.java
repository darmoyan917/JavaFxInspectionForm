package mainPackage;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    @FXML
    private AnchorPane customerMiddlePane;
    public List<Node> middlePaneContent = new ArrayList<>();

    public List<Node> getmiddlePane(){
        middlePaneContent.addAll(customerMiddlePane.getChildren());
        return  middlePaneContent;
    }

}
