package Entities;

public class VoucherPayment extends Payment {
	Voucher voucher;
	
	public VoucherPayment(Voucher voucher){
		this.voucher = voucher;
		this.type = PaymentType.Voucher;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Voucher" + "\n" + voucher.getVoucherID();
	}
}
