package com.luoyixin.jlitespider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.luoyixin.jlitespider.core.Downloader;
import com.luoyixin.jlitespider.core.MessageQueue;
import com.luoyixin.jlitespider.extension.Network;
import com.luoyixin.jlitespider.mq.MQItem;

/**
 * author : Yixin Luo
 * 2016/3/3
 * 
 * 下载器，其中download函数应当返回获取到的html页面字符串的链表
 * 
 * **/
public class DoubanDownloader implements Downloader {
	/*user agent*/
	private String agent = null;
	/*设置cookie*/
	private String cookie = null;
	/*传输超时*/
	private int timeout = 1000;
	/*设置代理*/
	private String proxy = null;
	
	/**
	 * 设置下载传输参数
	 * **/
	public DoubanDownloader setUserAgent(String s) {
		this.agent = s;
		return this;
	}
	public DoubanDownloader setCookie(String c) {
		this.cookie = c;
		return this;
	}
	public DoubanDownloader setTimeout(int t) {
		this.timeout = t;
		return this;
	}
	public DoubanDownloader setProxy(String p) {
		this.proxy = p;
		return this;
	}
	/**
	 * 使用UrlList对象中的url，开始下载
	 * @throws IOException 
	 * **/
	@Override
	public void download(Object url, Map<String, MessageQueue> messageQueue) throws IOException {
		// TODO Auto-generated method stub
		Network nw = Network.create();
		if (this.agent != null)
			nw.setUserAgent(this.agent);
		if (this.cookie != null)
			nw.setCookie(this.cookie);
		if (this.proxy != null)
			nw.setProxy(this.proxy);
		List<String> urList = (List<String>) url;
		List<String> pages = new ArrayList<>();
		for (String string : urList) {
			pages.add(nw.downloader(string));
		}
		messageQueue.get("two").sendPage(pages);
	}
}
