package com.zk.pbl;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_add_new_pb;
    private ListView lv_pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initActionBar();
        initWidget();
    }

    /*
     初始化控件
     */
    private void initWidget() {

        btn_add_new_pb = (Button) findViewById(R.id.btn_add_new_book);
        lv_pb = (ListView) findViewById(R.id.lv_pro_list);
        btn_add_new_pb.setOnClickListener(this) ;
    }


    /*
       初始化actionbar
     */
    private void initActionBar() {

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        actionBar.setCustomView(R.layout.titlebar);

        actionBar.setIcon(R.drawable.zklogo);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_SHOW_HOME);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_add_new_book :
                startActivity(new Intent(MainActivity.this , AddPbActivity.class));
                break;

        }
    }
}
