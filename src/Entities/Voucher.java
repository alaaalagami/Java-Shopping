package Entities;

public class Voucher {
	String voucherID;
	Buyer buyer;
	Store store;
	VoucherStatus status;
	
	public Voucher(String voucherID, Buyer buyer, Store store, VoucherStatus status) {
		this.voucherID = voucherID;
		this.buyer = buyer;
		this.store = store;
		this.status = status;
	}
	public Buyer getBuyer() {
		return buyer;
	}
	public void setBuyer(Buyer buyer) {
		this.buyer = buyer;
	}
	public String getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(String voucherID) {
		this.voucherID = voucherID;
	}
	public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
	public VoucherStatus getStatus() {
		return status;
	}
	public void setStatus(VoucherStatus status) {
		this.status = status;
	}
}
