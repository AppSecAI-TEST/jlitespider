package com.lyx.jlitespider;

import java.io.IOException;
import java.util.Map;

import com.lyx.jlitespider.core.MessageQueue;
import com.lyx.jlitespider.core.Processor;

public class DoubanProcessor implements Processor{

	@Override
	public void process(Object page, Map<String, MessageQueue> mQueue) throws IOException {
		// TODO Auto-generated method stub
		mQueue.get("two").sendResult(page);
	}

}
