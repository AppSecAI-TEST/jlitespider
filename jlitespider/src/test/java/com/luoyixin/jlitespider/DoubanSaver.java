package com.luoyixin.jlitespider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.luoyixin.jlitespider.core.MessageQueue;
import com.luoyixin.jlitespider.core.Saver;
import com.luoyixin.jlitespider.mq.MQItem;

/**
 * author : Yixin Luo
 * 2016/3/5
 * 
 * 对保存接口的实现，将结果打印出来
 * 
 * **/
public class DoubanSaver implements Saver {

	@Override
	public void save(Object value, Map<String, MessageQueue> messageQueue) throws IOException{
		// TODO Auto-generated method stub
		messageQueue.get("two").send("hello world", value);
	}


}
