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

import com.example.phantomlearning.databinding.FragmentLecturerSignUpBinding;
import com.example.phantomlearning.model.Lecturer;
import com.example.phantomlearning.model.Student;
import com.example.phantomlearning.viewModel.AuthViewModel;
import com.example.phantomlearning.viewModel.AuthViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class LecturerSignUpFragment extends Fragment {

    FragmentLecturerSignUpBinding binding;
    AuthViewModel authViewModel;
    String email;
    String password;
    Lecturer lecturer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        AuthViewModelFactory authViewModelFactory = new AuthViewModelFactory(auth, db);
        authViewModel = new ViewModelProvider(this, authViewModelFactory).get(AuthViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLecturerSignUpBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.lecturerRegisterButton.setOnClickListener(
                view1 -> {
                    email = binding.lecturerEmail.getText().toString();
                    password = binding.lecturerPassword.getText().toString();
                    lecturer = new Lecturer(
                            binding.lecturerName.getText().toString(),
                            binding.lecturerCourse.getText().toString(),
                            binding.lecturerEmail.getText().toString()
                    );
                    binding.pbIsLoading.setVisibility(View.VISIBLE);
                    binding.lecturerRegisterButton.setVisibility(View.GONE);
                    signUpUsers();
                }
        );

        authViewModel.isSignUpSuccess.observe(getViewLifecycleOwner(), isSignUpSuccess -> {
            if (isSignUpSuccess) {
                Toast.makeText(getContext(), "Sign up successfully", Toast.LENGTH_SHORT).show();
            } else {
                binding.pbIsLoading.setVisibility(View.GONE);
                binding.lecturerRegisterButton.setVisibility(View.VISIBLE);
                Toast.makeText(getContext(), "Sign up failed", Toast.LENGTH_SHORT).show();
            }
        });
        authViewModel.isRegistrationSuccessful.observe(getViewLifecycleOwner(), isRegistrationSuccess -> {
            if (isRegistrationSuccess) {
                Toast.makeText(getContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                NavHostFragment.findNavController(LecturerSignUpFragment.this).navigate(R.id.action_signUp_to_dashboard);
            } else {
                binding.pbIsLoading.setVisibility(View.GONE);
                binding.lecturerRegisterButton.setVisibility(View.VISIBLE);
            }
        });
    }

    void signUpUsers() {
        if (!email.isEmpty() || !password.isEmpty()) {
            authViewModel.signUpLecturer(email, password, lecturer);
        }
    }
}