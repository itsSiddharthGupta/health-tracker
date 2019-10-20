package com.kars.healthtracker;

import java.util.ArrayList;

public class Case {
    public String name, age, gender, occupation, religion, maritalStatus, prescription, presentingComplain, finalDiagnosis;
    public ArrayList<String> historyPresentingComplain;
    public Case(){}

    public Case(String name, String age, String gender, String occupation, String religion,
                String maritalStatus, String prescription, String presentingComplain, String finalDiagnosis
                ,ArrayList<String> historyPresentingComplain) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.occupation = occupation;
        this.religion = religion;
        this.maritalStatus = maritalStatus;
        this.prescription = prescription;
        this.presentingComplain = presentingComplain;
        this.finalDiagnosis = finalDiagnosis;
        this.historyPresentingComplain = historyPresentingComplain;
    }
}
