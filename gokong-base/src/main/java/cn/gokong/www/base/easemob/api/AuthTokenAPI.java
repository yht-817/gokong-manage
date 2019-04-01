package cn.gokong.www.base.easemob.api;

import io.swagger.client.ApiException;

public interface AuthTokenAPI{
	/**
	 * Request an Authentication Token
	 * @return String
	 * @throws ApiException If fail to call the API, e.g. dao error or cannot deserialize the response body
	 */
	Object getAuthToken();
}
