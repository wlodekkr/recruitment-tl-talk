package validator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class CreditCardValidator implements CreditCardChecking {

    @Override
    public boolean checkValidity(CreditCardVendor ccv, String number) {
        List<Integer> validLengths = ccv.getPossibleLengths();
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
        int[] algorytmicCardDigits = new int[cardDigits.length];

        IntStream.range(0, cardDigits.length)
                .forEach(i -> algorytmicCardDigits[i] = Integer.parseInt(cardDigits[i]));

        IntStream.range(0, algorytmicCardDigits.length)
                .forEach(i -> algorytmicCardDigits[i] = (i % 2 == 0) ? 2 * algorytmicCardDigits[i] : algorytmicCardDigits[i]);

        int sum = Arrays.stream(algorytmicCardDigits)
                .map(j -> (j < 10) ? j : (j % 10 + j / 10)).sum();

        return sum % 10 == 0;
    }

}
