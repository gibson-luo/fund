package gibson.exam.fund.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gibson.exam.fund.mapper.LoadCalculationDailyMapper;
import gibson.exam.fund.pojo.LoadCalculationDaily;
import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;
import gibson.exam.fund.tools.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoadCalculationDailyService extends ServiceImpl<LoadCalculationDailyMapper, LoadCalculationDaily> {

    @Autowired
    private LoadCalculationDailyMapper loadCalculationDailyMapper;

    public LoadCalculationDaily fetchByCustomerIdAndDate(String customerId, LocalDate date){
        return loadCalculationDailyMapper.fetchByCustomerIdAndDate(customerId, date);
    }

    public CustomerData calculateCustomerData(LoadFundRecord record){
        List<LocalDate> days = DateUtil.getDaysOfWeek(record.getTime().toLocalDate());
        List<LoadCalculationDaily> items = loadCalculationDailyMapper.listWeekByCustomerId(record.getCustomerId(), DateUtil.toInClauseString(days));

        CustomerData customerData = new CustomerData();
        customerData.setCustomerId(record.getCustomerId());
        customerData.setDate(record.getTime().toLocalDate());

        for(LoadCalculationDaily item : items) {
            if(item.getDate().equals(record.getTime().toLocalDate())){
                customerData.setLoadAmountOfDay(item.getLoadAmount());
                customerData.setLoadTimesOfDay(item.getLoadTimes());
            }
            customerData.setLoadAmountOfWeek(customerData.getLoadAmountOfWeek().add(item.getLoadAmount()));
        }

        return customerData;
    }

    public void updateDailyData(LoadFundRecord record){
        LoadCalculationDaily item = this.fetchByCustomerIdAndDate(record.getCustomerId(), record.getTime().toLocalDate());
        if(item == null){
            item = new LoadCalculationDaily();
            item.setCustomerId(record.getCustomerId());
            item.setDate(record.getTime().toLocalDate());
            item.setLoadAmount(record.getLoadAmount());
            item.setLoadTimes(1);
            this.save(item);
        }else{
            item.setLoadAmount(item.getLoadAmount().add(record.getLoadAmount()));
            item.setLoadTimes(item.getLoadTimes() + 1);
            this.updateById(item);
        }
    }
}
