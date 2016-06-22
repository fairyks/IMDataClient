/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.client;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

import com.google.gson.Gson;
import com.msunsoft.model.Message;
import com.msunsoft.util.ConfigRead;
import com.msunsoft.util.Constants;

/**
 * 
 * @author chenyanjun
 *
 */
public class Client {

	private static Logger logger = Logger.getLogger(Client.class);
	private static IoSession session;

	public static void connectServer() {
		try {

			// 创建客户端连接器.
			NioSocketConnector connector = new NioSocketConnector();
			connector.getFilterChain().addLast("logger", new LoggingFilter());
			connector.getFilterChain().addLast("codec",
					new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
			// 设置连接超时检查时间
			connector.setConnectTimeoutCheckInterval(10000);
			connector.setHandler(new ClientMessageHandler());

			// 建立连接
			ConnectFuture future = connector
					.connect(new InetSocketAddress(ConfigRead.getValue("im.properties", "IMSERVER_IP"),
							Integer.parseInt(ConfigRead.getValue("im.properties", "IMSERVER_PORT"))));
			// 等待连接创建完成
			future.awaitUninterruptibly();

			session = future.getSession();
			logger.info("connectServer successfully,the session info is :" + session.toString());

			Gson gson = new Gson();
			Message msg = new Message();
			msg.setFrom(ConfigRead.getValue("im.properties", "IDENTIFIER"));
			msg.setType(Constants.MessageType);
			System.out.println(gson.toJson(msg));
			sendMsg(gson.toJson(msg));

		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
	}

	public static void sendMsg(String message) {
		try {
			session.write(message);
		} catch (Exception e) {
			logger.error(e.getCause().toString());
		}
	}
}