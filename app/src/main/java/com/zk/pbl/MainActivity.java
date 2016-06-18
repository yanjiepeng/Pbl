package com.zk.pbl;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zk.EventBus.EventA;
import com.zk.pbl.adapter.PbExpandableAdapter;
import com.zk.pbl.bean.PbInfo;
import com.zk.service.UpdatePbService;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add_new_pb;
    private ExpandableListView lv_pb;
    private Gson gson ;
    private List<PbInfo> data = new ArrayList<PbInfo>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initWidget();
        gson = new Gson();
        EventBus.getDefault().register(this);

        startService(new Intent(MainActivity.this , UpdatePbService.class));
    }

    /*
     初始化控件
     */
    private void initWidget() {

        btn_add_new_pb = (Button) findViewById(R.id.btn_add_new_book);
        lv_pb = (ExpandableListView) findViewById(R.id.lv_pro_list);
        lv_pb.setGroupIndicator(getResources().getDrawable(R.mipmap.open));
        btn_add_new_pb.setOnClickListener(this) ;
    }


    /*
       初始化actionbar
     */
    private void initActionBar() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setCustomView(R.layout.titlebar);

        actionBar.setIcon(R.mipmap.mjlogo);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_new_book :
                startActivity(new Intent(MainActivity.this, AddPbActivity.class));

                break;

        }
    }

    /*
     EventBus回调
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventA eventA) {

        String msg = eventA.getMsg() ;
        Type type = new TypeToken<List<PbInfo>>(){}.getType();
        if (msg.equals("post success")) {
            startService(new Intent(MainActivity.this , UpdatePbService.class));
        }
        if (msg.equals("error")) {
            Toast.makeText(MainActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
        }else {
             /*
                数据解析
             */
            if (!msg.isEmpty()) {

                data.clear();
                data = gson.fromJson(msg , type);
                PbExpandableAdapter mAdapter = new PbExpandableAdapter(MainActivity.this , data);
                lv_pb.setAdapter(mAdapter);

               //默认全部展开
                int groupCount = lv_pb.getCount();
                for (int i=0; i<groupCount; i++) {
                    lv_pb.expandGroup(i);
                }
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        sendBroadcast(new Intent(MainActivity.this , UpdatePbService.class));
    }
}
