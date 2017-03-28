package com.example.gsu.shrikantaudioplayer.view;

import android.app.Application;

import com.example.gsu.shrikantaudioplayer.util.UtilLog;


/**
 * Created by YoungH on 2/6/17.
 */

public class YanApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UtilLog.setDebug(true);
    }
}
