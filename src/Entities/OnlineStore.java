package Entities;
// Online stores Class - inherits Store class

public class OnlineStore extends Store {

	public OnlineStore(int storeID, String name, String type, StoreOwner storeOwner) {
		super(storeID, name, type, storeOwner);
	}

	@Override
	public String toString() {
		return "storeID=" + storeID + ", name=" + name + ", type=" + type;
	}	
}
