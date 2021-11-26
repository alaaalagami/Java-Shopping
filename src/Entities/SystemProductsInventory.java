package Entities;
//Inventory of all registered system products using linked list

import java.util.LinkedList;

public class SystemProductsInventory {

	LinkedList<SystemProduct> systemProducts;

	public LinkedList<SystemProduct> getproducts() {
		return systemProducts;
	}

	public void setProducts(LinkedList<SystemProduct> systemProducts) {
		this.systemProducts = systemProducts;
	}

	public SystemProductsInventory() {
		this.systemProducts = new LinkedList<SystemProduct>();
	}
	
	public Boolean addProduct(String name, int min, int max, Types type, Category category) {
		SystemProduct systemProduct = new SystemProduct(name, min, max, type,category); 
		systemProducts.add(systemProduct); 
		return true; 
	}
	
}
