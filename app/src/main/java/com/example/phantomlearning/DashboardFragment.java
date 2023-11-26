package com.example.phantomlearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phantomlearning.databinding.FragmentDashboardBinding;
import com.example.phantomlearning.model.Semester;
import com.example.phantomlearning.viewModel.StudentViewModel;
import com.example.phantomlearning.viewModel.StudentViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class DashboardFragment extends Fragment {

    FragmentDashboardBinding binding;
    StudentViewModel studentViewModel;

    FirebaseAuth auth;

    FirebaseFirestore db;
    Semester semester;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        StudentViewModelFactory studentViewModelFactory = new StudentViewModelFactory(db);
        studentViewModel = new ViewModelProvider(this, studentViewModelFactory).get(StudentViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentViewModel.isSemester1Success.observe(getViewLifecycleOwner(), isSuccessful -> {
                    if (isSuccessful) {
                        binding.rlSem2.setVisibility(View.VISIBLE);
                        binding.rlSem1.setVisibility(View.GONE);
                        binding.pbSem1Loading.setVisibility(View.GONE);
                        binding.registerSem1.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Is successful", Toast.LENGTH_SHORT).show();
                    } else {
                        binding.pbSem1Loading.setVisibility(View.GONE);
                        binding.registerSem1.setVisibility(View.VISIBLE);
                        Toast.makeText(getContext(), "Failed to register", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        studentViewModel.isSemester2Success.observe(getViewLifecycleOwner(), isSuccessful -> {
            if (isSuccessful) {
                binding.pbSem2Loading.setVisibility(View.GONE);
                binding.registerSem2.setVisibility(View.VISIBLE);
                NavHostFragment.findNavController(DashboardFragment.this).navigate(R.id.action_dashboard_to_studentHomeFragment);
            } else {
                binding.pbSem2Loading.setVisibility(View.GONE);
                binding.registerSem2.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Failed to register", Toast.LENGTH_SHORT).show();
            }
        });

        binding.registerSem1.setOnClickListener(
                v -> {
                    registerSemester1Courses();
                }
        );

        binding.registerSem2.setOnClickListener(
                v -> {
                    registerSemester2Courses();
                }
        );
    }

    public void registerSemester1Courses() {
        binding.pbSem1Loading.setVisibility(View.VISIBLE);
        binding.registerSem1.setVisibility(View.GONE);
        semester = new Semester(
                binding.course1.getText().toString(),
                binding.course2.getText().toString(),
                binding.course3.getText().toString(),
                binding.course4.getText().toString(),
                binding.course5.getText().toString()
        );
        studentViewModel.registerSemester1Unit(auth.getUid(), semester);
    }

    public void registerSemester2Courses() {
        binding.pbSem2Loading.setVisibility(View.VISIBLE);
        binding.registerSem2.setVisibility(View.GONE);
        semester = new Semester(
                binding.course6.getText().toString(),
                binding.course7.getText().toString(),
                binding.course8.getText().toString(),
                binding.course9.getText().toString(),
                binding.course10.getText().toString()
        );
        studentViewModel.registerSemester2Unit(auth.getUid(), semester);
    }


}