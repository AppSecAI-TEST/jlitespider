package com.lyx.jlitespider.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.google.gson.Gson;
import com.lyx.jlitespider.mq.MQItem;
import com.lyx.jlitespider.setting.MqObject;
import com.lyx.jlitespider.setting.SettingObject;
import com.lyx.jlitespider.setting.SettingReader;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class SpiderUrlSender {
	private ConnectionFactory factory = new ConnectionFactory();
	private Connection connection;
	private Channel sendChannel;
	private String queueName;
	private Gson gson = new Gson();
	public SpiderUrlSender(String host, int port, String queue_name) throws IOException, TimeoutException {
		super();
		factory.setHost(host);
		factory.setPort(port);
		connection = factory.newConnection();
		sendChannel = connection.createChannel();
		sendChannel.queueDeclare(queue_name, true, false, false, null);
		this.queueName = queue_name;
	}
	
	public static SpiderUrlSender create(String filename) throws IOException, TimeoutException {
		SettingObject object = SettingReader.read(filename);
		MqObject mqObject = object.getMq();
		return new SpiderUrlSender(mqObject.getHost(), mqObject.getPort(), mqObject.getQueue());
	}
	
	public void send(List<String> urls) throws IOException, TimeoutException {
		for (String each : urls) {
			sendChannel.basicPublish("", this.queueName, 
		            MessageProperties.PERSISTENT_TEXT_PLAIN,
		            gson.toJson(new MQItem("url", each)).getBytes());
		}
		this.sendChannel.close();
		this.connection.close();
	}
	

}
