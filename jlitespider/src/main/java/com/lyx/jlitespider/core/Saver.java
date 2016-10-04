package com.lyx.jlitespider.core;

import java.io.IOException;
import java.util.List;

import com.lyx.jlitespider.mq.MQItem;

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
	public void save(Object result, MessageQueue mQueue) throws IOException;
}
