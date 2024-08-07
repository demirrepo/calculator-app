package com.demir.calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private double firstNumber;
    private String operation;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button num0 = findViewById(R.id.num0);
        Button num1 = findViewById(R.id.num1);
        Button num2 = findViewById(R.id.num2);
        Button num3 = findViewById(R.id.num3);
        Button num4 = findViewById(R.id.num4);
        Button num5 = findViewById(R.id.num5);
        Button num6 = findViewById(R.id.num6);
        Button num7 = findViewById(R.id.num7);
        Button num8 = findViewById(R.id.num8);
        Button num9 = findViewById(R.id.num9);
        
        Button onBtn = findViewById(R.id.on_button);
        Button offBtn = findViewById(R.id.off_button);
        Button acBtn = findViewById(R.id.ac_button);
        ImageButton clearBtn = findViewById(R.id.clearButton);
        Button pointBtn = findViewById(R.id.dot_button);
        Button equalBtn = findViewById(R.id.equal_button);
        Button addBtn = findViewById(R.id.addition);
        Button subBtn = findViewById(R.id.subtraction);
        Button timesBtn = findViewById(R.id.multiplication);
        Button divBtn = findViewById(R.id.division);

        TextView screen = findViewById(R.id.screen);

        acBtn.setOnClickListener(view -> {
            firstNumber = 0;
            screen.setText("0");

        });

        offBtn.setOnClickListener(view -> screen.setVisibility(View.GONE));
        onBtn.setOnClickListener(view -> {
            screen.setVisibility(View.VISIBLE);
            screen.setText("0");

        });

        ArrayList<Button> numsList = new ArrayList<>();

        numsList.add(num0);
        numsList.add(num1);
        numsList.add(num2);
        numsList.add(num3);
        numsList.add(num4);
        numsList.add(num5);
        numsList.add(num6);
        numsList.add(num7);
        numsList.add(num8);
        numsList.add(num9);


        for (Button btn: numsList){
            btn.setOnClickListener(view -> {
                if (!screen.getText().toString().equals("0")){
                    screen.setText(screen.getText().toString() + btn.getText().toString());
                } else {
                    screen.setText(btn.getText().toString());
                }
            });
        }


        ArrayList<Button> operationsList = new ArrayList<>();

        operationsList.add(divBtn);
        operationsList.add(addBtn);
        operationsList.add(timesBtn);
        operationsList.add(subBtn);

        for (Button button: operationsList){
            button.setOnClickListener(view -> {
                firstNumber = Double.parseDouble(screen.getText().toString());
                operation = button.getText().toString();
                screen.setText("0");
            });
        }



        clearBtn.setOnClickListener(view -> {
            String number = screen.getText().toString();
            if (number.length() >1){
                screen.setText(number.substring(0, number.length()-1));
            }else if (number.length() == 1 && !number.equals("0")){
                screen.setText("0");

            }
        });


        pointBtn.setOnClickListener(view -> {
            if (! screen.getText().toString().contains(".")){
                screen.setText(screen.getText().toString() + ".");
            }
        });

        equalBtn.setOnClickListener(view -> {
            double secondNumber = Double.parseDouble(screen.getText().toString());
            double result;

            switch (operation) {

                case "+":
                    result = firstNumber + secondNumber;
                    break;

                case "/":
                    result = firstNumber / secondNumber;
                    break;

                case "*":
                    result = firstNumber * secondNumber;
                    break;

                case "-":
                    result = firstNumber - secondNumber;
                    break;

                default:
                    result = firstNumber + secondNumber;
                    break;


            }


            screen.setText(String.valueOf(result));
            firstNumber = result;


        });

    }
}