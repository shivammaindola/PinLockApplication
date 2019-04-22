package com.example.shivammaindola.pinlockapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.graphics.drawable.DrawableWrapper;

public class MainActivity extends AppCompatActivity {

    final Context context=this;
    String code;
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        code= pref.getString("code",null);
        textView.setText(String.format("The true code is: %s", code));
        Button button= (Button)findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (code == null) {
                    Intent intent = new Intent(MainActivity.this, set_code.class);
                    startActivity(intent);
                } else {
                    final Dialog dialog = new Dialog(context); // Context, this, etc.
                    dialog.setContentView(R.layout.passcode_dialog);
                    Button change_btn = (Button) dialog.findViewById(R.id.change_btn);
                    Button turnoff_btn = (Button) dialog.findViewById(R.id.turnoff_btn);

                    change_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, set_code.class);
                            startActivity(intent);
                        }
                    });

                    turnoff_btn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MainActivity.this, set_code.class);
                            startActivity(intent);
                        }
                    });
                    dialog.show();
                }
            }
        });

        Button button1=(Button)findViewById(R.id.btn1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, Lock_activity.class);
                startActivity(intent);
            }
        });
    }
}