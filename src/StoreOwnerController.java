import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entities.*;
import javafx.fxml.Initializable;
import java.util.LinkedList;

// Controller Class for store owner boundary 

public class StoreOwnerController implements Initializable{
	
	private Stage prevStage;

	private StoreOwner storeOwner; 
    
    private LinkedList<OnsiteStore> onsiteStoresList; 
    
    private LinkedList<OnlineStore> onlineStoresList;
    
    private LinkedList<SystemProduct> systemProductsList; 
    
    private LinkedList<Brand> brandsList; 
    
    private LinkedList<Product> productsList;
	
    public void setStage(Stage prevStage) {
        this.prevStage = prevStage;
    }
    	
    
	public StoreOwner getStoreOwner() {
		return storeOwner;
	}

	public void setStoreOwner(StoreOwner storeOwner) {
		this.storeOwner = storeOwner;
		
		// Initialize onsite stores inventory
		onsiteStoresList = new LinkedList<OnsiteStore>(); 
		try {    	
    	File myObj = new File("src\\resources\\onsiteStores.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {
          int storeID = Integer.parseInt(myReader.nextLine());
          String name = myReader.nextLine();
          String type = myReader.nextLine();
          String username = myReader.nextLine();
          String address = myReader.nextLine();
          if (username.equals(this.storeOwner.getUsername()))
          {
        	  System.out.println("inside this");
        	  OnsiteStore onsiteStore = new OnsiteStore(storeID, name, type, this.storeOwner, address);  
        	  onsiteStoresList.add(onsiteStore); 
          }          
        }
        myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
        
        // Initialize online stores inventory 
		onlineStoresList = new LinkedList<OnlineStore>(); 
        try {        	
        	File myObj = new File("src\\resources\\onlineStores.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              int storeID = Integer.parseInt(myReader.nextLine());
              String name = myReader.nextLine();
              String type = myReader.nextLine();
              String username = myReader.nextLine();
              if (username.equals(this.storeOwner.getUsername()))
              {
            	  OnlineStore onlineStore = new OnlineStore(storeID, name, type, this.storeOwner);  
            	  onlineStoresList.add(onlineStore);         	  
              }          
            }
            myReader.close();        
	    }
        catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
        
        for(OnlineStore s: onlineStoresList)
        {
         OnlineProductStoreComboBox.getItems().add(s);          
        }
        
        for(OnsiteStore s: onsiteStoresList)
        {
        System.out.println(s.getStoreOwner().getUsername());
        OnsiteProductStoreComboBox.getItems().add(s);          
        }
	}

    public LinkedList<OnsiteStore> getOnsiteStoresList() {
		return onsiteStoresList;
	}


	public void setOnsiteStoresList(LinkedList<OnsiteStore> onsiteStoresList) {
		this.onsiteStoresList = onsiteStoresList;
	}


	public LinkedList<OnlineStore> getOnlineStoresList() {
		return onlineStoresList;
	}


	public void setOnlineStoresList(LinkedList<OnlineStore> onlineStoresList) {
		this.onlineStoresList = onlineStoresList;
	}


	public LinkedList<Brand> getBrandsList() {
		return brandsList;
	}


	public void setBrandsList(LinkedList<Brand> brandsList) {
		this.brandsList = brandsList;
	}






	@FXML
    private Button AddOnsiteProductButton;

    @FXML
    private TextField ProductNameTextField;

    @FXML
    private TextField ProductDescriptionTextField;

    @FXML
    private TextField ProductAgreementsTextField;

    @FXML
    private TextField ProductQuantityTextField;

    @FXML
    private TextField ProductPriceTextField;

    @FXML
    private Button AddOnlineProductButton;

    @FXML
    private ComboBox<OnsiteStore> OnsiteProductStoreComboBox;

    @FXML
    private ComboBox<SystemProduct> SystemProductComboBox;

    @FXML
    private ComboBox<Brand> BrandComboBox;

    @FXML
    private ComboBox<OnlineStore> OnlineProductStoreComboBox;

    @FXML
    private TextField OnlineStoreNameTextField;

    @FXML
    private TextField OnlineStoreIDTextField;

    @FXML
    private TextField OnlineStoreTypeTextField;

    @FXML
    private Button AddOnlineSSuggestionButton;

    @FXML
    private TextField OnsiteStoreNameTextField;

    @FXML
    private TextField OnsiteStoreIDTextField;

    @FXML
    private TextField OnsiteStoreTypeTextField;

    @FXML
    private Button AddOnsiteSSuggestionButton;

    @FXML
    private TextField OnsiteStoreAddressTextField;
    
  
    @FXML
    void addOnlineProduct(ActionEvent event) throws IOException {
    	SystemProduct systemProduct = SystemProductComboBox.getValue();
        Brand brand = BrandComboBox.getValue(); 
    	String name = ProductNameTextField.getText(); 
    	String price = ProductPriceTextField.getText(); 
    	String description = ProductDescriptionTextField.getText(); 
    	String agreement = ProductAgreementsTextField.getText();
    	String quantity = ProductQuantityTextField.getText(); 
    	OnlineStore store = OnlineProductStoreComboBox.getValue(); 
    	
    	if (systemProduct.getType().toString().equals("Online") && brand.getCategory().getCategoryName().equals(systemProduct.getCategory().getCategoryName()) )
    	{
    		if (Integer.parseInt(price) <= systemProduct.getMax() && Integer.parseInt(price) >= systemProduct.getMin())
    		{
    			FileWriter fw = new FileWriter("src\\resources\\products.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(name + "\n" + price + "\n" +  description + "\n" + Integer.parseInt(quantity) + agreement + "\n" +  description + "\n" + brand.getBrandName() + "\n"+ systemProduct.getName() + "\n" + store.getStoreID() + "\n" );
                bw.close();
                AlertBox.showMessage("Online product added!", "Online product added correctly. ");
    		}
    		else
    		{
    			AlertBox.showMessage(" Addition Error", "Make sure your price suits the specified system product range.");
    		}
    		
    	}
    	else
    	{
    		AlertBox.showMessage(" Addition Error", "Make sure your system product, category, and brand are matching.");
    	}
        
        

    }

    @FXML
    void addOnlineSSuggestion(ActionEvent event) throws NumberFormatException, IOException {
    	String name = OnlineStoreNameTextField.getText();
        String storeID = OnlineStoreIDTextField.getText();
        String type = OnlineStoreTypeTextField.getText();
        
        Boolean exists = false; 
        for(OnlineStore s: onlineStoresList)
        {
       	 if (s.getStoreID() == Integer.parseInt(storeID))
       	 {
       		 System.out.println("found it");
       		 exists = true; 
       		 break; 
       	 }
        }
        
        if (!exists)
        {       	 
       	 FileWriter fw = new FileWriter("src\\resources\\onlineStoreSuggestions.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(Integer.parseInt(storeID) + "\n" + name + "\n" + type + "\n" + this.storeOwner.getUsername() + "\n");
            bw.close();
            AlertBox.showMessage("Online store added!", "Online store added correctly. Await admin acceptance.");
        }
        else
        {
       	 AlertBox.showMessage(" Addition Error", "Online store already exists.");
        }
    }

    @FXML
    void addOnsiteProduct(ActionEvent event) throws IOException {
    	SystemProduct systemProduct = SystemProductComboBox.getValue();
        Brand brand = BrandComboBox.getValue(); 
    	String name = ProductNameTextField.getText(); 
    	String price = ProductPriceTextField.getText(); 
    	String description = ProductDescriptionTextField.getText(); 
    	String agreement = ProductAgreementsTextField.getText();
    	String quantity = ProductQuantityTextField.getText(); 
    	OnsiteStore store = OnsiteProductStoreComboBox.getValue(); 
    	
    	System.out.println(systemProduct.getType().toString());
		System.out.println(brand.getCategory().getCategoryName()); 
		System.out.println(systemProduct.getCategory().getCategoryName()); 
    	
    	if (systemProduct.getType().toString().equals("Onsite") && brand.getCategory().getCategoryName().equals(systemProduct.getCategory().getCategoryName()) )
    	{
    		
    				
    		if (Integer.parseInt(price) <= systemProduct.getMax() && Integer.parseInt(price) >= systemProduct.getMin())
    		{
    			FileWriter fw = new FileWriter("src\\resources\\products.txt", true);
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(name + "\n" + price + "\n" +  description + "\n" + quantity + "\n" + agreement + "\n" + brand.getBrandName() + "\n"+ systemProduct.getName() + "\n" + store.getStoreID() + "\n" );
                bw.close();
                AlertBox.showMessage("Onsite product added!", "Onsite product added correctly. ");
    		}
    		else
    		{
    			AlertBox.showMessage(" Addition Error", "Make sure your price suits the specified system product range.");
    		}
    		
    	}
    	else
    	{
    		AlertBox.showMessage(" Addition Error", "Make sure your system product, category, and brand are matching.");
    	}

    }

    @FXML
    void addOnsiteSSuggestion(ActionEvent event) throws IOException {
    	String name = OnsiteStoreNameTextField.getText();
    	System.out.println("1");
    	String storeID = OnsiteStoreIDTextField.getText();
        System.out.println("2");
        String type = OnsiteStoreTypeTextField.getText();
        String address = OnsiteStoreAddressTextField.getText(); 
        
        Boolean exists = false; 
        for(OnsiteStore s: onsiteStoresList)
        {
       	 if (s.getStoreID() == Integer.parseInt(storeID))
       	 {
       		 System.out.println("found it");
       		 exists = true; 
       		 break; 
       	 }
        }
        
        if (!exists)
        {
        	FileWriter fw = new FileWriter("src\\resources\\onsiteStoreSuggestions.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(storeID + "\n" + name + "\n" + type + "\n" + this.storeOwner.getUsername() + "\n" + address + "\n");
            bw.close();
            AlertBox.showMessage("Onsite store added!", "Onsite store added correctly.");
        }
        else
        {
       	 AlertBox.showMessage(" Addition Error", "Onsite store already exists.");
        }

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		System.out.println("Initializing storeowner");
        
        // Initialize system products inventory 
        systemProductsList = new LinkedList<SystemProduct>(); 
        try {        	
        	File myObj = new File("src\\resources\\systemProducts.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String name = myReader.nextLine();
              int min = Integer.parseInt(myReader.nextLine());
              int max = Integer.parseInt(myReader.nextLine());
              Types type = Types.valueOf(myReader.nextLine());
              String categoryName = myReader.nextLine();
              Category category = new Category(categoryName);
              SystemProduct systemProduct = new SystemProduct(name, min, max, type, category);
              systemProductsList.add(systemProduct);        
            }
            myReader.close();        
	    }
        catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
        
        // Initialize brands inventory
        brandsList = new LinkedList<Brand>(); 
        try {        	
        	File myObj = new File("src\\resources\\brands.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String name = myReader.nextLine();
              String categoryName = myReader.nextLine();
              Category category = new Category(categoryName);
              Brand brand = new Brand(name, category); 
              brandsList.add(brand);        
            }
            myReader.close();        
	    }
        catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
        
        // Initialize Choiceboxes
        
        for(SystemProduct s: systemProductsList)
        {
         SystemProductComboBox.getItems().add(s);          
        }
        
        for(Brand b: brandsList)
        {    		
    	 BrandComboBox.getItems().add(b);          
        }
               
        
        
	}
	

}
