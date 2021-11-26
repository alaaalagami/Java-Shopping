package Entities;
// Stores class

import java.util.*;

public class Store {

	int storeID;
	String name;
	String type;
	StoreOwner storeOwner;
	
	public StoreOwner getStoreOwner() {
		return storeOwner;
	}
	public void setStoreOwner(StoreOwner storeOwner) {
		this.storeOwner = storeOwner;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Store(int storeID, String name, String type, StoreOwner storeOwner) {
		super();
		this.storeID = storeID;
		this.name = name;
		this.type = type;
		this.storeOwner = storeOwner; 
	} 

	@Override
	public String toString() {

		return "Store [nationalID=" + storeID + ", name=" + name + ", type=" + type + ", store owner=" + storeOwner.username + "]";
	}
	
	
	
}
