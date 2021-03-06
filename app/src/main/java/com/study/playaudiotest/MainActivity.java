package com.study.playaudiotest;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

public class MainActivity extends Activity implements AdapterView.OnClickListener{

    private Button play;
    private Button pause;
    private Button stop;

    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (Button) findViewById(R.id.play);
        play.setOnClickListener(this);

        pause = (Button)findViewById(R.id.pause);
        pause.setOnClickListener(this);

        stop = (Button) findViewById(R.id.stop);
        stop.setOnClickListener(this);

        initMediaPlayer();
    }

    private void initMediaPlayer() {
        try {
            String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
            String fileName = "music.mp3";
            File file = new File(baseDir + File.separator + fileName);
            Toast.makeText(this, file.getPath().toString(), Toast.LENGTH_LONG).show();

            mediaPlayer.setDataSource(file.getPath().toString());
            mediaPlayer.prepare();
        } catch (Exception e) {
            //e.printStackTrace();
            Log.d("Fuck", "Fuck");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.play:
                if(!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.pause:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.stop:
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }
}
