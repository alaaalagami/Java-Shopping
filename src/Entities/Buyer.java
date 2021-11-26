package Entities;
// Buyer user class - inherits User class

public class Buyer extends User {
	
	public Buyer(String username, String password, String address) {
		super(username, password);
		this.address = address; 
	}

	String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	} 

}
