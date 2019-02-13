package com.example.tipcalculator_counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    RadioGroup rg;
    RadioButton tip15;
    RadioButton tip20;
    RadioButton tip25;
    RadioButton tipC;       //custom tip

    EditText customTip;
    EditText numPeople;
    EditText billAmount;
    Button reset;
    Button calc;
    TextView billTotal;
    TextView tipTotal;
    TextView perPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set IDs for each element

        tip15 = findViewById(R.id.tip15);
        tip20 = findViewById(R.id.tip20);
        tip25 = findViewById(R.id.tip25);

        customTip = findViewById(R.id.tf_tipPercent);
        numPeople = findViewById(R.id.tf_numOfPeople);
        billAmount = findViewById(R.id.tf_billAmount);
        reset = findViewById(R.id.resetButton);
        calc = findViewById(R.id.calcButton);
        billTotal = findViewById(R.id.billTotal);
        tipTotal = findViewById(R.id.tipTotal);
        perPerson = findViewById(R.id.perPerson);


        //set onClickListeners

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customTip.setText("");
                numPeople.setText("");
                billAmount.setText("");

                tip15.setChecked(true);

                billTotal.setText("Bill total:");
                tipTotal.setText("Tip total:");
                perPerson.setText("Total per person:");

                }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double calcBill = calculateBill();

                double calcTip = calculateTip() * calcBill;

                calcBill += calcTip;

                double totalPerPerson = Double.parseDouble(numPeople.getText().toString());

                totalPerPerson = calcBill / totalPerPerson;

                billTotal.setText("Bill total:" + calcBill);
                tipTotal.setText("Tip total:" + calcTip);
                perPerson.setText("Total per person:" + totalPerPerson);



            }
        });

    }

    private double calculateTip(){
        double tip = 0;

        //bad conditional statement
        //grab which percent tip

        if(tip15.isChecked()){
            tip = .15;
        }else if (tip20.isChecked()){
            tip = .20;
        }else if (tip25.isChecked()){
            tip = .25;
        }else{      //grab custom tip from editText field
            tip = Double.parseDouble(customTip.getText().toString());

        }
        return tip;

    }

    private double calculateBill(){

        double bill = Double.parseDouble(billAmount.getText().toString());


        return bill;
    }


}




