package validator.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("validator")
public class CreditCardValidatorRestService {
    public static void main(String[] args) {
        SpringApplication.run(CreditCardValidatorRestService.class, args);
    }
}
