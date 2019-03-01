package com.example.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    EditText num;
//    Button credits;
    Button calculate;
    Button clear;
    Button exit;
    TextView printArea;

    //private static final String result = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num = new EditText(this);
        clear = new Button(this);
        calculate = new Button(this);
//        credits = new Button(this);
        calculate = new Button(this);
        printArea = new TextView(this);

        num = (EditText) findViewById(R.id.num);
//        credits = (Button) findViewById(R.id.credits);
        clear = (Button) findViewById(R.id.clear);
        exit = (Button) findViewById(R.id.exit);
        calculate = (Button) findViewById(R.id.calculate);
        printArea = (TextView) findViewById(R.id.printArea);

        calculate.setOnClickListener(new View.OnClickListener() {
            // Perform action on Get Table Button click
            public void onClick(View v) {


                if (num.getText().length() == 0 ){
                    printArea.setText(R.string.err_msg);
                }

                else    {
                    //change the integer value into string
                    printArea.setText("");
                    int num1 = Integer.parseInt(num.getText().toString());
                    String result;

                    for (int i = 1; i <= 10; i++) {
                        result = (num1 + "      X     " + i + "      =     " + i * num1);
                        printArea.append("\n" + result);
                    }


                }
            }

        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num.setText("");
                printArea.setText("");
            }
        });
//        credits.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, Credits.class));
//            }
//        });
//


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Good Bye, User!", Toast.LENGTH_SHORT).show();
                finish();

            }
        });
    }

}