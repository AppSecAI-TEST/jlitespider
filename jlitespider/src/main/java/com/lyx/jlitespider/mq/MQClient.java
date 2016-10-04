package com.lyx.jlitespider.mq;

import java.io.IOException;
import java.net.InetAddress;
import java.util.concurrent.TimeoutException;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.Gson;
import com.lyx.jlitespider.core.MessageQueue;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class MQClient implements MessageQueue {
	private ConnectionFactory factory = new ConnectionFactory();
	private Connection connection;
	private Channel sendChannel;
	private Channel recvChannel;
	private QueueingConsumer consumer;
	private String queueName;
	private Gson gson = new Gson();
	public MQClient(String host, int port, String queue_name, int qos) throws IOException, TimeoutException {
		factory.setHost(host);
		factory.setPort(port);
		connection = factory.newConnection();
		sendChannel = connection.createChannel();
		recvChannel = connection.createChannel();
		sendChannel.queueDeclare(queue_name, true, false, false, null);
		recvChannel.queueDeclare(queue_name, true, false, false, null);
		queueName = queue_name;
		recvChannel.basicQos(qos);
		consumer = new QueueingConsumer(recvChannel);
		recvChannel.basicConsume(queue_name, false, consumer);
	}
	
	private void send(MQItem item) throws IOException {
		sendChannel.basicPublish("", this.queueName, 
	            MessageProperties.PERSISTENT_TEXT_PLAIN,
	            gson.toJson(item).getBytes());
	}
	
	public void sendUrl(Object url) throws IOException {
		send(new MQItem("url", url));
	}
	
	public void sendPage(Object page) throws IOException {
		send(new MQItem("page", page));
	}
	
	public void sendResult(Object result) throws IOException {
		send(new MQItem("result", result));
	}
	
	public MQItem recv() throws IOException, ShutdownSignalException, ConsumerCancelledException, InterruptedException {
		QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	    //String message = new String(delivery.getBody());
		MQItem item = gson.fromJson(new String(delivery.getBody()), MQItem.class);
	    recvChannel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
	    return item;
	}
	
	public String getAddress() {
		return this.connection.getAddress().toString() + " : " + this.connection.getPort();
	}
	
	public void close() throws IOException, TimeoutException {
		this.sendChannel.close();
		this.recvChannel.close();
		this.connection.close();
	}

}
