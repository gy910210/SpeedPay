package com.speedpay.ui;

import java.util.HashMap;
import java.util.Map;

import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.logic.Task;
import com.speedpay.logic.BaseActivity;
import com.speedpay.logic.MainService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.Session;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity implements BaseActivity {

	private Button buttonnLogin;
	private EditText input_account;
	private EditText input_password;
	Dialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		Intent intent = new Intent(this, MainService.class);
		startService(intent);
		
		 Typeface face = Typeface.createFromAsset (getAssets() ,"fonts/huakangshaonv.ttf");
	     Typeface face1 = Typeface.createFromAsset (getAssets() ,"fonts/pop.ttf");
	        
		input_account=(EditText) findViewById(R.id.input_account);
		input_password=(EditText) findViewById(R.id.input_password);
		
		TextView login_user_input=(TextView) findViewById(R.id.login_user_input);
		TextView login_password_input=(TextView) findViewById(R.id.login_password_input);
		login_user_input.setTypeface(face);
		login_password_input.setTypeface(face);
		
		buttonnLogin = (Button) this.findViewById(R.id.btn_login);
		buttonnLogin.setTypeface(face1);
		TextPaint tp=buttonnLogin.getPaint();
		tp.setFakeBoldText(true);
		
		buttonnLogin.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				
				if(input_account.getText().toString().equals(""))
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请输入账号!",
						     Toast.LENGTH_LONG);
					//toast.setGravity(Gravity.CENTER, 0, 0);
					LinearLayout toastView = (LinearLayout) toast.getView();
					ImageView imageCodeProject = new ImageView(getApplicationContext());
					imageCodeProject.setImageResource(R.drawable.toast);
					//toastView.setAlpha(50);
					//toastView.setOrientation(LinearLayout.HORIZONTAL);
					toastView.addView(imageCodeProject, 0);
					
					toast.show();
					
					
					return;
				}
				if(input_password.getText().toString().equals(""))
				{
					Toast toast=Toast.makeText(getApplicationContext(), "请输入密码!",
						     Toast.LENGTH_LONG);
					LinearLayout toastView = (LinearLayout) toast.getView();
					//toast.setGravity(Gravity.CENTER, 0, 0);
					ImageView imageCodeProject = new ImageView(getApplicationContext());
					imageCodeProject.setImageResource(R.drawable.toast);
					toastView.addView(imageCodeProject, 0);
					toast.show();
					return;
				}
				//新建任务，任务Id和任务参数
				Map<String, Object> map=new HashMap<String,Object>();
				//User user=new User(input_account.getText().toString(), input_password.getText().toString());
				
				System.out.println("------->"+input_account.getText().toString()+"------->"+input_password.getText().toString());
				
				map.put("account", input_account.getText().toString());
				map.put("password", input_password.getText().toString());
				
				Task task = new Task(Task.SPEEDPAY_LOGIN, map);
				MainService.newTask(task);
				ProgressDialog d=new ProgressDialog();
				dialog=d.showRoundProcessDialog(LoginActivity.this);
			}
		});

		MainService.addActivity(this);
		
	}

	public void init() {

	}

	public void refresh(Object... params) {
		
		User user=(User) Session.getSession().get("user");
		dialog.dismiss();
		String str=(String) params[0];
		if(str.equals("您的身份验证成功!"))
		{
			Toast.makeText(getApplicationContext(), str,
				     Toast.LENGTH_LONG).show();
			Intent intent=null;
			if(user.getUser_type()!=1)
			{
				intent = new Intent(this, MainActivity.class);
				
			}
			else
			{
				intent=new Intent(this, MarketMakeErcodeActivity.class);
			}
			startActivity(intent);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			LoginActivity.this.finish();
		}
		else
		{
			Toast.makeText(getApplicationContext(), str,
				     Toast.LENGTH_LONG).show();
			return;
		}
	}

}
