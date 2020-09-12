package validator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class CreditCardValidatorProject {
	
	private static CreditCardVendorsReader reader = new CreditCardVendorsReader();
	private static ArrayList<CreditCardVendor> vendors = reader.returnVendorArray();
	private static CreditCardValidator validator = new CreditCardValidator();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
        System.out.println("CREDIT CARD VALIDATOR\n");
        
        int vendorsLength = vendors.size();
        int selected = 0;
        
        while (true){
        	printMenu();
        	try{
                selected = Integer.parseInt(br.readLine());
                if(selected < vendorsLength){
    	        	System.out.println("Type your credit card number:");
    	        	String cardNumber;
    				try {
    					cardNumber = br.readLine();
    					boolean result = validator.checkValidity(vendors.get(selected), cardNumber);
    					printResultOfValidation(result);
    				} catch (IOException e) {
    					System.out.println("Something went wrong!\n Please retry the process");
    				}
    	        	
            	}else{
            		break;
            	}
            }catch(NumberFormatException | IOException e){
                System.err.println("Invalid Format!");
            }
        	
        }
        
    }
	
	static private void printMenu(){
		System.out.println("Select your card vendor from list below("+vendors.size()+" exits):");
    	for (int i = 0;i<vendors.size();++i)
    		System.out.println(i+" "+vendors.get(i).toString());
	}
	
	static private void printResultOfValidation(boolean result){
		if(result){
    		System.out.println("\n\nCard number is valid.\n");
    	}else{
    		System.out.println("\n\nCard number is invalid!\n");
    	}
	}
}
