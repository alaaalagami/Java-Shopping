package Entities;

public class OnDeliveryPayment extends Payment {
	public OnDeliveryPayment(){
		this.type = PaymentType.OnDelivery;
	}

	@Override
	public String toString() {
		return "On Delivery" + "\n" + "-";
	}
}
