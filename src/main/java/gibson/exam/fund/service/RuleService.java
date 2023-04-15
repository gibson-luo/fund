package gibson.exam.fund.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gibson.exam.fund.mapper.RuleMapper;
import gibson.exam.fund.pojo.Rule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService extends ServiceImpl<RuleMapper, Rule> {

    @Autowired
    private RuleMapper ruleMapper;

    public List<Rule> loadAll() {
        return ruleMapper.loadAll();
    }
}
