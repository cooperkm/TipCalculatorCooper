package com.example.tipcalculator_counter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

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
    TextView perPersonTotal;

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
        perPersonTotal = findViewById(R.id.perPersons);


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
                perPersonTotal.setText("Total per person:");


            }
        });

        calc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double calcBill = calculateBill();

                double calcTip = calculateTip() * calcBill;

                double numOfPeople = Double.parseDouble(numPeople.getText().toString());


                double finalBillTotal = calcTip + calcBill;


                tipTotal.setText(String.format("Tip total:" + "%.2f", calcBill));
                billTotal.setText(String.format("Bill total:" + "%.2f", finalBillTotal));

                finalBillTotal /= numOfPeople;


                perPersonTotal.setText(String.format("Total per person:" + "%.2f", finalBillTotal));


            }
        });
    }

    private double calculateTip(){
        double tip = 0;

        //grab which percent tip

        if(tip15.isChecked()){
            tip = .15;
        }else if (tip20.isChecked()){
            tip = .20;
        }else if (tip25.isChecked()){
            tip = .25;
        }else{      //grab custom tip from editText field
            tip = Double.parseDouble(customTip.getText().toString());


            if(tip > 1){        //if tip is already not a decimal
                tip = tip / 100;
            }


        }
        return tip;

    }

    private double calculateBill(){

        double bill = Double.parseDouble(billAmount.getText().toString());


        return bill;
    }

    //save the state when rotated

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String billStr;
        String tipStr;
        String peopleStr;

        billStr = billTotal.getText().toString();
        tipStr = tipTotal.getText().toString();
        peopleStr = perPersonTotal.getText().toString();

        outState.putString("billStrKey", billStr);
        outState.putString("tipStrKey", tipStr);
        outState.putString("peopleStrKey", peopleStr);

    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle){
        super.onRestoreInstanceState(bundle);

        String billStr = bundle.getString("billStrKey");
        String tipStr = bundle.getString("tipStrKey");
        String peopleStr = bundle.getString("peopleStrKey");

       billTotal.setText(billStr);
       tipTotal.setText(tipStr);
       perPersonTotal.setText(peopleStr);

    }



}




