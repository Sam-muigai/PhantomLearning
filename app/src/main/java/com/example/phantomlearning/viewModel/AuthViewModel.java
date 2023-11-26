package com.example.phantomlearning.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phantomlearning.model.Lecturer;
import com.example.phantomlearning.model.Student;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class AuthViewModel extends ViewModel {

    private final FirebaseAuth auth;
    private final FirebaseFirestore db;

    public Boolean isSignedIn;
    public MutableLiveData<Boolean> isSignUpSuccess = new MutableLiveData<>();
    public MutableLiveData<Boolean> isRegistrationSuccessful = new MutableLiveData<>();

    public AuthViewModel(FirebaseAuth auth, FirebaseFirestore db) {
        this.auth = auth;
        this.db = db;
        isSignedIn = auth.getCurrentUser() != null;
    }


    public void signUpStudent(String email,
                              String password,
                              Student student) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        isSignUpSuccess.postValue(true);
                        registerStudent(student);
                    } else {
                        isSignUpSuccess.postValue(false);
                    }
                }
        );
    }

    public void signUpLecturer(String email,
                        String password,
                        Lecturer lecturer) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        isSignUpSuccess.postValue(true);
                        registerLecturer(lecturer);
                    } else {
                        isSignUpSuccess.postValue(false);
                    }
                }
        );
    }


    private void registerStudent(Student student) {
        db.collection("students").document(Objects.requireNonNull(auth.getCurrentUser()).getUid()).set(student).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        isRegistrationSuccessful.postValue(true);
                    } else {
                        isRegistrationSuccessful.postValue(false);
                    }
                }
        );
    }

    private void registerLecturer(Lecturer lecturer) {
        db.collection("lecturer").document(lecturer.getUserEmail()).set(lecturer).addOnCompleteListener(
                task -> {
                    if (task.isSuccessful()) {
                        isRegistrationSuccessful.postValue(true);
                    } else {
                        isRegistrationSuccessful.postValue(false);
                    }
                }
        );
    }
}
