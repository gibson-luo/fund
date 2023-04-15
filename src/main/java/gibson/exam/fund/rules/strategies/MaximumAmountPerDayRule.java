package gibson.exam.fund.rules.strategies;

import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("maximumAmountPerDay")
public class MaximumAmountPerDayRule implements Strategy {
    private static final Logger LOGGER = LoggerFactory.getLogger(MaximumAmountPerDayRule.class);

    @Override
    public boolean check(LoadFundRecord record, String value, CustomerData customerData) throws IllegalArgumentException {

        if(!record.getCustomerId().equals(customerData.getCustomerId()))throw new IllegalArgumentException("Should be the customerId");
        if(!record.getTime().toLocalDate().equals(customerData.getDate()))throw new IllegalArgumentException("Should be the date");

        int result = customerData.getLoadAmountOfDay().add(record.getLoadAmount()).compareTo(new BigDecimal(value));
        if(result == -1 || result == 0){
            return true;
        }else {
            return false;
        }
    }
}
