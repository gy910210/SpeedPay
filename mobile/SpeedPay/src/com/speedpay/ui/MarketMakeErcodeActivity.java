/*
 * Basic no frills app which integrates the ZBar barcode scanner with
 * the camera.
 * 
 * Created by lisah0 on 2012-02-24
 */
package com.speedpay.ui;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.zxing.WriterException;
import com.speedpay.R;
import com.speedpay.bean.Goods;
import com.speedpay.bean.User;
import com.speedpay.services.InsertQrCodeService;
import com.speedpay.ui.utils.CameraPreview;
import com.speedpay.utils.ChangeCharset;
import com.speedpay.utils.DESCrypto;
import com.speedpay.utils.ErCodeUtils;
import com.speedpay.utils.Session;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Size;

import android.widget.TextView;
/* Import ZBar Class files */
import net.sourceforge.zbar.ImageScanner;
import net.sourceforge.zbar.Image;
import net.sourceforge.zbar.Symbol;
import net.sourceforge.zbar.SymbolSet;
import net.sourceforge.zbar.Config;

public class MarketMakeErcodeActivity extends Activity {
	private Camera mCamera;
	private CameraPreview mPreview;
	private Handler autoFocusHandler;

	//TextView scanText;
	//Button scanButton;

	TextView marketItemList;
	Button button_makeErcode;

	ImageScanner scanner;

	private boolean barcodeScanned = false;
	private boolean previewing = true;

	static {
		System.loadLibrary("iconv");
	}

	private String marketItemListStr = "";
	
	private String erCodeStr = "";
	private double sum = 0;
	String erCodeListStr="";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_market_make_ercode);
		
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		Session session = Session.getSession();
		List<Goods> goodsList = new ArrayList<Goods>();
		Goods good = new Goods(1, "HongBao Book", 43.80, "9787560422862");
		goodsList.add(good);
		good=new Goods(2, "Milk Tea", 4.00, "6925303730574");
		goodsList.add(good);
		session.put("goodsList", goodsList);
		
		autoFocusHandler = new Handler();
		mCamera = getCameraInstance();

		/* Instance barcode scanner */
		scanner = new ImageScanner();
		scanner.setConfig(0, Config.X_DENSITY, 3);
		scanner.setConfig(0, Config.Y_DENSITY, 3);

		mPreview = new CameraPreview(this, mCamera, previewCb, autoFocusCB);
		FrameLayout preview = (FrameLayout) findViewById(R.id.cameraPreview);
		preview.addView(mPreview);

		//scanText = (TextView) findViewById(R.id.scanText);
		marketItemList = (TextView) findViewById(R.id.textMarketItemList);
		
		Typeface face = Typeface.createFromAsset (getAssets() ,"fonts/huakangshaonv.ttf");
	    Typeface face1 = Typeface.createFromAsset (getAssets() ,"fonts/pop.ttf");
		TextView market_title=(TextView) findViewById(R.id.market_title);
		market_title.setTypeface(face1);
		
		//scanButton = (Button) findViewById(R.id.ScanButton);
		/*scanButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (barcodeScanned) {
					barcodeScanned = false;
					//scanText.setText("Scanning...");
					mCamera.setPreviewCallback(previewCb);
					mCamera.startPreview();
					previewing = true;
					mCamera.autoFocus(autoFocusCB);
				}
			}
		});*/

		button_makeErcode = (Button) findViewById(R.id.button_market_makeErcode);
		button_makeErcode.setOnClickListener(new OnClickListener() {

			@SuppressLint({ "NewApi", "ResourceAsColor", "SimpleDateFormat" })
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				User user = (User) Session.getSession().get("user");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
				SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				
				erCodeStr="/1/" + user.getUser_id() + "/" + user.getUser_name() + "/";
				erCodeStr += (sum + "/" + df.format(new Date()) + "/" + erCodeListStr
						.substring(0, erCodeListStr.length() - 1));
				erCodeStr+=("/"+df1.format(new Date()));
				
				String erCodeStrjia=DESCrypto.jiami(erCodeStr);
				InsertQrCodeService iqs=new InsertQrCodeService(erCodeStr);
				new Thread(iqs).start();
				
				System.out.println("Make:"+erCodeStr);
				
					System.out.println(DESCrypto.jiemi(erCodeStrjia));
				
				ErCodeUtils codeUtils = new ErCodeUtils();
				try {
					Bitmap bitmap = codeUtils.Create2DCode(erCodeStrjia);
					
					
					/*ImageView imageErcode = (ImageView) findViewById(R.id.imageErcode);
					Toast.makeText(getApplicationContext(), bitmap.toString(),
							Toast.LENGTH_LONG).show();
					imageErcode.setImageBitmap(bitmap);*/
					
					
					
					ImageView img=new ImageView(MarketMakeErcodeActivity.this);
					img.setImageBitmap(bitmap);
					
					
					
					Dialog dialog=new AlertDialog.Builder(new ContextThemeWrapper(MarketMakeErcodeActivity.this,R.style.AppBaseTheme))
					.setIconAttribute(android.R.attr.alertDialogIcon)
					.setTitle("超市二维码")
					.setView(img)
					.setPositiveButton("继续扫描", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(getApplicationContext(), MarketMakeErcodeActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							erCodeListStr = "";
							startActivity(intent);
							MarketMakeErcodeActivity.this.finish();
						}
					})
					.setNegativeButton("放弃扫描", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent=new Intent(getApplicationContext(), MainActivity.class);
							intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
							erCodeListStr = "";
							startActivity(intent);
							MarketMakeErcodeActivity.this.finish();
						}
					}).create();
					dialog.setCanceledOnTouchOutside(false);
					dialog.show();
					
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		erCodeListStr = "";
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
		
		/*Camera c = null;
		Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
		int cameraCount = Camera.getNumberOfCameras();
		
		for ( int camIdx = 0; camIdx < cameraCount;camIdx++ )
		{
			Camera.getCameraInfo( camIdx, cameraInfo ); // get camerainfo  
			if ( cameraInfo.facing ==Camera.CameraInfo.CAMERA_FACING_FRONT ) {
				try{
					c = Camera.open( camIdx );
				}catch(RuntimeException e)
				{
					e.printStackTrace();
				}
			}
		}
		return c;*/
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
		@SuppressLint("SimpleDateFormat")
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
				String str = null;
				for (Symbol sym : syms) {

					try {
						byte[] b = sym.getDataBytes();
						if (b[0] == -24) {
							b = sym.getData().getBytes("sjis");
						}
						str = new String(b);
						//scanText.setText("barcode result " + str);
						barcodeScanned = true;

						
						@SuppressWarnings("unchecked")
						List<Goods> goodsList = (List<Goods>) Session
								.getSession().get("goodsList");
						Goods goods = null;
						for (Goods temp : goodsList) {
							if (temp.getGoods_barCode().equals(str)) {
								goods = temp;
								sum += goods.getGoods_price();
								erCodeListStr += (goods.getGoods_id() + "_"
										+ goods.getGoods_name() + "_"
										+ goods.getGoods_price() + "_1,");
							}
						}

						

						marketItemListStr += goods + "\n";
						marketItemList.setText(marketItemListStr);
						
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (barcodeScanned) {
							barcodeScanned = false;
							//scanText.setText("Scanning...");
							mCamera.setPreviewCallback(previewCb);
							mCamera.startPreview();
							previewing = true;
							//mCamera.autoFocus(autoFocusCB);
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
}
