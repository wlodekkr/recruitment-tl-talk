package validator;

import java.util.ArrayList;

public class CreditCardValidator implements CreditCardChecking {

	@Override
	public boolean checkValidity(CreditCardVendor ccv, String number) {
		ArrayList<Integer> chars = ccv.getNumberOfChars();
		ArrayList<String> masks = ccv.getIIN();
		
		boolean result1 = checkLengthCorrectness(number, chars);
		boolean result2 = checkIINMaskCorrectness (number, masks);
		boolean result3 = checkLuhnalgorithmCorrectness(number);
		
		return result1 && result2 && result3;
	}
	
	private boolean checkLengthCorrectness(String number, ArrayList<Integer> chars){
		boolean result = false;
		for (int i = 0; i<chars.size();i++){
			if (chars.get(i) == number.length())
				result = true;
		}
		return result;
	}
	
	private boolean checkIINMaskCorrectness (String number, ArrayList<String> masks){
		boolean result = false;
		for (int i = 0; i<masks.size();i++){
			if (number.startsWith(masks.get(i)))
				result = true;
		}
		return result;
	}
	
	private boolean checkLuhnalgorithmCorrectness(String number){
		boolean result = false;
		
		String[] characters = number.split("");
		int[] intCharsReverse = new int[characters.length];
		int sum = 0;
		
		for(int i = 0;i<characters.length;i++){
			intCharsReverse[characters.length-1-i] = Integer.parseInt(characters[i]);
		}
		
		for(int i = 0;i<intCharsReverse.length;i++){
			intCharsReverse[i] = (i%2==1) ? 2*intCharsReverse[i] : intCharsReverse[i];
		}
		
		for(int i = 0;i<intCharsReverse.length;i++){
			sum += (intCharsReverse[i]<10) ? intCharsReverse[i] : (intCharsReverse[i]%10 + intCharsReverse[i]/10);
		}
		
		if(sum%10==0)
			result = true;
		
		return result;
	}

}
