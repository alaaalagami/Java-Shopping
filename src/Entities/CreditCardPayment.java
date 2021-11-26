package Entities;

public class CreditCardPayment extends Payment {
	String cardNumber;
	
	public CreditCardPayment(String number){
		this.cardNumber = number;
		this.type = PaymentType.CreditCard;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Credit Card" + "\n" +  cardNumber;
	}
}
