package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.base.BaseJunit;
import org.springframework.beans.factory.annotation.Autowired;

public class UsedDealTypeServiceTest extends BaseJunit {

    @Autowired
    UsedDealTypeService usedDealTypeService;

    /*@Test
    public void save() {
        usedDealTypeService.saveBatch(Arrays.asList(
                new UsedDealType(DataBaseTool.createId(),DataBaseTool.createNo("type_no"),"美妆"),
                new UsedDealType(DataBaseTool.createId(),DataBaseTool.createNo("type_no"),"玩具乐器"),
                new UsedDealType(DataBaseTool.createId(),DataBaseTool.createNo("type_no"),"家具"),
                new UsedDealType(DataBaseTool.createId(),DataBaseTool.createNo("type_no"),"运动"),
                new UsedDealType(DataBaseTool.createId(),DataBaseTool.createNo("type_no"),"其它")
                ));
    }*/
}