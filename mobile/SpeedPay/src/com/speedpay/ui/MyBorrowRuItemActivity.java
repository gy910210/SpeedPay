package com.speedpay.ui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.speedpay.R;
import com.speedpay.bean.BorrowProof;
import com.speedpay.bean.User;
import com.speedpay.services.InsertRenewalApplyService;
import com.speedpay.services.InsertRepayProofService;
import com.speedpay.utils.Session;

public class MyBorrowRuItemActivity extends Activity {
	/** Called when the activity is first created. */

	User user;
	Button button_myBorrowItem_repay;
	Button button_myBorrowItem_renew;
	String renewTime;
	BorrowProof borrowProof;
	TextView text_myBorrowRepayTime;
	SharedPreferences sharedPreferenceds;

	private Calendar cal = Calendar.getInstance();
	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int year, int month, int day) {
			// TODO Auto-generated method stub
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			updateDate();
		}

		private void updateDate() {
			// TODO Auto-generated method stub
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			renewTime = simpleDateFormat.format(cal.getTime());
			Toast.makeText(MyBorrowRuItemActivity.this, renewTime,
					Toast.LENGTH_LONG).show();

			InsertRenewalApplyService insertRenewalApplyService = new InsertRenewalApplyService(
					borrowProof.getBorrowProofId(), renewTime);
			new Thread(insertRenewalApplyService).start();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myborrowitem);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/huakangshaonv.ttf");
		button_myBorrowItem_repay = (Button) findViewById(R.id.button_myBorrowItem_repay);
		button_myBorrowItem_renew = (Button) findViewById(R.id.button_myBorrowItem_renew);

		TextView text_myBorrowRuId = (TextView) findViewById(R.id.text_myBorrowRuId);
		TextView text_myBorrowChuUser = (TextView) findViewById(R.id.text_myBorrowChuUser);
		TextView text_myBorrowRuSum = (TextView) findViewById(R.id.text_myBorrowRuSum);
		TextView text_myBorrowRuTime = (TextView) findViewById(R.id.text_myBorrowRuTime);
		text_myBorrowRepayTime = (TextView) findViewById(R.id.text_myBorrowRepayTime);

		text_myBorrowRuId.setTypeface(face);
		text_myBorrowChuUser.setTypeface(face);
		text_myBorrowRuSum.setTypeface(face);
		text_myBorrowRuTime.setTypeface(face);
		text_myBorrowRepayTime.setTypeface(face);

		borrowProof = (BorrowProof) getIntent().getSerializableExtra(
				"BorrowProof");
		text_myBorrowRuId
				.setText(String.valueOf(borrowProof.getBorrowProofId()));
		text_myBorrowChuUser.setText(String.valueOf(borrowProof
				.getBorrowUserId()));
		text_myBorrowRuSum.setText(String.valueOf(borrowProof.getBorrowSum()));
		text_myBorrowRuTime.setText(borrowProof.getBorrowTime());
		text_myBorrowRepayTime.setText(borrowProof.getRepayTime());

		if (borrowProof.getIsRepayed() == 1) {
			button_myBorrowItem_repay.setVisibility(View.INVISIBLE);
			button_myBorrowItem_renew.setVisibility(View.INVISIBLE);
		}

		user = (User) Session.getSession().get("user");
		button_myBorrowItem_repay.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (user.getUser_balance() < borrowProof.getBorrowSum()) {
					Toast.makeText(getApplicationContext(), "您的余额不足，还款失败!",
							Toast.LENGTH_LONG).show();
					return;
				}
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				InsertRepayProofService irs = new InsertRepayProofService(
						borrowProof.getBorrowProofId(), 1, borrowProof
								.getBorrowSum(), sdf.format(new Date()));
				new Thread(irs).start();

				Toast.makeText(getApplicationContext(), "还款成功!",
						Toast.LENGTH_LONG).show();
				Intent intent = new Intent(MyBorrowRuItemActivity.this,
						MainActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				MyBorrowRuItemActivity.this.finish();
			}
		});

		button_myBorrowItem_renew.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DatePickerDialog diglog = new DatePickerDialog(
						MyBorrowRuItemActivity.this, listener, cal
								.get(Calendar.YEAR), cal.get(Calendar.MONTH),
						cal.get(Calendar.DAY_OF_MONTH));
				diglog.setTitle("请选择展期时间");
				diglog.setIcon(R.drawable.ic_launcher);
				diglog.show();
			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.open:

			sharedPreferenceds = getSharedPreferences("light",
					Context.MODE_PRIVATE);
			int count = sharedPreferenceds.getInt("light", 0);
			
			if(count==0)
				item.setTitle("开启挥手发送");
			else
				item.setTitle("关闭挥手发送");
			System.out.println("count"+count);
			
			if(count==0)
			{
				Editor editor = sharedPreferenceds.edit();
				editor.putInt("light", 1);
				editor.commit();
			}
			else
			{
				Editor editor = sharedPreferenceds.edit();
				editor.putInt("light", 0);
				editor.commit();
			}
			return true;
			
		case R.id.toHome:
			Intent intent=new Intent(getApplicationContext(), MainActivity.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			this.finish();
			
			return true;
		case R.id.sign_out:
			System.exit(0);
			return true;
		}

		return false;
	}
}
