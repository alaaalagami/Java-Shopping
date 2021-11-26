package Entities;
// Products class

public class Product {

	String name; 
	int price; 
	String description; 
	int quantity; 
	String agreement; 
	Brand brand; 
	SystemProduct systemProduct;
	Store store;
	
	
	public SystemProduct getSystemProduct() {
		return systemProduct;
	}
	public void setSystemProduct(SystemProduct systemProduct) {
		this.systemProduct = systemProduct;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQuantity() {
		return quantity;
	}
	public String getStoreName() {
		return this.getStore().getName();
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getAgreement() {
		return agreement;
	}
	public void setAgreement(String agreement) {
		this.agreement = agreement;
	}
	
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public Product(String name, int price, String description, int quantity, String agreement,
			Brand brand, SystemProduct systemProduct, Store store) {
		if (price <= systemProduct.priceRange.max && price >= systemProduct.priceRange.min) {
		
		this.name = name;		
		this.price = price;
		this.description = description;
		this.quantity = quantity;
		this.agreement = agreement;
		this.brand = brand;
		this.systemProduct = systemProduct;
		this.store = store;
		}
		else 
			throw new IllegalArgumentException("Price range not within the system product price");
	}
	@Override
	public String toString() {
		return "Product [ name=" + name + ", price=" + price + ", description="
				+ description + ", quantity=" + quantity + ", agreement=" + agreement  + ", brand="
				+ brand + ", systemProduct=" + systemProduct + "]";
	} 
	
	
	
	
	
}
