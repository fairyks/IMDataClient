/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.model;

/**
 * <p>Copyright: Copyright (c) 2011</p>
 * <p>公司名称 :山东众阳软件有限公司 </p>
 * <p>项目名称 : IMDataClient</p>
 * <p>创建时间 : 2016年5月19日 下午8:33:36</p>
 * <p>类描述 :         </p>
 *
 *
 * @version 1.0.0
 * @author <a href=" ">chenyanjun</a>
 */

public class Result {

	private boolean success = false;
	private String msg = "";
	private Object obj = null;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

}
