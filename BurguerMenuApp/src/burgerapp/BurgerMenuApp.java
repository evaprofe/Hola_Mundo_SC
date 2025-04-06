package burgerapp;
/**Application: Clase base para cualquier aplicación JavaFX.

Scene y Stage: Representan la ventana principal y el contenido de la aplicación.

GridPane, VBox, HBox: Contenedores para organizar los elementos de la interfaz.

Label, ComboBox, CheckBox, RadioButton, Button: Controles de la interfaz gráfica.

Insets, Pos: Clases para manejar márgenes y alineación.*/

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BurgerMenuApp extends Application {
    
    // Precio base del menú
    private final double PRECIO_BASE = 8.0;
    
    // Controles para las opciones obligatorias
    private ComboBox<String> cmbHamburguesa;
    private ComboBox<String> cmbPan;
    private ComboBox<String> cmbPatatas;
    private ComboBox<String> cmbBebida;
    
    // Controles para las opciones extra
    private CheckBox chkDoble;
    private CheckBox chkQueso;
    private CheckBox chkExtraPatatas;
    
    // Controles para las salsas
    private CheckBox chkKetchup;
    private CheckBox chkBarbacoa;
    private CheckBox chkMostaza;
    private CheckBox chkThai;
    
    // Controles para el tipo de entrega
    private RadioButton radDomicilio;
    private RadioButton radLocal;
    
    // Controles para mostrar el precio
    private Label lblPrecioBase;
    private Label lblPrecioExtras;
    private Label lblPrecioSubtotal;
    private Label lblPrecioIVA;
    private Label lblPrecioTotal;
    
    @Override
    public void start(Stage primaryStage) {
        // Creamos los controles para las opciones obligatorias
        Label lblHamburguesa = new Label("Tipo de hamburguesa:");
        cmbHamburguesa = new ComboBox<>();
        cmbHamburguesa.getItems().addAll("Pollo", "Cerdo", "Ternera (+1?)", "Vegana (+1?)");
        cmbHamburguesa.setValue("Pollo");
        cmbHamburguesa.setOnAction(e -> actualizarPrecio());
        
        Label lblPan = new Label("Tipo de pan:");
        cmbPan = new ComboBox<>();
        cmbPan.getItems().addAll("Normal", "Integral", "Centeno");
        cmbPan.setValue("Normal");
        
        Label lblPatatas = new Label("Tipo de patatas:");
        cmbPatatas = new ComboBox<>();
        cmbPatatas.getItems().addAll("Fritas", "Gajo", "Caseras (+1?)");
        cmbPatatas.setValue("Fritas");
        cmbPatatas.setOnAction(e -> actualizarPrecio());
        
        Label lblBebida = new Label("Bebida:");
        cmbBebida = new ComboBox<>();
        cmbBebida.getItems().addAll("Refresco de cola", "Refresco de naranja", "Refresco de limón", "Agua", "Cerveza");
        cmbBebida.setValue("Refresco de cola");
        
        // Creamos los controles para las opciones extra
        Label lblExtras = new Label("Extras:");
        lblExtras.setStyle("-fx-font-weight: bold;");
        
        chkDoble = new CheckBox("Hamburguesa doble (+2?)");
        chkDoble.setOnAction(e -> actualizarPrecio());
        
        chkQueso = new CheckBox("Extra de queso (+0,50?)");
        chkQueso.setOnAction(e -> actualizarPrecio());
        
        chkExtraPatatas = new CheckBox("Extra de patatas (+1?)");
        chkExtraPatatas.setOnAction(e -> actualizarPrecio());
        
        // Creamos los controles para las salsas
        Label lblSalsas = new Label("Salsas (+0,50? cada una):");
        lblSalsas.setStyle("-fx-font-weight: bold;");
        
        chkKetchup = new CheckBox("Ketchup");
        chkKetchup.setOnAction(e -> actualizarPrecio());
        
        chkBarbacoa = new CheckBox("Barbacoa");
        chkBarbacoa.setOnAction(e -> actualizarPrecio());
        
        chkMostaza = new CheckBox("Mostaza");
        chkMostaza.setOnAction(e -> actualizarPrecio());
        
        chkThai = new CheckBox("Thai");
        chkThai.setOnAction(e -> actualizarPrecio());
        
        // Creamos los controles para el tipo de entrega
        Label lblEntrega = new Label("Tipo de entrega:");
        lblEntrega.setStyle("-fx-font-weight: bold;");
        
        radDomicilio = new RadioButton("Reparto a domicilio");
        radLocal = new RadioButton("Recogida en local (-20%)");
        
        ToggleGroup groupEntrega = new ToggleGroup();
        radDomicilio.setToggleGroup(groupEntrega);
        radLocal.setToggleGroup(groupEntrega);
        radDomicilio.setSelected(true);
        
        radDomicilio.setOnAction(e -> actualizarPrecio());
        radLocal.setOnAction(e -> actualizarPrecio());
        
        // Creamos los controles para mostrar el precio
        Label lblResumen = new Label("Resumen del pedido:");
        lblResumen.setStyle("-fx-font-weight: bold;");
        
        GridPane gridPrecio = new GridPane();
        gridPrecio.setHgap(10);
        gridPrecio.setVgap(5);
        
        gridPrecio.add(new Label("Precio base:"), 0, 0);
        lblPrecioBase = new Label(String.format("%.2f?", PRECIO_BASE));
        gridPrecio.add(lblPrecioBase, 1, 0);
        
        gridPrecio.add(new Label("Extras:"), 0, 1);
        lblPrecioExtras = new Label("0,00?");
        gridPrecio.add(lblPrecioExtras, 1, 1);
        
        gridPrecio.add(new Label("Subtotal:"), 0, 2);
        lblPrecioSubtotal = new Label(String.format("%.2f?", PRECIO_BASE));
        gridPrecio.add(lblPrecioSubtotal, 1, 2);
        
        gridPrecio.add(new Label("IVA (21%):"), 0, 3);
        lblPrecioIVA = new Label(String.format("%.2f?", PRECIO_BASE * 0.21));
        gridPrecio.add(lblPrecioIVA, 1, 3);
        
        gridPrecio.add(new Label("TOTAL:"), 0, 4);
        lblPrecioTotal = new Label(String.format("%.2f?", PRECIO_BASE * 1.21));
        lblPrecioTotal.setStyle("-fx-font-weight: bold;");
        gridPrecio.add(lblPrecioTotal, 1, 4);
        
        // Creamos el botón para realizar el pedido
        Button btnPedir = new Button("Realizar Pedido");
        btnPedir.setStyle("-fx-font-weight: bold; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        btnPedir.setPrefWidth(200);
        btnPedir.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Pedido Realizado");
            alert.setHeaderText("¡Gracias por tu pedido!");
            alert.setContentText("Tu pedido ha sido registrado correctamente.\nPrecio total: " + lblPrecioTotal.getText());
            alert.showAndWait();
        });
        
        // Creamos el layout para las opciones obligatorias
        GridPane gridObligatorias = new GridPane();
        gridObligatorias.setHgap(10);
        gridObligatorias.setVgap(10);
        gridObligatorias.add(lblHamburguesa, 0, 0);
        gridObligatorias.add(cmbHamburguesa, 1, 0);
        gridObligatorias.add(lblPan, 0, 1);
        gridObligatorias.add(cmbPan, 1, 1);
        gridObligatorias.add(lblPatatas, 0, 2);
        gridObligatorias.add(cmbPatatas, 1, 2);
        gridObligatorias.add(lblBebida, 0, 3);
        gridObligatorias.add(cmbBebida, 1, 3);
        
        // Creamos el layout para las opciones extra
        VBox vboxExtras = new VBox(5);
        vboxExtras.getChildren().addAll(lblExtras, chkDoble, chkQueso, chkExtraPatatas);
        
        // Creamos el layout para las salsas
        VBox vboxSalsas = new VBox(5);
        vboxSalsas.getChildren().addAll(lblSalsas, chkKetchup, chkBarbacoa, chkMostaza, chkThai);
        
        // Creamos el layout para el tipo de entrega
        VBox vboxEntrega = new VBox(5);
        vboxEntrega.getChildren().addAll(lblEntrega, radDomicilio, radLocal);
        
        // Creamos el layout para las opciones adicionales
        HBox hboxAdicionales = new HBox(20);
        hboxAdicionales.getChildren().addAll(vboxExtras, vboxSalsas, vboxEntrega);
        
        // Creamos el layout para el resumen y el botón
        VBox vboxResumen = new VBox(10);
        vboxResumen.getChildren().addAll(lblResumen, gridPrecio, btnPedir);
        vboxResumen.setAlignment(Pos.CENTER);
        
        // Creamos el layout principal
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.getChildren().addAll(gridObligatorias, hboxAdicionales, vboxResumen);
        
        // Creamos la escena
        Scene scene = new Scene(root, 600, 600);
        
        // Configuramos y mostramos el stage
        primaryStage.setTitle("Burger Menu App");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        // Actualizamos el precio inicial
        actualizarPrecio();
    }
    
    // Método para actualizar el precio según las opciones seleccionadas
    private void actualizarPrecio() {
        double precioBase = PRECIO_BASE;
        double precioExtras = 0;
        
        // Calculamos el precio según el tipo de hamburguesa
        String hamburguesa = cmbHamburguesa.getValue();
        if (hamburguesa.contains("Ternera") || hamburguesa.contains("Vegana")) {
            precioExtras += 1;
        }
        
        // Calculamos el precio según el tipo de patatas
        String patatas = cmbPatatas.getValue();
        if (patatas.contains("Caseras")) {
            precioExtras += 1;
        }
        
        // Calculamos el precio de los extras
        if (chkDoble.isSelected()) {
            precioExtras += 2;
        }
        if (chkQueso.isSelected()) {
            precioExtras += 0.5;
        }
        if (chkExtraPatatas.isSelected()) {
            precioExtras += 1;
        }
        
        // Calculamos el precio de las salsas
        if (chkKetchup.isSelected()) {
            precioExtras += 0.5;
        }
        if (chkBarbacoa.isSelected()) {
            precioExtras += 0.5;
        }
        if (chkMostaza.isSelected()) {
            precioExtras += 0.5;
        }
        if (chkThai.isSelected()) {
            precioExtras += 0.5;
        }
        
        // Calculamos el subtotal
        double subtotal = precioBase + precioExtras;
        
        // Aplicamos el descuento si es recogida en local
        if (radLocal.isSelected()) {
            subtotal = subtotal * 0.8; // 20% de descuento
        }
        
        // Calculamos el IVA (21%)
        double iva = subtotal * 0.21;
        
        // Calculamos el total
        double total = subtotal + iva;
        
        // Actualizamos las etiquetas
        lblPrecioBase.setText(String.format("%.2f??", precioBase));
        lblPrecioSubtotal.setText(String.format("%.2f?", subtotal));
        lblPrecioIVA.setText(String.format("%.2f?", iva));
        lblPrecioTotal.setText(String.format("%.2f?", total));
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
