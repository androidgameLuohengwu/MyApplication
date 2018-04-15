package com.example.xiewen.myapplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

/**
 * 更新小组件事件的服务
 *
 * @author lgl
 *
 */
public class ClockService extends Service {

    // 定时器
    private Timer timer;
    // 日期格式
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd E HH:mm:ss");

    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        timer = new Timer();
        /**
         * 參数：1.事件2.延时事件3.运行间隔事件
         */
        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                updateView();
            }
        }, 0, 1000);
    }

    /**
     * 更新事件的方法
     */
    private void updateView() {
        // 时间
        String time = sdf.format(new Date());
        /**
         * 參数：1.包名2.小组件布局
         */
        RemoteViews rViews = new RemoteViews(getPackageName(),
                R.layout.my_app_widget_provider);
        // 显示当前事件
        rViews.setTextViewText(R.id.appwidget_text, time);

        // 刷新
        AppWidgetManager manager = AppWidgetManager
                .getInstance(getApplicationContext());
        ComponentName cName = new ComponentName(getApplicationContext(),
                MyAppWidgetProvider.class);
        manager.updateAppWidget(cName, rViews);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer = null;
    }

}