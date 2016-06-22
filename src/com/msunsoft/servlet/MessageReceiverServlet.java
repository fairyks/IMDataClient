/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.servlet;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.msunsoft.client.Client;
import com.msunsoft.model.Message;
import com.msunsoft.model.Result;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataServer</p>
 * <p>创建时间 : 2016年5月19日 下午8:28:04</p>
 * <p>类描述 :       消息接收  </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */
@WebServlet("/notifyRemoteServer")
public class MessageReceiverServlet extends HttpServlet {

	private static Logger logger = Logger.getLogger(MessageReceiverServlet.class);

	/** 
	 * serialVersionUID :  
	 */
	private static final long serialVersionUID = -2030810045122539582L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		Result result = new Result();
		Gson gson = new Gson();
		ServletOutputStream out = null;
		try {
			response.setContentType("charset=utf-8");
			out = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			InputStream inputStream = request.getInputStream();
			String receivedParams = IOUtils.toString(inputStream);

			logger.info("receive notify message ---" + receivedParams);

			Message message = gson.fromJson(receivedParams, Message.class);
			Client.sendMsg(gson.toJson(message));

			result.setSuccess(true);
			result.setMsg("success");
			String json = gson.toJson(result);
			out.write(json.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getStackTrace().toString());
			result.setSuccess(false);
			String json = gson.toJson(result);
			try {
				out.write(json.getBytes());
				out.flush();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
