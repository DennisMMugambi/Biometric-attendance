package com.Helpers;

import android.content.Context;
import android.view.WindowManager;

import com.example.handsets.R;

public class SuccessWindowManager extends WindowManager.LayoutParams {
    private Context mContext;

    public SuccessWindowManager(Context context){
        this.mContext = context;
        this.width = (int)mContext.getResources().getDimension(R.dimen.size_card_staff_info_width);
        this.height = (int)mContext.getResources().getDimension(R.dimen.size_card_staff_info_height);
    }
}
