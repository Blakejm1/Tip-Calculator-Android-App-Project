package com.example.tip_calculator_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Button;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    EditText inputPercent;
    EditText inputMembers;
    RadioGroup radioGroup1;
    RadioButton splitCost1;
    RadioButton dontSplitCost1;
    Button saveChanges;

    private int defaultTipPercentage;
    private int members;
    private boolean splitCostChecked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inputPercent = findViewById(R.id.inputPercent);
        inputMembers = findViewById(R.id.inputMembers);
        radioGroup1 = findViewById(R.id.radioGroup1);
        splitCost1 = findViewById(R.id.splitCostRadio1);
        dontSplitCost1 = findViewById(R.id.dontSplitCostRadio1);
        saveChanges = findViewById(R.id.saveButton);

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // For tip percent
                int input = Integer.parseInt(inputPercent.getText().toString());
                defaultTipPercentage = input;

                // For members
                int input2 = Integer.parseInt(inputMembers.getText().toString());
                members = input2;

                if (splitCost1.isChecked() == true) {
                    splitCostChecked = true;
                }
                else {
                    splitCostChecked = false;
                }
            }
        });

        Intent i = getIntent();
        defaultTipPercentage = i.getIntExtra("tip percent", 15);
        members = i.getIntExtra("number of members", 1);
        splitCostChecked = i.getBooleanExtra("if split cost", false);

        inputPercent.setText(defaultTipPercentage + "");
        inputMembers.setText(members + "");

        if(splitCostChecked == true) {
            splitCost1.setChecked(true);
        }
        else {
            dontSplitCost1.setChecked(true);
        }
    }

    @Override
    public  void onPause() {
        super.onPause();
        updateSharedPreferences();
    }

    private void updateSharedPreferences() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt("default tip percentage", defaultTipPercentage);
        editor.putInt("number of members", members);
        editor.putBoolean("if split cost", splitCostChecked);
        editor.commit();
    }

    private void updateOptions() {
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        defaultTipPercentage = sp.getInt("default tip percentage", 15);
        members = sp.getInt("number of members", 1);
        splitCostChecked = sp.getBoolean("if split cost", false);

        inputPercent.setText(defaultTipPercentage + "");
        inputMembers.setText(members + "");

        if(splitCostChecked == true) {
            splitCost1.setChecked(true);
        }
        else {
            dontSplitCost1.setChecked(true);
        }
    }

    @Override
    public void onResume() { // called whenever the user changes
        super.onResume();
        updateOptions();
    }
}