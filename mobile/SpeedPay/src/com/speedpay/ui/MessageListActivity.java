package com.speedpay.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.speedpay.R;
import com.speedpay.bean.MyMessage;
import com.speedpay.bean.User;
import com.speedpay.services.DeleteMessageService;
import com.speedpay.services.InsertBorrowProofService;
import com.speedpay.services.InsertTransferProofService;
import com.speedpay.services.MyBorrowRuService;
import com.speedpay.services.MyMessageService;
import com.speedpay.services.OKRenewalApplyService;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.ErCodeParse;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;

public class MessageListActivity extends Activity {  
	
	Handler handler;
	ListView listView;
	ArrayList<MyMessage> messageList;
	int check;
	Dialog dialog;
	int renewalID;
	
    @Override  
    public void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_messagelist);  
        listView = (ListView) findViewById(R.id.listView_message);
        
        handler=new Handler()
        {
        	public void handleMessage(Message msg) {
        		messageList=(ArrayList<MyMessage>) msg.obj;
        		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        		for(int i=0;i<messageList.size();i++)
        		{
        			HashMap<String, Object> map = new HashMap<String, Object>();
        			ErCodeUtils codeUtils = new ErCodeUtils();
        			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
					switch(messageList.get(i).getMessage_type())
        			{
        			case 0:
        				
        				try {
        					map.put("message_itemLogo", R.drawable.ic_launcher);
        					
        					map.put("message_itemTitle", sdf.format(new Date())+"  您有一条转账消息...");
        					ErCodeParse ercodeParse=new ErCodeParse();
        					Map<String,Object> map1=ercodeParse.parse(messageList.get(i).getMessage_content());
        					map.put("message_itemContent", "转出方编号:" + map1.get("TransferUerId") + "\n" + "转账金额:"
									+ map1.get("TransferSum") + "\n" + "转账时间:"
									+ map1.get("TransferTime") + "\n" + "转账原因:"
									+ map1.get("TransferCause"));
        				}catch (Exception e) {
							// TODO: handle exception
        					e.printStackTrace();
						}
        				break;
        			case 1:
        				try {
        					map.put("message_itemLogo", R.drawable.ic_launcher);
        					map.put("message_itemTitle", sdf.format(new Date())+"  您有一条借款消息...");
        					ErCodeParse ercodeParse=new ErCodeParse();
        					Map<String,Object> map1=ercodeParse.parse(messageList.get(i).getMessage_content());
        					map.put("message_itemContent","借入方编号:" + map1.get("BorrowUserId") + "\n" + "借入金额:"
									+ map1.get("BorrowSum") + "\n" + "借入时间:" + map1.get("BorrowTime")
									+ "\n" + "约定还款时间:" + map1.get("RepayTime"));
        				}catch (Exception e) {
							// TODO: handle exception
        					e.printStackTrace();
						}
        				break;
        			case 2:
        				map.put("message_itemLogo", R.drawable.ic_launcher);
    					map.put("message_itemTitle", sdf.format(new Date())+"  您有一条展期申请消息...");
    					String[] strs=messageList.get(i).getMessage_content().split("/");
    					map.put("message_itemContent", strs[0]);
    					renewalID=Integer.parseInt(strs[1]);
        				break;
        			case 3:
        				break;
        			}
        			
        			list.add(map);
        		}
        		SimpleAdapter listItemAdapter = new SimpleAdapter(MessageListActivity.this,list,R.layout.messagelist_item,
		        		new String[]{"message_itemLogo","message_itemTitle","message_itemContent"},
		        		new int[]{R.id.message_itemLogo,R.id.message_itemTitle,R.id.message_itemContent});
		        
        		listView.setAdapter(listItemAdapter);
		        
				Thread.currentThread().interrupt();
				dialog.dismiss();
        	}
        };
        
        User user=(User) Session.getSession().get("user");
		MyMessageService mms=new MyMessageService(user.getUser_id(), handler);
		new Thread(mms).start();
		
		ProgressDialog d=new ProgressDialog();
		dialog=d.showRoundProcessDialog(MessageListActivity.this);
        
        listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				check=arg2;
				String title="";
				switch(messageList.get(arg2).getMessage_type())
				{
				case 0:
					title="是否确认接收对方转账?";
					break;
				case 1:
					title="是否确认向对方借出?";
					break;
				case 2:
					title="是否同意对方展期申请?";
					break;
				}
				// TODO Auto-generated method stub
				Dialog d=new AlertDialog.Builder(MessageListActivity.this)
				.setIconAttribute(android.R.attr.alertDialogIcon)
				.setTitle(title)
				.setPositiveButton("确认", new DialogInterface.OnClickListener() {
					User user=(User) Session.getSession().get("user");
					ErCodeParse ercodeParse=new ErCodeParse();
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						switch(messageList.get(check).getMessage_type())
						{
						case 0:
							Map<String,Object> map1=ercodeParse.parse(messageList.get(check).getMessage_content());
							InsertTransferProofService itps=new InsertTransferProofService(String.valueOf(map1.get("TransferUerId")), String.valueOf(user.getUser_id()), (String)map1.get("TransferTime"), String.valueOf(map1.get("TransferSum")), (String)map1.get("TransferCause"));
							new Thread(itps).start();
							Toast.makeText(getApplicationContext(),
									"收款成功!", Toast.LENGTH_LONG)
									.show();
							break;
						case 1:
							Map<String,Object> map2=ercodeParse.parse(messageList.get(check).getMessage_content());
							if(user.getUser_balance()<(Double)map2.get("BorrowSum"))
							{
								Toast.makeText(getApplicationContext(),
										"您的余额不足,借出失败!", Toast.LENGTH_LONG)
										.show();
								return;
							}
							
							InsertBorrowProofService inps=new InsertBorrowProofService(String.valueOf(user.getUser_id()), String.valueOf(map2.get("BorrowUserId")), (String)map2.get("BorrowTime"), String.valueOf(map2.get("BorrowSum")), (String)map2.get("RepayTime"));
							new Thread(inps).start();
							Toast.makeText(getApplicationContext(),
									"借出成功!", Toast.LENGTH_LONG)
									.show();
							break;
						case 2:
							OKRenewalApplyService oKRenewalApplyService=new OKRenewalApplyService(renewalID);
							new Thread(oKRenewalApplyService).start();
							Toast.makeText(getApplicationContext(),
									"您已同意展期申请!", Toast.LENGTH_LONG)
									.show();
							break;
						}
						//check=0;
						DeleteMessageService deleteMessageService=new DeleteMessageService(messageList.get(check).getMessage_id());
						new Thread(deleteMessageService).start();
						
						Intent intent=new Intent(MessageListActivity.this, MainActivity.class);
						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						startActivity(intent);
						MessageListActivity.this.finish();
						//return;
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						return;
					}
				}).create();
				d.show();
			}
		});
    }

}
