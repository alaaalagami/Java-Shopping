package Entities;

public class Order {
	int orderID;
	String address;
	Product product;
	Buyer buyer;
	Payment payment;
	
	public Order(int orderID, String address, Product product, Buyer buyer, Payment payment) {
		this.orderID = orderID;
		this.address = address;
		this.product = product;
		this.payment = payment;
		this.buyer = buyer;
	}
	
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	
}
