package com.example.gsu.shrikantaudioplayer.view;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;

public class BaseActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void toActivty(Class name, Bundle bundle){
        Intent intent = new Intent(this,name);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    public void toActivty(Class name, String key, int value){
        Intent intent = new Intent(this,name);
        intent.putExtra("Integer",value);
        startActivity(intent);
        ButterKnife.bind(this);
    }

    public void toActivty(Class name, String key, String value){
        Intent intent = new Intent(this,name);
        intent.putExtra("String",value);
        startActivity(intent);
        ButterKnife.bind(this);
    }

    public void toActivty(Class name){
        Intent intent = new Intent(this,name);
        startActivity(intent);
        ButterKnife.bind(this);
    }

    public void toastLong(String content){
        Toast.makeText(this,content,Toast.LENGTH_LONG).show();
    }

    public void toastShort(String content){
        Toast.makeText(this,content,Toast.LENGTH_SHORT).show();
    }

    public PendingIntent getDefalutIntent(int flags){
        PendingIntent pendingIntent= PendingIntent.getActivity(this, 1, new Intent(), flags);
        return pendingIntent;
    }
}
