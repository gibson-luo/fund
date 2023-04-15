package gibson.exam.fund.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import gibson.exam.fund.pojo.LoadFundRecord;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class RequestLoadFundRecord implements Serializable {

    private String id;

    private String customerId;

    @JsonSetter(value = "customer_id")
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    private String loadAmount;

    @JsonSetter(value = "load_amount")
    public void setLoadAmount(String loadAmount) {
        this.loadAmount = loadAmount;
    }

    public BigDecimal getLoadAmountNumber() {
        if(loadAmount.isEmpty()){
           return BigDecimal.ZERO;
        }
        return new BigDecimal(loadAmount.replace("$", ""));
    }

    private String time;

    public LocalDateTime getLocalDateTime() {
        if(time == null){
            return null;
        }
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
        return LocalDateTime.parse(time, pattern);
    }

    public LoadFundRecord toLoadFundRecord() {
        return new LoadFundRecord(Long.valueOf(id), customerId, getLoadAmountNumber(), getLocalDateTime(), false);
    }
}
