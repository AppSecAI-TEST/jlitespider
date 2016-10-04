package com.lyx.jlitespider.setting;
/**
 * 与配置文件中读取出来的信息，一一对应
 * @author luoyixin
 *
 */
public class SettingObject {
	private int workerid;
	private MqObject mq;
	
	
	public int getWorkerid() {
		return workerid;
	}
	public void setWorkerid(int workerid) {
		this.workerid = workerid;
	}
	public MqObject getMq() {
		return mq;
	}
	public void setMq(MqObject mq) {
		this.mq = mq;
	}
	
}
