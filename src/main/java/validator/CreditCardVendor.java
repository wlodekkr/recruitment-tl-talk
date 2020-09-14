package validator;

import java.util.List;

public class CreditCardVendor {
	private String name;
	private List<Integer> numberOfChars;
	private List<String> IIN;
	
	public String getName(){
		return name;
	}
	
	public List<Integer> getNumberOfChars(){
		return numberOfChars;
	}
	
	public List<String> getIIN(){
		return IIN;
	}
	
	public CreditCardVendor(String vendorName, List<Integer> charsNumbers, List<String> masks){
		name = vendorName;
		numberOfChars = charsNumbers;
		IIN = masks;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
