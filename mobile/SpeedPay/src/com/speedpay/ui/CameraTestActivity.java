package com.speedpay.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.LocationListener;
import com.baidu.mapapi.MKAddrInfo;
import com.baidu.mapapi.MKBusLineResult;
import com.baidu.mapapi.MKDrivingRouteResult;
import com.baidu.mapapi.MKPoiResult;
import com.baidu.mapapi.MKSearch;
import com.baidu.mapapi.MKSearchListener;
import com.baidu.mapapi.MKSuggestionResult;
import com.baidu.mapapi.MKTransitRouteResult;
import com.baidu.mapapi.MKWalkingRouteResult;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.MyLocationOverlay;
import com.speedpay.R;
import com.speedpay.bean.MarketItem;
import com.speedpay.bean.User;
import com.speedpay.services.CheckPaypasswordService;
import com.speedpay.services.CheckQrCodeService;
import com.speedpay.services.InsertBorrowProofService;
import com.speedpay.services.InsertConsumptionProofService;
import com.speedpay.services.InsertPurchaseContentService;
import com.speedpay.services.InsertTransferProofService;
import com.speedpay.services.InsertWithdrawProofService;
import com.speedpay.services.SetQrCodeService;
import com.speedpay.ui.utils.CameraPreview;
import com.speedpay.ui.utils.ProgressDialog;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeParse;
import com.speedpay.utils.Session;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import net.sourceforge.zbar.Config;

public class CameraTestActivity extends MapActivity {
	private Camera mCamera;
	private CameraPreview mPreview;
	private Handler autoFocusHandler;
	public String bill = null;
	public String MarketId, MarketName, MarketSum, MarketTime;

	private int CustomId;
	private double ShopSum;
	private String ShopTime;
	private String ShopCause;

	private int WithDrawUserId;
	private double WithDrawSum;
	private String WithDrawTime;

	private int TransferUerId;
	private double TransferSum;
	private String TransferTime;
	private String TransferCause;

	private int BorrowUserId;
	private double BorrowSum;
	private String BorrowTime;
	private String RepayTime;

	final String FILE = "/location.txt";
	BMapManager mBMapMan = null;
	MapView bMapView;
	LocationListener locationlistener;
	MyLocationOverlay mLocationOverlay;
	private MKSearch mMKSearch;
	public double x, y;
	List<Map<String, Object>> list;
	List<MarketItem> MarketItemList = new ArrayList<MarketItem>();
	ImageScanner scanner;
	private boolean previewing = true;

	static {
		System.loadLibrary("iconv");
	}

	User user;

	Dialog dialog;
	Dialog prodialog;
	String str;
	ErCodeParse billCode;
	EditText paypassword_edit;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_ercodemain);
		
		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("006830AC5F687CD0E1DA876F354A649A99FE3718", null);
		super.initMapActivity(mBMapMan);
		bMapView = (MapView) findViewById(R.id.bmapsView);
		bMapView.setVisibility(View.INVISIBLE);
		mMKSearch = new MKSearch();
		mMKSearch.init(mBMapMan, new MySearchListener());
		bMapView.setBuiltInZoomControls(true);
		mLocationOverlay = new MyLocationOverlay(this, bMapView);
		bMapView.getOverlays().add(mLocationOverlay);
		locationlistener = new LocationListener() {
			public void onLocationChanged(Location location) {
				if (location != null) {
					GeoPoint pt = new GeoPoint(
							(int) (location.getLatitude() * 1e6),
							(int) (location.getLongitude() * 1e6));
					x = location.getLatitude();
					y = location.getLongitude();
					bMapView.getController().animateTo(pt);
					mMKSearch.reverseGeocode(new GeoPoint((int) (location
							.getLatitude() * 1e6), (int) (location
							.getLongitude() * 1e6)));

				}
			}
		};

		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

		autoFocusHandler = new Handler();
		mCamera = getCameraInstance();
		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);
		mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
		FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
		preview.addView(mPreview);
	}

	private String read() {
		try {
			if (Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED)) {
				File sdDirFile = Environment.getExternalStorageDirectory();
				FileInputStream fis = new FileInputStream(
						sdDirFile.getCanonicalPath() + FILE);
				BufferedReader br = new BufferedReader(new InputStreamReader(
						fis));
				StringBuilder sb = new StringBuilder("");
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				return sb.toString();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	private boolean compareLocaton(double x, double y) {
		int start = 0, end = 0, middle = 0;
		double X, Y;
		String temp = read();
		boolean flag = false;
		if (temp != null) {
			for (int i = 0; i < temp.length() - 1; i++) {
				if (temp.subSequence(i, i + 1).equals(","))
					middle = i;
				if (temp.subSequence(i, i + 1).equals(";")) {
					end = i;
					X = Double.valueOf(temp.subSequence(start, middle)
							.toString());
					Y = Double.valueOf(temp.subSequence(middle + 1, end)
							.toString());
					if (Math.abs(x - X) < 0.001 && Math.abs(y - Y) < 0.001) {
						flag = true;
					}
					start = i + 1;
				}
			}
		}
		return flag;

	}

	@SuppressLint("NewApi")
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		Dialog dialog;
		switch (id) {
		case 1:
			String itemList = "";
			for (MarketItem iterable_element : MarketItemList) {
				itemList += iterable_element + "\n";
			}
			dialog = new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("超市购物明细")
					.setMessage(
							"超市名称:" + MarketName + "\n" + "购物金额:" + MarketSum
									+ "\n" + "购物时间:" + MarketTime + "\n"
									+ "物品名称" + "       " + "              单价"
									+ "   " + "数量" + "   " + "\n" + itemList)
					.setPositiveButton("付款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									SetQrCodeService scs = new SetQrCodeService(
											str);
									new Thread(scs).start();

									if (compareLocaton(x, y) == false) {
										Toast.makeText(getApplicationContext(),
												"异常登陆", Toast.LENGTH_SHORT)
												.show();
										String add = String.valueOf(x) + ","
												+ String.valueOf(y) + ";";
										CheckDialog check = new CheckDialog(
												CameraTestActivity.this,
												MarketSum, MarketTime,
												MarketId, add);
										check.setTitle("登录异常验证");
//										check.setIconAttribute(
//												android.R.attr.alertDialogIcon);
										check.show();

									} else {
										User user = (User) Session.getSession()
												.get("user");
										if (user.getUser_balance() < Double
												.parseDouble(MarketSum)) {
											Toast.makeText(
													getApplicationContext(),
													"您的余额不足！",
													Toast.LENGTH_LONG).show();
											Intent toHome = new Intent();
											toHome.setClass(
													getApplicationContext(),
													MainActivity.class);
											toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											startActivity(toHome);
										} else {
											Thread thread = new Thread(
													new InsertPurchaseContentService(
															MarketId,
															String.valueOf(user
																	.getUser_id()),
															MarketSum,
															MarketTime));
											thread.start();

											Intent toHome = new Intent();
											toHome.setClass(
													getApplicationContext(),
													MainActivity.class);
											toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
											startActivity(toHome);
											Toast.makeText(
													getApplicationContext(),
													"付款成功,返回主页",
													Toast.LENGTH_LONG).show();
										}
									}
								}
							})
					.setNegativeButton("不付款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent back = new Intent();
									back.setClass(getApplicationContext(),
											CameraTestActivity.class);
									back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(back);
									Toast.makeText(getApplicationContext(),
											"您取消了本次付款", Toast.LENGTH_LONG)
											.show();
								}
							}).create();
			dialog.setCanceledOnTouchOutside(false);

			return dialog;
		case 2:
			dialog = new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("商户消费凭据")
					.setMessage(
							"顾客编号:" + CustomId + "\n" + "消费金额:" + ShopSum
									+ "\n" + "消费时间:" + ShopTime + "\n"
									+ "消费原因:" + ShopCause)
					.setPositiveButton("交易",
							new DialogInterface.OnClickListener() {

								@SuppressLint("HandlerLeak")
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									SetQrCodeService scs = new SetQrCodeService(
											str);
									new Thread(scs).start();

									Handler handler = new Handler() {
										@SuppressLint("HandlerLeak")
										public void handleMessage(Message msg) {
											if (msg.obj == null) {
												Toast.makeText(
														getApplicationContext(),
														"交易失败,返回主页",
														Toast.LENGTH_LONG)
														.show();
												Intent toHome = new Intent();
												toHome.setClass(
														getApplicationContext(),
														MainActivity.class);
												toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
												startActivity(toHome);
											}
											if ((Boolean) msg.obj == true) {
												Toast.makeText(
														getApplicationContext(),
														"交易成功,返回主页",
														Toast.LENGTH_LONG)
														.show();
												Intent toHome = new Intent();
												toHome.setClass(
														getApplicationContext(),
														MainActivity.class);
												toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
												startActivity(toHome);
											} else {
												Toast.makeText(
														getApplicationContext(),
														"交易失败,顾客余额不足",
														Toast.LENGTH_LONG)
														.show();
												Intent toHome = new Intent();
												toHome.setClass(
														getApplicationContext(),
														MainActivity.class);
												toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
												startActivity(toHome);
											}
											Thread.currentThread().interrupt();
										}

									};
									User user = (User) Session.getSession()
											.get("user");
									InsertConsumptionProofService icps = new InsertConsumptionProofService(
											String.valueOf(user.getUser_id()),
											String.valueOf(CustomId), ShopTime,
											String.valueOf(ShopSum), ShopCause,
											handler);
									new Thread(icps).start();

									// Thread.currentThread().interrupt();
								}
							})
					.setNegativeButton("不交易",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent back = new Intent();
									back.setClass(getApplicationContext(),
											CameraTestActivity.class);
									back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(back);
									Toast.makeText(getApplicationContext(),
											"您取消了本次交易", Toast.LENGTH_LONG)
											.show();
								}
							}).create();
			dialog.setCanceledOnTouchOutside(false);
			return dialog;
		case 3:
			user = (User) Session.getSession().get("user");
			dialog = new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("取款凭据")
					.setMessage(
							"取款人编号:" + WithDrawUserId + "\n" + "取款金额:"
									+ WithDrawSum + "\n" + "取款时间:"
									+ WithDrawTime)
					.setPositiveButton("取款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									SetQrCodeService scs = new SetQrCodeService(
											str);
									new Thread(scs).start();

									InsertWithdrawProofService insertWithdrawProofService = new InsertWithdrawProofService(
											String.valueOf(WithDrawUserId),
											user.getUser_name(), WithDrawTime,
											String.valueOf(WithDrawSum));
									new Thread(insertWithdrawProofService)
											.start();

									Toast.makeText(getApplicationContext(),
											"取款成功,返回主页", Toast.LENGTH_LONG)
											.show();
									Intent toHome = new Intent();
									toHome.setClass(getApplicationContext(),
											MainActivity.class);
									toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(toHome);
								}
							})
					.setNegativeButton("不取款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent back = new Intent();
									back.setClass(getApplicationContext(),
											CameraTestActivity.class);
									back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(back);
									Toast.makeText(getApplicationContext(),
											"您取消了本次取款", Toast.LENGTH_LONG)
											.show();
								}
							}).create();
			dialog.setCanceledOnTouchOutside(false);
			return dialog;
		case 4:
			user = (User) Session.getSession().get("user");
			dialog = new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("转账凭据")
					.setMessage(
							"转出方编号:" + TransferUerId + "\n" + "转账金额:"
									+ TransferSum + "\n" + "转账时间:"
									+ TransferTime + "\n" + "转账原因:"
									+ TransferCause)
					.setPositiveButton("收款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub

									SetQrCodeService scs = new SetQrCodeService(
											str);
									new Thread(scs).start();

									InsertTransferProofService itps = new InsertTransferProofService(
											String.valueOf(TransferUerId),
											String.valueOf(user.getUser_id()),
											TransferTime, String
													.valueOf(TransferSum),
											TransferCause);
									new Thread(itps).start();

									Toast.makeText(getApplicationContext(),
											"收款成功,返回主页", Toast.LENGTH_LONG)
											.show();
									Intent toHome = new Intent();
									toHome.setClass(getApplicationContext(),
											MainActivity.class);
									toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(toHome);
									;
								}
							})
					.setNegativeButton("不收款",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent back = new Intent();
									back.setClass(getApplicationContext(),
											CameraTestActivity.class);
									back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(back);
									Toast.makeText(getApplicationContext(),
											"您取消了本次转账", Toast.LENGTH_LONG)
											.show();
								}
							}).create();
			dialog.setCanceledOnTouchOutside(false);
			return dialog;

		case 5:
			user = (User) Session.getSession().get("user");
			dialog = new AlertDialog.Builder(this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("借款凭据")
					.setMessage(
							"借入方编号:" + BorrowUserId + "\n" + "借入金额:"
									+ BorrowSum + "\n" + "借入时间:" + BorrowTime
									+ "\n" + "还款时间:" + RepayTime)
					.setPositiveButton("借出",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									SetQrCodeService scs = new SetQrCodeService(
											str);
									new Thread(scs).start();

									if (user.getUser_balance() < BorrowSum) {
										Toast.makeText(getApplicationContext(),
												"您的余额不足,借出失败",
												Toast.LENGTH_LONG).show();
										Intent toHome = new Intent();
										toHome.setClass(
												getApplicationContext(),
												MainActivity.class);
										toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
										startActivity(toHome);
									} else {

										LayoutInflater factory = LayoutInflater
												.from(getApplicationContext());
										final View textEntryView = factory
												.inflate(
														R.layout.password_dialog_text_entry,
														null);

										new AlertDialog.Builder(
												CameraTestActivity.this)
												.setIconAttribute(
														android.R.attr.alertDialogIcon)
												.setTitle("安全验证")
												.setView(textEntryView)
												.setPositiveButton(
														"确认",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dd,
																	int which) {
																// TODO
																// Auto-generated
																 
																Handler handler=new Handler()
																{
																	public void handleMessage(Message msg) {
																		prodialog.dismiss();
																		if((Boolean) msg.obj==true)
																		{
																			InsertBorrowProofService inps = new InsertBorrowProofService(
																					String.valueOf(user
																							.getUser_id()), String
																							.valueOf(BorrowUserId),
																					BorrowTime, String
																							.valueOf(BorrowSum),
																					RepayTime);
																			new Thread(inps).start();
																			Toast.makeText(getApplicationContext(),
																					"借出成功,返回主页", Toast.LENGTH_LONG)
																					.show();
																			Intent toHome = new Intent();
																			toHome.setClass(
																					getApplicationContext(),
																					MainActivity.class);
																			toHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
																			startActivity(toHome);
																		}
																		else
																		{
																			Toast.makeText(getApplicationContext(),
																					"您的支付密码错误!", Toast.LENGTH_LONG)
																					.show();
																			return;
																		}
																			
																	}
																};
																
																paypassword_edit=(EditText) textEntryView.findViewById(R.id.paypassword_edit);
																CheckPaypasswordService cps=new CheckPaypasswordService(handler, paypassword_edit.getText().toString().trim(),user.getUser_id());
																new Thread(cps).start();
																
																ProgressDialog d=new ProgressDialog();
																prodialog=d.showRoundProcessDialog(CameraTestActivity.this);
															}
														})
												.setNegativeButton(
														"取消",
														new DialogInterface.OnClickListener() {

															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																return;

															}
														}).create().show();
									}
								}
							})
					.setNegativeButton("不借出",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									Intent back = new Intent();
									back.setClass(getApplicationContext(),
											CameraTestActivity.class);
									back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
									startActivity(back);
									Toast.makeText(getApplicationContext(),
											"您取消了本次借出", Toast.LENGTH_LONG)
											.show();
								}
							}).create();
			dialog.setCanceledOnTouchOutside(false);
			return dialog;
		}
		return null;
	}

	public void onPause() {
		super.onPause();
		releaseCamera();
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open();
		} catch (Exception e) {
		}
		return c;
	}

	private void releaseCamera() {
		if (mCamera != null) {
			previewing = false;
			mCamera.setPreviewCallback(null);
			mCamera.release();
			mCamera = null;
		}
	}

	private Runnable doAutoFocus = new Runnable() {
		public void run() {
			if (previewing)
				mCamera.autoFocus(autoFocusCB);
		}
	};

	PreviewCallback previewCb = new PreviewCallback() {
		@SuppressWarnings({ "deprecation", "unchecked" })
		public void onPreviewFrame(byte[] data, Camera camera) {
			Camera.Parameters parameters = camera.getParameters();
			Size size = parameters.getPreviewSize();

			Image barcode = new Image(size.width, size.height, "Y800");
			barcode.setData(data);
			int result = scanner.scanImage(barcode);
			if (result != 0) {
				previewing = false;
				mCamera.setPreviewCallback(null);
				mCamera.stopPreview();

				SymbolSet syms = scanner.getResults();
				str = null;
				for (Symbol sym : syms) {

					try {
						byte[] b = sym.getDataBytes();
						if (b[0] == -24) {
							b = sym.getData().getBytes("sjis");
						}
						str = new String(b);
						System.out.println("--------------"+str);
						str = DESCrypto.jiemi(str);
						
						System.out.println("扫描:" + str);

						billCode = new ErCodeParse();
						if (str.substring(1, 2).equals("1") == false
								&& str.substring(1, 2).equals("2") == false
								&& str.substring(1, 2).equals("3") == false
								&& str.substring(1, 2).equals("4") == false
								&& str.substring(1, 2).equals("5") == false) {
							Toast.makeText(getApplicationContext(), "非法二维码",
									Toast.LENGTH_LONG).show();
						} else {

							Handler handler = new Handler() {
								public void handleMessage(Message msg) {

									dialog.dismiss();
									if ((Boolean) msg.obj == false) {
										Toast.makeText(getApplicationContext(),
												"二维码已过期!", Toast.LENGTH_LONG)
												.show();
										Intent intent = new Intent(
												getApplicationContext(),
												CameraTestActivity.class);
										intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
										startActivity(intent);
										CameraTestActivity.this.finish();
									} else {
										Toast.makeText(getApplicationContext(),
												"二维码有效!", Toast.LENGTH_LONG)
												.show();

										switch (Integer.valueOf(str.substring(
												1, 2))) {
										case 1:
											MarketId = String
													.valueOf(billCode
															.parse(str).get(
																	"MarketId"));
											MarketName = billCode.parse(str)
													.get("MarketName")
													.toString();
											MarketSum = String.valueOf(billCode
													.parse(str)
													.get("MarketSum"));
											MarketTime = billCode.parse(str)
													.get("MarketTime")
													.toString();
											MarketItemList = (List<MarketItem>) billCode
													.parse(str).get(
															"MarketItemList");
											user = (User) Session.getSession()
													.get("user");
											if (user.getUser_type() == 0)
												showDialog(1);
											else
												Toast.makeText(
														getApplicationContext(),
														"非法二维码",
														Toast.LENGTH_LONG)
														.show();
											break;
										case 2:
											CustomId = (Integer) billCode
													.parse(str).get("CustomId");
											ShopSum = (Double) billCode.parse(
													str).get("ShopSum");
											ShopTime = (String) billCode.parse(
													str).get("ShopTime");
											ShopCause = (String) billCode
													.parse(str)
													.get("ShopCause");
											user = (User) Session.getSession()
													.get("user");
											if (user.getUser_type() == 2)
												showDialog(2);
											else
												Toast.makeText(
														getApplicationContext(),
														"非法二维码",
														Toast.LENGTH_LONG)
														.show();
											break;
										case 3:
											WithDrawUserId = (Integer) billCode
													.parse(str).get(
															"WithDrawUserId");
											WithDrawSum = (Double) billCode
													.parse(str).get(
															"WithDrawSum");
											WithDrawTime = (String) billCode
													.parse(str).get(
															"WithDrawTime");
											user = (User) Session.getSession()
													.get("user");
											if (user.getUser_type() == 0)
												showDialog(3);
											else
												Toast.makeText(
														getApplicationContext(),
														"非法二维码",
														Toast.LENGTH_LONG)
														.show();
											break;
										case 4:
											TransferUerId = (Integer) billCode
													.parse(str).get(
															"TransferUerId");
											TransferSum = (Double) billCode
													.parse(str).get(
															"TransferSum");
											TransferTime = (String) billCode
													.parse(str).get(
															"TransferTime");
											TransferCause = (String) billCode
													.parse(str).get(
															"TransferCause");
											user = (User) Session.getSession()
													.get("user");
											if (user.getUser_type() == 0)
												showDialog(4);
											else
												Toast.makeText(
														getApplicationContext(),
														"非法二维码",
														Toast.LENGTH_LONG)
														.show();
											break;
										case 5:
											BorrowUserId = (Integer) billCode
													.parse(str).get(
															"BorrowUserId");
											BorrowSum = (Double) billCode
													.parse(str)
													.get("BorrowSum");
											BorrowTime = (String) billCode
													.parse(str).get(
															"BorrowTime");
											RepayTime = (String) billCode
													.parse(str)
													.get("RepayTime");
											showDialog(5);
											break;
										}
									}
								}
							};

							CheckQrCodeService cqs = new CheckQrCodeService(
									handler, str);
							new Thread(cqs).start();
							ProgressDialog d = new ProgressDialog();
							dialog = d
									.showRoundProcessDialog(CameraTestActivity.this);
						}
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}

				}
			}
		}
	};

	// Mimic continuous auto-focusing
	AutoFocusCallback autoFocusCB = new AutoFocusCallback() {
		public void onAutoFocus(boolean success, Camera camera) {
			autoFocusHandler.postDelayed(doAutoFocus, 1000);
		}
	};

	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	protected void onResume() {
		// 获取位置管理者，视图根据位置监听更新位置
		mBMapMan.getLocationManager().requestLocationUpdates(locationlistener);
		// 打开定位图标
		mLocationOverlay.enableMyLocation();
		// 打开指南针
		mLocationOverlay.enableCompass();
		// 启动管理着
		mBMapMan.start();
		super.onResume();
	}

	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	public class MySearchListener implements MKSearchListener {
		@Override
		public void onGetAddrResult(MKAddrInfo result, int iError) {
		}

		@Override
		public void onGetDrivingRouteResult(MKDrivingRouteResult result,
				int iError) {
		}

		@Override
		public void onGetPoiResult(MKPoiResult result, int type, int iError) {
		}

		@Override
		public void onGetTransitRouteResult(MKTransitRouteResult result,
				int iError) {
		}

		@Override
		public void onGetWalkingRouteResult(MKWalkingRouteResult result,
				int iError) {
		}

		@Override
		public void onGetBusDetailResult(MKBusLineResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetPoiDetailSearchResult(int arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetRGCShareUrlResult(String arg0, int arg1) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onGetSuggestionResult(MKSuggestionResult arg0, int arg1) {
			// TODO Auto-generated method stub

		}
	}
}
