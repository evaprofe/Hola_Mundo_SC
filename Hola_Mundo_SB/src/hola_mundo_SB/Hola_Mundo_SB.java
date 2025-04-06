package hola_mundo_SB;

/**
 *
 * @author evgoab
 */




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Hola_Mundo_SB extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Button btn1 = new Button("Hola MUNDO !");
        
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hola Mundo");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn1);
        
        Scene scene = new Scene(root, 300, 200);
        
        stage.setScene(scene);
        stage.setTitle("Primera Aplicación JavaFX");
        stage.show();
    }
}

