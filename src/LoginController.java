import javafx.fxml.FXML;

import javafx.scene.control.*;
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
import java.io.FileWriter;
import java.io.BufferedWriter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entities.*;
import javafx.fxml.Initializable;

// Controller class for login boundary

public class LoginController implements Initializable {
	
	private Stage prevStage;
	
	private LinkedList<Buyer> buyers;
	
	private LinkedList<StoreOwner> storeOwners;
	
	private LinkedList<Administrator> admins;


	@FXML
    private TextField UsernameTextField;

    @FXML
    private PasswordField PasswordTextField;
    
    @FXML
    private ChoiceBox<String> TypeChoiceBox;
    
    @FXML
    private TextField AddressTextField;
    
    @FXML
    private Text AddressLabel;
    
    @FXML
    private Text AddressNoticeLabel;

    @FXML
    private Button LoginButton;

    @FXML
    private Button RegisterButton;
    
	public LinkedList<Buyer> getBuyers() {
		return buyers;
	}

	public void setBuyers(LinkedList<Buyer> buyers) {
		this.buyers = buyers;
	}
    
	public LinkedList<StoreOwner> getStoreOwners() {
		return storeOwners;
	}

	public void setStoreOwners(LinkedList<StoreOwner> storeOwners) {
		this.storeOwners = storeOwners;
	}

	public Boolean addBuyer(String username, String password, String address) {
		Buyer buyer = new Buyer(username, password, address); 
		buyers.add(buyer); 
		return true; 
	}
	 
	
	public Boolean addAdmin(String username, String password) {
		Administrator admin = new Administrator(username, password); 
		admins.add(admin); 
		return true; 
	}
	
	 void loginStoreOwner(String username, String password) throws IOException {
    	StoreOwner currentStoreOwner = null;
    	boolean isFound = false;
    	
    	for (int i = 0; i < storeOwners.size(); i++) {
    		currentStoreOwner = storeOwners.get(i);
    		if (currentStoreOwner.getUsername().equals(username) && currentStoreOwner.getPassword().equals(password)) {
    			isFound = true;
    			break;
    		}
    	}
    	
    	if (isFound) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/storeowner.fxml"));
            Parent root = loader.load();
           	Stage newStage = new Stage();
        	StoreOwnerController controller = loader.getController();
        	controller.setStage(newStage);
        	controller.setStoreOwner(currentStoreOwner);
        	newStage.setTitle("Store Owner Interface");
        	newStage.setScene(new Scene(root));
        	prevStage.close();
        	newStage.show();
    	}
    	else {
    		AlertBox.showMessage("Data Error", "Username or password is wrong.\nPlease make sure you are entering the correct credentials.");
    	}
	}
	 
	 void loginBuyer(String username, String password) throws IOException {
	    	Buyer currentBuyer = null;
	    	boolean isFound = false;
	    	
	    	for (int i = 0; i < buyers.size(); i++) {
	    		currentBuyer = buyers.get(i);
	    		if (currentBuyer.getUsername().equals(username) && currentBuyer.getPassword().equals(password)) {
	    			isFound = true;
	    			break;
	    		}
	    	}
	    	
	    	if (isFound) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/buyer.fxml"));
	            Parent root = loader.load();
	           	Stage newStage = new Stage();
	        	BuyerController controller = loader.getController();
	        	controller.setStage(newStage);
	        	controller.setBuyer(currentBuyer);
	        	newStage.setTitle("Buyer Interface");
	        	newStage.setScene(new Scene(root));
	        	prevStage.close();
	        	newStage.show();
	    	}
	    	else {
	    		AlertBox.showMessage("Data Error", "Username or password is wrong.\nPlease make sure you are entering the correct credentials.");
	    	}
		}
	 
	 void loginAdmin(String username, String password) throws IOException {
	    	Administrator currentAdmin = null;
	    	boolean isFound = false;
	    	
	    	for (int i = 0; i < admins.size(); i++) {
	    		currentAdmin = admins.get(i);
	    		if (currentAdmin.getUsername().equals(username) && currentAdmin.getPassword().equals(password)) {
	    			isFound = true;
	    			break;
	    		}
	    	}
	    	
	    	if (isFound) {
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/administrator.fxml"));
	            Parent root = loader.load();
	           	Stage newStage = new Stage();
	        	AdministratorController controller = loader.getController();
	        	controller.setStage(newStage);
	        	controller.setAdministrator(currentAdmin);
	        	newStage.setTitle("Administrator Interface");
	        	newStage.setScene(new Scene(root));
	        	prevStage.close();
	        	newStage.show();
	    	}
	    	else {
	    		AlertBox.showMessage("Data Error", "Username or password is wrong.\nPlease make sure you are entering the correct credentials.");
	    	}
		}
    
    @FXML
    void login(ActionEvent event) throws IOException {
        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();
        String type = TypeChoiceBox.getValue();
        
        if (type == "Store Owner") {
        	loginStoreOwner(username, password);
        }
        else if (type == "Buyer") {
        	loginBuyer(username, password);
        }
        else if (type == "Admin") {
        	loginAdmin(username, password);
        }
    }
    
    @FXML
    void register(ActionEvent event) throws IOException {
        String username = UsernameTextField.getText();
        String password = PasswordTextField.getText();
        String address = AddressTextField.getText();
        String type = TypeChoiceBox.getValue();
        
        String filename = "";
        
        if (type == "Store Owner") {
        	filename = "storeOwners.txt";
        }
        else if (type == "Buyer") {
        	filename = "buyers.txt";
        }
        
        FileWriter fw = new FileWriter("src\\resources\\" + filename, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(username + "\n" + password + "\n");
        if (type == "Buyer") {
        	bw.write(address + "\n");
        }
        bw.close();
        
    	Stage newStage = new Stage();
    	Parent root = null;
    	if (type == "Store Owner") {
    		StoreOwner newStoreOwner = new StoreOwner(username, password);
    		storeOwners.add(newStoreOwner);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/storeowner.fxml"));
            root = loader.load();
        	StoreOwnerController controller = loader.getController();
        	controller.setStage(newStage);
        	controller.setStoreOwner(newStoreOwner);
        	newStage.setTitle("Store Owner Interface");
    	}
    	else if (type == "Buyer") {
    		Buyer newBuyer = new Buyer(username, password, address);
    		buyers.add(newBuyer);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("resources/buyer.fxml"));
            root = loader.load();
        	BuyerController controller = loader.getController();
        	controller.setStage(newStage);
        	controller.setBuyer(newBuyer);
        	newStage.setTitle("Buyer Interface");
        }
    	newStage.setScene(new Scene(root));
    	prevStage.close();
    	newStage.show();
        
    }
    
    public void setStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("Inside");
		// Initialize store owners list
		this.storeOwners = Utilities.loadStoreOwners();
		
		
		// Initialize buyers list
		this.buyers = Utilities.loadBuyers();

		
		// Initialize admins list
		this.admins = new LinkedList<Administrator>();
		try {
	    	File myObj = new File("src\\resources\\administrators.txt");
	    	System.out.println(myObj.getAbsolutePath());
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String username = myReader.nextLine();
	          String password = myReader.nextLine();
	          addAdmin(username, password);}
	          myReader.close();    	
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
       
		TypeChoiceBox.getItems().add("Store Owner");
		
		TypeChoiceBox.getItems().add("Buyer");
		
		TypeChoiceBox.getItems().add("Admin");
		
		TypeChoiceBox.setOnAction((event) -> {
			
			String type = TypeChoiceBox.getValue();
			if (type == "Buyer") {
				AddressLabel.setVisible(true);
				AddressNoticeLabel.setVisible(true);
				AddressTextField.setVisible(true);
			}
			else {
				AddressLabel.setVisible(false);
				AddressNoticeLabel.setVisible(false);
				AddressTextField.setVisible(false);
			}
			
			
			if (type == "Admin") {
				RegisterButton.setDisable(true);
			}
			else {
				RegisterButton.setDisable(false);
			}
			
		});

	}

}