package com.speedpay.logic;

import java.util.Map;

public class Task {

	private int taskId;

	private Map<String, Object> taskParams;

	public static final int SPEEDPAY_LOGIN = 1;

	public Task(int taskId, Map<String, Object> taskParams) {
		this.taskId = taskId;
		this.taskParams = taskParams;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public Map<String, Object> getTaskParams() {
		return taskParams;
	}

	public void setTaskParams(Map<String, Object> taskParams) {
		this.taskParams = taskParams;
	}

}
