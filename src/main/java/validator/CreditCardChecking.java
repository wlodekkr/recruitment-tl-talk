package validator;

public interface CreditCardChecking {
	boolean checkValidity(CreditCardVendor ccv, String number);
}
