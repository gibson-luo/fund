package gibson.exam.fund.pojo;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"load_amount", "time"})
@JsonPropertyOrder({"id", "customer_id", "accepted"})
public class LoadFundRecord implements Serializable {

    private Long id;
    @JsonGetter(value = "id")
    public String getIdString() {
        return id.toString();
    }

    private String customerId;

    @JsonSetter(value = "customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private BigDecimal loadAmount;

    @JsonSetter(value = "load_amount")
    public void setLoadAmount(String loadAmount) {
        this.loadAmount = new BigDecimal(loadAmount);
    }

    private LocalDateTime time;

    private boolean accepted;


}
