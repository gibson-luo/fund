package gibson.exam.fund.controller;

import gibson.exam.fund.model.RequestLoadFundRecord;
import gibson.exam.fund.pojo.LoadFundRecord;
import gibson.exam.fund.rules.CustomerData;
import gibson.exam.fund.rules.RuleEngine;
import gibson.exam.fund.service.LoadCalculationDailyService;
import gibson.exam.fund.service.LoadFundRecordService;
import gibson.exam.fund.tools.JsonUtil;
import gibson.exam.fund.tools.RecordReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/fund")
public class FundController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FundController.class);

    @Autowired
    private LoadFundRecordService loadFundRecordService;

    @Autowired
    private LoadCalculationDailyService loadCalculationDailyService;

    @PostMapping(value = "importData")
    @ResponseBody
    public ResponseEntity importData(@RequestParam("file") MultipartFile file, HttpServletResponse response) {
        try {
            List<LoadFundRecord> dataSet = RecordReader.loadObjectList(RequestLoadFundRecord.class, file).stream()
                    .map(item -> item.toLoadFundRecord()).collect(Collectors.toList());
            LOGGER.debug("dataSet: {}", dataSet.size());

            for(LoadFundRecord item : dataSet){
                // if a load ID is observed more than once for a particular user, all but the first instance can be ignored (i.e. no response given).
                if(loadFundRecordService.isExist(item.getId(), item.getCustomerId())){
                    LOGGER.debug("existing ID: " + item.getId());
                    continue;
                }
                // 1. load customer data
                CustomerData customerData = loadCalculationDailyService.calculateCustomerData(item);
                // 2. check rules
                boolean accepted = RuleEngine.checkRules(item, customerData);
                if(accepted){
                    // 3. update customer data (daily table)
                    loadCalculationDailyService.updateDailyData(item);
                    // 4. insert loadFundRecord table for logging
                    item.setAccepted(true);
                }
                loadFundRecordService.save(item);
                // output
                response.getOutputStream().write(JsonUtil.encode(item).getBytes(StandardCharsets.UTF_8));
                response.getOutputStream().write("\n".getBytes(StandardCharsets.UTF_8));
            }
            response.getOutputStream().flush();
        }catch (Exception e){
            LOGGER.error("fail {}", e);
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
