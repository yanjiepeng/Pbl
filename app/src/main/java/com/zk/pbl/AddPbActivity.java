package com.zk.pbl;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.zk.EventBus.EventA;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AddPbActivity extends AppCompatActivity implements View.OnClickListener {


    private Spinner spinner_produce_line, spinner_produce_status, spinner_produce_name, spinner_produce_amount;
    EditText et_info;
    private Button btn_confirm, btn_cancel;
    private OkHttpClient client;
    private String url = "http://192.168.7.183:8080/sshe/base/prodorder!notNeedSecurity_add.sy";  //post接口
    String[] pro_code = {"c2158cb5bc374cdcac759b32f57aed45", "c066bffef2664f589393184aaa6bda27"};
    ProgressDialog pDialog;

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

            case R.id.btn_confirm:

                new UploadNewTaskThread().start();

                break;
            case R.id.btn_cancel:

                AddPbActivity.this.finish();
                break;

        }
    }

    class UploadNewTaskThread extends Thread {
        @Override
        public void run() {
            super.run();

            RequestBody fromBody = new FormBody.Builder()
                    .add("data.prodline", (spinner_produce_line.getSelectedItemPosition() + 1) + "")
                    .add("data.orderState.code", "202020")
                    .add("data.orderType.code", "202020")
                    .add("data.emergency", "否")
                    .add("data.descript", et_info.getText().toString())
                    .add("orderProd.prod", pro_code[spinner_produce_name.getSelectedItemPosition()])
                    .add("orderProd.number", getResources().getStringArray(R.array.produce_count)[spinner_produce_amount.getSelectedItemPosition()])
                    .build();

            Request request = new Request.Builder().url(url).post(fromBody).build();
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    pDialog = new ProgressDialog(AddPbActivity.this);
                    pDialog.setTitle("提交中 ，请稍候");
                    pDialog.show();
                }
            });


            //响应
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            pDialog.dismiss();
                            new AlertDialog.Builder(AddPbActivity.this)
                                    .setTitle("消息")
                                    .setIcon(R.mipmap.failed)
                                    .setMessage("提交失败，请重试！")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                pDialog.dismiss();
                                new AlertDialog.Builder(AddPbActivity.this)
                                        .setTitle("消息")
                                        .setMessage("提交成功")
                                        .setIcon(R.mipmap.success)
                                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                dialog.dismiss();
                                                AddPbActivity.this.finish();
                                            }
                                        })
                                        .show();
                            }
                        });
                    }

                    EventBus.getDefault().post(new EventA("post success"));
                }
            });


        }
    }

}
