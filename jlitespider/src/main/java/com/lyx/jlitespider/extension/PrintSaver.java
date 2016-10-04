package com.lyx.jlitespider.extension;

import java.io.IOException;
import java.util.List;

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
	public void save(Object value, MessageQueue messageQueue) throws IOException{
		// TODO Auto-generated method stub
		System.out.println(value);
	}


}
