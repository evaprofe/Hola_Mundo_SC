package hola_mundo_SB;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXML_HolaMundoController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Label lbTexto;

    @FXML
    private void handleButtonAction (ActionEvent event) {
        System.out.println("You clicked me!");
        lbTexto.setText("Hello World!");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
// TODO
    }
}
