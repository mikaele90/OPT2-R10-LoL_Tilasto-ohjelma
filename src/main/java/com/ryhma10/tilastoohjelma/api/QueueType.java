package com.ryhma10.tilastoohjelma.api;

public class QueueType extends ApiData {
	
	String queueType;
	
	public QueueType(String queueType) {
		this.queueType = queueType;
	}

	public String getQueueType() {
		return queueType;
	}

	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
}
