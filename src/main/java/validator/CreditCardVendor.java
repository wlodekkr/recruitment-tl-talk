package validator;

import java.util.List;

public class CreditCardVendor {
	private String name;
	private List<Integer> numberOfChars;
	private List<String> IIN;

	public CreditCardVendor(String vendorName, List<Integer> charsNumbers, List<String> masks){
		name = vendorName;
		numberOfChars = charsNumbers;
		IIN = masks;
	}

	public String getName(){
		return name;
	}

	public List<Integer> getPossibleLengths(){
		return numberOfChars;
	}

	public List<String> getIIN(){
		return IIN;
	}
}
