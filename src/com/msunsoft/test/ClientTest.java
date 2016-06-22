/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 * 山东众阳软件有限公司 专有/保密源代码,未经许可禁止任何人通过任何* 渠道使用、修改源代码.
 */
package com.msunsoft.test;

import com.google.gson.Gson;
import com.msunsoft.client.Client;
import com.msunsoft.model.Message;

/**
 * 
 * <p>项目名称 : MessageClient</p>
 * <p>创建时间 : 2014年12月7日 下午1:13:31</p>
 * <p>类描述 :    运行客户端程序     </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */
public class ClientTest {

	public static void main(String[] args) throws InterruptedException {
		//		Client client = new Client();
		Gson gson = new Gson();
		Message message = new Message();
		message.setFrom("china");
		message.setTo("192.168.1.110");
		//		message.setTo("192.168.1.105");
		//		message.setMessage("hello");
		Client.connectServer();
		//		client.sendMsg(gson.toJson(message));
		for (int i = 0; i < 2; i++) {
			Thread.sleep(3000);
			message.setMessage("我来了" + i);
			Client.sendMsg(gson.toJson(message));
		}
	}

}