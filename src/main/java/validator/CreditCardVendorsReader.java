package validator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardVendorsReader implements CreditCardVendorReading {

    public static final String CARDS_CONFIG = "Base_of_credit_card_vendors.txt";

    @Override
    public List<CreditCardVendor> returnVendorArray() {
        List<CreditCardVendor> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(CARDS_CONFIG))) {
            br.lines().map(line -> line.split(" "))
                    .forEach(values -> {
                        String vendorName = values[0];
                        List<Integer> vendorNumbersOfChars = getLengths(values[1]);
                        List<String> vendorMasks = getMasks(values[2]);
                        list.add(new CreditCardVendor(vendorName, vendorNumbersOfChars, vendorMasks));
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private List<String> getMasks(String value) {
        List<String> vendorMasks = new ArrayList<>();
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

    private List<Integer> getLengths(String value) {
        List<Integer> vendorNumbersOfChars = new ArrayList<>();
        String[] values = value.split("/");
        for (String val : values) {
            vendorNumbersOfChars.add(Integer.parseInt(val));
        }
        return vendorNumbersOfChars;
    }

}
