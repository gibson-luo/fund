package gibson.exam.fund.rules;

import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.pojo.Rule;
import gibson.exam.fund.rules.strategies.Strategy;
import gibson.exam.fund.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RuleEngine {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleEngine.class);

    private static Map<String, Strategy> ruleMap;

    @Resource
    public void setRuleMap(Map<String, Strategy> ruleMap) {
        RuleEngine.ruleMap = ruleMap;
    }

    private static RuleService ruleService;

    @Autowired
    public void setRuleService(RuleService ruleService) {
        RuleEngine.ruleService = ruleService;
    }

    private static Map<String, String> rules = new HashMap<>();

    public static Map<String, String> loadRules() {
        if(!rules.isEmpty()){
            rules.clear();
        }
        rules = ruleService.loadAll().stream().collect(Collectors.toMap(Rule::getKey, Rule::getValue));
        LOGGER.debug("Load All Rules: {}", rules);
        LOGGER.debug("rule map: {}", ruleMap);
        return rules;
    }

    public static boolean checkRules(LoadFundRecord record, CustomerData customerData) {
        for(Map.Entry<String, String> entry : rules.entrySet()){
            if (!ruleMap.containsKey(entry.getKey())) {
                throw new IllegalArgumentException("ruleType not found");
            }
            Strategy rule = ruleMap.get(entry.getKey());
            if(!rule.check(record, entry.getValue(), customerData)){
                return false;
            }
        }
        return true;
    }
}
