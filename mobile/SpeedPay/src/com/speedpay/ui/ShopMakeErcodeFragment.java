package com.speedpay.ui;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;
import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.services.InsertQrCodeService;
import com.speedpay.utils.ChangeCharset;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;

public class ShopMakeErcodeFragment extends Fragment
{
	
	EditText input_shopSum;
	Spinner spinner_shopType;
	ArrayAdapter adapter;
	Button button_shop_makeErcode;
	ImageView image_shop_makeErcode;
	String shop_cause="";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_selection_shop_make_ercode, container, false);
		input_shopSum=(EditText) rootView.findViewById(R.id.input_shopSum);
		spinner_shopType=(Spinner) rootView.findViewById(R.id.spinner_shopType);
		button_shop_makeErcode=(Button) rootView.findViewById(R.id.button_shop_makeErcode);
		
		adapter=ArrayAdapter.createFromResource(getActivity(), R.array.shop_type, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner_shopType.setAdapter(adapter);
		
		spinner_shopType.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				shop_cause=(String) adapter.getItem(arg2);
				ChangeCharset test = new ChangeCharset();
				try {
					shop_cause=test.toUTF_8(shop_cause);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				shop_cause=(String) adapter.getItem(0);
				ChangeCharset test = new ChangeCharset();
				try {
					shop_cause=test.toUTF_8(shop_cause);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		button_shop_makeErcode.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				//Toast.makeText(getActivity(), shop_cause, Toast.LENGTH_LONG).show();
				
				User user=(User)Session.getSession().get("user");
				String shop_sum=input_shopSum.getText().toString();
				if(shop_sum.equals(""))
				{
					Toast.makeText(getActivity(), "请输入消费金额!", Toast.LENGTH_LONG).show();
					return;
				}
				if(Double.parseDouble(shop_sum)>user.getUser_balance())
				{
					Toast.makeText(getActivity(), "您的余额不足!", Toast.LENGTH_LONG).show();
					return;
				}
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				
				String erCodeStr="/2/"+user.getUser_id()+"/"+shop_sum+"/"+df.format(new Date())+"/"+shop_cause+"/"+df1.format(new Date());
				String erCodeStrjia=DESCrypto.jiami(erCodeStr);
				InsertQrCodeService iqs=new InsertQrCodeService(erCodeStr);
				new Thread(iqs).start();
				
				System.out.println(erCodeStrjia);
				System.out.println(DESCrypto.jiemi(erCodeStrjia));
				
				ErCodeUtils codeUtils = new ErCodeUtils();
				Bitmap bitmap = null;
				try {
					bitmap = codeUtils.Create2DCode(erCodeStrjia);
					ImageView img = new ImageView(getActivity());
					img.setImageBitmap(bitmap);
					
					Dialog d=new AlertDialog.Builder(new ContextThemeWrapper(
							getActivity(), R.style.AppBaseTheme))
							.setIcon(R.drawable.ic_launcher)
							.setTitle("商家二维码")
							.setView(img)
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											Toast.makeText(getActivity(), "您已取消商家二维码！",
													Toast.LENGTH_LONG).show();
											return;
										}
									}).create();
					d.setCanceledOnTouchOutside(false);
					d.show();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//image_shop_makeErcode.setImageBitmap(bitmap);
				input_shopSum.setText("");
				//shop_cause="";
			}
		});
		
		return rootView;
	}
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
