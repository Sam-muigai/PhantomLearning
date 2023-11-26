package com.example.phantomlearning;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavHostController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.phantomlearning.databinding.FragmentOnBoardingBinding;
import com.example.phantomlearning.viewModel.AuthViewModel;
import com.example.phantomlearning.viewModel.AuthViewModelFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class OnBoardingFragment extends Fragment {
    FragmentOnBoardingBinding binding;

    AuthViewModel authViewModel;

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
        binding = FragmentOnBoardingBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("AUTH_CUR", String.valueOf(authViewModel.isSignedIn));

//        if (authViewModel.isSignedIn){
//            NavHostFragment.findNavController(OnBoardingFragment.this).navigate(
//                    R.id.action_onBoard_to_studentHomeFragment
//            );
//        }

        binding.signUp.setOnClickListener(
                view1 -> {
                    NavHostFragment.findNavController(OnBoardingFragment.this)
                            .navigate(R.id.action_onBoard_to_continueAsFragment);
                }
        );
    }
}