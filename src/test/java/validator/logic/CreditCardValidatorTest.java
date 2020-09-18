package validator.logic;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditCardValidatorTest {
	private static CreditCardVendorsReader reader = new CreditCardVendorsReader();
	private static List<CreditCardVendor> vendors = reader.returnVendorArray();
	private static CreditCardValidator validator = new CreditCardValidator();

	public static final String MASTER_CARD = "MasterCard";
	private static CreditCardVendor vendorMasterCard = vendors.stream()
			.collect(Collectors.toMap(CreditCardVendor::getName, Function.identity()))
			.get(MASTER_CARD);

	private void checkCardValidity(String number, boolean valid) {
		assertEquals(valid, validator.checkValidity(vendorMasterCard, number));
	}

	@Test
	public void testLengthOfNumberWhenCorrect() {
		checkCardValidity("5584239583699571", true);
	}

	@Test
	public void testLengthOfNumberWhenIncorrect() {
		checkCardValidity("55842395", false);
	}
	
	@Test
	public void testIINWhenCorrect() {
		checkCardValidity("5584239583699571", true);
	}
	
	@Test
	public void testIINWhenIncorrect() {
		checkCardValidity("7784239583699571", false);
	}
	
	@Test
	public void testLuhnAlghoritmWhenCorrect() {
		checkCardValidity("5584239583699571", true);
	}
	
	@Test
	public void testLuhnAlghoritmWhenIncorrect() {
		checkCardValidity("5584239583611111", false);
	}

}
