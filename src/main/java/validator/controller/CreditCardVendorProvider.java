package validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import validator.logic.CreditCardVendor;
import validator.logic.CreditCardVendorReading;

import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class CreditCardVendorProvider {

    private final Map<String, CreditCardVendor> vendors;

    @Autowired
    public CreditCardVendorProvider(CreditCardVendorReading vendorReading) {
        vendors = vendorReading.returnVendorArray().stream()
                .collect(Collectors.toMap(CreditCardVendor::getName, Function.identity(),
                        (left, right) -> {
                            throw new IllegalArgumentException("Duplicated vendor in config : " + left);
                        }));
    }

    public Optional<CreditCardVendor> getVendor(String cardVendor) {
        return Optional.ofNullable(vendors.get(cardVendor));
    }


}
