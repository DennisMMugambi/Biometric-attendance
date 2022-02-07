package com.UI.EnrollmentDetails;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Database.FingerPrintsDatabase;
import com.example.handsets.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import Model.Employee;


public class EnrollmentDetailsFragment extends Fragment {
    private TextInputEditText firstName, secondName, age,
    jobPosition, id;
    private View root;
    private Button
            saveDetails;
    EnrollmentDetailsViewModel viewModel;
    FingerPrintsDatabase db;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_enrollment_details, container, false);

        //Initialize Views
        InitViews();

        //Launch Camera Intent
        //getRoomDatabaseReference and Insert Values

        db = Room.databaseBuilder(requireContext(), FingerPrintsDatabase.class,
                "fingerprints_db") .build();

        //getViewModel to fetch data
        viewModel = new ViewModelProvider(requireActivity()).get(EnrollmentDetailsViewModel.class);
        return root;
    }

    private void InitViews(){
        v = root.findViewById(R.id.enrollmentDetailsLayout);
        firstName = root.findViewById(R.id.edt_first_name);
        secondName = root.findViewById(R.id.edt_second_name);
        age = root.findViewById(R.id.edt_age);
        jobPosition = root.findViewById(R.id.edt_position);
        id = root.findViewById(R.id.edt_id);
        saveDetails = root.findViewById(R.id.save_details);
        saveDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDetails();
            }
        });
    }

    private void saveDetails(){
        if(Objects.requireNonNull(firstName.getText()).toString().isEmpty()){
            Snackbar.make(requireView(), R.string.name_empty, Snackbar.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(secondName.getText()).toString().isEmpty()){
            Snackbar.make(requireView(), R.string.second_name_empty, Snackbar.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(age.getText()).toString().isEmpty()){
            Snackbar.make(requireView(), R.string.age_empty, Snackbar.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(jobPosition.getText()).toString().isEmpty()){
            Snackbar.make(requireView(), R.string.position_empty, Snackbar.LENGTH_SHORT).show();
        } else if (Objects.requireNonNull(id.getText()).toString().isEmpty()){
            Snackbar.make(requireView(), R.string.id_empty, Snackbar.LENGTH_SHORT).show();
        } else {
            String fullname = firstName.getText().toString() + " " + secondName.getText().toString();
            Employee employee = new Employee(viewModel.getRightThumb(), viewModel.getRightIndex(), viewModel.getLeftThumb(),
                    viewModel.getLeftIndex(), fullname, Integer.parseInt(age.getText().toString()), jobPosition.getText().toString(), id.getText().toString());

            Executor executor = Executors.newSingleThreadExecutor();
            executor.execute(() -> db.fingerPrintDao().insertEmployee(employee));
            Navigation.findNavController(v).navigate(R.id.homeFragment);
        }
    }
}