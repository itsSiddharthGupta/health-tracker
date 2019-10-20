package com.kars.healthtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class PatientActivity extends AppCompatActivity {
    TextView  txtname, txtage, txtgender, txtoccupation, txtreligion, txtmaritalStatus, txtprescription, txtpresentingComplain, txtfinalDiagnosis;
    FirebaseDatabase mDatabase ;
    DatabaseReference myRef, userReportRef;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_case);
        txtname = findViewById(R.id.txtName);
        txtage = findViewById(R.id.txtAge);
        txtgender = findViewById(R.id.txtGender);
        txtoccupation = findViewById(R.id.txtOccupation);
        txtreligion = findViewById(R.id.txtReligion);
        txtmaritalStatus = findViewById(R.id.txtMaritalStatus);
        txtprescription = findViewById(R.id.txtPrescription);
        txtpresentingComplain = findViewById(R.id.txtPresentingComplain);
        txtfinalDiagnosis = findViewById(R.id.txtFinalDiagnosis);
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        userReportRef = myRef.child("user100");
        userReportRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> userVal = (Map<String, Object>) dataSnapshot.getValue();
                txtage.setText(userVal.get("age") + "");
                txtfinalDiagnosis.setText(userVal.get("finalDiagnosis") + "");
                txtgender.setText(userVal.get("gender")+"");
                txtmaritalStatus.setText(userVal.get("maritalStatus") + "");
                txtname.setText(userVal.get("name")+"");
                txtoccupation.setText(userVal.get("occupation") + "");
                txtreligion.setText(userVal.get("religion") + "");
                txtpresentingComplain.setText(userVal.get("presentingComplain") + "");
                txtfinalDiagnosis.setText(userVal.get("finalDiagnosis") + "");
                txtprescription.setText(userVal.get("prescription")+"");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
