package com.example.phantomlearning.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class AuthViewModelFactory implements ViewModelProvider.Factory {
    private FirebaseAuth auth;
    private FirebaseFirestore db;

    public AuthViewModelFactory (FirebaseAuth auth,FirebaseFirestore db) {
        this.auth = auth;
        this.db = db;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            return (T) new AuthViewModel(auth, db);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
