package com.example.clipvidva;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

/**
 * Created by nuttt on 12/9/13.
 */
public class Splash extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splash);
    }
}