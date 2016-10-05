package com.lyx.jlitespider;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.lyx.jlitespider.core.Spider;
import com.lyx.jlitespider.exception.SpiderSettingFileException;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

public class DoubanSpider {
	public static void main(String[] args) {
		try {
			Spider.create().setDownloader(new DoubanDownloader())
			               .setProcessor(new DoubanProcessor())
					       .setSaver(new DoubanSaver())
					       .setFreeman(new DoubanPrinter())
					       .setSettingFile("./conf/setting.json")
					       .begin();
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
		} catch (SpiderSettingFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
