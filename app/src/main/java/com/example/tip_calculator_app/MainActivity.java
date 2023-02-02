package com.example.tip_calculator_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView seekBarLabel;
    ConstraintLayout layout;
    EditText inputText;
    EditText inputText2;
    RadioGroup radioGroup;
    RadioButton splitCost;
    RadioButton dontSplitCost;
    TextView outputText;
    TextView outputText2;
    TextView outputText3;
    Button calculateButton;
    double value;
    double percent;
    double totalPrice;
    double splitPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        seekBarLabel = findViewById(R.id.seekBarLabel);
        layout = findViewById(R.id.layout);
        inputText = findViewById(R.id.inputText);
        inputText2 = findViewById(R.id.inputText2);
        radioGroup = findViewById(R.id.radioGroup);
        splitCost = findViewById(R.id.splitCostRadio);
        dontSplitCost = findViewById(R.id.dontSplitCostRadio);
        outputText = findViewById(R.id.outputText);
        outputText2 = findViewById(R.id.outputText2);
        outputText3 = findViewById(R.id.outputText3);
        calculateButton = findViewById(R.id.calculateButton);


        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                value = Double.parseDouble(inputText.getText().toString());
                percent = Double.parseDouble(seekBarLabel.getText().toString());
                totalPrice = value * (1 + percent / 100);
                outputText.setText("$" + totalPrice);
                outputText2.setText("$" + (totalPrice - value));

                double splitValue = Double.parseDouble(inputText2.getText().toString());
                if(splitCost.isChecked()) {
                    splitPrice = totalPrice / splitValue;
                    outputText3.setText("$" + splitPrice);
                }
                else {
                    outputText3.setText("$" + totalPrice);
                }
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBarLabel.setText(i+"");
                value = Double.parseDouble(inputText.getText().toString());
                percent = Double.parseDouble(seekBarLabel.getText().toString());

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}