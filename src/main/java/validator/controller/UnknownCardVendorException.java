package validator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UnknownCardVendorException extends ResponseStatusException {

    public UnknownCardVendorException(String unknownCardVendor) {
        super(HttpStatus.NOT_FOUND, String.format("Unknown Card Vendor : %s", unknownCardVendor));
    }
}
