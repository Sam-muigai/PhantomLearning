package com.example.phantomlearning.viewModel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.firestore.FirebaseFirestore;

public class LecturerViewModelFactory  implements ViewModelProvider.Factory {

    private final FirebaseFirestore db;

    public LecturerViewModelFactory(FirebaseFirestore db) {
        this.db = db;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LecturerViewModel.class)) {
            return (T) new LecturerViewModel(db);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}