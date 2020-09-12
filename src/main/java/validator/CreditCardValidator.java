package validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CreditCardValidator implements CreditCardChecking {

	@Override
	public boolean checkValidity(CreditCardVendor ccv, String number) {
		List<Integer> chars = ccv.getNumberOfChars();
		List<String> masks = ccv.getIIN();

		return checkLengthCorrectness(number, chars) &&
				checkIINMaskCorrectness (number, masks) &&
				checkLuhnalgorithmCorrectness(number);
	}
	
	private boolean checkLengthCorrectness(String number, List<Integer> chars){
		return chars.stream()
				.anyMatch(aChar -> aChar == number.length());
	}
	
	private boolean checkIINMaskCorrectness (String number, List<String> masks){
		return masks.stream()
				.anyMatch(number::startsWith);
	}
	
	private boolean checkLuhnalgorithmCorrectness(String number){
		String[] characters = number.split("");
		int[] intCharsReverse = new int[characters.length];

		IntStream.range(0, characters.length)
				.forEach(i -> intCharsReverse[characters.length - 1 - i] = Integer.parseInt(characters[i]));

		IntStream.range(0, intCharsReverse.length)
				.forEach(i -> intCharsReverse[i] = (i % 2 == 1) ? 2 * intCharsReverse[i] : intCharsReverse[i]);

		int sum = Arrays.stream(intCharsReverse)
				.map(j -> (j < 10) ? j : (j % 10 + j / 10)).sum();

		return sum % 10 == 0;
	}

}
