import javafx.fxml.FXML;
import javafx.scene.control.*;

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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entities.*;
import javafx.fxml.Initializable;



public class AdministratorController implements Initializable {
	
	private Stage prevStage;

	private Administrator administrator; 
    
    private LinkedList<OnsiteStore> onsiteStoreSuggestionsList; 
    
    private LinkedList<OnlineStore> onlineStoreSuggestionsList; 
    
    private LinkedList<Category> categoriesList;
    
    private LinkedList<Brand> brandsList;
    
    private LinkedList<SystemProduct> systemProductsList;

    
    
    public Administrator getAdministrator() {
		return administrator;
	}

	public void setAdministrator(Administrator administrator) {
		this.administrator = administrator;
	}

	public LinkedList<OnsiteStore> getOnsiteStoreSuggestionsList() {
		return onsiteStoreSuggestionsList;
	}

	public void setOnsiteStoreSuggestionsList(LinkedList<OnsiteStore> onsiteStoreSuggestionsList) {
		this.onsiteStoreSuggestionsList = onsiteStoreSuggestionsList;
	}

	public LinkedList<OnlineStore> getOnlineStoreSuggestionsList() {
		return onlineStoreSuggestionsList;
	}

	public void setOnlineStoreSuggestionsList(LinkedList<OnlineStore> onlineStoreSuggestionsList) {
		this.onlineStoreSuggestionsList = onlineStoreSuggestionsList;
	}

	public LinkedList<Category> getCategoriesList() {
		return categoriesList;
	}

	public void setCategoriesList(LinkedList<Category> categoriesList) {
		this.categoriesList = categoriesList;
	}


	@FXML
    private TextField NameTextField;

    @FXML
    private TextField MaxPriceTextField;

    @FXML
    private TextField MinPriceTextField;

    @FXML
    private ChoiceBox<String> CategoryChoiceBox;

    @FXML
    private ChoiceBox<String> TypeChoiceBox;

    @FXML
    private Button AddSystemProductButton;

    @FXML
    private TableView<Store> OnlineSSuggestionsTableView;

    @FXML
    private Button OnlineAddressSSuggestionButton;

    @FXML
    private ChoiceBox<String> OnlineSSuggestionAction;

    @FXML
    private TableView<Store> OnsiteSSuggestionsTableView;

    @FXML
    private Button OnsiteAddressSSuggestionButton;

    @FXML
    private ChoiceBox<String> OnsiteSSuggestionAction; 
    

    @FXML
    private TextField BrandNameTextField;

    @FXML
    private ChoiceBox<String> BrandCategoryNameChoiceBox;

    @FXML
    private Button AddBrandButton;
    
    @FXML
    private ComboBox<OnlineStore> OnlineSSuggestionsComboBox;
    
    @FXML
    private ComboBox<OnsiteStore> OnsiteSSuggestionsComboBox;
    

    @FXML
    void addBrand(ActionEvent event) throws IOException {
   	 String brandName = BrandNameTextField.getText();
     String categoryName = BrandCategoryNameChoiceBox.getValue();
     
     Category category = new Category(categoryName); 
     Brand brand = new Brand(brandName, category); 
     
     Boolean exists = false; 
     for(Brand insideBrand: brandsList)
     {
    	 if (insideBrand.getBrandName().equals(brandName))
    	 {
    		 System.out.println("found it");
    		 exists = true; 
    		 break; 
    	 }
     }
     
     if (!exists)
     {
    	 brandsList.add(brand);
    	 
    	 FileWriter fw = new FileWriter("src\\resources\\brands.txt", true);
         BufferedWriter bw = new BufferedWriter(fw);
         bw.write(brandName + "\n" + categoryName + "\n");
         bw.close();
         AlertBox.showMessage("Brand added!", "Brand added correctl.");
     }
     else
     {
    	 AlertBox.showMessage("Brand Error", "Brand already exists.");
     }
    }

    @FXML
    void addSystemProduct(ActionEvent event) throws IOException {
      	 String name = NameTextField.getText();
         String min = MinPriceTextField.getText();
         String max = MaxPriceTextField.getText();
         String type = TypeChoiceBox.getValue();
         String categoryName = CategoryChoiceBox.getValue();
         
         Types systemProductType; 
         
         if (type.equals("Online"))
        	 systemProductType = Types.Online; 
         else 
        	 systemProductType = Types.Onsite; 
         
         Category category = new Category(categoryName); 
         SystemProduct systemProduct = new SystemProduct(name, Integer.parseInt(min), Integer.parseInt(max), systemProductType, category); 
         
         Boolean exists = false; 
         for(SystemProduct s: systemProductsList)
         {
        	 if (s.getName().equals(name))
        	 {
        		 System.out.println("found it");
        		 exists = true; 
        		 break; 
        	 }
         }
         
         if (!exists)
         {
        	 systemProductsList.add(systemProduct);
        	 
        	 FileWriter fw = new FileWriter("src\\resources\\systemProducts.txt", true);
             BufferedWriter bw = new BufferedWriter(fw);
             bw.write(name + "\n" + min + "\n" + max + "\n" + type + "\n" + categoryName + "\n");
             bw.close();
             AlertBox.showMessage("System product added!", "System product added correctly.");
         }
         else
         {
        	 AlertBox.showMessage(" Addition Error", "System product already exists.");
         }

    }

    @FXML
    void addressOnlineSSuggestion(ActionEvent event) throws IOException {
    	
    	OnlineStore store = OnlineSSuggestionsComboBox.getValue(); 
    	
    	if (OnlineSSuggestionAction.getValue().equals("Accept"))
    	{
    		FileWriter fw = new FileWriter("src\\resources\\onlineStores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(store.getStoreID() + "\n" + store.getName() + "\n" + store.getType() + "\n" + store.getStoreOwner().getUsername());
            bw.close();
            AlertBox.showMessage("Online store added!", "Online store added correctly.");  
                     
    	}
    	else
    	{
    		AlertBox.showMessage("Online store not added!", "You rejected online store for now.");
    	}
    	
    	 for(OnlineStore s: onlineStoreSuggestionsList)
         {
        	 if (s.getStoreID()== store.getStoreID())
        	 {
        		onlineStoreSuggestionsList.remove(s); 
        		break; 
        	 }
         }
         
         OnlineSSuggestionsComboBox.getItems().clear();
         for(OnlineStore s: onlineStoreSuggestionsList)
         {
           OnlineSSuggestionsComboBox.getItems().add(s);
         }
         
         FileWriter fw = new FileWriter("src\\resources\\onlineStoreSuggestions.txt", true);         
         
         for(OnlineStore s: onlineStoreSuggestionsList)
         {
         	fw.write(s.getStoreID() + "\n" + s.getName() + "\n" + s.getType() + "\n" + s.getStoreOwner().getUsername());
         }            
         fw.close();

    }

    @FXML
    void addressOnsiteSSuggestion(ActionEvent event) throws IOException {
    	
        OnsiteStore store = OnsiteSSuggestionsComboBox.getValue(); 
    	
    	if (OnsiteSSuggestionAction.getValue().equals("Accept"))
    	{
    		FileWriter fw = new FileWriter("src\\resources\\onsiteStores.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(store.getStoreID() + "\n" + store.getName() + "\n" + store.getType() + "\n" + store.getStoreOwner().getUsername() + "\n" + store.getAddress());
            bw.close();
            AlertBox.showMessage("Onsite store added!", "Online store added correctly.");
    	}
    	else
    	{
    		AlertBox.showMessage("Onsite store not added!", "You rejected onsite store for now.");
    	}
    	
    	for(OnsiteStore s: onsiteStoreSuggestionsList)
        {
       	 if (s.getStoreID()== store.getStoreID())
       	 {
       		
       		onsiteStoreSuggestionsList.remove(s); 
       		break; 
       	 }
       	System.out.println("inside");
        }
        
        OnsiteSSuggestionsComboBox.getItems().clear();
        for(OnsiteStore s: onsiteStoreSuggestionsList)
        {
          OnsiteSSuggestionsComboBox.getItems().add(s);
        }
        
        
        FileWriter fw = new FileWriter("src\\resources\\onsiteStoreSuggestions.txt", true);         
        
        for(OnsiteStore s: onsiteStoreSuggestionsList)
        {
        	fw.write(store.getStoreID() + "\n" + store.getName() + "\n" + store.getType() + "\n" + store.getStoreOwner().getUsername() + "\n" + store.getAddress());
        }            
        fw.close();

    }
    
    public void setStage(Stage prevStage) {
        this.prevStage = prevStage;
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		System.out.println("Initializing admin");
		
		// Initialize categories inventory
	    categoriesList = new LinkedList<Category>(); 
        try {  
        	
        	File myObj = new File("src\\resources\\categories.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              String name = myReader.nextLine();
              Category category = new Category(name); 
              categoriesList.add(category);        
            }
            myReader.close();        
	    }
        catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
     // Initialize onsite stores inventory
            onsiteStoreSuggestionsList = new LinkedList<OnsiteStore>(); 
     		try {    	
         	File myObj = new File("src\\resources\\onsiteStoreSuggestions.txt");
             Scanner myReader = new Scanner(myObj);
             while (myReader.hasNextLine()) {
               int nationalID = Integer.parseInt(myReader.nextLine());
               String name = myReader.nextLine();
               String type = myReader.nextLine();
               String username = myReader.nextLine();
               String address = myReader.nextLine();
               StoreOwner storeOwner = new StoreOwner(username, "-"); 
               OnsiteStore onsiteStoreSuggestion = new OnsiteStore(nationalID, name, type, storeOwner, address);
               onsiteStoreSuggestionsList.add(onsiteStoreSuggestion); 
               }
             myReader.close();
     		}
     		catch (FileNotFoundException e) {
     		      System.out.println("An error occurred.");
     		      e.printStackTrace();
     		    }
             
             // Initialize online stores inventory 
     		onlineStoreSuggestionsList = new LinkedList<OnlineStore>();  
             try {        	
             	File myObj = new File("src\\resources\\onlineStoreSuggestions.txt");
                 Scanner myReader = new Scanner(myObj);
                 while (myReader.hasNextLine()) {
                   int nationalID = Integer.parseInt(myReader.nextLine());
                   String name = myReader.nextLine();
                   String type = myReader.nextLine();
                   String username = myReader.nextLine();
                   StoreOwner storeOwner = new StoreOwner(username, "-"); 
                   OnlineStore onlineStoreSuggestion = new OnlineStore(nationalID, name, type, storeOwner);
                   onlineStoreSuggestionsList.add(onlineStoreSuggestion); 
                   System.out.println(name);
                            
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
             
             
             // Initialize Choiceboxes
             for(Category category: categoriesList)
             {
               BrandCategoryNameChoiceBox.getItems().add(category.toString());
               CategoryChoiceBox.getItems().add(category.toString());
             }
             
             TypeChoiceBox.getItems().add("Online");
             TypeChoiceBox.getItems().add("Onsite");
             
             OnsiteSSuggestionAction.getItems().add("Accept");
             OnsiteSSuggestionAction.getItems().add("Reject");
             OnlineSSuggestionAction.getItems().add("Accept");
             OnlineSSuggestionAction.getItems().add("Reject");
             
             for(OnlineStore s: onlineStoreSuggestionsList)
             {
               OnlineSSuggestionsComboBox.getItems().add(s);
             }
             
             for(OnsiteStore s: onsiteStoreSuggestionsList)
             {
               OnsiteSSuggestionsComboBox.getItems().add(s);
             }
            
             
	}

}