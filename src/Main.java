import java.io.FileWriter;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

	private ObservableList<Items> Categories;
	private ObservableList<Price> Sizes;
	private Label CategoryLbl, SizeLbl;
	private ComboBox<Items> CategoryBox;
	private ComboBox<Price> SizeBox;
	private VBox Layout;
	private Button Buy;
	private Button Save;
	private TextArea Receipt;
	private TextField TotalField;
	private double Total;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage PrimaryStage) throws Exception {

		Alert a = new Alert(AlertType.INFORMATION);
		EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				a.setTitle("Sirayel.com");
				a.setHeaderText("Sirayel Fashion Shop");
				a.setContentText("Welcome to Sirayel Shop here is the list of available products in our shop click to proceed to the next page\n\n"
						+ "--------------------------------------\n"
						+ " Category\t Size\t \tPrice \n"
						+ "--------------------------------------"
						+ "\n Caps\t\t Small\t\t $5.00 \n Caps\t\t Medium\t\t $10.00 \n Caps\t\t Large\t\t $15.00"
						+ "\n Shirts\t\t Small\t\t $5.00 \n Shirts\t\t Medium\t\t $5.00 \n Shirts\t\t Large\t\t $15.00"
						+ "\n Trousers\t\t Small\t\t $5.00 \n Trousers\t\t Medium\t\t $10.00 \n Trousers\t\t Large\t\t $15.00"
						+ "\n\n\n ------------------------------------------------------"
						+ "\n \t\tThank you for choosing to shop with us"
						+ "\n ------------------------------------------------------");
				a.setResizable(true);
				a.getDialogPane().setPrefSize(600, 600);
				a.show();
			}
		};

		CategoryBox.setOnAction(e -> {
			if (CategoryBox.getSelectionModel().getSelectedItem() != null) {
				Sizes = FXCollections.observableArrayList(CategoryBox.getSelectionModel().getSelectedItem().getPrice());
				SizeBox.setItems(Sizes);
				SizeBox.setDisable(false);
			}
		});

		Buy.setOnAction(e -> {
			if (CategoryBox.getSelectionModel().getSelectedItem() != null
					&& SizeBox.getSelectionModel().getSelectedItem() != null) {
				Receipt.setText(Receipt.getText() + CategoryBox.getSelectionModel().getSelectedItem() + "\t\t"
						+ SizeBox.getSelectionModel().getSelectedItem().toString().split(" ")[0] + "\t\t"
						+ SizeBox.getSelectionModel().getSelectedItem().toString().split(" ")[1] + "\n");
				Total += SizeBox.getSelectionModel().getSelectedItem().getCost();
				TotalField.setText(Total + "");
			}
		});

		Save.setOnAction(e -> {
			try {
				FileWriter fw = new FileWriter("receipts/" + System.currentTimeMillis() + ".txt");
				fw.write(Receipt.getText());
				fw.close();
				Receipt.setText(
						"-------------------------------------------------------------------------------\nName\t\tCategory\t\tPrice\n-------------------------------------------------------------------------------\n");
				TotalField.setText("");
			} catch (Exception e1) {
				System.out.println(e1);
			}
		});

		Layout.getChildren().addAll(CategoryLbl, CategoryBox, SizeLbl, SizeBox, Buy, Save, Receipt, TotalField);

		PrimaryStage.setScene(new Scene(Layout, 600, 600));
		PrimaryStage.setTitle("Welcome to Sirayel Shop");
		PrimaryStage.show();
		event2.handle(new ActionEvent());

	}

	public Main() {

		Total = 0;
		TotalField = new TextField();
		TotalField.setEditable(false);

		Categories = FXCollections.observableArrayList(new Caps(), new Shirts(), new Trousers());
		CategoryLbl = new Label("Category");
		CategoryBox = new ComboBox<Items>(Categories);

		SizeLbl = new Label("Size");
		SizeBox = new ComboBox<Price>();
		SizeBox.setDisable(true);

		Receipt = new TextArea();
		Receipt.setText(
				"-------------------------------------------------------------------------------\nName\t\tCategory\t\tPrice\n-------------------------------------------------------------------------------\n");
		
		Buy = new Button("Buy");
		Save = new Button("Save");

		Layout = new VBox();
		Layout.setPadding(new Insets(25));
		Layout.setSpacing(10);

	}

}
