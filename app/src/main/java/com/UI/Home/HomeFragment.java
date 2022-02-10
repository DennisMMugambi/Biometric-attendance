package com.UI.Home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.StrictMode;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.room.Room;

import com.Dao.FingerPrintDao;
import com.Database.FingerPrintsDatabase;
import com.Helpers.SignInDialogHelper;
import com.UI.Admin.AdminFragment;
import com.UI.AttendanceHistory.AttendanceHistoryFragment;
import com.UI.EnrollmentDetails.EnrollmentDetailsViewModel;
import com.Utils.DateTime;
import com.example.handsets.R;
import com.fgtit.data.wsq;
import com.fgtit.device.Constants;
import com.fgtit.device.FPModule;
import com.google.android.material.snackbar.Snackbar;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.zip.Inflater;

import Model.Attendance;
import Model.Employee;

public class HomeFragment extends Fragment {
    View root;
    private Button mSignIn, mSignOut, mEnroll;
    private Context mContext;
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

    FingerPrintsDatabase db;

    private Button dismissDialog;

    Toolbar toolbar;
    NavController navController;

    private int enrollCounter = 0;
    private EnrollmentDetailsViewModel viewModel;

    private TextView userName, time;

    private SignInDialogHelper mSignInDialogHelper;

    private String date , dtime, name;
    private Thread thread;

    //dynamic setting of the permission for writing the data into phone memory
    private int REQUEST_PERMISSION_CODE = 1;
    private static String[] PERMISSIONS_STORAGE = {Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    Boolean matched;
    String phone_number;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());

        viewModel = new ViewModelProvider(requireActivity()).get(EnrollmentDetailsViewModel.class);

        db = Room.databaseBuilder(requireContext(), FingerPrintsDatabase.class,
                "fingerprints_db").allowMainThreadQueries().fallbackToDestructiveMigration().build();


        InitViews();
        tvDevStatu.setText(String.valueOf(fpm.getDeviceType()));
        fpm.InitMatch();
        fpm.SetContextHandler(requireContext(), mHandler);
        fpm.SetTimeOut(Constants.TIMEOUT_LONG);
        fpm.SetLastCheckLift(true);
        return root;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @RequiresApi(api = Build.VERSION_CODES.O)
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
                            getFingerPrint();
                        } else {
                            tvFpStatu.setText("Enrol Template OK");
                            refsize = fpm.GetTemplateByGen(refdata);
                            //insertFingerPrint(refdata);
                            //navigate to different fragment
                            if(enrollCounter <= 3){
                                captureFingerPrint();
                                enrollCounter+=1;
                                if(enrollCounter == 0){
                                    viewModel.setRightThumb(refdata);
                                } else if(enrollCounter == 1){
                                    viewModel.setRightIndex(refdata);
                                } else if(enrollCounter == 2){
                                    viewModel.setLeftThumb(refdata);
                                } else if(enrollCounter == 3){
                                    viewModel.setLeftIndex(refdata);
                                }
                                //enrollCounter+=1;
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


    private void InitViews(){
        mSignIn = root.findViewById(R.id.sign_in);
        mSignOut = root.findViewById(R.id.sign_out);
        mEnroll = root.findViewById(R.id.enroll);
        mContext = root.getContext();

        tvDevStatu = (TextView) root.findViewById(R.id.textView1);
        //showDialog();
        tvDevStatu = (TextView) root.findViewById(R.id.textView1);
        tvFpStatu = (TextView) root.findViewById(R.id.textView2);
        ivFpImage = (ImageView) root.findViewById(R.id.imageView1);
        currentFinger = root.findViewById(R.id.current_finger);


        mEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setViewToInvisible();
                    captureFingerPrint();
            }
        });

        mSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewToInvisible();
                matchFingerPrint();
            }
        });

        mSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewToInvisible();
                matchFingerPrint();
            }
        });
    }

    private void setViewToInvisible(){
        mSignIn.setVisibility(View.INVISIBLE);
        mSignOut.setVisibility(View.INVISIBLE);
        mEnroll.setVisibility(View.INVISIBLE);
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

    private void matchFingerPrint(){
        if(fpm.GenerateTemplate(1)){
            worktype=0;
        }else{
            Toast.makeText(requireContext(), "Busy", Toast.LENGTH_SHORT).show();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getFingerPrint() {

                FingerPrintDao fingerPrintDao = db.fingerPrintDao();
                matched = false;
                List<Employee> storedFingerPrints = fingerPrintDao.getEmployeeList();
                Snackbar.make(requireView(), String.valueOf(fingerPrintDao.getEmployeeList().size()), Snackbar.LENGTH_LONG).show();
                List<byte[]> fingerprints = new ArrayList<>();
                //displayToast();
                for (int i = 0; i < storedFingerPrints.size(); i++) {
                    fingerprints.add(storedFingerPrints.get(i).getRightThumb());
                    fingerprints.add(storedFingerPrints.get(i).getRightIndex());
                    fingerprints.add(storedFingerPrints.get(i).getLeftThumb());
                    fingerprints.add(storedFingerPrints.get(i).getLeftIndex());
                    for (int m = 0; m < fingerprints.size(); m++) {
                        if (fpm.MatchTemplate(fingerprints.get(i), 512, matdata, matsize, 60)) {
                            //tvFpStatu.setText(String.format("Match OK"));
                            name = storedFingerPrints.get(i).getEmployeeName();
                            phone_number = storedFingerPrints.get(i).getPhone_number();
                            //showSignInDialog(name);
                            matched = true;
                            queryResult();
                        }
                    }
                }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void queryResult(){
        thread = null;
        if (matched){
            tvFpStatu.setText(String.format("Match OK"));
            showSignInDialog(name, phone_number);
        } else {
            tvFpStatu.setText(String.format("Match Fail"));
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showSignInDialog(String employeeName, String number){
        //Format date time Values and insert to db

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        date = dtf.format(now);
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("kk:mm:ss");
        dtime = dtf.format(now);
        Attendance attendance = new Attendance(employeeName, date, dtime);

        Executor executor = Executors.newSingleThreadExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                boolean exists = db.attendanceDao().exists(employeeName);

                if(exists){
                 db.attendanceDao().updateAttendance(attendance);
                } else {
                    db.attendanceDao().insertAttendance(attendance);
                }
            }
        });

        Snackbar.make(requireView(), R.string.sign_in_noted, Snackbar.LENGTH_LONG).show();

        mSignInDialogHelper = new SignInDialogHelper(requireContext());
        //Toast.makeText(requireContext(), String.valueOf(mLocation.getLatitude()) + " | " + String.valueOf(mLocation.getLongitude()), Toast.LENGTH_SHORT).show();
        mSignInDialogHelper.call();

        String message = employeeName + " " + R.string.sign_in_message + " " + dtime + " on " + date;
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone_number, null, message, null, null);
        } catch (Exception e){
            Toast.makeText(requireContext(), e.toString(), Toast.LENGTH_SHORT).show();
        }
    }
    private void showSignOutDialog(){
        mSignInDialogHelper = new SignInDialogHelper(requireContext());
        //Toast.makeText(requireContext(), String.valueOf(mLocation.getLatitude()) + " | " + String.valueOf(mLocation.getLongitude()), Toast.LENGTH_SHORT).show();
        mSignInDialogHelper.call();
    }
}