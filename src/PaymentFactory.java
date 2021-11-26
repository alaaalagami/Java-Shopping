import Entities.CreditCardPayment;
import Entities.OnDeliveryPayment;
import Entities.Payment;
import Entities.Voucher;
import Entities.VoucherPayment;
import Entities.VoucherStatus;

public class PaymentFactory {
	
	public Payment createPayment(String paymentType, String paymentID){
		if (paymentType.equals("On Delivery")) {
			return new Payment();
		}
		else if (paymentType.equals("Credit Card")) {
			return new CreditCardPayment(paymentID);
		}
		else if (paymentType.equals("Voucher")) {
			Utilities.loadVouchers();
			Voucher voucher = Utilities.getVoucher(paymentID);
			if (voucher != null && voucher.getStatus() == VoucherStatus.Valid) {
				return new VoucherPayment(voucher);
			}
			else {
				return null;
			}
			
		}
		
		return null;
	}
}
