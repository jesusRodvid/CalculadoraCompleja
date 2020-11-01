package dad.primertrimestre;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class Aplicacion extends Application {
	Complejo complejoinput1 = new Complejo();
	Complejo complejoinput2 = new Complejo();
	Complejo complejoresult = new Complejo();

	private ComboBox<String> operatorComboBox;
	private TextField numinput1aText, numinput1bText, numinput2cText, numinput2dText, numr1Text, numr2Text;
	private Label pluslabel1, imaginarylabel1, pluslabel2, imaginarylabel2, numresultpluslabel, numresultimagilabel;
	private Separator separator;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub

		//Hbox del primer numero
				HBox numinput1HBox = new HBox(4);
				numinput1aText = new TextField("0");
				numinput1aText.setPrefColumnCount(4);
				numinput1aText.setAlignment(Pos.CENTER);
				pluslabel1 = new Label("+");
				numinput1bText = new TextField("0");
				numinput1bText.setPrefColumnCount(4);
				numinput1bText.setAlignment(Pos.CENTER);
				imaginarylabel1 = new Label("i");
				numinput1HBox.getChildren().addAll(numinput1aText, pluslabel1, numinput1bText, imaginarylabel1);
				numinput1HBox.setSpacing(5);
				numinput1HBox.setAlignment(Pos.CENTER);

				// hbox del segundo numero
				HBox numinput2HBox = new HBox(4);
				numinput2cText = new TextField("0");
				numinput2cText.setPrefColumnCount(4);
				numinput2cText.setAlignment(Pos.CENTER);
				pluslabel2 = new Label("+");
				numinput2dText = new TextField("0");
				numinput2dText.setPrefColumnCount(4);
				numinput2dText.setAlignment(Pos.CENTER);
				imaginarylabel2 = new Label("i");
				numinput2HBox.getChildren().addAll(numinput2cText, pluslabel2, numinput2dText, imaginarylabel2);
				numinput2HBox.setAlignment(Pos.CENTER);
				numinput2HBox.setSpacing(5);

				// Separator
				separator = new Separator();

				// Numero Resultado
				HBox resultHBox = new HBox(5);
				numr1Text = new TextField("0");
				numr1Text.setPrefColumnCount(5);
				numr1Text.setDisable(true);
				numr1Text.setAlignment(Pos.CENTER);
				numresultpluslabel = new Label("+");
				numr2Text = new TextField("0");
				numr2Text.setPrefColumnCount(5);
				numr2Text.setDisable(true);
				numr2Text.setAlignment(Pos.CENTER);
				numresultimagilabel = new Label("i");
				resultHBox.getChildren().addAll(numr1Text, numresultpluslabel, numr2Text, numresultimagilabel);
				resultHBox.setSpacing(5);
				resultHBox.setAlignment(Pos.CENTER);

				//Vbox donde van todos los numeros que se van a introducir y el resultado
				VBox inoutVBox = new VBox(4);
				inoutVBox.getChildren().addAll(numinput1HBox, numinput2HBox, separator, resultHBox);
				inoutVBox.setAlignment(Pos.CENTER);

				// Combobox para seleccionar operacion
				operatorComboBox = new ComboBox<>();
				operatorComboBox.getItems().addAll( "-","+","*","/");
			
				// VBox que contiene la seleccion de operaciones
				VBox operacionBox = new VBox(4);
				operacionBox.getChildren().add(operatorComboBox);
				operacionBox.setAlignment(Pos.CENTER);

				// HBox madre
				HBox cajamadreHBox = new HBox(4);
				cajamadreHBox.getChildren().addAll(operacionBox, inoutVBox);
				cajamadreHBox.setAlignment(Pos.CENTER);

				// Scene
				Scene scene = new Scene(cajamadreHBox, 320, 200);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Calculadora de numeros Complejos");
				primaryStage.show();

				// binding 1er numero
				numinput1aText.textProperty().bindBidirectional(complejoinput1.realProperty(), new NumberStringConverter());
				numinput1bText.textProperty().bindBidirectional(complejoinput1.imaginarioProperty(), new NumberStringConverter());

				// binding 2º numero
				numinput2cText.textProperty().bindBidirectional(complejoinput2.realProperty(), new NumberStringConverter());
				numinput2dText.textProperty().bindBidirectional(complejoinput2.imaginarioProperty(), new NumberStringConverter());

				// binding del resultado 
				numr1Text.textProperty().bind(complejoresult.realProperty().asString("%.2f"));
				numr2Text.textProperty().bind(complejoresult.imaginarioProperty().asString("%.2f"));

				// listener operacion
				operatorComboBox.valueProperty().addListener(e -> {
					
					// sumar
					if (operatorComboBox.valueProperty().getValue() == "+") {
						suma();
					}

					// restar
					else if (operatorComboBox.valueProperty().getValue() == "-") {
						resta();
					}

					// multiplicar
					else if (operatorComboBox.valueProperty().getValue() == "*") {
						multiplicacion();
					}

					// dividir
					else if (operatorComboBox.valueProperty().getValue() == "/") {
						division();
					}
				});

		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public void suma() {

		complejoresult.realProperty().bind(complejoinput1.realProperty().add(complejoinput2.realProperty()));
		
		complejoresult.imaginarioProperty().bind(complejoinput1.imaginarioProperty().add(complejoinput2.imaginarioProperty()));
		
	}

	public void resta() {

		complejoresult.realProperty().bind(complejoinput1.realProperty().subtract(complejoinput2.realProperty()));

		complejoresult.imaginarioProperty().bind(complejoinput1.imaginarioProperty().subtract(complejoinput2.imaginarioProperty()));
		
	}

	public void multiplicacion() {

		complejoresult.realProperty().bind(complejoinput1.realProperty().multiply(complejoinput2.realProperty())
				.subtract(complejoinput1.imaginarioProperty().multiply(complejoinput2.imaginarioProperty())));

		complejoresult.imaginarioProperty().bind(complejoinput1.realProperty().multiply(complejoinput2.imaginarioProperty())
				.add(complejoinput2.realProperty().multiply(complejoinput1.imaginarioProperty())));
		
	}

	public void division() {

		complejoresult.realProperty()
				.bind(((complejoinput1.realProperty().multiply(complejoinput2.realProperty())
						.add(complejoinput1.imaginarioProperty().multiply(complejoinput2.imaginarioProperty())))
								.divide(complejoinput2.realProperty().multiply(complejoinput2.realProperty())
										.add(complejoinput2.realProperty().multiply(complejoinput2.realProperty())))));

		complejoresult.imaginarioProperty()
				.bind(((complejoinput2.realProperty().multiply(complejoinput1.imaginarioProperty())
						.subtract(complejoinput1.realProperty().multiply(complejoinput2.imaginarioProperty())))
								.divide(complejoinput2.realProperty().multiply(complejoinput2.realProperty())
										.add(complejoinput2.realProperty().multiply(complejoinput2.realProperty())))));
	}
}
