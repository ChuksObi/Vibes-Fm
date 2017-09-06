package com.example.chuks.vibefmbenin;


import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RadioFragment extends Fragment {

    //Play & Pause Button
    Button mPlayButton = null;
    Button mStopButton = null;
    View v;

    public RadioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_radio, container, false);

        //Allow hardware audio buttons to control volume
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        clickListeners(); //Start click listeners

        return v;
    }

    private void clickListeners() {

        //connecting xml button to java
        //Play button
        mPlayButton = (Button) v.findViewById(R.id.radio_button);
        //stop button
        mStopButton = (Button) v.findViewById(R.id.button);

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getActivity(), RadioService.class);
                intent.putExtra(RadioService.START_PLAY, true);
                getActivity().startService(intent);
            }
        });
        //Stop button
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().stopService(new Intent(getActivity(), RadioService.class));
            }
        });


    }

}
