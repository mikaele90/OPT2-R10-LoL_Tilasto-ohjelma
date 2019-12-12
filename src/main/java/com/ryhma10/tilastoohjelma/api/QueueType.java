package com.ryhma10.tilastoohjelma.api;

public class QueueType extends ApiData {
	
	String queueType;
	
	/**
	 * Constructor
	 * @param queueType
	 */
	public QueueType(String queueType) {
		this.queueType = queueType;
	}

	/**
	 * Method to get queue type
	 * @return queueType
	 */
	public String getQueueType() {
		return queueType;
	}

	/**
	 * Method to set queue type
	 * @param queueType
	 */
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
}
