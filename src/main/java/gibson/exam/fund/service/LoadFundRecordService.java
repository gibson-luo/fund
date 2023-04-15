package gibson.exam.fund.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import gibson.exam.fund.mapper.LoadFundRecordMapper;
import gibson.exam.fund.pojo.LoadFundRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoadFundRecordService extends ServiceImpl<LoadFundRecordMapper, LoadFundRecord> {

    @Autowired
    private LoadFundRecordMapper loadFundRecordMapper;

    public boolean isExist(Long id, String customerId) {
        return loadFundRecordMapper.isExist(id, customerId);
    }


}
