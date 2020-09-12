package validator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CreditCardVendorsReader implements CreditCardVendorReading {
	@Override
	public ArrayList<CreditCardVendor> returnVendorArray() {
		ArrayList<CreditCardVendor> list = new ArrayList<CreditCardVendor>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("Base_of_credit_card_vendors.txt"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] values = line.split(" ");
		        
		        String vendorName = values[0];
		        ArrayList<Integer> vendorNumbersOfChars = new ArrayList<Integer>();
		        ArrayList<String> vendorMasks = new ArrayList<String>();
		        
		        parseLengths(values[1], vendorNumbersOfChars);
		        parseMasks(values[2], vendorMasks);
		        
		        list.add(new CreditCardVendor(vendorName,vendorNumbersOfChars,vendorMasks));
		        
		    }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	private void parseLengths(String value, ArrayList<Integer> vendorNumbersOfChars){
		String[] values = value.split("/");
		for(int i=0; i<values.length; i++){
        	vendorNumbersOfChars.add(Integer.parseInt(values[i]));
        }
	}

	private void parseMasks(String value,  ArrayList<String> vendorMasks){
	     String[] masks = value.split("/");
	     for(int i=0;i<masks.length; i++){
	     	if (masks[i].split("-").length == 1){
	     		vendorMasks.add(masks[i]);
	     	}else{
	     		long min= Long.parseLong((masks[i].split("-"))[0]);
	     		long max= Long.parseLong((masks[i].split("-"))[1]);
	     		for (long j = min; j<=max;j++)
	     			vendorMasks.add(""+j);
	     	}
	     }
	}
	
}
