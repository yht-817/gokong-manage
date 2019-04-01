package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.base.BaseJunit;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LocalActivityApplyServiceTest extends BaseJunit {

    @Autowired
    LocalActivityApplyService localActivityApplyService;

    @Test
    public void deleteActivityApply() {
        //拒绝后删除申请信息
        localActivityApplyService.deleteActivityApply("apply_no154228453423980644");
    }
}