package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import chapter.android.aweme.ss.com.homework.R;

/**
 * 作业1：
 * Logcat在屏幕旋转的时候 #onStop() #onDestroy()会展示出来
 * 但我们的 mLifecycleDisplay 由于生命周期的原因(Tips:执行#onStop()之后，UI界面我们是看不到的)并没有展示
 * 在原有@see Exercises1 基础上如何补全它，让其跟logcat的展示一样?
 * <p>
 * Tips：思考用比Activity的生命周期要长的来存储？  （比如：application、static变量）
 */
public class Exercises1 extends AppCompatActivity {

    private static final String LOG_POS = "lxx";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";
    private static final String ON_RESTORE_INSTANCE_STATE = "onRestoreInstanceState";
    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    private TextView textView1;
    //使用变量存储状态，修改后传给 Bundle savedInstanceState
    private String savedState;

    private void logAndAppend(String lifecycleEvent){
        Log.d(LOG_POS,lifecycleEvent+"\n");
        textView1.append(lifecycleEvent+"\n");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise1);
        textView1 = findViewById(R.id.text_view1);
        if (savedInstanceState != null){
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)){
                String lastState = savedInstanceState.getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                textView1.setText(lastState);
                Log.d("he",lastState);
            }
        }
        logAndAppend(ON_CREATE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        logAndAppend(ON_START);
    }

    @Override
    protected void onResume() {
        super.onResume();
        logAndAppend(ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        logAndAppend(ON_PAUSE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        logAndAppend(ON_STOP);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logAndAppend(ON_DESTROY);

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        logAndAppend(ON_RESTART);
    }

    @Override
    //值得注意的是：该函数在onStop与onDestroy之间调用
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        logAndAppend(ON_SAVE_INSTANCE_STATE);
        savedState = textView1.getText().toString();
        savedState = savedState + ON_DESTROY + "\n";
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY,savedState);

    }
}
