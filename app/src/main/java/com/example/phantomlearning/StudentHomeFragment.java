package com.example.phantomlearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.phantomlearning.databinding.FragmentStudentHomeBinding;
import com.example.phantomlearning.viewModel.StudentViewModel;
import com.example.phantomlearning.viewModel.StudentViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentHomeFragment extends Fragment {
    FragmentStudentHomeBinding binding;

    FirebaseAuth auth;
    FirebaseFirestore db;
    StudentViewModel studentViewModel;
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
        binding = FragmentStudentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.pbSem1Loading.setVisibility(View.VISIBLE);
        binding.llSem1.setVisibility(View.GONE);
        studentViewModel.getSemester1Units(auth.getUid());

        binding.pbSem2Loading.setVisibility(View.VISIBLE);
        binding.llSem2.setVisibility(View.GONE);
        studentViewModel.getSemester2Units(auth.getUid());

        binding.welcome.setVisibility(View.GONE);
        binding.studentInfoLoading.setVisibility(View.VISIBLE);
        studentViewModel.getStudentInfo(auth.getUid());


        studentViewModel.semester1.observe(getViewLifecycleOwner(),semester -> {
            if (semester != null){
                binding.pbSem1Loading.setVisibility(View.GONE);
                binding.llSem1.setVisibility(View.VISIBLE);

                binding.btnCourse1.setText(semester.getCourse1());
                binding.btnCourse2.setText(semester.getCourse2());
                binding.btnCourse3.setText(semester.getCourse3());
                binding.btnCourse4.setText(semester.getCourse4());
                binding.btnCourse5.setText(semester.getCourse5());
            }else {
                binding.pbSem1Loading.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Failed to fetch the subject",Toast.LENGTH_SHORT).show();
            }
        });

        studentViewModel.semester2.observe(getViewLifecycleOwner(),semester -> {
            if (semester != null){
                binding.pbSem2Loading.setVisibility(View.GONE);
                binding.llSem2.setVisibility(View.VISIBLE);

                binding.btnCourse6.setText(semester.getCourse1());
                binding.btnCourse7.setText(semester.getCourse2());
                binding.btnCourse8.setText(semester.getCourse3());
                binding.btnCourse9.setText(semester.getCourse4());
                binding.btnCourse10.setText(semester.getCourse5());
            }else {
                binding.pbSem2Loading.setVisibility(View.GONE);
                Toast.makeText(getContext(),"Failed to fetch the courses",Toast.LENGTH_SHORT).show();
            }
        });

        studentViewModel.student.observe(getViewLifecycleOwner(),student -> {
            if (student != null){
                String welcomeMessage = "Hello " + student.getUserName();
                binding.welcome.setVisibility(View.VISIBLE);
                binding.studentInfoLoading.setVisibility(View.GONE);
                binding.welcome.setText(welcomeMessage);
            }else {
                Toast.makeText(getContext(),"Failed to fetch user info",Toast.LENGTH_SHORT).show();
            }
        });
    }
}