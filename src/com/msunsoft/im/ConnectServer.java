/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.im;

import org.apache.log4j.Logger;

import com.msunsoft.client.Client;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataClient</p>
 * <p>创建时间 : 2016年5月19日 下午5:39:57</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */

public class ConnectServer implements Runnable {

	private static Logger logger = Logger.getLogger(ConnectServer.class);

	/**
	 * <h4>  </h4>
	 * @see java.lang.Runnable#run()
	 * @throws 
	 */
	@Override
	public void run() {
		try {
			Client.connectServer();
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
	}
}
