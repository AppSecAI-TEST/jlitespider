package com.lyx.jlitespider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.lyx.jlitespider.core.Spider;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class SpiderTest {
	public static void main(String[] args) {
		try {
			Spider.create().setDownloader(new DefaultDownloader()).setProcessor(new DoubanProcessor())
					.setSaver(new PrintSaver()).setSettingFile("./conf/setting.json").begin();
		} catch (ShutdownSignalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ConsumerCancelledException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
