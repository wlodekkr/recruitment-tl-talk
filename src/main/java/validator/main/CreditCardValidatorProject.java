package validator.main;

import validator.logic.CreditCardChecking;
import validator.logic.CreditCardValidator;
import validator.logic.CreditCardVendor;
import validator.logic.CreditCardVendorsReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.IntStream;

import static java.lang.System.err;
import static java.lang.System.out;

public class CreditCardValidatorProject {
	
	private static CreditCardVendorsReader reader = new CreditCardVendorsReader();
	private static List<CreditCardVendor> vendors = reader.returnVendorArray();
	private static CreditCardChecking validator = new CreditCardValidator();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
        out.println("CREDIT CARD VALIDATOR\n");
        
        int vendorsLength = vendors.size();
        int selected;
        
        while (true){
        	printMenu();
        	try{
                selected = Integer.parseInt(br.readLine());
                if(selected < vendorsLength){
    	        	out.println("Type your credit card number:");
    	        	String cardNumber;
    				try {
    					cardNumber = br.readLine();
    					boolean result = validator.checkValidity(vendors.get(selected), cardNumber);
    					printResultOfValidation(result);
    				} catch (IOException e) {
    					out.println("Something went wrong!\n Please retry the process");
    				}
    	        	
            	}else{
            		break;
            	}
            }catch(NumberFormatException | IOException e){
                err.println("Invalid Format!");
            }
        	
        }
        
    }
	
	static private void printMenu(){
		out.println("Select your card vendor from list below("+vendors.size()+" exits):");

		IntStream.range(0, vendors.size())
				.mapToObj(i -> i + " " + vendors.get(i).toString())
				.forEach(out::println);
	}
	
	static private void printResultOfValidation(boolean result){
		if(result){
    		out.println("\n\nCard number is valid.\n");
    	}else{
    		out.println("\n\nCard number is invalid!\n");
    	}
	}
}
