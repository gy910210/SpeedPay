package com.speedpay.ui;

import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.utils.Session;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UserInfoActivity extends Activity
{
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        
        Typeface face = Typeface.createFromAsset (getAssets() ,"fonts/huakangshaonv.ttf");
        Typeface face1 = Typeface.createFromAsset (getAssets() ,"fonts/pop.ttf");
        Session session=Session.getSession();
        User user=(User) session.get("user");
        
        TextView text_name=(TextView) findViewById(R.id.text_name);
        text_name.setText(user.getUser_name());
        text_name.setTypeface(face);
        
        TextView text_phone=(TextView) findViewById(R.id.text_phone);
        text_phone.setText(user.getUser_phoneNum());
        text_phone.setTypeface(face);
        
        TextView text_banknum=(TextView) findViewById(R.id.text_banknum);
        text_banknum.setText(user.getUser_bankcardNum());
        text_banknum.setTypeface(face);
        
        TextView text_balance=(TextView) findViewById(R.id.text_balance);
        text_balance.setText(String.valueOf(user.getUser_balance()));
        text_balance.setTypeface(face);
        
        TextView text_userType=(TextView) findViewById(R.id.text_userType);
        String type=null;
        switch(user.getUser_type())
        {
        case User.USER_CUSTOM:
        	type="顾客";
        	break;
        case User.USER_MARKET:
        	type="超市";
        	break;
        case User.USER_SHOP:
        	type="商户";
        	break;
        case User.USER_ATM:
        	type="ATM";
        	break;
        }
        text_userType.setText(type);
        text_userType.setTypeface(face);
        
	}
	
	
}
