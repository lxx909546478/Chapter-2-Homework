package com.ss.android.ugc.chapter1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Main3Acvtivity extends Activity {

    private static String TAG = Main3Acvtivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        findViewById(R.id.txt_intent).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Main3Acvtivity.this.finish();
            }
        });
    }

    @Override protected void onDestroy() {
        Log.d(TAG, "onDestroy: ");
        super.onDestroy();
    }

    /**
     * 界面用户可见
     */
    @Override protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart");
    }

    /**
     * 用户不可见
     */
    @Override protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop");
    }

    /**
     * 界面失去焦点
     */
    @Override protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    /**
     * 界面获得焦点
     */
    @Override protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }

    @Override protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart: ");
    }
}
