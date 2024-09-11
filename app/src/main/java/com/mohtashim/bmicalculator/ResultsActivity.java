package com.mohtashim.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    TextView userGender, userAge, userHeight, userWeight, userBmi, userResultDescriptionTxt;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        //Receiving Intent
        Intent fromMainAct = getIntent();
        //Getting and storing all values from previous activity
        String genderToSet = fromMainAct.getStringExtra("finalGender");
        int ageToSet = fromMainAct.getIntExtra("FinalAge",0);
        int heightToSet = fromMainAct.getIntExtra("FinalHeight", 0);
        int weightToSet = fromMainAct.getIntExtra("FinalWeight",0);
        double bmiToSet = fromMainAct.getDoubleExtra("FinalBmi",0);

        userGender = findViewById(R.id.userGender);
        userAge = findViewById(R.id.userAge);
        userHeight = findViewById(R.id.userHeight);
        userWeight = findViewById(R.id.userWeight);
        userBmi = findViewById(R.id.userBmi);
        userResultDescriptionTxt = findViewById(R.id.userResultDescription);

        //Setting Values
        userGender.setText(genderToSet);
        userAge.setText(String.valueOf(ageToSet) + "  years");
        userHeight.setText(String.valueOf(heightToSet) + " cm");
        userWeight.setText(String.valueOf(weightToSet) + " kg");
        userBmi.setText(String.valueOf(bmiToSet));

        //Description Text Setting
        if(bmiToSet<18.5){
            userResultDescriptionTxt.setText("Underweight");
        } else if (bmiToSet>=18.5 && bmiToSet<= 24.9) {
            userResultDescriptionTxt.setText("Normal Weight");
        } else if (bmiToSet>=25.0 && bmiToSet<= 29.9) {
            userResultDescriptionTxt.setText("Overweight");
        } else if (bmiToSet>=30.0 && bmiToSet<= 34.9) {
            userResultDescriptionTxt.setText("Moderately Overweight");
        } else if (bmiToSet>=35.0 && bmiToSet<= 39.9) {
            userResultDescriptionTxt.setText("Severely Overweight");
        } else {
            userResultDescriptionTxt.setText("Very Severely Overweight");
        }
    }
}