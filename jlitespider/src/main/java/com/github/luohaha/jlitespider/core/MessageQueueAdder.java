package com.github.luohaha.jlitespider.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import java.util.logging.Logger;

import com.github.luohaha.jlitespider.mq.MQItem;
import com.github.luohaha.jlitespider.mq.MQSender;
import com.github.luohaha.jlitespider.setting.MqObject;
import com.github.luohaha.jlitespider.setting.SettingObject;
import com.github.luohaha.jlitespider.setting.SettingReader;
import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class MessageQueueAdder {
	private ConnectionFactory factory = new ConnectionFactory();
	private Connection connection;
	private Channel sendChannel;
	private String queueName;
	private Gson gson = new Gson();
	private Logger logger = Logger.getLogger("adder");

	public MessageQueueAdder(String host, int port, String queue_name) throws IOException, TimeoutException {
		super();
		factory.setHost(host);
		factory.setPort(port);
		connection = factory.newConnection();
		sendChannel = connection.createChannel();
		sendChannel.queueDeclare(queue_name, true, false, false, null);
		this.queueName = queue_name;
	}

	public static MessageQueueAdder create(String host, int port, String queue) throws TimeoutException, IOException {
		return new MessageQueueAdder(host, port, queue);
	}
	
	public void close() throws IOException, TimeoutException {
		this.sendChannel.close();
		this.connection.close();
	}
	
	public MessageQueueAdder add(String key, Object msg) throws IOException, TimeoutException {
		sendChannel.basicPublish("", this.queueName, MessageProperties.PERSISTENT_TEXT_PLAIN,
				gson.toJson(new MQItem(key, msg)).getBytes());
		logger.info("add finish!");
		return this;
	}

	public MessageQueueAdder addUrl(Object url) throws IOException, TimeoutException {
		return add("url", url);
	}

	public MessageQueueAdder addPage(Object page) throws IOException, TimeoutException {
		return add("page", page);
	}
	
	public MessageQueueAdder addResult(Object result) throws IOException, TimeoutException {
		return add("result", result);
	}
}
