package com.zk.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.zk.EventBus.EventA;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UpdatePbService extends Service {
    Timer timer = new Timer();
    OkHttpClient client = new OkHttpClient();
    private String url = "http://www.mocky.io/v2/5762466c100000fd0a8b139d";
    private String url_x = "http://192.168.7.183:8080/sshe/base/prodorder!notNeedSecurity_orderlist.sy";
    public UpdatePbService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(new ExitReceiver() , null);
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        new UpdateTask().start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    class UpdateTask extends Thread {

        @Override
        public void run() {
           enqueueData();
        }
    }

    private void enqueueData() {
        Request request = new Request.Builder().url(url_x).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EventBus.getDefault().post(new EventA("error"));
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    //获取数据传递
                    String res = response.body().string();
                    EventBus.getDefault().post(new EventA(res));
                }
            }
        });
    }

    /*
      app退出时关闭服务
     */
    class ExitReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            stopSelf(); //关闭服务
        }
    }

}
