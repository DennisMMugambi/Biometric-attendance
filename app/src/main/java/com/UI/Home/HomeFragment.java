package com.UI.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.UI.Admin.AdminFragment;
import com.UI.AttendanceHistory.AttendanceHistoryFragment;
import com.UI.Enroll.EnrollFragment;
import com.example.handsets.R;

public class HomeFragment extends Fragment {
    View root;
    private Button mSignIn, mSignOut, mEnroll;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        InitViews();
        return root;
    }


    private void InitViews(){
        mSignIn = root.findViewById(R.id.sign_in);
        mSignOut = root.findViewById(R.id.sign_out);
        mEnroll = root.findViewById(R.id.enroll);
        mContext = root.getContext();

        mEnroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Navigation.findNavController(v).navigate(R.id.enrollFragment);
            }
        });
    }

    /*private void navigateToActivity(Context context, final Class<? extends Fragment> ActivityToOpen){
        Intent intent = new Intent(context, ActivityToOpen);
        context.startActivity(intent);
    }*/
}