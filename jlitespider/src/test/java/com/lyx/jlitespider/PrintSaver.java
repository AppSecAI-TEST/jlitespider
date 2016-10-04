package com.lyx.jlitespider;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lyx.jlitespider.core.MessageQueue;
import com.lyx.jlitespider.core.Saver;
import com.lyx.jlitespider.mq.MQItem;

/**
 * author : Yixin Luo
 * 2016/3/5
 * 
 * 对保存接口的实现，将结果打印出来
 * 
 * **/
public class PrintSaver implements Saver {

	@Override
	public void save(Object value, Map<String, MessageQueue> messageQueue) throws IOException{
		// TODO Auto-generated method stub
		List<String> rsList = (List<String>) value;
		System.out.println("size : " + rsList.get(1));
	}


}
