package com.example.shivammaindola.pinlockapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

import org.w3c.dom.Text;

public class set_code extends AppCompatActivity {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private TextView ProfileName;
    private final static String TAG = Lock_activity.class.getSimpleName();
    public static final String MY_PREFS_NAME = "Code_File";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_code);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        Intent intent= getIntent();
        String text= intent.getStringExtra("text");

        ProfileName=(TextView)findViewById(R.id.profile_name);
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);

        if(text==null)
            ProfileName.setText("Enter your new passcode");
        else
            ProfileName.setText("Re-Enter your new passcode");


        //attach lock view with dot indicator
        mPinLockView.attachIndicatorDots(mIndicatorDots);

        //set lock code length
        mPinLockView.setPinLength(4);

        //set listener for lock code change
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.d(TAG, "lock code: " + pin);
                Intent intent1=getIntent();
                String pin1=intent1.getStringExtra("text");
                if(pin1==null) {
                    Intent intent = new Intent(set_code.this, set_code.class);
                    intent.putExtra("text", pin);
                    startActivity(intent);
                }
                else {
                    if(pin.equals(pin1)) {
                        editor.putString("code", pin);
                        editor.apply();
                        Intent intent = new Intent(set_code.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(set_code.this, "Invalid pin", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onEmpty() {
                Log.d(TAG, "lock code is empty!");
            }

            @Override
            public void onPinChange(int pinLength, String intermediatePin) {
                Log.d(TAG, "Pin changed, new length " + pinLength + " with intermediate pin " + intermediatePin);
            }
        });
    }
    }

