package cn.gokong.www.base.easemob.entity;

/**
 * 说明:请求环信entities响应结果
 *
 * @author Mick
 * CreateDate 2018/5/30 16:26
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class Entities {
    private String uuid;
    private String type;
    private int created;
    private int modified;
    private String username;
    private boolean activated;
    private String device_token;
    private String nickname;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public int getModified() {
        return modified;
    }

    public void setModified(int modified) {
        this.modified = modified;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getDevice_token() {
        return device_token;
    }

    public void setDevice_token(String device_token) {
        this.device_token = device_token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Entities() {
    }

    public Entities(String uuid, String type, int created, int modified, String username, boolean activated) {
        this.uuid = uuid;
        this.type = type;
        this.created = created;
        this.modified = modified;
        this.username = username;
        this.activated = activated;
    }

    public Entities(String uuid, String type, int created, int modified, String username, boolean activated, String device_token, String nickname) {
        this.uuid = uuid;
        this.type = type;
        this.created = created;
        this.modified = modified;
        this.username = username;
        this.activated = activated;
        this.device_token = device_token;
        this.nickname = nickname;
    }
}
