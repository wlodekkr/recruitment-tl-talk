package validator.controller;

public class CardValidationResponseDto {
    private boolean valid;

    public CardValidationResponseDto() {
    }

    public CardValidationResponseDto(boolean valid) {
        this.valid = valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}
