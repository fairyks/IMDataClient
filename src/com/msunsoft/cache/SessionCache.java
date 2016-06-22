/**
 * Copyright 山东众阳软件有限公司 All rights reserved.
 */
package com.msunsoft.cache;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

/**
 * 
 * @author chenyanjun
 *
 */
public class SessionCache {

	public static Map<String, IoSession> map = new HashMap<>();
}
