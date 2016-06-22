/**
 * Copyright 灞变笢浼楅槼杞欢鏈夐檺鍏徃 All rights reserved.
 */
package com.msunsoft.util;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;

import com.msunsoft.model.Message;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataClient</p>
 * <p>创建时间 : 2016年5月19日 下午9:11:48</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */

public class HttpMethodUtils {

	private static final Logger logger = Logger.getLogger(HttpMethodUtils.class);

	public static void postMethod(Message message) {
		try {
			String timeOut = ConfigRead.getValue("im.properties", "TIMEOUT");
			HttpClient httpClient = new HttpClient();
			HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
			// 设置连接超时时间(单位毫秒)
			managerParams.setConnectionTimeout(Integer.parseInt(timeOut));
			// 设置读数据超时时间(单位毫秒)
			managerParams.setSoTimeout(Integer.parseInt(timeOut));

			String remoteAddress = ConfigRead.getValue("im.properties", "NOTIFY_ADDRESS");
			logger.info("call remote address ------ :" + remoteAddress);
			PostMethod postMethod = new PostMethod(remoteAddress);
			//			postMethod.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
			postMethod.addParameter("msg", message.getMessage());
			httpClient.executeMethod(postMethod);
			String retVal = postMethod.getResponseBodyAsString();
			postMethod.releaseConnection();
		} catch (IOException e) {
			logger.error(e.getMessage().toString());
			e.printStackTrace();
		}
	}
}
