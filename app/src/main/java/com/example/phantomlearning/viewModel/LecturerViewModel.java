package com.example.phantomlearning.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phantomlearning.model.Lecturer;
import com.google.firebase.firestore.FirebaseFirestore;

public class LecturerViewModel extends ViewModel {

    FirebaseFirestore db;

    public MutableLiveData<Boolean> isSuccessful = new MutableLiveData<>();

    public LecturerViewModel(FirebaseFirestore db){
        this.db = db;
    }



    public void registerLecturer(
            String studentUid,
            Lecturer lecturer
    ){
        try{
            db.collection("lecturer").document(studentUid).set(
                    lecturer
            ).addOnCompleteListener(
                    task -> {
                        if (task.isSuccessful()){
                            isSuccessful.postValue(true);
                        }else {
                            isSuccessful.postValue(false);
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            isSuccessful.postValue(false);
        }
    }

}
