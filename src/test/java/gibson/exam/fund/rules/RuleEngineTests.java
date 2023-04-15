package gibson.exam.fund.rules;

import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.pojo.Rule;
import gibson.exam.fund.service.RuleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class RuleEngineTests {

    private LoadFundRecord record;

    private CustomerData customerData;

    @MockBean
    private RuleService ruleService;

    @BeforeEach
    void setup(){
        record = new LoadFundRecord();
        record.setCustomerId("1");
        record.setTime(LocalDateTime.of(2023, 4, 15, 14, 10));

        customerData = new CustomerData();
        customerData.setCustomerId("1");
        customerData.setDate(LocalDate.of(2023, 4, 15));

        Rule rule1 = new Rule(1l, "", "maximumAmountPerDay", "5000");
        Rule rule2 = new Rule(2l, "", "maximumAmountPerWeek", "20000");
        Rule rule3 = new Rule(3l, "", "maximumLoadsPerDay", "3");
        List<Rule> rules = List.of(rule1, rule2, rule3);
        when(ruleService.loadAll()).thenReturn(rules);

        RuleEngine.loadRules();
    }


    /**
     *
     * @param loadAmount
     * @param loadAmountOfDay
     * @param loadAmountOfWeek
     * @param loadTimesOfDay
     * @param expected
     */
    @ParameterizedTest
    @CsvSource({
            "100, 4000, 10000, 1, true",
            "1001, 4000, 10000, 1, false",
            "1001, 3000, 19000, 1, false",
            "1001, 3000, 10000, 3, false",
    })
    void test_check_rules(String loadAmount, String loadAmountOfDay, String loadAmountOfWeek, String loadTimesOfDay, boolean expected){
        // set up
        record.setLoadAmount(loadAmount);
        customerData.setLoadAmountOfDay(new BigDecimal(loadAmountOfDay));
        customerData.setLoadAmountOfWeek(new BigDecimal(loadAmountOfWeek));
        customerData.setLoadTimesOfDay(Integer.valueOf(loadTimesOfDay));

        // execute
        boolean actual = RuleEngine.checkRules(record, customerData);

        // assert
        assertEquals(expected, actual, "Expected value should be the same as actual value");
    }
}
