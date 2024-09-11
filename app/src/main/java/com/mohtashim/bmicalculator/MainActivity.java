package com.mohtashim.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import java.text.DecimalFormat;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearMale, linearFemale;
    ImageView maleSymbol, femaleSymbol, weightIncrementBtn, weightDecrementBtn,
            ageIncrementBtn, ageDecrementBtn, moreBtn;
    TextView maleText, femaleText, defaultWeightTxt, defaultAgeTxt, heightText;
    SeekBar seekBar;
    AppCompatButton calculateBmiBtn;
    int FinalWeight, FinalHeight, FinalAge;
    String FinalGender;
    double FinalBmi;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearMale = findViewById(R.id.linearLayoutMale);
        linearFemale = findViewById(R.id.linearLayoutFemale);
        maleSymbol = findViewById(R.id.maleCardImage);
        femaleSymbol = findViewById(R.id.femaleCardImage);
        maleText = findViewById(R.id.maleText);
        femaleText = findViewById(R.id.femaleText);
        defaultWeightTxt = findViewById(R.id.defaultWeight);
        weightIncrementBtn = findViewById(R.id.weightIncrementBtn);
        weightDecrementBtn = findViewById(R.id.weightDecrementBtn);
        defaultAgeTxt = findViewById(R.id.defaultAge);
        ageIncrementBtn = findViewById(R.id.ageIncrementBtn);
        ageDecrementBtn =findViewById(R.id.ageDecrementBtn);
        heightText = findViewById(R.id.heightText);
        seekBar = findViewById(R.id.seekBar);
        calculateBmiBtn = findViewById(R.id.calculateBMIBtn);
        moreBtn = findViewById(R.id.moreBtn);

        //To preSelect male Box
        onSelectedChange(linearMale,maleSymbol,maleText);
        //Saving default Weight as integer
        FinalWeight = Integer.parseInt(defaultWeightTxt.getText().toString());
        //Saving default Age as integer
        FinalAge = Integer.parseInt(defaultAgeTxt.getText().toString());
        //Setting default Gender
        FinalGender = "Male";
        //Saving default height
        FinalHeight = Integer.parseInt(heightText.getText().toString());
        //Toast for user guide
        Toast.makeText(MainActivity.this,"Please set the details according to your own information",Toast.LENGTH_SHORT).show();
        //Setting click listener to the male gender block
        linearMale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedChange(linearMale,maleSymbol,maleText);
                changePreviousToDefault(linearFemale,femaleSymbol,femaleText);
                FinalGender = "Male";
            }
        });
        //Setting click listener to the female gender block
        linearFemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSelectedChange(linearFemale,femaleSymbol,femaleText);
                changePreviousToDefault(linearMale,maleSymbol,maleText);
                FinalGender = "Female";
            }
        });
        //Increment to weight
        weightIncrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementToField(defaultWeightTxt);
                FinalWeight = Integer.parseInt(defaultWeightTxt.getText().toString());
            }
        });
        //Decrement to weight
        weightDecrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                decrementToField(defaultWeightTxt);
                FinalWeight = Integer.parseInt(defaultWeightTxt.getText().toString());
            }
        });
        //increment to Age
        ageIncrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                incrementToField(defaultAgeTxt);
                FinalAge = Integer.parseInt(defaultAgeTxt.getText().toString());
            }
        });
        //decrement to Age
        ageDecrementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrementToField(defaultAgeTxt);
                FinalAge = Integer.parseInt(defaultAgeTxt.getText().toString());
            }
        });
        //SeekBar Progress Changes Height value
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //setting progress value to height txt
                heightText.setText(String.valueOf(progress));
                //final height storing
                FinalHeight = Integer.parseInt(heightText.getText().toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //Calculate btn
        calculateBmiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double heightInMeterSquare = cmToMeterSquare(FinalHeight);

                //Putting a condition if the weight & height & age will be grater than 0, it will work
                if(FinalWeight>0 && heightInMeterSquare>0 && FinalAge>0){
                FinalBmi = calculateBmi(FinalWeight,heightInMeterSquare);
                    // Defining a pattern of Rounding off to 5 decimal places
                    DecimalFormat decimalFormat = new DecimalFormat("#.#####");
                    //Format the double value
                    String formattedBmi = decimalFormat.format(FinalBmi);
                    //ConvertBack to double
                    double roundedFormatBmi = Double.parseDouble(formattedBmi);
                    //Creating Intent
                    Intent mainToResultAct = new Intent(MainActivity.this,ResultsActivity.class);
                    //Passing Values with intent by BundlePassing
                    mainToResultAct.putExtra("finalGender", FinalGender);
                    mainToResultAct.putExtra("FinalAge",FinalAge);
                    mainToResultAct.putExtra("FinalHeight",FinalHeight);
                    mainToResultAct.putExtra("FinalWeight",FinalWeight);
                    mainToResultAct.putExtra("FinalBmi",roundedFormatBmi);
                    //Starting Intent
                    startActivity(mainToResultAct);
                    System.out.println(FinalBmi);
                }
                //else will show this msg
                else{Toast.makeText(MainActivity.this,"Invalid Height OR Weight OR Age",Toast.LENGTH_SHORT).show();}

            }
        });
        //More Items
        moreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,converterActivity.class);
                startActivity(intent);
            }
        });


    }

    @SuppressLint("UseCompatLoadingForDrawables")
    public void onSelectedChange(LinearLayout linear, ImageView img, TextView txt){
        linear.setBackground(getResources().getDrawable(R.drawable.card_selected_background));
        img.setColorFilter(getResources().getColor(R.color.white));
        txt.setTextColor(getResources().getColor(R.color.white));
    }
    @SuppressLint("UseCompatLoadingForDrawables")
    public void changePreviousToDefault(LinearLayout linear, ImageView img, TextView txt){
        linear.setBackground(getResources().getDrawable(R.drawable.card_unselected_background));
        img.setColorFilter(getResources().getColor(R.color.grey));
        txt.setTextColor(getResources().getColor(R.color.grey));
    }
    public void incrementToField(TextView fieldToSetOn){
        String TextValue = fieldToSetOn.getText().toString();
        int CurrentValue = Integer.parseInt(TextValue);
        CurrentValue ++;
        fieldToSetOn.setText(String.valueOf(CurrentValue));
    }
    public void decrementToField(TextView fieldToSetOn){
        String TextValue = fieldToSetOn.getText().toString();
        int CurrentValue = Integer.parseInt(TextValue);
        CurrentValue --;
        fieldToSetOn.setText(String.valueOf(CurrentValue));
    }
    public double cmToMeterSquare(int value){
        double newValue = (double) value /100;
        return newValue*newValue;
    }
    public double calculateBmi(int value1, double value2){
        return (double) value1/value2;
    }


}