package com.luoyixin.jlitespider.core;

import java.io.IOException;
import java.util.Map;

/**
 * 自定义的，自由的处理接口
 * @author luoyixin
 *
 */
public interface Freeman {
	/**
	 * 自定义的处理函数，key为自定义的消息标记
	 * @param key
	 * @param msg
	 * @param mQueue
	 * @throws IOException
	 */
	public void doSomeThing(String key, Object msg, Map<String, MessageQueue> mQueue) throws IOException;
}
