package chapter.android.aweme.ss.com.homework;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;


/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements ContractAdapter.ListItemClickListener{

    private List<Message> messages;
    private RecyclerView contractRecyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise3);
        try {
            InputStream assetInput = getAssets().open("data.xml");
            messages = PullParser.pull2xml(assetInput);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Message message : messages){
            Log.d("message",message.toString());
        }
        //设定布局
        contractRecyclerView = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        contractRecyclerView.setLayoutManager(layoutManager);

//        Log.d("size",String.valueOf(messages.size()));
        ContractAdapter adapter = new ContractAdapter(messages.size(),this);
        adapter.setMessages(messages);

        contractRecyclerView.setAdapter(adapter);


    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(this, ChatActivity.class);
        Bundle budle = new Bundle();
        budle.putString("order",String.valueOf(clickedItemIndex+1));
        intent.putExtras(budle);
        startActivity(intent);
    }

    public List<Message> getMessages(){
        return messages;
    }


}
