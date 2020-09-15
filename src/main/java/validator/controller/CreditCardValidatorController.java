package validator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import validator.logic.CreditCardChecking;


@RestController
public class CreditCardValidatorController {
    @Autowired
    CreditCardChecking validator;

    @Autowired
    CreditCardVendorProvider cardVendors;

    @PostMapping("/validate/card")
    public CardValidationResponseDto validateCard(@RequestBody CardValidationRequestDto request) {
        return cardVendors.getVendor(request.getCardVendor())
                .map(vendor -> validator.checkValidity(vendor, request.getCardNumber()))
                .map(valid -> new CardValidationResponseDto(valid))
                .orElseThrow(() -> new UnknownCardVendorException(request.getCardVendor()));
    }
}
