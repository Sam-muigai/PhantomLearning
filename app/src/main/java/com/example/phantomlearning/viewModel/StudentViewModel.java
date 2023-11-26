package com.example.phantomlearning.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.phantomlearning.model.Semester;
import com.example.phantomlearning.model.Student;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.List;
import java.util.Objects;

public class StudentViewModel extends ViewModel {

    FirebaseFirestore db;

    public MutableLiveData<Boolean> isSemester1Success = new MutableLiveData<>();
    public MutableLiveData<Boolean> isSemester2Success = new MutableLiveData<>();
    public MutableLiveData<Semester> semester1 = new MutableLiveData<>();
    public MutableLiveData<Semester> semester2 = new MutableLiveData<>();

    public MutableLiveData<Student> student = new MutableLiveData<>();

    public StudentViewModel(FirebaseFirestore db) {
        this.db = db;
    }

    public void registerSemester1Unit(
            String studentUid,
            Semester semester) {
        db.collection("students").document(studentUid)
                .collection("semester_1")
                .document("semester_1")
                .set(semester)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                isSemester1Success.postValue(true);
                            } else {
                                isSemester1Success.postValue(false);
                            }
                        }
                );
    }

    public void registerSemester2Unit(
            String studentUid,
            Semester semester) {
        db.collection("students").document(studentUid)
                .collection("semester_2")
                .document("semester_2")
                .set(semester)
                .addOnCompleteListener(
                        task -> {
                            if (task.isSuccessful()) {
                                isSemester2Success.postValue(true);
                            } else {
                                isSemester2Success.postValue(false);
                            }
                        }
                );
    }

    public void getSemester1Units(String studentUid) {
        try {

            db.collection("students").document(studentUid)
                    .collection("semester_1")
                    .document("semester_1")
                    .get()
                    .addOnCompleteListener(
                            task -> {
                                if (task.isSuccessful()) {
                                    Semester semester = task.getResult().toObject(Semester.class);
                                    semester1.postValue(semester);
                                } else {
                                    semester1.postValue(null);
                                }
                            }
                    );
        }catch (Exception e){
            e.printStackTrace();
            semester1.postValue(null);
        }
    }

    public void getSemester2Units(String studentUid) {
        try{
            db.collection("students").document(studentUid)
                    .collection("semester_2")
                    .document("semester_2")
                    .get()
                    .addOnCompleteListener(
                            task -> {
                                if (task.isSuccessful()) {
                                    Semester semester = task.getResult().toObject(Semester.class);
                                    semester2.postValue(semester);
                                } else {
                                    semester2.postValue(null);
                                }
                            }
                    );

        }catch (Exception e){
            e.printStackTrace();
            semester2.postValue(null);
        }
    }

    public void getStudentInfo(String studentUid){
        try {
            db.collection("students").document(studentUid).get().addOnCompleteListener(
                    task -> {
                        if (task.isSuccessful()){
                            Student student1 = task.getResult().toObject(Student.class);
                            student.postValue(student1);
                        }else {
                            student.postValue(null);
                        }
                    }
            );
        }catch (Exception e){
            e.printStackTrace();
            student.postValue(null);
        }
    }





}
