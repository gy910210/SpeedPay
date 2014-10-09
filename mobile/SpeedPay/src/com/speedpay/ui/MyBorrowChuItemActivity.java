package com.speedpay.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.speedpay.R;
import com.speedpay.bean.BorrowProof;

public class MyBorrowChuItemActivity extends Activity{
	
	
	SharedPreferences sharedPreferenceds;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_myborrowchuitem);
        
        Typeface face = Typeface.createFromAsset (getAssets() ,"fonts/huakangshaonv.ttf");
        
        BorrowProof borrowProof=(BorrowProof) getIntent().getSerializableExtra("BorrowProof");
        TextView text_myBorrowChuId=(TextView) findViewById(R.id.text_myBorrowChuId);
        TextView text_myBorrowRuUser=(TextView) findViewById(R.id.text_myBorrowRuUser);
        TextView text_myBorrowChuSum=(TextView) findViewById(R.id.text_myBorrowChuSum);
        TextView text_myBorrowChuTime=(TextView) findViewById(R.id.text_myBorrowChuTime);
        TextView text_myBorrowRepayTime=(TextView) findViewById(R.id.text_myBorrowRepayTime);
        
        text_myBorrowChuId.setTypeface(face);
        text_myBorrowRuUser.setTypeface(face);
        text_myBorrowChuSum.setTypeface(face);
        text_myBorrowChuTime.setTypeface(face);
        text_myBorrowRepayTime.setTypeface(face);
        
        
        
        text_myBorrowChuId.setText(String.valueOf(borrowProof.getBorrowProofId()));
        text_myBorrowRuUser.setText(String.valueOf(borrowProof.getRepayUserId()));
        text_myBorrowChuSum.setText(String.valueOf(borrowProof.getBorrowSum()));
        text_myBorrowChuTime.setText(borrowProof.getBorrowTime());
        text_myBorrowRepayTime.setText(borrowProof.getRepayTime());
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
