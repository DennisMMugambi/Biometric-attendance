package com.UI.AttendanceHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.handsets.R;


public class AttendanceHistoryFragment extends Fragment {
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_attendance_history, container, false);
        CalendarView myCalendar = root.findViewById(R.id.calendarView);
        v = root.findViewById(R.id.attendance_layout);

        CalendarView.OnDateChangeListener myCalendarListener = new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Navigation.findNavController(v).navigate(R.id.selectedDateAttendanceFragment);
            }
        };

        myCalendar.setOnDateChangeListener(myCalendarListener);
        return root;
    }
}