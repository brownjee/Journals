package com.brownjee.journals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

public class SplashScreen extends Activity {
    SharedPref sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(2000,1000) {

            /** This method will be invoked on finishing or expiring the timer */
            @Override
            public void onFinish() {
                /** Creates an intent to start new activity*/
                sharedPref = SharedPref.getInstance();
                if (sharedPref.getISLogged_IN(SplashScreen.this)) {
                    Intent NextScreen = new Intent(getApplicationContext(),
                            MenuScreen.class);
                    startActivity(NextScreen);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashScreen.this, Authentication.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();

                }
            }
            /** This method will be invoked in every 1000 milli seconds until
             * this timer is expired.Because we specified 1000 as tick time
             * while creating this CountDownTimer
             */
            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }
}
