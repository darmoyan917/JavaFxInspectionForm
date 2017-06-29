package mainPackage;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class printPageController {
    private static mainController controller;

    @FXML
    public AnchorPane TopPane;

    public Node setNode (AnchorPane pane){
        try {
            TopPane = pane;
            TopPane.applyCss();
            TopPane.layout();
        }catch (NullPointerException e){
            //System.out.print(e);
        }
        return TopPane;
    }
    public Node getNode(){
        return TopPane;
    }
    public void printNode(){
        Node node = TopPane;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainLayout.fxml"));
        controller = loader.getController();
        controller.printPage(node);
    }

}
