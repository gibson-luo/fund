package gibson.exam.fund.rules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerData {

    private String customerId;

    private LocalDate date;

    private BigDecimal loadAmountOfDay = BigDecimal.ZERO;

    private BigDecimal loadAmountOfWeek = BigDecimal.ZERO;

    private Integer loadTimesOfDay = 0;
}
