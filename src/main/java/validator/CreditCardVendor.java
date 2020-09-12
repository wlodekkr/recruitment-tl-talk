package validator;

import java.util.ArrayList;

public class CreditCardVendor {
	private String name;
	private ArrayList<Integer> numberOfChars;
	private ArrayList<String> IIN;
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Integer> getNumberOfChars(){
		return numberOfChars;
	}
	
	public ArrayList<String> getIIN(){
		return IIN;
	}
	
	public CreditCardVendor(String vendorName, ArrayList<Integer> charsNumbers, ArrayList<String> masks){
		name = vendorName;
		numberOfChars = charsNumbers;
		IIN = masks;
	}
	
	@Override
	public String toString(){
		return name;
	}
}
