package com.speedpay.ui;

import java.io.File;
import java.io.RandomAccessFile;
import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.services.InsertPurchaseContentService;
import com.speedpay.utils.Session;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckDialog extends Dialog {
	EditText idCard;
	Button sure, cancel;
	User user;
	String MarketSum, MarketTime, MarketId;
	String address;
	final String FILE = "/location.txt";

	public CheckDialog(Context context, String MarketSum, String MarketTime,
			String MarketId, String address) {
		super(context);
		this.MarketSum = MarketSum;
		this.MarketTime = MarketTime;
		this.MarketId = MarketId;
		this.address = address;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.check_dialog);

		idCard = (EditText) findViewById(R.id.idcard);
		sure = (Button) findViewById(R.id.sure);
		cancel = (Button) findViewById(R.id.cancel);
		user = (User) Session.getSession().get("user");
		System.out.println(user);
		final String idNum = user.getUser_IDNum().substring(
				user.getUser_IDNum().length() - 4,
				user.getUser_IDNum().length());
		sure.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (idCard.getText().toString().equals(idNum)) {
					Toast.makeText(getContext(), "您通过了身份验证！", Toast.LENGTH_LONG)
							.show();
					write(address);
					if (user.getUser_balance() < Double.parseDouble(MarketSum)) {
						Toast.makeText(getContext(), "您的余额不足！",
								Toast.LENGTH_LONG).show();
						Intent toHome = new Intent();
						toHome.setClass(getContext(), MainActivity.class);
						toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						getContext().startActivity(toHome);
					} else {
						Thread thread = new Thread(
								new InsertPurchaseContentService(MarketId,
										String.valueOf(user.getUser_id()),
										MarketSum, MarketTime));
						thread.start();
						Intent toHome = new Intent();
						toHome.setClass(getContext(), MainActivity.class);
						toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						getContext().startActivity(toHome);
						Toast.makeText(getContext(), "付款成功,返回主页",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(getContext(), "您的身份验证有误，请重新输入！",
							Toast.LENGTH_SHORT).show();
					idCard.setText("");
				}

			}

		});
		cancel.setOnClickListener(new Button.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent toHome = new Intent();
				toHome.setClass(getContext(), MainActivity.class);
				toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				getContext().startActivity(toHome);
				Toast.makeText(getContext(), "您取消了本次身份验证！", Toast.LENGTH_LONG)
						.show();
			}

		});
	}

	private void write(String context) {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File sdDir = Environment.getExternalStorageDirectory();
				File targetFile = new File(sdDir.getCanonicalPath() + FILE);
				RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
				raf.seek(targetFile.length());
				raf.write(context.getBytes());
				raf.close();
			}
		} catch (Exception e) {
		}
	}

}
