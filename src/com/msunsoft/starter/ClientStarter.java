/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.starter;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.gson.Gson;
import com.msunsoft.client.Client;
import com.msunsoft.im.ConnectServer;
import com.msunsoft.model.Message;
import com.msunsoft.servlet.MessageReceiverServlet;
import com.msunsoft.util.ConfigRead;
import com.msunsoft.util.Constants;

import org.apache.log4j.Logger;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataClient</p>
 * <p>创建时间 : 2016年5月19日 下午5:18:32</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */

public class ClientStarter {

	private static Logger logger = Logger.getLogger(ClientStarter.class);

	/**
	 * 方法描述 : 启动服务
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			logger.info("ClientStarter starting");
			int host = Integer.parseInt(ConfigRead.getValue("im.properties", "WEB_PORT"));

			ResourceHandler resourceHandler = new ResourceHandler();
			resourceHandler.setDirectoriesListed(true);
			resourceHandler.setResourceBase("data");
			resourceHandler.setStylesheet("");
			ContextHandler staticContextHandler = new ContextHandler();
			staticContextHandler.setContextPath("/data");
			//staticContextHandler.setContextPath("/files");
			staticContextHandler.setHandler(resourceHandler);

			//添加servlet支持
			ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
			servletContextHandler.setContextPath("/imDataClient");

			/**可在下面依次添加多个servlet**/
			servletContextHandler.addServlet(new ServletHolder(new MessageReceiverServlet()), "/notifyRemoteServer");

			HandlerList handlers = new HandlerList();
			handlers.addHandler(servletContextHandler);
			handlers.addHandler(staticContextHandler);

			Thread connectServerThread = new Thread(new ConnectServer());
			connectServerThread.start();

			Server server = new Server(host);
			server.setHandler(handlers);

			server.start();
			server.join();
			logger.info("ClientStarter has started");
		} catch (Exception e) {
			logger.error(e.getCause().toString());
			e.printStackTrace();
		}
	}

}
