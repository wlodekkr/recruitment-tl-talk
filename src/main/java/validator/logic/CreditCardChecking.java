package validator.logic;

// TODO Is this interface required?
public interface CreditCardChecking {
	boolean checkValidity(CreditCardVendor ccv, String number);
}
