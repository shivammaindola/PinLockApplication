package com.example.shivammaindola.pinlockapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.pinlockview.IndicatorDots;
import com.andrognito.pinlockview.PinLockListener;
import com.andrognito.pinlockview.PinLockView;

public class Disable_Passcode extends AppCompatActivity {

    private PinLockView mPinLockView;
    private IndicatorDots mIndicatorDots;
    private TextView ProfileName;
    private final static String TAG = Lock_activity.class.getSimpleName();
    String code;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_code);

        ProfileName=(TextView)findViewById(R.id.profile_name);
        mPinLockView = (PinLockView) findViewById(R.id.pin_lock_view);
        mIndicatorDots = (IndicatorDots) findViewById(R.id.indicator_dots);

        ProfileName.setText("Confirm your Passcode");
        //attach lock view with dot indicator
        mPinLockView.attachIndicatorDots(mIndicatorDots);

        //set lock code length
        mPinLockView.setPinLength(4);

        //set listener for lock code change
        mPinLockView.setPinLockListener(new PinLockListener() {
            @Override
            public void onComplete(String pin) {
                Log.d(TAG, "lock code: " + pin);
                SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
                code= pref.getString("code",null);
                Intent intent1=getIntent();
                String set=intent1.getStringExtra("set");
                //User input true code
                if (pin.equals(code)) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.clear();
                    editor.apply();
                    if(set==null) {
                        Intent intent = new Intent(Disable_Passcode.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Intent intent = new Intent(Disable_Passcode.this, set_code.class);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(Disable_Passcode.this, "Failed code, try again!", Toast.LENGTH_SHORT).show();
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
