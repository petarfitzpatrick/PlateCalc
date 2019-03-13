package com.example.platecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    RadioButton lb, kg;
    TextView weightFormat[] = new TextView[2];
    TextView calculatedWeight;
    EditText barWeight, targetWeight, resultText;
    Button calculate;

    String format;
    Plate weight;
    List<Double> answer = new ArrayList<Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lb = findViewById(R.id.radio_lb);
        kg = findViewById(R.id.radio_kg);

        weightFormat[0] = findViewById(R.id.weight_format1);
        weightFormat[1] = findViewById(R.id.weight_format2);

        calculatedWeight = findViewById(R.id.calculated_weight);

        barWeight = findViewById(R.id.bar_weight);
        targetWeight = findViewById(R.id.target_weight);
        resultText = findViewById(R.id.result_text);

        resultText.setFocusable(false);
        resultText.setClickable(true);

        calculate = findViewById(R.id.calculate_btn);

        kg.toggle();
        format = "kg";
    }

    public void onRadioButtonClicked(View view){
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_lb:
                if (checked)
                    lb.toggle();
                    kg.setChecked(false);
                    format = "lb";

                    weightFormat[0].setText(format);
                    weightFormat[1].setText(format);

                    barWeight.setText("45");
                    targetWeight.setText("0");
                    resultText.setText("");
                    calculatedWeight.setText("0.0");
                    break;
            case R.id.radio_kg:
                if (checked)
                    kg.toggle();
                    lb.setChecked(false);
                    format = "kg";

                    weightFormat[0].setText(format);
                    weightFormat[1].setText(format);

                    barWeight.setText("20");
                    targetWeight.setText("0");
                    resultText.setText("");
                    calculatedWeight.setText("0.0");
                    break;
        }
    }

    public void onButtonClicked(View view){
        //Create Stringbuilder for answer section
        StringBuilder sb = new StringBuilder();

        //Collapse Keyboard
        barWeight.onEditorAction(EditorInfo.IME_ACTION_DONE);

        Double bar = Double.parseDouble(barWeight.getText().toString());
        Double target = Double.parseDouble(targetWeight.getText().toString());
        Double resultWeight = 0.0;

        if (target > 20) {
            resultWeight = (target - bar)/2;

            weight = new Plate(format);
            answer = weight.Calculate(resultWeight);

            //Create a stringbuilder to output everything
            //CONSIDER: instead making a format like 25kg x 4
            //CONSIDER: Showing total weight at the bottom, in case people enter impossible values
            for (Double a : answer){
                //Doesn't work in cases like 1.25kg
                //sb.append(String.format("%.0f", a) + format);
                sb.append(a.toString() + format);
                sb.append(System.getProperty("line.separator"));
            }
        }

        resultText.setText(sb);
        calculatedWeight.setText(resultWeight.toString());
    }
}
