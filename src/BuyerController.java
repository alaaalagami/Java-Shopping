import javafx.fxml.FXML;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.event.ChangeListener;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entities.*; 
import javafx.fxml.Initializable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BuyerController implements Initializable{

private Buyer buyer;

private Stage prevStage;

private LinkedList<OnsiteStore> onsiteStoresList; 

private LinkedList<OnlineStore> onlineStoresList; 

private LinkedList<Product> productsList;

private LinkedList<Brand> brandsList;

private LinkedList<Category> categoriesList;


public Buyer getBuyer() {
	return buyer;
}

public void setBuyer(Buyer buyer) {
	this.buyer = buyer;
	 AddressTextField.setText(buyer.getAddress());
}


@FXML
private TextField ProductQuantityTextField;

@FXML
private TextField AddressTextField;

@FXML
private CheckBox AgreementsCheckBox;

@FXML
private Button MakePaymentButton;

@FXML
private TextField PaymentIDTextField;

@FXML
private ChoiceBox<String> PaymentTypeChoiceBox;

@FXML
private Text PaymentIDLabel;


@FXML
private Button ConfirmPaymentButton;

@FXML
private Text OrderDetailsText;

@FXML
private Tab makePaymentTab;

@FXML
private Tab buyProductTab;

@FXML
private TabPane tabPane;

@FXML
private TableView<Product> ProductsTableView;

@FXML
private TableColumn<Product, String> ProductNameCol;

@FXML
private TableColumn<Product, Integer> ProductPriceCol;

@FXML
private TableColumn<Product, String> ProductDescriptionCol;

@FXML
private TableColumn<Product, String> ProductStoreNameCol;


@FXML
private TableColumn<Product, Integer> ProductQuantityCol;


@FXML
void handleAgreementsCheckBox(ActionEvent event) {
	if(AgreementsCheckBox.isSelected())
		MakePaymentButton.setDisable(false);
	else
		MakePaymentButton.setDisable(true);
}

@FXML
void cancelOrder(ActionEvent event) {
	tabPane.getSelectionModel().select(buyProductTab);
	buyProductTab.setDisable(false);
	makePaymentTab.setDisable(true);
}

@FXML
void makePayment(ActionEvent event) {
	Product selectedProduct = ProductsTableView.getSelectionModel().getSelectedItem();
	try {
		int quantity = Integer.parseInt(ProductQuantityTextField.getText());
		if (quantity <= selectedProduct.getQuantity()) {
			tabPane.getSelectionModel().select(makePaymentTab);
			buyProductTab.setDisable(true);
			makePaymentTab.setDisable(false);
			OrderDetailsText.setText("You are buying " + Integer.toString(quantity) + " of " + selectedProduct.getName()
			+ " for a total of " + Integer.toString(quantity * selectedProduct.getPrice()) + " pounds.");
		}
		else {
			AlertBox.showMessage("Invalid Quantity", "There is not enough quantity for your order!");
		}
	}
	catch (Exception e) {
		AlertBox.showMessage("Invalid Quantity", "Please Enter a Valid Quantity");
	}
	
}

@FXML
void ConfirmPayment(ActionEvent event) throws IOException {
	LinkedList<Order> orders = Utilities.loadOrders();
	int newOrderID = orders.getLast().getOrderID() + 1;
	String address = AddressTextField.getText();
	Product product = ProductsTableView.getSelectionModel().getSelectedItem();
	int boughtQuantity = Integer.parseInt(ProductQuantityTextField.getText());
	product.setQuantity(product.getQuantity() - boughtQuantity);
	Payment payment = new PaymentFactory().createPayment(
			PaymentTypeChoiceBox.getSelectionModel().getSelectedItem(), PaymentIDTextField.getText());
	if (payment == null) {
	    AlertBox.showMessage("Invalid Voucher!", "Voucher either does not exist or has expired!");
	    return;
	}
	Order newOrder = new Order(newOrderID, address, product, buyer, payment);
	orders.add(newOrder);
  	FileWriter fw = new FileWriter("src\\resources\\orders.txt", true);
    BufferedWriter bw = new BufferedWriter(fw);
    bw.write(newOrderID + "\n" + address + "\n" + product.getName() + "\n" + buyer.getUsername() +
    		"\n" + payment.toString() + "\n");
    bw.close();
    AlertBox.showMessage("Successful Order!", "The order is on its way to you!");
    
	tabPane.getSelectionModel().select(buyProductTab);
	buyProductTab.setDisable(false);
	makePaymentTab.setDisable(true);
	ProductsTableView.refresh();
	Utilities.saveProducts(ProductsTableView.getItems());
}


@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	
	System.out.println("Initializing Buyer");
	
	// Initialize onsite stores inventory
	onsiteStoresList = Utilities.loadOnsiteStores();
	        
	// Initialize online stores inventory 
	onlineStoresList = Utilities.loadOnlineStores();
	
	
	
	// Initialize products inventory
	productsList = Utilities.loadProducts();
	
	final ObservableList<Product> productsData = FXCollections.observableArrayList(productsList);
	
	ProductNameCol.setCellValueFactory(
		    new PropertyValueFactory<Product,String>("name")
		);
	
	ProductPriceCol.setCellValueFactory(
		    new PropertyValueFactory<Product,Integer>("price")
		);
	
	ProductDescriptionCol.setCellValueFactory(
		    new PropertyValueFactory<Product,String>("description")
		);
	
	ProductQuantityCol.setCellValueFactory(
		    new PropertyValueFactory<Product,Integer>("quantity")
		);
	
	ProductStoreNameCol.setCellValueFactory(
		    new PropertyValueFactory<Product,String>("storeName")
		);
	 ProductsTableView.setItems(productsData);
	 
	 PaymentTypeChoiceBox.getItems().add("On Delivery");
	 PaymentTypeChoiceBox.getItems().add("Credit Card");
	 PaymentTypeChoiceBox.getItems().add("Voucher");
	 
	 ProductsTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
		    if (newSelection != null) {
		    	if (newSelection.getAgreement().equals("None")) {
		    		MakePaymentButton.setDisable(false);
		    	}
		    	else {
		    		AgreementsCheckBox.setText(newSelection.getAgreement());
		    		AgreementsCheckBox.setVisible(true);
		    	}
		    	}
		});
	 
	 PaymentTypeChoiceBox.setOnAction((event) -> {
			
			String type = PaymentTypeChoiceBox.getValue();
			if (type.equals("On Delivery")) {
				PaymentIDLabel.setVisible(false);
				PaymentIDTextField.setVisible(false);
			}
			else {
				PaymentIDLabel.setVisible(true);
				PaymentIDTextField.setVisible(true);
				if (type.equals("Credit Card")) {
					PaymentIDLabel.setText("Card Number:");
				}
				else if (type.equals("Voucher")) {
					PaymentIDLabel.setText("Voucher ID:");
				}
			}
	 });
			
} 

public void setStage(Stage prevStage) {
    this.prevStage = prevStage;
}
	
}
