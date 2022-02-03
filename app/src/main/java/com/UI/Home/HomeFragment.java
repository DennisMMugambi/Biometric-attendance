package com.UI.Home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

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
        // Inflate the layout for this fragment
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
                Intent intent = new Intent(mContext, EnrollFragment.class);
                startActivity(intent);
            }
        });
    }

    //Get menu for appbar
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater = requireActivity().getMenuInflater();
        inflater.inflate(R.menu.main, menu);
    }

    //standard function to set actions for different menu items
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                //Do nothing for now

                return true;
            case R.id.action_history:
                //showHelp();
                //navigate to attendance history fragment
                //navigateToActivity(mContext, AttendanceHistoryFragment.class);
                //navigateToActivity(mContext, AttendanceHistoryFragment.class);
                Intent intent = new Intent(mContext, AttendanceHistoryFragment.class);
                startActivity(intent);
                return true;
            case R.id.action_admin:
                //navigate to admin fragment
                //navigateToActivity(mContext, AdminFragment.class);
                Intent intent2 = new Intent(mContext, AdminFragment.class);
                startActivity(intent2);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /*private void navigateToActivity(Context context, final Class<? extends Fragment> ActivityToOpen){
        Intent intent = new Intent(context, ActivityToOpen);
        context.startActivity(intent);
    }*/
}