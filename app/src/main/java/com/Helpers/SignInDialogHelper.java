package com.Helpers;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

import androidx.appcompat.app.AlertDialog;

import com.UI.Dialogs.SignInDialog;
import com.Utils.TimeWriter;

import java.util.Date;
import java.util.Timer;

public class SignInDialogHelper {
    public static final int ATTENDANCE_SUCCESS_DIALOG = 4000;
    private static final String TAG = SignInDialogHelper.class.getSimpleName();

    private TimeWriter mTimeWriter = new TimeWriter();
    //    private CurrentDateWriter mDateWriter = new CurrentDateWriter();
    private Context mContext;

    public SignInDialogHelper(Context context){
        this.mContext = context;
    }

    public void call() {
        SignInDialog builder = new SignInDialog(mContext);

        Date date = new Date();
        String currTime = mTimeWriter.getText(date);
//        String currDate = mDateWriter.getText(date);
        builder.setCurrTime(currTime);

        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        SuccessWindowManager layoutParams = new SuccessWindowManager(mContext);
        alertDialog.getWindow().setAttributes(layoutParams);

        final Handler handler  = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (alertDialog.isShowing()) {
                    alertDialog.cancel();
                }
            }
        };

        alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                handler.removeCallbacks(runnable);
            }
        });

        handler.postDelayed(runnable, ATTENDANCE_SUCCESS_DIALOG);
    }
}
