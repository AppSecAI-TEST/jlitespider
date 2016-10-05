package com.luoyixin.jlitespider.core;

import java.io.IOException;
import java.util.Map;


/**
 * author : Yixin Luo
 * 2016/3/4
 * 
 * 数据持久化的接口
 * **/
public interface Saver {
	/**
	 * 将传入此函数的result进行持久化操作。
	 * **/
	public void save(Object result, Map<String, MessageQueue> mQueue) throws IOException;
}
