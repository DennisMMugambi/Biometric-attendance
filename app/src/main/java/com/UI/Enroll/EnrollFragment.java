package com.UI.Enroll;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.room.Room;

import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Dao.FingerPrintDao;
import com.Database.FingerPrintsDatabase;
import com.UI.EnrollmentDetails.EnrollmentDetailsViewModel;
import com.example.handsets.R;
import com.fgtit.data.wsq;
import com.fgtit.device.Constants;
import com.fgtit.device.FPModule;

import java.util.ArrayList;
import java.util.List;

import Model.Employee;


public class EnrollFragment extends Fragment {
    private FPModule fpm = new FPModule();

    private byte bmpdata[] = new byte[Constants.RESBMP_SIZE];
    private int bmpsize = 0;

    private byte refdata[] = new byte[Constants.TEMPLATESIZE * 2];
    private int refsize = 0;
    private int dbrefsize = 0;

    private byte matdata[] = new byte[Constants.TEMPLATESIZE * 2];
    private int matsize = 0;

    private int worktype = 0;

    private ArrayList<Bitmap> bitmaps = new ArrayList<>();

    private TextView tvDevStatu, tvFpStatu, currentFinger;
    private ImageView ivFpImage = null;

    private Context mContext;
    FingerPrintsDatabase db;

    private Button dismissDialog;

    Toolbar toolbar;
    NavController navController;

    View root;
    private int enrollCounter = 0;
    private EnrollmentDetailsViewModel viewModel;

    //dynamic setting of the permission for writing the data into phone memory
    private int REQUEST_PERMISSION_CODE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_enroll, container, false);
           /* if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission
                        .WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE,
                            REQUEST_PERMISSION_CODE);
                }
            }*/

        //Initialize viewModel
        viewModel = new ViewModelProvider(requireActivity()).get(EnrollmentDetailsViewModel.class);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        initView();
        tvDevStatu.setText(String.valueOf(fpm.getDeviceType()));

        fpm.InitMatch();
        fpm.SetContextHandler(requireContext(), mHandler);
        fpm.SetTimeOut(Constants.TIMEOUT_LONG);
        fpm.SetLastCheckLift(true);
        return root;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case Constants.FPM_DEVICE:
                    switch (msg.arg1) {
                        case Constants.DEV_OK:
                            tvFpStatu.setText("Open Device OK");
                            //initializeEnrollment();
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
                case Constants.FPM_GENCHAR: {
                    if (msg.arg1 == 1) {
                        if (worktype == 0) {
                            tvFpStatu.setText("Generate Template OK");
                            matsize = fpm.GetTemplateByGen(matdata);
                            Boolean matched = false;
                            List<Employee> storedFingerPrints = getFingerPrint();
                            //displayToast();
                            for (int i = 0; i < storedFingerPrints.size(); i++) {
                              /*  if (fpm.MatchTemplate(storedFingerPrints.get(i).getFingerprints(), 512, matdata, matsize, 60)) {
                                    tvFpStatu.setText(String.format("Match OK"));
                                    matched = true;
                                    Log.d("dbsize", String.valueOf(storedFingerPrints.size()));
                                    displayToast();
                                } */
                            }
                            if (!matched) {
                                tvFpStatu.setText(String.format("Match Fail"));
                            }
                        } else {
                            tvFpStatu.setText("Enrol Template OK");
                            refsize = fpm.GetTemplateByGen(refdata);
                            //insertFingerPrint(refdata);
                            //navigate to different fragment
                            if(enrollCounter <= 3){
                                captureFingerPrint();
                                if(enrollCounter == 0){
                                    viewModel.setRightThumb(refdata);
                                } else if(enrollCounter == 1){
                                    viewModel.setRightIndex(refdata);
                                } else if(enrollCounter == 2){
                                    viewModel.setLeftThumb(refdata);
                                } else if(enrollCounter == 3){
                                    viewModel.setLeftIndex(refdata);
                                }
                                enrollCounter+=1;
                            } else {
                                View v = root.findViewById(R.id.enroll_layout);
                                Navigation.findNavController(v).navigate(R.id.enrollmentDetailsFragment);
                            }
                        }
                    } else {
                        tvFpStatu.setText("Generate Template Fail");
                    }
                }
                break;
                case Constants.FPM_NEWIMAGE: {
                    bmpsize = fpm.GetBmpImage(bmpdata);
                    Bitmap bm1 = BitmapFactory.decodeByteArray(bmpdata, 0, bmpsize);

                    byte[] inpdata = new byte[73728];
                    int inpsize = 73728;
                    System.arraycopy(bmpdata, 1078, inpdata, 0, inpsize);
                    wsq.getInstance().SaveWsqFile(inpdata, inpsize, "test.wsq");

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

    private void displayToast() {
        //List<FingerPrint> storedFingerPrints = getFingerPrint();
        Toast.makeText(mContext, "Toast is working", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onResume() {
        super.onResume();
        fpm.ResumeRegister();
        fpm.OpenDevice();
    }

    private void insertFingerPrint(byte[] sampleRefData) {

		/*Employee employee = new Employee(sampleRefData);
		FingerPrintDao fingerPrintDao = db.fingerPrintDao();
		fingerPrintDao.insertFingerPrint(employee);
        //db.clearAllTables();*/
    }

    private List<Employee> getFingerPrint() {
        FingerPrintDao fingerPrintDao = db.fingerPrintDao();
        return fingerPrintDao.getEmployeeList();
    }


    @Override
    public void onPause() {
        super.onPause();
        fpm.PauseUnRegister();
        fpm.CloseDevice();
    }


    @Override
    public void onStop() {
        super.onStop();
        //fpm.PauseUnRegister();
        fpm.CloseDevice();
    }

    private void initView() {

        //Initialize room database
        db = Room.databaseBuilder(requireContext(), FingerPrintsDatabase.class,
                "fingerprints_db").allowMainThreadQueries().build();

        tvDevStatu = (TextView) root.findViewById(R.id.textView1);
        //showDialog();
        tvDevStatu = (TextView) root.findViewById(R.id.textView1);
        tvFpStatu = (TextView) root.findViewById(R.id.textView2);
        ivFpImage = (ImageView) root.findViewById(R.id.imageView1);
        currentFinger = root.findViewById(R.id.current_finger);


        final Button btn_enrol = (Button) root.findViewById(R.id.button1);
        //final Button btn_capture=(Button)findViewById(R.id.button2);

        btn_enrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  captureFingerPrint();
            }
        });

    }

    private void captureFingerPrint(){
        if (fpm.GenerateTemplate(2)) {
            worktype = 1;
        } else {
            Toast.makeText(requireContext(), "Busy", Toast.LENGTH_SHORT).show();
        }

        if(enrollCounter == 0){
            currentFinger.setText(R.string.right_thumb);
        } else if ( enrollCounter == 1){
            currentFinger.setText(R.string.right_index);
        } else if (enrollCounter == 2){
            currentFinger.setText(R.string.left_thumb);
        } else if (enrollCounter == 3){
            currentFinger.setText(R.string.left_index);
        }
    }
}