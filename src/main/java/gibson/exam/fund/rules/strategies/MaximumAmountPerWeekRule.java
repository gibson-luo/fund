package gibson.exam.fund.rules.strategies;


import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service("maximumAmountPerWeek")
public class MaximumAmountPerWeekRule implements Strategy {
    @Override
    public boolean check(LoadFundRecord record, String value, CustomerData customerData) throws IllegalArgumentException {

        if(!record.getCustomerId().equals(customerData.getCustomerId()))throw new IllegalArgumentException("Should be the customerId");
        if(!record.getTime().toLocalDate().equals(customerData.getDate()))throw new IllegalArgumentException("Should be the date");

        int result = customerData.getLoadAmountOfWeek().add(record.getLoadAmount()).compareTo(new BigDecimal(value));
        if(result == -1 || result == 0){
            return true;
        }else {
            return false;
        }
    }
}
