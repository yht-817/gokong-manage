package cn.gokong.www.base.easemob.api.impl;

import cn.gokong.www.base.easemob.api.SendMessageAPI;
import cn.gokong.www.base.easemob.comm.EasemobAPI;
import cn.gokong.www.base.easemob.comm.OrgInfo;
import cn.gokong.www.base.easemob.comm.ResponseHandler;
import cn.gokong.www.base.easemob.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandler responseHandler = new ResponseHandler();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameMessagesPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
