package com.speedpay.logic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import com.speedpay.logic.Task;
import com.speedpay.logic.BaseActivity;
import com.speedpay.services.LoginService;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

@SuppressLint("HandlerLeak")
public class MainService extends Service implements Runnable {

	private static Queue<Task> tasks = new LinkedList<Task>();
	private static ArrayList<Activity> appActivities = new ArrayList<Activity>();

	private boolean isRun;

	@SuppressLint("HandlerLeak")
	Handler handler = new Handler() {

		public void handleMessage(Message msg) {
			//System.out.println(msg.obj);
			switch (msg.what) {
			case Task.SPEEDPAY_LOGIN:

				BaseActivity loginActivity = (BaseActivity) getActivityByName("LoginActivity");
				loginActivity.refresh(msg.obj);
				break;
			default:
				break;
			}
		};
	};

	public static void addActivity(Activity activity) {
		appActivities.add(activity);
	}

	public static void newTask(Task t) {
		tasks.add(t);
	}

	private Activity getActivityByName(String name) {

		if (!appActivities.isEmpty()) {
			for (Activity activity : appActivities) {
				if (null != activity) {
					if (activity.getClass().getName().indexOf(name) > 0) {
						return activity;
					}
				}
			}
		}

		return null;

	}

	@Override
	public void onCreate() {

		isRun = true;
		Thread thread = new Thread(this);
		thread.start();

		super.onCreate();
	}

	public void run() {

		while (isRun) {
			Task task = null;
			if (!tasks.isEmpty()) {
				task = tasks.poll();
				if (null != task) {
					doTask(task);
				}
			}
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
			}

		}

	}

	private void doTask(Task t) {
		Message msg = handler.obtainMessage();
		msg.what = t.getTaskId();

		switch (t.getTaskId()) {
		case Task.SPEEDPAY_LOGIN:
			System.out.println("doTask >>>>>LoginService");
			LoginService ls=new LoginService();
			String account=(String) t.getTaskParams().get("account");
			String password=(String) t.getTaskParams().get("password");
			System.out.println("doTask--->"+ls.login(account,password));
			msg.obj = ls.login(account,password);
			break;
			
		default:
			break;
		}

		handler.sendMessage(msg);
	}

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

}
