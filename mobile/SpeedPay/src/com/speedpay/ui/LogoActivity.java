package com.speedpay.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.text.TextPaint;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.speedpay.R;

public class LogoActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_logo);
        
        Typeface face = Typeface.createFromAsset (getAssets() ,"fonts/huakangshaonv.ttf");
	    Typeface face1 = Typeface.createFromAsset (getAssets() ,"fonts/pop.ttf");
	    TextView text_logo=(TextView) findViewById(R.id.text_logo);
	    text_logo.setTypeface(face1);
	    
	    TextPaint tp=text_logo.getPaint();
		tp.setFakeBoldText(true);
	    
        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
        animation.setDuration(5000);
        
        ImageView img_logo = (ImageView) this.findViewById(R.id.img_logo);
        img_logo.setAnimation(animation);
        
        
        animation.setAnimationListener(new AnimationListener(){

			public void onAnimationEnd(Animation animation)
			{

				 Intent intent = new Intent(LogoActivity.this, LoginActivity.class);
			     startActivity(intent);
			     
			     LogoActivity.this.finish();
			}

			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}

			public void onAnimationStart(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}});
        
       
        
    }
}