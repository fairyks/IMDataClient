/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 * 山东众阳软件有限公司 专有/保密源代码,未经许可禁止任何人通过任何* 渠道使用、修改源代码.
 */
package com.msunsoft.client;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.msunsoft.worker.NotifyRemoteServerWorker;

/**
 * 
 * @author chenyanjun
 *
 */
public class ClientMessageHandler extends IoHandlerAdapter {

	private static Logger logger = Logger.getLogger(ClientMessageHandler.class);
	private static ExecutorService servicePool = Executors.newCachedThreadPool();

	@Override
	public void messageReceived(IoSession session, Object message) {
		try {
			String content = message.toString();
			logger.info("received message is : " + content);
			servicePool.execute(new NotifyRemoteServerWorker(content));
		} catch (Exception e) {
			logger.error(e.getMessage().toString());
		}
	}

	//	@Override
	//	public void messageSent(IoSession session, Object message) throws Exception {
	//		System.out.println("3--messageSent -> ：" + message);
	//	}

}