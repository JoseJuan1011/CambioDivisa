package cambioDivisa;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import cambioDivisa.Divisa;


public class CambioDivisa extends Application{
	
	private Divisa euro = new Divisa("Euro", 1.0);
	private Divisa libra = new Divisa("Libra", 0.8873);
	private Divisa dolar = new Divisa("Dolar", 1.2007);
	private Divisa yen = new Divisa("Yen", 133.59);
	private Divisa divisaOrigen;
	private Divisa divisaDestino;
	
	private VBox root;
	
	private HBox origenHBox;
	private HBox destinoHBox;
	private HBox ButtonHBox;
	
	private TextField origenTexto;
	private TextField destinoTexto;
	
	private ComboBox origenComboBox;
	private ComboBox destinoComboBox;
	
	private Button CambiarButton;
	
	private Alert alertaVariante;
	
	private Double cantidadOrigen=(double) 0;
	
	private String indice_origen;
	private String indice_destino;
	
	public void start(Stage primaryStage) throws Exception {
		
		origenTexto=new TextField("0");
		origenTexto.setPrefColumnCount(5);
		
		origenComboBox= new ComboBox();
		destinoComboBox= new ComboBox();
		
		origenHBox = new HBox(2);
		origenHBox.setAlignment(Pos.BASELINE_CENTER);
		origenHBox.setSpacing(5);
		
		destinoHBox = new HBox(2);
		destinoHBox.setAlignment(Pos.BASELINE_CENTER);
		destinoHBox.setSpacing(5);
		
		destinoTexto = new TextField();
		destinoTexto.setPrefColumnCount(5);
		destinoTexto.setEditable(false);
		
		origenComboBox.getItems().addAll("EUR","YEN","LIB","USD");
		origenComboBox.getSelectionModel().selectFirst();
		destinoComboBox.getItems().addAll("YEN","EUR","LIB","USD");
		destinoComboBox.getSelectionModel().selectFirst();
		
		origenHBox.getChildren().addAll(origenTexto,origenComboBox);
		destinoHBox.getChildren().addAll(destinoTexto,destinoComboBox);
		
		CambiarButton = new Button();
		CambiarButton.setAlignment(Pos.BASELINE_CENTER);
		CambiarButton.setText("Cambiar");
		CambiarButton.setOnAction(e -> CambioDivisas());
		
		ButtonHBox = new HBox(2);
		ButtonHBox.setAlignment(Pos.BASELINE_CENTER);
		ButtonHBox.setSpacing(5);
		ButtonHBox.getChildren().addAll(CambiarButton);
		
		root = new VBox();
		root.setSpacing(5);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(origenHBox, destinoHBox, ButtonHBox);
		
		Scene scene = new Scene(root, 320, 200);
		
		primaryStage.setTitle("Cambio de Divisa");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void CambioDivisas () {
		Prueba_Errores_VoidError(); //cantidadOrigen = Double.parseDouble(origenTexto.getText());
		Prueba_Errores_EmptyBox(); 
		/*
		indice_origen = origenComboBox.getSelectionModel().getSelectedItem().toString();
		indice_destino = destinoComboBox.getSelectionModel().getSelectedItem().toString();
		*/
		divisaOrigen = Divisas_aux(indice_origen);
		divisaDestino = Divisas_aux(indice_destino);
		Double cantidadDestino = divisaDestino.fromEuro(divisaOrigen.toEuro(cantidadOrigen));
		
		destinoTexto.setText("" + cantidadDestino);
		
	}

	public void Prueba_Errores_VoidError () {
		try {
			if (origenTexto.getText().equals("")||(origenTexto.getText().equals("0"))) {
				throw new IllegalArgumentException();
			}
			else {
				Prueba_Errores_ImproperError();
			}
		}
		
		catch (Exception e) {
			alertaVariante=new Alert(AlertType.ERROR);
			alertaVariante.setTitle("Void Error");
			alertaVariante.setHeaderText("Void Error");
			alertaVariante.setContentText("No ha introducido ningún valor, por favor, inténtelo otra vez");
			alertaVariante.showAndWait();
		}
	}

	public void Prueba_Errores_ImproperError () {
		try {
			cantidadOrigen = Double.parseDouble(origenTexto.getText());
		}
		catch (Exception e) {
			alertaVariante=new Alert(AlertType.ERROR);
			alertaVariante.setTitle("Improper Error");
			alertaVariante.setHeaderText("Improper Error");
			alertaVariante.setContentText("Ha introducido un tipo de carácter especial el cual no está permitido en este programa, solo números por favor");
			alertaVariante.showAndWait();
		}
	}
	
	public void Prueba_Errores_EmptyBox () {
		try {
			indice_origen = origenComboBox.getSelectionModel().getSelectedItem().toString();
			indice_destino = destinoComboBox.getSelectionModel().getSelectedItem().toString();
		}
		catch (Exception e) {
			alertaVariante=new Alert(AlertType.ERROR);
			alertaVariante.setTitle("EmptyBox Error");
			alertaVariante.setHeaderText("EmptyBox Error");
			alertaVariante.setContentText("No ha introducido ningún valor en los desplegables, por favor, inténtelo otra vez");
			alertaVariante.showAndWait();
		}
	}
	
	public Divisa Divisas_aux (String Seleccionado) {
		Divisa aux=new Divisa("Nulo",(double) 0);
		switch (Seleccionado) {
		case "EUR":
			aux=euro;
		break;
		case "YEN":
			aux=yen;
		break;
		case "LIB":
			aux=libra;
		break;
		case "USD":
			aux=dolar;
		break;
		}
		return aux;
	}
	
	public static void main (String args[]) {
		launch(args);
	}
}
