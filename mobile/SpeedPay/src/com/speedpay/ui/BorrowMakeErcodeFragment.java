package com.speedpay.ui;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.speedpay.R;
import com.speedpay.bean.User;
import com.speedpay.receiver.AlarmReceiver;
import com.speedpay.services.InsertMessageService;
import com.speedpay.services.InsertQrCodeService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.ContactsUtils;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

public class BorrowMakeErcodeFragment extends Fragment {

	EditText input_borrowSum;
	EditText input_repayDate;
	EditText input_borrowName;
	Button button_borrow_checkContacts;
	Button button_borrow_makeErcode;
	Handler handler;
	String[] names;
	String[] phones;
	int chosen;
	String chosenTel;
	String erCodeStr;
	String erCodeStrJia;
	
	String temp;
	
	SharedPreferences sharedPreferenceds;
	
	Dialog dialog;
	private Calendar cal = Calendar.getInstance();

	/*private OnTimeSetListener timelistener = new OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			cal.set(Calendar.HOUR_OF_DAY, 19);
			cal.set(Calendar.MINUTE, 55);
			updateTime();
		}

		@SuppressLint("SimpleDateFormat")
		private void updateTime() {
			// TODO Auto-generated method stub
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm");
			//input_repayDate.setText(simpleDateFormat.format(cal.getTime()));
			temp = simpleDateFormat.format(cal.getTime());
			Intent intent = new Intent(getActivity(), AlarmReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getActivity(), 0, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getActivity()
					.getSystemService("alarm");
			alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
					pendingIntent);
			System.out.println(simpleDateFormat.format(cal.getTime()));
		}

	};*/
	private OnDateSetListener listener = new OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker arg0, int year, int month, int day) {
			// TODO Auto-generated method stub
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
			cal.set(Calendar.DAY_OF_MONTH, day);
			
			
			updateDate();
		}

		@SuppressLint("SimpleDateFormat")
		private void updateDate() {
			// TODO Auto-generated method stub
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
					"yyyy-MM-dd");
			
			Intent intent = new Intent(getActivity(), AlarmReceiver.class);
			PendingIntent pendingIntent = PendingIntent.getBroadcast(
					getActivity(), 0, intent, 0);
			AlarmManager alarmManager = (AlarmManager) getActivity()
					.getSystemService("alarm");
			alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
					pendingIntent);
			
			input_repayDate.setText(simpleDateFormat.format(cal.getTime()));
			System.out.println(cal.getTime());
		}
	};

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(
				R.layout.fragment_selection_borrow_make_ercode, container,
				false);

		input_borrowName = (EditText) rootView
				.findViewById(R.id.input_borrowName);
		button_borrow_checkContacts = (Button) rootView
				.findViewById(R.id.button_borrow_checkContacts);
		button_borrow_makeErcode = (Button) rootView
				.findViewById(R.id.button_borrow_makeErcode);
		input_borrowSum = (EditText) rootView
				.findViewById(R.id.input_borrowSum);
		input_repayDate = (EditText) rootView
				.findViewById(R.id.input_repayDate);		

		input_repayDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				 DatePickerDialog dialog = new DatePickerDialog(getActivity(),
				 listener, cal.get(Calendar.YEAR), cal
				 .get(Calendar.MONTH), cal
				 .get(Calendar.DAY_OF_MONTH));
				 dialog.setTitle("请选择还款时间");
				 dialog.setIcon(R.drawable.ic_launcher);
				 dialog.show();
				 /*TimePickerDialog timedialog = new TimePickerDialog(
				 getActivity(), timelistener, cal
				 .get(Calendar.HOUR_OF_DAY), cal
				 .get(Calendar.MINUTE), true);*/
			}

		});

		button_borrow_checkContacts.setOnClickListener(new OnClickListener() {

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
											input_borrowName
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

		
		button_borrow_makeErcode.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@SuppressLint({ "NewApi", "SimpleDateFormat" })
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String borrowSum = input_borrowSum.getText().toString().trim();
				String repayDate = input_repayDate.getText().toString();
				String borrowName = input_borrowName.getText().toString()
						.trim();
				if (borrowSum.equals("")) {
					Toast.makeText(getActivity(), "请输入借款金额！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (repayDate.equals("")) {
					Toast.makeText(getActivity(), "请输入还款时间！", Toast.LENGTH_LONG)
							.show();
					return;
				}
				if (borrowName.equals("")) {
					Toast.makeText(getActivity(), "请输入借出人姓名！",
							Toast.LENGTH_LONG).show();
					return;
				}

				User user = (User) Session.getSession().get("user");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				
				erCodeStr = "/5/" + user.getUser_id() + "/" + borrowSum + "/"
						+ df.format(new Date()) + "/" + repayDate+"/"+chosenTel+"/"+df1.format(new Date());
				erCodeStrJia=DESCrypto.jiami(erCodeStr);
				
				InsertQrCodeService iqs=new InsertQrCodeService(erCodeStr);
				new Thread(iqs).start();
				
				System.out.println(erCodeStr);
				System.out.println(DESCrypto.jiemi(erCodeStr));
				
				ErCodeUtils codeUtils = new ErCodeUtils();
				try {
					Bitmap bitmap = codeUtils.Create2DCode(erCodeStrJia);
					ImageView img = new ImageView(getActivity());
					img.setImageBitmap(bitmap);

					/*Dialog d=new AlertDialog.Builder(new ContextThemeWrapper(
							getActivity(), R.style.AppBaseTheme))
							.setIconAttribute(R.drawable.ic_launcher)
							.setTitle("借贷二维码")
							.setView(img)
							.setPositiveButton("确认发送",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub

											InsertMessageService ims = new InsertMessageService(
													chosenTel, erCodeStr, "1");
											new Thread(ims).start();

											Toast.makeText(getActivity(),
													"转账二维码发送成功！",
													Toast.LENGTH_LONG).show();
											Intent intent = new Intent(
													getActivity(),
													BorrowActivity.class);
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
											Toast.makeText(getActivity(),
													"您已取消发送借贷二维码！",
													Toast.LENGTH_LONG).show();
											Intent intent = new Intent(
													getActivity(),
													BorrowActivity.class);
											intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											startActivity(intent);
											getActivity().finish();
										}
									}).create();
						d.setCanceledOnTouchOutside(false);
						d.show();*/
					
					BorrowErcodeDialog d=new BorrowErcodeDialog(getActivity());
					d.setIconAttribute(R.drawable.ic_launcher);
					d.setTitle("借贷二维码");
					d.setView(img);
					d.setButton("确认发送",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									InsertMessageService ims = new InsertMessageService(
											chosenTel, erCodeStr, "1");
									new Thread(ims).start();

									Toast.makeText(getActivity(),
											"转账二维码发送成功！",
											Toast.LENGTH_LONG).show();
									Intent intent = new Intent(
											getActivity(),
											BorrowActivity.class);
									intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
									getActivity().finish();
								}
							});
					d.setButton2("取消发送",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(
										DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Toast.makeText(getActivity(),
											"您已取消发送借贷二维码！",
											Toast.LENGTH_LONG).show();
									Intent intent = new Intent(
											getActivity(),
											BorrowActivity.class);
									intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(intent);
									getActivity().finish();
								}
							});
				d.setCanceledOnTouchOutside(false);
				d.show();
				
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
