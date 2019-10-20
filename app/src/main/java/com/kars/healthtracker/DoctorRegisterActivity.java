package com.kars.healthtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorRegisterActivity extends AppCompatActivity {
    private EditText edtEmail, edtPassword, edtRegisteredNumber;
    private Button btnCreateAct;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);
        edtEmail = findViewById(R.id.edtDoctorEmail);
        edtPassword = findViewById(R.id.edtDoctorPassword);
        edtRegisteredNumber = findViewById(R.id.edtDoctorRegisteredNumber);
        btnCreateAct = findViewById(R.id.btnCreateDoctorAct);
        mAuth = FirebaseAuth.getInstance();
        btnCreateAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
