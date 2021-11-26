package Entities;
// System products class

   class range{
	int min; 
	int max; 
	range(int min, int max){
		this.min = min; 
		this.max = max;
	}	
}

public class SystemProduct {
	
	String name; 
	range priceRange; 
	Types type;
	Category category; 
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public range getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(range priceRange) {
		this.priceRange = priceRange;
	}
	public Types getType() {
		return type;
	}
	public void setType(Types type) {
		this.type = type;
	}
	
	public int getMin() {
		return priceRange.min;
	}
	public int getMax() {
		return priceRange.max;
	}
	
	public SystemProduct(String name, int min, int max, Types type, Category category) {
		this.name = name;
		this.priceRange = new range(min, max); 
		this.type = type;
		this.category = category; 
	}
	@Override
	public String toString() {
		return name + ", Min Price =" + priceRange.min + ", Max Price =" + priceRange.max + ", type=" + type + ", category="
				+ category;
	} 

	
	
}
