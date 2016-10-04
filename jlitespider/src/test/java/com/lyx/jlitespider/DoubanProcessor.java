package com.lyx.jlitespider;

import java.io.IOException;

import com.lyx.jlitespider.core.MessageQueue;
import com.lyx.jlitespider.core.Processor;

public class DoubanProcessor implements Processor{

	@Override
	public void process(Object page, MessageQueue mQueue) throws IOException {
		// TODO Auto-generated method stub
		mQueue.sendResult(page.toString());
	}

}
