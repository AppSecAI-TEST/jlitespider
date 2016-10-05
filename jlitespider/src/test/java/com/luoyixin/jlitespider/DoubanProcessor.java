package com.luoyixin.jlitespider;

import java.io.IOException;
import java.util.Map;

import com.luoyixin.jlitespider.core.MessageQueue;
import com.luoyixin.jlitespider.core.Processor;

public class DoubanProcessor implements Processor{

	@Override
	public void process(Object page, Map<String, MessageQueue> mQueue) throws IOException {
		// TODO Auto-generated method stub
		mQueue.get("two").sendResult(page);
	}

}
