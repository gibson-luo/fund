package gibson.exam.fund.rules.strategies;

import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class MaximumAmountPerWeekRuleTests {

    private LoadFundRecord record;

    private CustomerData customerData;

    @BeforeEach
    void setup(){
        record = new LoadFundRecord();
        record.setCustomerId("1");
        record.setTime(LocalDateTime.of(2023, 4, 15, 14, 10));

        customerData = new CustomerData();
        customerData.setCustomerId("1");
        customerData.setDate(LocalDate.of(2023, 4, 15));
    }

    /**
     *
     * @param limit
     * @param loadAmount
     * @param loadAmountOfWeek
     * @param expected
     */
    @ParameterizedTest
    @CsvSource({
            "20000, 100, 5000, true",
            "20000, 4000, 16000, true",
            "20000, 7000, 16000, false",
            "10000, 1000, 9500, false",
    })
    void test_Maximum_Amount_Per_Week_Rule(String limit, String loadAmount, String loadAmountOfWeek, boolean expected){
        // set up
        record.setLoadAmount(loadAmount);
        customerData.setLoadAmountOfWeek(new BigDecimal(loadAmountOfWeek));

        // execute
        MaximumAmountPerWeekRule rule = new MaximumAmountPerWeekRule();
        boolean actual = rule.check(record, limit, customerData);

        // assert
        assertEquals(expected, actual, "Expected value should be the same as actual value");
    }

    @Test
    void test_throws_with_different_customerId() {
        // set up
        record.setCustomerId("1");
        customerData.setCustomerId("2");

        MaximumAmountPerWeekRule rule = new MaximumAmountPerWeekRule();

        // assert
        assertThrows(IllegalArgumentException.class, () -> {rule.check(record, "5000", customerData); }, "Should throw exception");
    }

    @Test
    void test_throws_with_different_date() {
        // set up
        record.setTime(LocalDateTime.of(2023, 4, 15, 10, 20));
        customerData.setDate(LocalDate.of(2023, 4, 14));

        MaximumAmountPerWeekRule rule = new MaximumAmountPerWeekRule();

        // assert
        assertThrows(IllegalArgumentException.class, () -> {rule.check(record, "5000", customerData); }, "Should throw exception");
    }
}
