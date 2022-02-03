package com.example.handsets;

import com.Dao.FingerPrintDao;
import com.Database.FingerPrintsDatabase;
import com.fgtit.data.wsq;
import com.fgtit.device.Constants;
import com.fgtit.device.FPModule;
import com.fgtit.fpcore.FPMatch;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import Model.FingerPrint;

public class MainActivity extends AppCompatActivity {

	private FPModule fpm=new FPModule();
    
    private byte bmpdata[]=new byte[Constants.RESBMP_SIZE];
    private int bmpsize=0;
    
    private byte refdata[]=new byte[Constants.TEMPLATESIZE*2];
    private int refsize=0;
    private int dbrefsize=0;
    
    private byte matdata[]=new byte[Constants.TEMPLATESIZE*2];
    private int matsize=0;
    
    private int worktype=0;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();
    
	private TextView	tvDevStatu,tvFpStatu;
	private ImageView 	ivFpImage=null;

	private Context mContext;
	FingerPrintsDatabase db;

	//dynamic setting of the permission for writing the data into phone memory
	private int REQUEST_PERMISSION_CODE = 1;
	private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
			Manifest.permission.WRITE_EXTERNAL_STORAGE};
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


		//checking the permission
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			if (ActivityCompat.checkSelfPermission(this, Manifest.permission
					.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
						REQUEST_PERMISSION_CODE);
			}
		}


		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		
        initView();
        tvDevStatu.setText(String.valueOf(fpm.getDeviceType()));

		fpm.InitMatch();
		fpm.SetContextHandler(this, mHandler);
        fpm.SetTimeOut(Constants.TIMEOUT_LONG);
        fpm.SetLastCheckLift(true);
    }

	@SuppressLint("HandlerLeak")
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg){
		switch (msg.what){
				case Constants.FPM_DEVICE:
					switch(msg.arg1){
						case Constants.DEV_OK:
							tvFpStatu.setText("Open Device OK");
							break;
						case Constants.DEV_FAIL:
							tvFpStatu.setText("Open Device Fail");
							break;
						case Constants.DEV_ATTACHED:

							tvFpStatu.setText("USB Device Attached");
							break;
						case Constants.DEV_DETACHED:
							tvFpStatu.setText("USB Device Detached");
							break;
						case Constants.DEV_CLOSE:
							tvFpStatu.setText("Device Close");
							break;
					}
					break;
				case Constants.FPM_PLACE:
					tvFpStatu.setText("Place Finger");
					break;
				case Constants.FPM_LIFT:
					tvFpStatu.setText("Lift Finger");
					break;
				case Constants.FPM_GENCHAR:{
					if(msg.arg1==1){
						if(worktype==0){
							tvFpStatu.setText("Generate Template OK");
							matsize=fpm.GetTemplateByGen(matdata);
							Boolean matched = false;
							List<FingerPrint> storedFingerPrints = getFingerPrint();
							//displayToast();
							for (int i = 0; i < storedFingerPrints.size(); i++) {
								if (fpm.MatchTemplate(storedFingerPrints.get(i).getRefdata(), 512, matdata, matsize, 60)) {
									tvFpStatu.setText(String.format("Match OK"));
									matched = true;
									Log.d("dbsize", String.valueOf(storedFingerPrints.size()));
									displayToast();
								}
							}
							if(!matched){
								tvFpStatu.setText(String.format("Match Fail"));
							}
						}else{
							tvFpStatu.setText("Enrol Template OK");
							refsize=fpm.GetTemplateByGen(refdata);
							insertFingerPrint(refdata);
						}
					}else{
						tvFpStatu.setText("Generate Template Fail");
					}
				}
				break;
				case Constants.FPM_NEWIMAGE:{
					bmpsize=fpm.GetBmpImage(bmpdata);
					Bitmap bm1=BitmapFactory.decodeByteArray(bmpdata, 0, bmpsize);

					byte[] inpdata = new byte[73728];
					int inpsize = 73728;
					System.arraycopy(bmpdata, 1078, inpdata, 0, inpsize);
					wsq.getInstance().SaveWsqFile(inpdata,inpsize,"test.wsq");

					ivFpImage.setImageBitmap(bm1);
					bitmaps.add(bm1);
				}
				break;
				case Constants.FPM_TIMEOUT:
					tvFpStatu.setText("Time Out");
					break;
			}
		}
	};

    private void displayToast(){
		//List<FingerPrint> storedFingerPrints = getFingerPrint();
    	Toast.makeText(mContext, "Toast is working", Toast.LENGTH_LONG).show();
	}
    
	@Override
	protected void onDestroy(){
		super.onDestroy();
	}
	
    @Override
	protected void onResume() {
		super.onResume();
		fpm.ResumeRegister();
		fpm.OpenDevice();
    }

    private void insertFingerPrint( byte[] sampleRefData){

		FingerPrint fingerPrint = new FingerPrint(sampleRefData);
		FingerPrintDao fingerPrintDao = db.fingerPrintDao();
		fingerPrintDao.insertFingerPrint(fingerPrint);
        //db.clearAllTables();
	}

	private List<FingerPrint> getFingerPrint(){
		FingerPrintDao fingerPrintDao = db.fingerPrintDao();
		return fingerPrintDao.getFingerPrintList();
	}
    
    /*
	@Override
	protected void onPause() {		
		super.onPause();
		fpm.PauseUnRegister();
		fpm.CloseDevice();
	}
	*/

	@Override
	protected void onStop() {		
		super.onStop();
		fpm.PauseUnRegister();
		fpm.CloseDevice();
	}

	private void initView(){

		//Initialize room database
		db = Room.databaseBuilder(getApplicationContext(), FingerPrintsDatabase.class,
				"fingerprints_db").allowMainThreadQueries().build();
		mContext = getApplicationContext();
		
		tvDevStatu=(TextView)findViewById(R.id.textView1);
		tvFpStatu=(TextView)findViewById(R.id.textView2);
		ivFpImage=(ImageView)findViewById(R.id.imageView1);
		
		final Button btn_enrol=(Button)findViewById(R.id.button1);
		final Button btn_capture=(Button)findViewById(R.id.button2);

		btn_enrol.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v) {
				if(fpm.GenerateTemplate(2)){
					worktype=1;
				}else{
					Toast.makeText(MainActivity.this, "Busy", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
		btn_capture.setOnClickListener(new View.OnClickListener(){
			@Override			
			public void onClick(View v) {
				if(fpm.GenerateTemplate(1)){
					worktype=0;
				}else{
					Toast.makeText(MainActivity.this, "Busy", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}


}
