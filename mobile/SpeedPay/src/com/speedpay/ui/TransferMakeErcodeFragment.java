package com.speedpay.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.services.InsertMessageService;
import com.speedpay.services.InsertQrCodeService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.ContactsUtils;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;
import com.speedpay.utils.StringUtils;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TransferMakeErcodeFragment extends Fragment {

	EditText input_transferSum;
	EditText input_transferCause;
	EditText input_transferReceiveName;
	Button button_transfer_checkContacts;
	Button button_transfer_makeErcode;

	Handler handler;
	String[] names;
	String[] phones;
	int chosen;
	String chosenTel;
	String erCodeStr;
	String erCodeStrJia;
	
	Dialog dialog;
	@SuppressLint("HandlerLeak")
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_selection_transfer_make_ercode, container,
				false);

		input_transferSum = (EditText) rootView
				.findViewById(R.id.input_transferSum);
		input_transferCause = (EditText) rootView
				.findViewById(R.id.input_transferCause);
		input_transferReceiveName = (EditText) rootView
				.findViewById(R.id.input_transferReceiveName);
		button_transfer_checkContacts = (Button) rootView
				.findViewById(R.id.button_transfer_checkContacts);
		button_transfer_makeErcode = (Button) rootView
				.findViewById(R.id.button_transfer_makeErcode);

		button_transfer_checkContacts.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				handler = new Handler() {

					public void handleMessage(Message msg) {
						if (msg.what == 1) {
							ArrayList<Pair<String, String>> list = (ArrayList<Pair<String, String>>) msg.obj;

							names = new String[list.size()];
							phones = new String[list.size()];
							for (int i = 0; i < list.size(); i++) {
								names[i] = list.get(i).first;
								phones[i] = list.get(i).second;
								System.out.println(names[i]);

							}

							
							Thread.currentThread().interrupt();
							dialog.dismiss();
							
							new AlertDialog.Builder(getActivity())
							.setIconAttribute(R.drawable.ic_launcher)
							.setTitle("开通的业务联系人")
							.setSingleChoiceItems(names, 0,
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											chosen = which;
										}
									})
							.setPositiveButton("选择",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											input_transferReceiveName
													.setText(names[chosen]);
											//chosen = 0;
											chosenTel=phones[chosen];
										}
									})
							.setNegativeButton("取消",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											chosen = 0;
										}
									}).create().show();
						}
					}
				};

				new Thread(new Runnable() {

					@Override
					public void run() {
						System.out.println("Runing");
						// TODO Auto-generated method stub
						ContactsUtils contactsUtils = new ContactsUtils();
						ArrayList<Pair<String, String>> list = contactsUtils
								.getContacts(getActivity());
						Message msg = handler.obtainMessage();
						msg.obj = list;
						msg.what = 1;
						handler.sendMessage(msg);
					}
				}).start();
				
				ProgressDialog d=new ProgressDialog();
				dialog=d.showRoundProcessDialog(getActivity());
			}
		});

		button_transfer_makeErcode.setOnClickListener(new OnClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String transferSum = input_transferSum.getText().toString()
						.trim();
				String transferCause = input_transferCause.getText().toString()
						.trim();
				String transferReceiveName = input_transferReceiveName
						.getText().toString().trim();
				if (transferSum.equals("")) {
					Toast.makeText(getActivity(), "请输入转账金额！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (transferReceiveName.equals("")) {
					Toast.makeText(getActivity(), "请输入或选择收款人姓名！",
							Toast.LENGTH_LONG).show();
					return;
				}

				if (!StringUtils.isNumeric(transferSum)) {
					Toast.makeText(getActivity(), "您输入的转账金额有误，请重新输入！",
							Toast.LENGTH_LONG).show();
					return;
				}
				User user = (User) Session.getSession().get("user");
				
				if(user.getUser_balance()<Double.parseDouble(transferSum))
				{
					Toast.makeText(getActivity(), "您的余额不足!",
							Toast.LENGTH_LONG).show();
					input_transferSum.setText("");
					input_transferCause.setText("");
					input_transferReceiveName.setText("");
					return;
				}
				
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				erCodeStr = "/4/" + user.getUser_id() + "/"
						+ transferSum + "/" + df.format(new Date()) + "/"
						+ transferCause+"/"+chosenTel+"/"+df1.format(new Date());
				erCodeStrJia=DESCrypto.jiami(erCodeStr);
				InsertQrCodeService iqs=new InsertQrCodeService(erCodeStr);
				new Thread(iqs).start();
				
				System.out.println(erCodeStr);
				System.out.println(erCodeStrJia);
				System.out.println(DESCrypto.jiemi(erCodeStrJia));
				
				ErCodeUtils codeUtils = new ErCodeUtils();
				try {
					Bitmap bitmap = codeUtils.Create2DCode(erCodeStrJia);
					ImageView img = new ImageView(getActivity());
					img.setImageBitmap(bitmap);

					new AlertDialog.Builder(new ContextThemeWrapper(
							getActivity(), R.style.AppBaseTheme))
							.setIcon(R.drawable.ic_launcher)
							.setTitle("转账二维码")
							.setView(img)
							.setPositiveButton("确认发送",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											
											InsertMessageService ims=new InsertMessageService(chosenTel, erCodeStr, "0");
											new Thread(ims).start();
											
											Toast.makeText(getActivity(), "转账二维码发送成功！",
													Toast.LENGTH_LONG).show();
											Intent intent=new Intent(getActivity(), UserMakeErcodeActivity.class);
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											startActivity(intent);
											getActivity().finish();
										}
									})
							.setNegativeButton("取消发送",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											Toast.makeText(getActivity(), "您已取消发送转账二维码！",
													Toast.LENGTH_LONG).show();
											Intent intent=new Intent(getActivity(), UserMakeErcodeActivity.class);
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											startActivity(intent);
											getActivity().finish();
										}
									}).create().show();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		return rootView;
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
}
