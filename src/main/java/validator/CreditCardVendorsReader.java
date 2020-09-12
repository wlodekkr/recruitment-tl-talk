package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardVendorsReader implements CreditCardVendorReading {
	@Override
	public List<CreditCardVendor> returnVendorArray() {
		List<CreditCardVendor> list = new ArrayList<>();
		
		try(BufferedReader br = new BufferedReader(new FileReader("Base_of_credit_card_vendors.txt"))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        String[] values = line.split(" ");
		        
		        String vendorName = values[0];
				ArrayList<Integer> vendorNumbersOfChars = getLengths(values[1]);
				ArrayList<String> vendorMasks = getMasks(values[2]);

				list.add(new CreditCardVendor(vendorName,vendorNumbersOfChars,vendorMasks));
		        
		    }
		} catch (IOException e) {
			e.printStackTrace();
		}

		return list;
	}

	private ArrayList<String> getMasks(String value) {
		ArrayList<String> vendorMasks = new ArrayList<String>();
		String[] masks = value.split("/");
		for (String mask : masks) {
			if (mask.split("-").length == 1) {
				vendorMasks.add(mask);
			} else {
				long min = Long.parseLong((mask.split("-"))[0]);
				long max = Long.parseLong((mask.split("-"))[1]);
				for (long j = min; j <= max; j++)
					vendorMasks.add("" + j);
			}
		}
		return vendorMasks;
	}

	private ArrayList<Integer> getLengths(String value) {
		ArrayList<Integer> vendorNumbersOfChars = new ArrayList<Integer>();
		String[] values = value.split("/");
		for (String val : values) {
			vendorNumbersOfChars.add(Integer.parseInt(val));
		}
		return vendorNumbersOfChars;
	}

}
