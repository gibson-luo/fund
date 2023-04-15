package gibson.exam.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gibson.exam.fund.pojo.LoadFundRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface LoadFundRecordMapper extends BaseMapper<LoadFundRecord> {

    @Select("SELECT count(id) FROM load_fund_record WHERE id = #{id} and customer_id = #{customerId} limit 1")
    boolean isExist(Long id, String customerId);

}
