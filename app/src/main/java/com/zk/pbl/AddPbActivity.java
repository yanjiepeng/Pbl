package com.zk.pbl;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddPbActivity extends AppCompatActivity implements View.OnClickListener {


    private Spinner spinner_produce_line , spinner_produce_status , spinner_produce_name , spinner_produce_amount;
    EditText et_info;
    private Button btn_confirm , btn_cancel;
    private OkHttpClient client;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("新建订单");
        setContentView(R.layout.activity_add_pb);
        initActionBar();
        initWidget();
        client = new OkHttpClient();
    }

    private void initWidget() {

        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_confirm = (Button) findViewById(R.id.btn_confirm);
        btn_confirm.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        spinner_produce_line = (Spinner) findViewById(R.id.spinner_produce_line);
        spinner_produce_line.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.produce_line)));
        spinner_produce_line.setSelection(0, true);
        spinner_produce_line.setPrompt("选择产线");

        spinner_produce_status = (Spinner) findViewById(R.id.spinner_produce_status);
        spinner_produce_status.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.produce_status)));
        spinner_produce_status.setSelection(0, true);
        spinner_produce_status.setPrompt("选择状态");

        spinner_produce_name = (Spinner) findViewById(R.id.spinner_produce_name);
        spinner_produce_name.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.produce_name)));
        spinner_produce_name.setSelection(0, true);
        spinner_produce_name.setPrompt("选择产品名");

        spinner_produce_amount = (Spinner) findViewById(R.id.spinner_produce_amount);
        spinner_produce_amount.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.produce_count)));
        spinner_produce_amount.setSelection(0, true);
        spinner_produce_amount.setPrompt("选择数量");

        /*spinner_produce_name = (Spinner) findViewById(R.id.spinner_produce_name);
        spinner_produce_amount.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.produce_name)));
        spinner_produce_amount.setSelection(0, true);
        spinner_produce_amount.setPrompt("选择产品名");*/
        et_info = (EditText) findViewById(R.id.et_info);
    }


    /*
    初始化action bar
 */
    private void initActionBar() {

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true); // 决定左上角图标的右侧是否有向左的小箭头, true
            // 有小箭头，并且图标可以点击
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setTitle("新建订单");
        }

    }

    /*
        ActionBar的菜单监听
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:// 点击返回图标事件
                this.finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_confirm :



                break;
            case R.id.btn_cancel :

                AddPbActivity.this.finish();
                break;

        }
    }

    class UploadNewTaskThread extends Thread {
        @Override
        public void run() {
            super.run();

            RequestBody fromBody = new FormBody.Builder()
                    .add("data.prodline" , (String)spinner_produce_line.getSelectedItem())
                    .add("data.orderState.code","订单类型")
                    .add("data.emergency" , "否")
                    .add("data.descript","备注")
                    .add("orderProd.prod" , "产品名")
                    .add("orderProd.number","数量")
                    .build();
            Request request = new Request.Builder().url(url).post(fromBody).build();
            //响应
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                        if (response.isSuccessful()) {

                        }
                }
            });


        }
    }
}
