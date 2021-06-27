package com.example.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    //Class variables are also called fields.
    private TextView resultText;
    private Button calculateFatness;
    private RadioButton maleChoice;
    private RadioButton femaleChoice;
    private EditText inchesText;
    private EditText feetText;
    private EditText ageText;
    private EditText weightText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.result_text_message);
        ageText = findViewById(R.id.edit_text_age);
        weightText = findViewById(R.id.edit_text_weight);
        feetText = findViewById(R.id.edit_text_feet);
        inchesText = findViewById(R.id.edit_text_inches);
        maleChoice = findViewById(R.id.male_radio_button);
        femaleChoice = findViewById(R.id.female_radio_button);
        calculateFatness = findViewById(R.id.calculate_button);
    }

    private void setupButtonClickListener() {
        calculateFatness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBMI();
                displayResult(bmiResult);
                String ageEText = ageText.getText().toString();
                int age = Integer.parseInt(ageEText);

                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    displayGuidance(bmiResult);
                }
            }

        });
    }


    private double calculateBMI() {

        String feetEText = feetText.getText().toString();
        String inchesEText = inchesText.getText().toString();
        String weightEText = weightText.getText().toString();

        // Converting the number strings into int variables to do math

        int feet = Integer.parseInt(feetEText);
        int inches = Integer.parseInt(inchesEText);
        int weight = Integer.parseInt(weightEText);
        int totalInches = (feet * 12) + inches;

        // Height in meters is the inches multipled by 0.0254
        double heightInMeters = totalInches * 0.0254;

        //BMI formula = weight in kg divided by height in meters squared
        return weight / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;
        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + " - You need to eat more cupcakes - NOM NOM NOM!";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + " - No cupcakes for you :(";

        } else {
            //Display healthy
            fullResultString = bmiTextResult + " - Yay! You are healthy.... now hit the gym!";
        }
        resultText.setText(fullResultString);
    }

    private void displayGuidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (maleChoice.isChecked()) {
            //Display boy guidance.
            fullResultString = bmiTextResult + " - Since you are under 18, you are too young to use this app and need to speak with your doctor for the healthy range for dudes!";
        } else if (femaleChoice.isChecked()) {
            //Display girl guidance.
            fullResultString = bmiTextResult + " - Since you are under 18, you are too young to use this app and need to speak with your doctor for the healthy range for girlies!";
        } else {
            //Display gender neutral guidance.
            fullResultString = bmiTextResult + " - Since you are under 18, you are too young to use this app and need to speak with your doctor for the healthy range!";
        }
        resultText.setText(fullResultString);
    }
}