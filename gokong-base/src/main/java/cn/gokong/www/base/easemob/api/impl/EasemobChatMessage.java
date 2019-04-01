package cn.gokong.www.base.easemob.api.impl;

import cn.gokong.www.base.easemob.api.ChatMessageAPI;
import cn.gokong.www.base.easemob.comm.EasemobAPI;
import cn.gokong.www.base.easemob.comm.OrgInfo;
import cn.gokong.www.base.easemob.comm.ResponseHandler;
import cn.gokong.www.base.easemob.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChatHistoryApi;


public class EasemobChatMessage  implements ChatMessageAPI {

    private ResponseHandler responseHandler = new ResponseHandler();
    private ChatHistoryApi api = new ChatHistoryApi();

    @Override
    public Object exportChatMessages(final Long limit,final String cursor,final String query) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatmessagesGet(OrgInfo.ORG_NAME, OrgInfo.APP_NAME, TokenUtil.getAccessToken(),query,limit+"",cursor);
            }
        });
    }
}
