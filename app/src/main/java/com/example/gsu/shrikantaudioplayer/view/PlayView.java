package com.example.gsu.shrikantaudioplayer.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.example.gsu.shrikantaudioplayer.view.AudioOb;
import com.example.gsu.shrikantaudioplayer.view.MainActivity;
import com.example.gsu.shrikantaudioplayer.R;
import com.example.gsu.shrikantaudioplayer.audio.BaseAudioOb;
import com.example.gsu.shrikantaudioplayer.audio.MusicController;
import com.example.gsu.shrikantaudioplayer.util.UtilLog;
import com.example.gsu.shrikantaudioplayer.util.UtilTime;

/**
 * Created by Yan on 3/16/17.
 */

public class PlayView extends LinearLayout implements MusicController.IPlayerStatus {

    private final View view;
    private final MusicController controller;
    private final ArrayList<BaseAudioOb> playList;
    private MainActivity mContext;

    @BindView(R.id.main_player_title) TextView name;
    @BindView(R.id.main_play_play) ImageView playBt;
    @BindView (R.id.pb_play_loading) ProgressBar progressBar;
    @BindView(R.id.main_play_seekbar) SeekBar seekBar;
    @BindView(R.id.main_play_totaltime) TextView totalTime;
    @BindView(R.id.play_play_seektime) TextView seekTime;


    @OnClick(R.id.main_play__last)
    public void previosu() {
        controller.playPrevious();
        name.setText(((AudioOb)playList.get(controller.position)).getName());
    }

    @OnClick(R.id.main_play__next)
    public void next() {
        controller.playNext();
        name.setText(((AudioOb)playList.get(controller.position)).getName());
    }

    @OnClick(R.id.main_play__last15)
    public void last15() {
        if(seekBar.getProgress()-15000>seekBar.getMax()){
            controller.play(seekBar.getMax());
        }else{
            controller.play(seekBar.getProgress()-15000);
        }
    }

    @OnClick(R.id.main_play_next15)
    public void next15() {
        if (seekBar.getProgress()+15000>seekBar.getMax()){
            controller.play(seekBar.getMax());
        }else{
            controller.play(seekBar.getProgress()+15000);
        }

    }


    @OnClick(R.id.main_play_play)
    public void play(){
        if(!controller.isPlaying){
            UtilLog.logD("Music","Play");
            mContext.toastShort("Play");
            controller.play();
        }else {
            controller.pause();
        }

    }

    public PlayView(Context context) {
        super(context);
        this.mContext = (MainActivity)context;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.viewpage_player, this);
        ButterKnife.bind(this,view);
        controller = MusicController.getInstance(mContext);
        controller.setPlayList(mContext.getContent());
        controller.addListener("PlayView", this);

        playList = controller.getPlayList();

        name.setText(((AudioOb)playList.get(0)).getName());

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                controller.play(seekBar.getProgress());
            }
        });
    }

    public void update(){

    }

    @Override
    public void onLoading() {
        prepareStatus();

    }

    private void prepareStatus() {

        playBt.setBackgroundResource(R.drawable.playscreen_play_pause);
        playBt.setVisibility(INVISIBLE);
        progressBar.setVisibility(VISIBLE);

    }

    private void pauseStatus() {

        playBt.setBackgroundResource(R.drawable.playscreen_play_pause);
        playBt.setVisibility(VISIBLE);
        progressBar.setVisibility(INVISIBLE);

    }

    private void startStatus() {

        playBt.setBackgroundResource(R.drawable.playscreen_play_play);

    }

    @Override
    public void onProgress(int i) {
        seekBar.setProgress(i);
        seekTime.setText(UtilTime.secToTime(i));
    }

    @Override
    public void onError(String error) {

        mContext.toastShort(error);
    }

    @Override
    public void onPrepared() {

    }

    @Override
    public void onSeekComplete() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onUpdateCache(int i) {

    }

    @Override
    public void onPause() {

        startStatus();

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart(int i) {

        mContext.toastShort("started");
        seekBar.setMax(i);
        totalTime.setText(UtilTime.secToTime(i));
        pauseStatus();

    }

    @Override
    public void onInitComplete() {

    }
}
