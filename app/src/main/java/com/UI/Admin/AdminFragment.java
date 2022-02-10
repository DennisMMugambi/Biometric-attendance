package com.UI.Admin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.Database.FingerPrintsDatabase;
import com.example.handsets.R;

import java.util.ArrayList;

import Model.Employee;


public class AdminFragment extends Fragment {
    SearchView searchRegistree;
    ListAdapter listAdapter;
    ArrayAdapter arrayAdapter;
    ListView listView;
    ArrayList<Employee> employees;
    ArrayList<String> studentNames;
    FingerPrintsDatabase db;
    TextView noResult;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_admin, container, false);
        searchRegistree = root.findViewById(R.id.search_student);
        searchRegistree.setIconifiedByDefault(false);
        searchRegistree.setQueryHint("Search students");
        listView = root.findViewById(R.id.student_list);
        noResult = root.findViewById(R.id.no_result);

        db = Room.databaseBuilder(requireContext(), FingerPrintsDatabase.class,
                "fingerprints_db").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        CharSequence queryHint = searchRegistree.getQueryHint();
        employees = new ArrayList<>();
        studentNames = new ArrayList<>();
        searchRegistree.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Toast.makeText(requireContext(), "Query is running", Toast.LENGTH_LONG).show();
                employees = (ArrayList<Employee>) (ArrayList<Employee>) db.fingerPrintDao().getSearchedEmployees(query);
                if(employees.size() > 0){
                    for (int i = 0; i < employees.size() ; i++) {
                        studentNames.add(employees.get(i).getEmployeeName());
                        ArrayAdapter<String> names = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, studentNames);
                        listView.setAdapter(names);
                    }
                } else {
                    noResult.setVisibility(View.VISIBLE);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return root;
    }
}