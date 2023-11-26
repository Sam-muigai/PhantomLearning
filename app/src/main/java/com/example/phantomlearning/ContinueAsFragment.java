package com.example.phantomlearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phantomlearning.databinding.FragmentContinueAsBinding;


public class ContinueAsFragment extends Fragment {
    FragmentContinueAsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentContinueAsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnStudent.setOnClickListener(
                v -> {
                    NavHostFragment.findNavController(ContinueAsFragment.this)
                            .navigate(R.id.action_continueAsFragment_to_signUp);
                }
        );

        binding.btnLecturer.setOnClickListener(
                v -> {
                    NavHostFragment.findNavController(ContinueAsFragment.this)
                            .navigate(R.id.action_continueAsFragment_to_lecturerSignUpFragment);
                }
        );
    }
}