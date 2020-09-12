package validator;

public interface CreditCardChecking {
	public boolean checkValidity(CreditCardVendor ccv, String number);
}
