package gibson.exam.fund.controller;

import gibson.exam.fund.rules.RuleEngine;
import gibson.exam.fund.service.RuleService;
import gibson.exam.fund.tools.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
@RequestMapping("/v1/rule")
public class RuleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RuleController.class);

    @Autowired
    private RuleService ruleService;

    @GetMapping(value = "load")
    @ResponseBody
    public ResponseEntity load(HttpServletResponse response){
        try {
            Map<String, String> rules = RuleEngine.loadRules();
            response.getOutputStream().write(JsonUtil.encode(rules).getBytes(StandardCharsets.UTF_8));
            response.getOutputStream().flush();
        }catch (Exception e){
            LOGGER.error("fail {}", e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
