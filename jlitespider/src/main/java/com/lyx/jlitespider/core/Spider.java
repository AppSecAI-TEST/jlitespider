package com.lyx.jlitespider.core;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;
import com.lyx.jlitespider.mq.MQClient;
import com.lyx.jlitespider.mq.MQItem;
import com.lyx.jlitespider.setting.MqObject;
import com.lyx.jlitespider.setting.SettingObject;
import com.lyx.jlitespider.setting.SettingReader;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;


/**
 * author:Yixin Luo
 * 2016/3/3
 * 
 * 爬虫组装工厂
 * **/
public class Spider {

	/**
	 * 处理函数接口
	 */
	private Downloader downloader;
	private Processor processor;
	private Saver saver;
	private Freeman freeman;
	//配置文件位置
	private String settingFile;
	//配置文件信息
	private SettingObject settingObject;
	
	//log
	private Logger logger = Logger.getLogger("spider");
	
	public static Spider create() {
		return new Spider();
	}
	
	public Spider setDownloader(Downloader d) {
		this.downloader = d;
		return this;
	}
	
	public Spider setProcessor(Processor p) {
		this.processor = p;
		return this;
	}
	
	public Spider setSaver(Saver s) {
		this.saver = s;
		return this;
	}
	
	public Spider setFreeman(Freeman freeman) {
		this.freeman = freeman;
		return this;
	}
	
	public Spider setSettingFile(String filename) {
		this.settingFile = filename;
		return this;
	}

	/**
	 * 读取配置文件
	 * @throws FileNotFoundException 
	 */
	private void readSetting() throws FileNotFoundException {
		if (this.settingFile == null) {
			throw new FileNotFoundException();
		} else {
			this.settingObject = SettingReader.read(this.settingFile);
		}
	}
	
	/*开始下载和解析*/
	public void begin() throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		readSetting();
		MqObject mqObject = this.settingObject.getMq();
		MQClient client = new MQClient(mqObject.getHost(), mqObject.getPort(), mqObject.getQueue(), mqObject.getQos());
		logger.info("worker [" + this.settingObject.getWorkerid() + "] start...");
		while (true) {
			MQItem item = client.recv();
			switch (item.getKey()) {
			case "url":
				this.downloader.download(item.getValue(), client);
				break;
			case "page":
				this.processor.process(item.getValue(), client);
				break;
			case "result":
				this.saver.save(item.getValue(), client);
				break;
			default:
				this.freeman.doSomeThing(item.getKey(), item.getValue(), client);
				break;
			}
		}
	}
}

