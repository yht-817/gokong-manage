package cn.gokong.www.base.easemob.entity;

import java.util.List;

/**
 * 说明:
 *
 * @author Mick
 * CreateDate 2018/5/30 16:56
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class ListEntities {
    private List<Entities> entitiesList;

    public ListEntities(List<Entities> entitiesList) {
        this.entitiesList = entitiesList;
    }

    public List<Entities> getEntitiesList() {
        return entitiesList;
    }

    public void setEntitiesList(List<Entities> entitiesList) {
        this.entitiesList = entitiesList;
    }
}
