import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

import Entities.Brand;
import Entities.Buyer;
import Entities.Category;
import Entities.OnlineStore;
import Entities.OnsiteStore;
import Entities.Order;
import Entities.Payment;
import Entities.Product;
import Entities.Store;
import Entities.StoreOwner;
import Entities.SystemProduct;
import Entities.Types;
import Entities.Voucher;
import Entities.VoucherStatus;
import javafx.collections.ObservableList;

public class Utilities {
	
	static LinkedList<Category> categories;
	static LinkedList<Brand> brands;
	static LinkedList<SystemProduct> systemProducts;
	static LinkedList<Product> products;
	static LinkedList<OnlineStore> onlineStores;
	static LinkedList<OnsiteStore> onsiteStores;
	static LinkedList<StoreOwner> storeOwners;
	static LinkedList<Buyer> buyers;
	static LinkedList<Voucher> vouchers;
	static LinkedList<Order> orders;

	
	/////////////////////////////////////////////////// LOADERS ///////////////////////////////////////////
	
	static LinkedList<StoreOwner> loadStoreOwners(){
		storeOwners = new LinkedList<StoreOwner>(); 
		try {        	
		    	File myObj = new File("src\\resources\\storeOwners.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String username = myReader.nextLine();
			      String password = myReader.nextLine();
			      StoreOwner storeOwner = new StoreOwner(username, password); 
			      storeOwners.add(storeOwner); 
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return storeOwners;	
	}
	
	static LinkedList<Buyer> loadBuyers(){
		buyers = new LinkedList<Buyer>(); 
		try {
	    	File myObj = new File("src\\resources\\buyers.txt");
	    	System.out.println(myObj.getAbsolutePath());
	        Scanner myReader = new Scanner(myObj);
	        while (myReader.hasNextLine()) {
	          String username = myReader.nextLine();
	          String password = myReader.nextLine();
	          String address = myReader.nextLine();
	  		  Buyer buyer = new Buyer(username, password, address); 
	  		  buyers.add(buyer); 
	          }
	          myReader.close();    	
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return buyers;	
	}
	
	static LinkedList<OnlineStore> loadOnlineStores(){
		onlineStores = new LinkedList<OnlineStore>(); 
		try {        	
		    	File myObj = new File("src\\resources\\onlineStores.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
			      int storeID = Integer.parseInt(myReader.nextLine());
			      String name = myReader.nextLine();
			      String type = myReader.nextLine();
			      StoreOwner storeOwner = getStoreOwner(myReader.nextLine()); 
			      OnlineStore onlineStore = new OnlineStore(storeID, name, type, storeOwner);
			      onlineStores.add(onlineStore); 
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return onlineStores;	
	}
	
	static LinkedList<OnsiteStore> loadOnsiteStores(){
		onsiteStores = new LinkedList<OnsiteStore>(); 
		try {        	
		    	File myObj = new File("src\\resources\\onsiteStores.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
			      int storeID = Integer.parseInt(myReader.nextLine());
			      String name = myReader.nextLine();
			      String type = myReader.nextLine();
			      StoreOwner storeOwner = getStoreOwner(myReader.nextLine()); 
			      String address = myReader.nextLine();
			      OnsiteStore onsiteStore = new OnsiteStore(storeID, name, type, storeOwner, address);
			      onsiteStores.add(onsiteStore); 
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return onsiteStores;	
	}
	
	static LinkedList<Product> loadProducts(){
		products = new LinkedList<Product>();
		loadCategories();
		loadBrands();
		loadSystemProducts();
		loadOnlineStores();
		loadOnsiteStores();
		try {        	
		    	File myObj = new File("src\\resources\\products.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String name = myReader.nextLine();
		          int price = Integer.parseInt(myReader.nextLine());
		          String description = myReader.nextLine();
		          int quantity = Integer.parseInt(myReader.nextLine());
		          String agreement = myReader.nextLine();
		          Brand brand = getBrand(myReader.nextLine());
		          SystemProduct sysProduct = getSystemProduct(myReader.nextLine());
		          Store store = getStore(Integer.parseInt(myReader.nextLine()));
		          Product product = new Product(name, price, description, quantity, agreement, brand, sysProduct, store); 
		          products.add(product);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return products;
	}
	
	static LinkedList<Category> loadCategories(){
		categories = new LinkedList<Category>(); 
		try {        	
		    	File myObj = new File("src\\resources\\categories.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String name = myReader.nextLine();
		          Category category = new Category(name); 
		          categories.add(category);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return categories;	
	}
	
	static LinkedList<Voucher> loadVouchers(){
		loadBuyers();
		loadOnsiteStores();
		loadOnlineStores();
		vouchers = new LinkedList<Voucher>(); 
		try {        	
		    	File myObj = new File("src\\resources\\vouchers.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String voucherID = myReader.nextLine();
		          Buyer buyer = getBuyer(myReader.nextLine());
		          Store store = getStore(Integer.parseInt(myReader.nextLine()));
		          VoucherStatus status = getVoucherValidity(myReader.nextLine());
		          Voucher voucher = new Voucher(voucherID, buyer, store, status);
		          vouchers.add(voucher);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return vouchers;	
	}

	static LinkedList<Brand> loadBrands(){
		loadCategories();
		brands = new LinkedList<Brand>(); 
		try {        	
		    	File myObj = new File("src\\resources\\brands.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String name = myReader.nextLine();
		          Category category = getCategory(myReader.nextLine());
		          Brand brand = new Brand(name, category); 
		          brands.add(brand);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return brands;	
	}
	
	static LinkedList<SystemProduct> loadSystemProducts(){
		loadCategories();
		systemProducts = new LinkedList<SystemProduct>(); 
		try {        	
		    	File myObj = new File("src\\resources\\systemProducts.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          String name = myReader.nextLine();
		          int min = Integer.parseInt(myReader.nextLine());
		          int max = Integer.parseInt(myReader.nextLine());
		          Types type = getType(myReader.nextLine());
		          Category category = getCategory(myReader.nextLine());
		          SystemProduct sysProduct = new SystemProduct(name, min, max, type, category);
		          systemProducts.add(sysProduct);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return systemProducts;	
	}
	
	static LinkedList<Order> loadOrders(){
		loadVouchers();
		loadProducts();
		orders = new LinkedList<Order>(); 
		try {        	
		    	File myObj = new File("src\\resources\\orders.txt");
		        Scanner myReader = new Scanner(myObj);
		        while (myReader.hasNextLine()) {
		          int orderID = Integer.parseInt(myReader.nextLine());
		          String address = myReader.nextLine();
		          Product product = getProduct(myReader.nextLine());
		          Buyer buyer = getBuyer(myReader.nextLine());
		          PaymentFactory paymentFactory = new PaymentFactory();
		          Payment payment = paymentFactory.createPayment(myReader.nextLine(), myReader.nextLine());
		          Order order = new Order(orderID, address, product, buyer, payment);
		          orders.add(order);        
		        }
		        myReader.close();
		 }
		 catch (FileNotFoundException e) {
			      System.out.println("An error occurred.");
			      e.printStackTrace();
		 }
		
		return orders;	
	}
	
	///////////////////////////////////////// SAVERS ///////////////////////////////////////////////
	static void saveProducts(ObservableList<Product> products) throws IOException {
	  FileWriter fw = new FileWriter("src\\resources\\products.txt");
	  for (int i = 0; i < products.size(); i++) {
		  Product product = products.get(i);
		  fw.write(product.getName() + "\n" + product.getPrice() + "\n" + 
				  product.getDescription() + "\n" + product.getQuantity() + "\n" +
				  product.getAgreement() + "\n" + product.getBrand().getBrandName() + "\n" +
				  product.getSystemProduct().getName() + "\n" + product.getStore().getStoreID() + "\n");
	  }
	  fw.close();
	}
	
	///////////////////////////////////////// HELPERS ///////////////////////////////////////////////
	
	static Store getStore(int ID) {
		for (int i = 0; i < onsiteStores.size(); i++) {
			if (onsiteStores.get(i).getStoreID() == ID) {
				return onsiteStores.get(i);
			}
		}
		
		for (int i = 0; i < onlineStores.size(); i++) {
			if (onlineStores.get(i).getStoreID() == ID) {
				return onlineStores.get(i);
			}
		}
		return null;
	}
	
	static StoreOwner getStoreOwner(String username) {
		for (int i = 0; i < storeOwners.size(); i++) {
			if (storeOwners.get(i).getUsername().equals(username)) {
				return storeOwners.get(i);
			}
		}
		return null;
	}
	
	static Buyer getBuyer(String username) {
		for (int i = 0; i < buyers.size(); i++) {
			if (buyers.get(i).getUsername().equals(username)) {
				return buyers.get(i);
			}
		}
		return null;
	}
	
	static Category getCategory(String categoryName) {
		for (int i = 0; i < categories.size(); i++) {
			if (categories.get(i).getCategoryName().equals(categoryName)) {
				return categories.get(i);
			}
		}
		return null;
	}
	
	static Brand getBrand(String brandName) {
		for (int i = 0; i < brands.size(); i++) {
			if (brands.get(i).getBrandName().equals(brandName)) {
				return brands.get(i);
			}
		}
		return null;
	}
	
	static SystemProduct getSystemProduct(String systemProductName) {
		for (int i = 0; i < systemProducts.size(); i++) {
			if (systemProducts.get(i).getName().equals(systemProductName)) {
				return systemProducts.get(i);
			}
		}
		return null;
	}
	
	static Voucher getVoucher(String voucherID) {
		for (int i = 0; i < vouchers.size(); i++) {
			if (vouchers.get(i).getVoucherID().equals(voucherID)) {
				return vouchers.get(i);
			}
		}
		return null;
	}
	
	static Product getProduct(String name) {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).getName().equals(name)) {
				return products.get(i);
			}
		}
		return null;
	}
	
	
	static Types getType(String type) {
		switch (type) {
		case "Onsite": return Types.Onsite;
		case "Online": return Types.Online;
		default: return null;
		}
	}
		
	static VoucherStatus getVoucherValidity(String validity) {
		switch (validity) {
		case "Valid": return VoucherStatus.Valid;
		case "Invalid": return VoucherStatus.Invalid;
		default: return null;
		}
			
	}
}
