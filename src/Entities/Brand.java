package Entities;

// Brands class

public class Brand {

	String brandName;
	Category category; 
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	
	public Brand(String brandName, Category category) {
		this.brandName = brandName;
		this.category = category; 
	}
	
	@Override
	public String toString() {
		return brandName + ", category= " + category;
	} 
	
	
}
