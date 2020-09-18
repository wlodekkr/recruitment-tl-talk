package validator.main;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import validator.controller.CardValidationRequestDto;
import validator.controller.CardValidationResponseDto;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class CreditCardValidatorRestServiceTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void validatesSuccesfullyValidCardNumber() throws Exception {
        CardValidationRequestDto requestDto =
                new CardValidationRequestDto("MasterCard", "5584239583699571");

        CardValidationResponseDto response = restTemplate
                .postForObject("/validate/card",
                        requestDto,
                        CardValidationResponseDto.class);

        assertThat(response.isValid())
                .isTrue();
    }

    @Test
    public void validatesUnsuccesfullyInvalidCardNumber() throws Exception {
        CardValidationRequestDto requestDto =
                new CardValidationRequestDto("MasterCard", "invalid");

        CardValidationResponseDto response = restTemplate
                .postForObject("/validate/card",
                        requestDto,
                        CardValidationResponseDto.class);

        assertThat(response.isValid())
                .isFalse();
    }

    @Test
    public void returns404ErrorForUnknownCardVendor() throws Exception {
        CardValidationRequestDto requestDto =
                new CardValidationRequestDto("UnknownCard", "5584239583699571");

        Map response = restTemplate.postForObject("/validate/card",
                        requestDto,
                        Map.class);

        assertThat(response)
                .containsEntry("status", 404)
                .containsEntry("message", "Unknown Card Vendor : UnknownCard");
    }

}