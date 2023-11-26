package com.example.phantomlearning.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class StudentViewModelFactory implements ViewModelProvider.Factory {

    private FirebaseFirestore db;

    public StudentViewModelFactory(FirebaseFirestore db) {
        this.db = db;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(StudentViewModel.class)) {
            return (T) new StudentViewModel(db);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
