package gibson.exam.fund.rules.strategies;


import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;

public interface Strategy {

    boolean check(LoadFundRecord record, String value, CustomerData customerData);
}
