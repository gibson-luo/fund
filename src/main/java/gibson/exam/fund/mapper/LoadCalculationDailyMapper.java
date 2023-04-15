package gibson.exam.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gibson.exam.fund.pojo.LoadCalculationDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Mapper
@Repository
public interface LoadCalculationDailyMapper extends BaseMapper<LoadCalculationDaily> {

    @Select("SELECT * FROM load_calculation_daily t WHERE t.customer_id = #{customerId} and t.date = #{date} limit 1")
    @Result(column = "customer_id", property = "customerId")
    @Result(column = "load_amount", property = "loadAmount")
    @Result(column = "load_times", property = "loadTimes")
    LoadCalculationDaily fetchByCustomerIdAndDate(String customerId, LocalDate date);

    @Select({"SELECT * FROM load_calculation_daily t WHERE t.customer_id = #{customerId} and t.date in (${days})"})
    @Result(column = "customer_id", property = "customerId")
    @Result(column = "load_amount", property = "loadAmount")
    @Result(column = "load_times", property = "loadTimes")
    List<LoadCalculationDaily> listWeekByCustomerId(String customerId, @Param("days") String days);

}
