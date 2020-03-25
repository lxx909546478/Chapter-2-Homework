package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
//import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise2);
        View rootView = findViewById(R.id.root_layout);
        TextView textView = findViewById(R.id.text_view2);
        int viewCount = getAllChildViewCount(rootView);
        textView.setText("The number of view is "+viewCount);

    }

    public int getAllChildViewCount(View view) {
        //todo 补全你的代码
        int viewCount = 0;

        if (view == null){
            return 0;
        }

        if (view instanceof ViewGroup){
            viewCount++;
            for (int i=0;i<((ViewGroup) view).getChildCount();i++){
                View subView = ((ViewGroup) view).getChildAt(i);
                viewCount+=getAllChildViewCount(subView);
            }
        } else{
            viewCount++;
        }
        return viewCount;
    }
}
