package cn.gokong.www.base.easemob.api.impl;


import cn.gokong.www.base.easemob.api.AuthTokenAPI;
import cn.gokong.www.base.easemob.comm.TokenUtil;

public class EasemobAuthToken implements AuthTokenAPI {

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
