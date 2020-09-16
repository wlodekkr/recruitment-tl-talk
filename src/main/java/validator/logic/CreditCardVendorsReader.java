package validator.logic;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Component
public class CreditCardVendorsReader implements CreditCardVendorReading {

    public static final String CARDS_CONFIG = "Base_of_credit_card_vendors.txt";

    @Override
    public List<CreditCardVendor> returnVendorArray() {
        ClassPathResource resource = new ClassPathResource(CARDS_CONFIG);

        try {
            return Files.lines(Path.of(resource.getURI()))
                    .map(line -> line.split(" "))
                    .map(values -> {
                        String vendorName = values[0];
                        List<Integer> possibleLengths = getLengths(values[1]);
                        List<String> vendorMasks = getMasks(values[2]);

                        return new CreditCardVendor(vendorName, possibleLengths, vendorMasks);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private List<String> getMasks(String value) {
        String[] masks = value.split("/");

        return Arrays.stream(masks)
                .flatMap(mask -> {
                    String[] maskData = mask.split("-");

                    if (maskData.length == 1) {
                        return Stream.of(maskData);
                    } else {
                        int min = Integer.parseInt(maskData[0]);
                        int max = Integer.parseInt(maskData[1]);

                        return IntStream
                                .range(min, max + 1)
                                .mapToObj(i -> "" + i);
                    }
                })
                .collect(Collectors.toList());
    }

    private List<Integer> getLengths(String value) {
        String[] values = value.split("/");

        return Arrays.stream(values)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

}
