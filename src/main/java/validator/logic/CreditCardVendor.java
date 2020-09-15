package validator.logic;

import java.util.List;

public class CreditCardVendor {
	private String name;
	private List<Integer> validLengths;
	private List<String> IIN;

	public CreditCardVendor(String vendorName, List<Integer> validLengths, List<String> masks){
		name = vendorName;
		this.validLengths = validLengths;
		IIN = masks;
	}

	public String getName(){
		return name;
	}

	public List<Integer> getValidLengths(){
		return validLengths;
	}

	public List<String> getIIN(){
		return IIN;
	}

	// Used as output for CLI (CreditCardValidatorProject)
	@Override
	public String toString() {
		return name;
	}
}
