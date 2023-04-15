package gibson.exam.fund.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoadCalculationDaily implements Serializable {

    private Long id;

    private String customerId;

    private BigDecimal loadAmount;

    private Integer loadTimes;

    private LocalDate date;

}
