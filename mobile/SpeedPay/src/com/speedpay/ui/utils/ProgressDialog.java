package com.speedpay.ui.utils;

import com.speedpay.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;

public class ProgressDialog 
{
	
	 public Dialog showRoundProcessDialog(Context mContext)
	    {
	        OnKeyListener keyListener = new OnKeyListener()
	        {
	            @Override
	            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
	            {
	                if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH)
	                {
	                    return true;
	                }
	                return false;
	            }
	        };

	        Dialog mDialog = new AlertDialog.Builder(mContext).create();
	        mDialog.setCanceledOnTouchOutside(false);
	        mDialog.setOnKeyListener(keyListener);
	        mDialog.show();
	        mDialog.setContentView(R.layout.loading_process_dialog_color);
	        return mDialog;
	    }
}
