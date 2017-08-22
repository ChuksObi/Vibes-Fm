package com.example.chuks.vibefmbenin;



import android.media.AudioManager;
import android.media.MediaPlayer;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.IOException;


public class RadioFragment extends Fragment {

    //Play & Pause Button
    Button mButton;

    //MediaPlayer
    MediaPlayer mMediaPlayer;

    //Stream Link
    String mStream = "http://74.50.122.103:9516/;";

    //started
    boolean started = false;

    //prepared
    boolean prepared = false;

    public RadioFragment() {
        // Required empty public constructor

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_radio, container, false);

        //connecting xml button
        mButton = (Button) v.findViewById(R.id.radio_button);
        mButton.setEnabled(false);
        mButton.setText("LOADING");

        //ini... Media Player & setting stream type
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        new PlayerTask().execute(mStream);

        //Button Listener
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(started){
                    started = false;
                    mMediaPlayer.pause();
                    mButton.setText("PLAY");
                }
                else {
                    started = true;
                    mMediaPlayer.start();
                    mButton.setText("PAUSE");
                }

            }
        });

        return v;
    }

    private class PlayerTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... strings) {

            try {
                mMediaPlayer.setDataSource(strings[0]);
                mMediaPlayer.prepare();
                prepared = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return prepared;

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            mButton.setEnabled(true);
            mButton.setText("PLAY");

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (started){
            mMediaPlayer.pause();
            mMediaPlayer.release();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (started){
            mMediaPlayer.start();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (prepared) {
            mMediaPlayer.release();
        }
    }

}
