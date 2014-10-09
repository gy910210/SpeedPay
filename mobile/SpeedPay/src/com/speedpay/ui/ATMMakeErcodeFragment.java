package com.speedpay.ui;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.zxing.WriterException;
import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.services.InsertQrCodeService;
import com.speedpay.ui.UserMakeErcodeActivity;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;
import com.speedpay.utils.StringUtils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;


public class ATMMakeErcodeFragment extends Fragment {
	
	
	EditText input_atmSum;
	Button button_atm_makeErcode;
	ImageView image_atm_makeErcode;
	String sum="100";
	@SuppressLint({ "NewApi", "CutPasteId" })
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_selection_atm_make_ercode, container, false);
		
		
		input_atmSum=(EditText) rootView.findViewById(R.id.input_atmSum);
		RadioGroup radioGroup_atmMoney=(RadioGroup) rootView.findViewById(R.id.radioGroup_atmMoney);
		radioGroup_atmMoney.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup radio, int id) {
				// TODO Auto-generated method stub
				switch(id)
				{
				case R.id.radio_atm100:
					input_atmSum.setVisibility(View.INVISIBLE);
					sum="100";
					break;
				case R.id.radio_atm200:
					input_atmSum.setVisibility(View.INVISIBLE);
					sum="200";
					break;
				case R.id.radio_atm400:
					input_atmSum.setVisibility(View.INVISIBLE);
					sum="400";
					break;
				case R.id.radio_atmOther:
					input_atmSum.setVisibility(View.VISIBLE);
					break;
				}
			}
		});
		
		
		button_atm_makeErcode=(Button) rootView.findViewById(R.id.button_atm_makeErcode);
		button_atm_makeErcode.setOnClickListener(new OnClickListener() {
			
			@SuppressLint("SimpleDateFormat")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User user=(User) Session.getSession().get("user");
				ErCodeUtils codeUtils = new ErCodeUtils();
				
				if(input_atmSum.isShown())
				{
					sum=input_atmSum.getText().toString().trim();
					if(sum.equals(""))
					{
						Toast.makeText(getActivity(), "请输入取款金额!", Toast.LENGTH_LONG).show();
						return;
					}
					if((Double.parseDouble(sum)%100!=0))
					{
						Toast.makeText(getActivity(), "请输入100元的整数倍金额!", Toast.LENGTH_LONG).show();
						input_atmSum.setText("");
						return;
					}
					
					if(user.getUser_balance()<Double.parseDouble(sum))
					{
						Toast.makeText(getActivity(), "您的余额不足!", Toast.LENGTH_LONG).show();
						input_atmSum.setText("");
						return;
					}
					System.out.println("ATM sum="+sum);
				}
				
				if(user.getUser_balance()<Double.parseDouble(sum))
				{
					Toast.makeText(getActivity(), "您的余额不足!", Toast.LENGTH_LONG).show();
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String erCodeStr="/3/"+user.getUser_id()+"/"+sum+"/"+df.format(new Date())+"/"+df1.format(new Date());
				String erCodeStrjia=DESCrypto.jiami(erCodeStr);
				InsertQrCodeService iqs=new InsertQrCodeService(erCodeStr);
				new Thread(iqs).start();
				
				
				System.out.println(erCodeStr);
				System.out.println(DESCrypto.jiemi(erCodeStrjia));
				
				try {
					Bitmap bitmap = codeUtils.Create2DCode(erCodeStrjia);
					ImageView img = new ImageView(getActivity());
					img.setImageBitmap(bitmap);
					
					Dialog d=new AlertDialog.Builder(new ContextThemeWrapper(
							getActivity(), R.style.AppBaseTheme))
							.setIcon(R.drawable.ic_launcher)
							.setTitle("取款二维码")
							.setView(img)
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											Toast.makeText(getActivity(), "您已取消取款二维码！",
													Toast.LENGTH_LONG).show();
											return;
										}
									}).create();
					d.setCanceledOnTouchOutside(false);
					d.show();
					
					input_atmSum.setText("");
					//sum="";
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		return rootView;
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
}
