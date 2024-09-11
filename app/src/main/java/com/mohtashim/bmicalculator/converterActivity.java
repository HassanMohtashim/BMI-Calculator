package com.mohtashim.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class converterActivity extends AppCompatActivity {

    EditText feetTxt, inchTxt, weightPoundTxt;
    TextView heightInCmTxt, weightInKg;
    Button convertHeightBtn, convertWeightBtn;
    final double kgInPound = 2.20462, inchInFeet = 12.0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        feetTxt = findViewById(R.id.feetTxt);
        inchTxt = findViewById(R.id.inchTxt);
        weightPoundTxt = findViewById(R.id.weightInPounds);
        heightInCmTxt = findViewById(R.id.heightInCmTxt);
        weightInKg = findViewById(R.id.weightInKg);
        convertHeightBtn = findViewById(R.id.convertHeightBtn);
        convertWeightBtn = findViewById(R.id.convertWeightBtn);

        //heightConversion Btn
        convertHeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String feetTxtToConvert =  feetTxt.getText().toString();
                String inchTxtToConvert = inchTxt.getText().toString();
                //Checking if the string is empty or not
                if(TextUtils.isEmpty(feetTxtToConvert) || TextUtils.isEmpty(inchTxtToConvert)){
                    Toast.makeText(converterActivity.this,"Either feet OR inches is empty, to proceed write Zero",Toast.LENGTH_SHORT).show();
                }
                //if contains only spaces
                else if(feetTxtToConvert.trim().isEmpty() || inchTxtToConvert.trim().isEmpty()){
                    Toast.makeText(converterActivity.this,"Inputs contains only spaces,  Enter a valid number",Toast.LENGTH_SHORT).show();
                }
                //if contain single space
                else if(feetTxtToConvert.equals(" ") || inchTxtToConvert.equals(" ")){
                    Toast.makeText(converterActivity.this,"Inputs contains a single space, Enter a valid number",Toast.LENGTH_SHORT).show();
                } else{
                    //Exception handling in converting string to double
                    try{
                        double feetDouble = Double.parseDouble(feetTxtToConvert.trim());
                        double inchDouble = Double.parseDouble(inchTxtToConvert.trim());
                        double TotalInches = feetDouble*inchInFeet+inchDouble;
                        double inCm = TotalInches*2.54;
                        double convertedCmRoundedOff = Math.round(inCm);
                        heightInCmTxt.setText(String.valueOf(convertedCmRoundedOff));
                    }
                    // if the input is not a number it will handle exception, prints the input is not valid
                    catch (NumberFormatException e){
                        Toast.makeText(converterActivity.this,"Input is not a valid number",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        //weightConversion Btn
        convertWeightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wt = weightPoundTxt.getText().toString();
                //Checking if the string is empty or not
                if(TextUtils.isEmpty(wt)){
                    Toast.makeText(converterActivity.this,"Field is empty",Toast.LENGTH_SHORT).show();
                }
                //if contains only spaces
                else if(wt.trim().isEmpty()){
                    Toast.makeText(converterActivity.this,"Input contains only spaces,  Enter a valid number",Toast.LENGTH_SHORT).show();
                }
                //if contain single space
                else if(wt.equals(" ")){
                    Toast.makeText(converterActivity.this,"Input contains a single space, Enter a valid number",Toast.LENGTH_SHORT).show();
                } else{
                    //Exception handling in converting string to double
                    try{
                        double weightInPound = Double.parseDouble(wt.trim());
                        double inKg = weightInPound/kgInPound;
                        System.out.println(inKg);
                        double convertedKgRoundedOff = Math.round(inKg);
                        weightInKg.setText(String.valueOf(convertedKgRoundedOff));
                    }
                    // if the input is not a number it will handle exception, prints the input is not valid
                    catch (NumberFormatException e){
                        Toast.makeText(converterActivity.this,"Input is not a valid number",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

    }

}