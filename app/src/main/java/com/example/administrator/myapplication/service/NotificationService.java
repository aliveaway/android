package com.example.administrator.myapplication.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.administrator.myapplication.Main2Activity;
import com.example.administrator.myapplication.R;

public class NotificationService extends Service {
    public NotificationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildNotification();

        //耗时任务会使Application ANR
        /*try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * 使用前台服务使得服务优先级相对高不容易被杀死
     */
    private void buildNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        builder.setContentIntent(PendingIntent.getActivity(this, 0,
                new Intent(this, Main2Activity.class), 0));
        builder.setAutoCancel(false);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setTicker("Foreground service start");
        builder.setContentTitle("Socket服务器端");
        builder.setContentText("正在运行");
        startForeground(1, builder.getNotification());
    }
}
