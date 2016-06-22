/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.worker;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.msunsoft.model.Message;
import com.msunsoft.util.HttpMethodUtils;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataClient</p>
 * <p>创建时间 : 2016年5月19日 下午9:03:40</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */

public class NotifyRemoteServerWorker implements Runnable {

	private static Logger logger = Logger.getLogger(NotifyRemoteServerWorker.class);

	private String message;

	public NotifyRemoteServerWorker(String message) {
		this.message = message;
	}

	/**
	 * <h4>  </h4>
	 * @see java.lang.Runnable#run()
	 * @throws 
	 */
	@Override
	public void run() {
		try {
			Gson gson = new Gson();
			Message bean = gson.fromJson(message, Message.class);
			logger.info(bean.getFrom() + "---" + bean.getMessage() + "---" + bean.getTo());
			HttpMethodUtils.postMethod(bean);
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
	}

}
