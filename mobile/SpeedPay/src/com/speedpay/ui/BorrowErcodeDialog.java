package com.speedpay.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

public class BorrowErcodeDialog extends AlertDialog {

	SensorManager sensorManager = null;
	Sensor lightSensor = null;

	int pre = 640;
	private boolean flag = false;
	SharedPreferences sharedPreferenceds;
	SensorEventListener lsn;

	protected BorrowErcodeDialog(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		sensorManager = (SensorManager) getContext().getSystemService(
				Context.SENSOR_SERVICE);
		lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

		lsn = new SensorEventListener() {

			@Override
			public void onSensorChanged(SensorEvent event) {
				// TODO Auto-generated method stub
				if (event.sensor.getType() == Sensor.TYPE_LIGHT) {

					float[] values = event.values;
					Log.v("light",
							String.valueOf(values[0])
									+ String.valueOf(values[1])
									+ String.valueOf(values[2]));
					System.out.println(String.valueOf(values[0])
							+ String.valueOf(values[1])
							+ String.valueOf(values[2]));
					if (Math.abs((values[0] - 640)) > 0) {
						if (flag) {
							Toast.makeText(getContext(), "转账二维码发送成功！",
									Toast.LENGTH_LONG).show();
							Intent intent = new Intent(getContext(),
									BorrowActivity.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							getContext().startActivity(intent);
						}
						flag = !flag;
					}
				}
			}

			@Override
			public void onAccuracyChanged(Sensor sensor, int accuracy) {
				// TODO Auto-generated method stub

			}
		};

		sharedPreferenceds = getContext().getSharedPreferences("light",
				Context.MODE_PRIVATE);
		int count = sharedPreferenceds.getInt("light", 0);

		if (count == 1)
			sensorManager.registerListener(lsn, lightSensor,
					SensorManager.SENSOR_DELAY_NORMAL);
	}
}
