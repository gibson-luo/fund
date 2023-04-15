package gibson.exam.fund.tools;


import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DateUtilTests {

    @Test
    void test_getDaysOfWeek_to_return_expected_list() {
        // set up
        LocalDate date = LocalDate.of(2023, 4, 8);

        LocalDate monday = LocalDate.of(2023, 4, 3);
        LocalDate tuesday = LocalDate.of(2023, 4, 4);
        LocalDate wednesday = LocalDate.of(2023, 4, 5);
        LocalDate thursday = LocalDate.of(2023, 4, 6);
        LocalDate friday = LocalDate.of(2023, 4, 7);
        LocalDate saturday = LocalDate.of(2023, 4, 8);
        LocalDate sunday = LocalDate.of(2023, 4, 9);

        List<LocalDate> expectedList = List.of(monday, tuesday, wednesday, thursday, friday, saturday, sunday);

        // execute
        List<LocalDate> actual = DateUtil.getDaysOfWeek(date);

        // assert
        assertIterableEquals(expectedList, actual, "Expected list should be same as actual list");
    }

    @Test
    void test_toInClauseString_to_return_expected_value() {
        // set up
        LocalDate monday = LocalDate.of(2023, 4, 3);
        LocalDate tuesday = LocalDate.of(2023, 4, 4);
        LocalDate wednesday = LocalDate.of(2023, 4, 5);
        LocalDate thursday = LocalDate.of(2023, 4, 6);

        List<LocalDate> days = List.of(monday, tuesday, wednesday, thursday);

        String expected = "'2023-04-03','2023-04-04','2023-04-05','2023-04-06'";

        // execute
        String actual = DateUtil.toInClauseString(days);

        // assert
        assertEquals(expected, actual, "Expected value should be same as actual value");
    }

}
