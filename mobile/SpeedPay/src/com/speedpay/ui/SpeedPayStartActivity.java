package com.speedpay.ui;

import com.speedpay.R;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

public class SpeedPayStartActivity extends Activity {

	SharedPreferences preferences;

	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_logo);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
		animation.setDuration(3000);

		ImageView img_logo = (ImageView) this.findViewById(R.id.img_logo);
		img_logo.setAnimation(animation);

		animation.setAnimationListener(new AnimationListener() {

			public void onAnimationEnd(Animation animation) {
				preferences = getSharedPreferences("count", MODE_WORLD_READABLE);
				int count = preferences.getInt("count", 0);
				if (count == 0) {
					Intent intent = new Intent();
					intent.setClass(getApplicationContext(),
							TestSpeedPayNewActivity.class);
					startActivity(intent);
					finish();
				} else {
					Intent intent = new Intent(SpeedPayStartActivity.this,
							LoginActivity.class);
					startActivity(intent);
					SpeedPayStartActivity.this.finish();
				}
				Editor editor = preferences.edit();
				
				editor.putInt("count", ++count);
				
				editor.commit();
			}

			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub

			}

			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub

			}
		});
	}
}
