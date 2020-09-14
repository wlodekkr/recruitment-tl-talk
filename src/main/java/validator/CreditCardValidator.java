package validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CreditCardValidator implements CreditCardChecking {

    @Override
    public boolean checkValidity(CreditCardVendor ccv, String number) {
        List<Integer> validLengths = ccv.getValidLengths();
        List<String> validMasks = ccv.getIIN();

        return checkLengthCorrectness(number, validLengths) &&
                checkIINMaskCorrectness(number, validMasks) &&
                checkLuhnAlgorithmCorrectness(number);
    }

    private boolean checkLengthCorrectness(String number, List<Integer> validLengths) {
        return validLengths.stream()
                .anyMatch(lengthPossibility -> lengthPossibility == number.length());
    }

    private boolean checkIINMaskCorrectness(String number, List<String> validMasks) {
        return validMasks.stream()
                .anyMatch(number::startsWith);
    }

    private boolean checkLuhnAlgorithmCorrectness(String cardNumber) {
        String[] cardDigits = cardNumber.split("");
        int[] algorithmicCardDigits = new int[cardDigits.length];

        IntStream.range(0, cardDigits.length)
                .forEach(i -> algorithmicCardDigits[i] = Integer.parseInt(cardDigits[i]));

        IntStream.range(0, algorithmicCardDigits.length)
                .forEach(i -> algorithmicCardDigits[i] = (i % 2 == 0) ? 2 * algorithmicCardDigits[i] : algorithmicCardDigits[i]);

        int sum = Arrays.stream(algorithmicCardDigits)
                .map(j -> (j < 10) ? j : (j % 10 + j / 10)).sum();

        return sum % 10 == 0;
    }

}
