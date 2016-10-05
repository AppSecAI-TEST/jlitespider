package com.luoyixin.jlitespider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

import com.luoyixin.jlitespider.core.MessageQueueAdder;


public class AddUrls {
	public static void main(String[] args) {
		List<String> urList = new ArrayList<>();
		urList.add("https://movie.douban.com/tag/爱情");
		urList.add("https://movie.douban.com");
		urList.add("https://movie.douban.com/tag/爱情?start=40&type=T");
		try {
			MessageQueueAdder.create("localhost", 5672, "url").add(urList);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
}
