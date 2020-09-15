package validator.controller;

public class CardValidationRequestDto {
    public CardValidationRequestDto(String cardVendor, String cardNumber) {
        this.cardVendor = cardVendor;
        this.cardNumber = cardNumber;
    }

    private String cardVendor;
    private String cardNumber;

    public String getCardVendor() {
        return cardVendor;
    }

    public String getCardNumber() {
        return cardNumber;
    }
}
