package Entities;

public class Payment {
	PaymentType type;
	
	public Payment(){
		this.type = PaymentType.OnDelivery;
	}
	
	public String toString() {
		return "On Delivery" + "\n" + "-"; 
	}
}
