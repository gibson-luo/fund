package gibson.exam.fund.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import gibson.exam.fund.pojo.Rule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RuleMapper extends BaseMapper<Rule> {

    @Select("SELECT * FROM rule")
    List<Rule> loadAll();

}
