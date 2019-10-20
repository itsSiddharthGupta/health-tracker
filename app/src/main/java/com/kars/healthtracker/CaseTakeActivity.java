package com.kars.healthtracker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class CaseTakeActivity extends AppCompatActivity {
    EditText edtName, edtAge, edtGender, edtOccupation, edtPrescription, edtReligion, edtPresentingComplain, edtFinalDiagnosis;
    RadioGroup radioGroupMaritalStatus;
    RadioButton radioBtnMarried, radioBtnUnmarried;
    ListView rvHistoryPresentingComplain;
    Button btnSave;
    FirebaseDatabase mDatabase ;
    DatabaseReference myRef;
    DatabaseReference userReportRef;
    ArrayAdapter<String> adapter;
    ArrayList<String> historyPreComplain;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_take);
        edtName = findViewById(R.id.edtName);
        edtAge = findViewById(R.id.edtAge);
        edtGender = findViewById(R.id.edtGender);
        edtOccupation = findViewById(R.id.edtOccupation);
        edtPrescription = findViewById(R.id.edtPrescription);
        edtReligion = findViewById(R.id.edtReligion);
        edtPresentingComplain = findViewById(R.id.edtPresentingComplain);
        edtFinalDiagnosis = findViewById(R.id.edtFinalDiagnosis);
        radioGroupMaritalStatus = findViewById(R.id.radioGrpMarry);
        radioBtnMarried = findViewById(R.id.radioMarried);
        radioBtnUnmarried = findViewById(R.id.radioUnmarried);
        btnSave = findViewById(R.id.btnSaveRecord);
        rvHistoryPresentingComplain = findViewById(R.id.rvHistoryPresentingComplain);
        historyPreComplain = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();


        mDatabase = FirebaseDatabase.getInstance();
        myRef = mDatabase.getReference();
        userReportRef = myRef.child("user100");
        userReportRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Map<String, Object> userVal = (Map<String, Object>) dataSnapshot.getValue();
                edtAge.setText(userVal.get("age") + "");
                edtFinalDiagnosis.setText(userVal.get("finalDiagnosis") + "");
                edtGender.setText(userVal.get("gender")+"");
//                .setText(userVal.get("maritalStatus") + "");
                edtName.setText(userVal.get("name")+"");
                edtOccupation.setText(userVal.get("occupation") + "");
                edtReligion.setText(userVal.get("religion") + "");
                edtPresentingComplain.setText(userVal.get("presentingComplain") + "");
                edtFinalDiagnosis.setText(userVal.get("finalDiagnosis") + "");
                edtPrescription.setText(userVal.get("prescription")+"");
                if((ArrayList<String>) userVal.get("historyPresentingComplain") != null) {
                    Log.v("ARRAyLIST",(ArrayList<String>) userVal.get("historyPresentingComplain") +"");
                    historyPreComplain.addAll((ArrayList<String>) userVal.get("historyPresentingComplain"));

                    adapter = new ArrayAdapter<String>(CaseTakeActivity.this, android.R.layout.simple_list_item_1, historyPreComplain);


                    rvHistoryPresentingComplain.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String age = edtAge.getText().toString();
                String gender = edtGender.getText().toString();
                String occupation = edtOccupation.getText().toString();
                String religion = edtReligion.getText().toString();
                int id = radioGroupMaritalStatus.getCheckedRadioButtonId();
                String maritalStatus = "";
                switch (id){
                    case R.id.radioMarried:
                        maritalStatus = "Married";
                        break;

                    case R.id.radioUnmarried:
                        maritalStatus = "UnMarried";
                        break;
                }
                String prescription = edtPrescription.getText().toString();
                String presentingComplain = edtPresentingComplain.getText().toString();
                String finalDiagnosis = edtFinalDiagnosis.getText().toString();
                historyPreComplain.add(presentingComplain);
                Case caseTakeActivity = new Case(name, age,  gender, occupation, religion, maritalStatus, prescription, presentingComplain, finalDiagnosis, historyPreComplain);
                myRef.child("user100").setValue(caseTakeActivity).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(CaseTakeActivity.this, "Successfully saved", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
