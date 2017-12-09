package com.example.chuks.vibefmbenin;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyfishjy.library.RippleBackground;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class RadioFragment extends Fragment {

    TextView mTextViewHead;
    TextView mTextVievOap;
    TextView mTextViewTopic;
    Context mContext;
    RippleBackground rippleBackground;
    ImageView buttonPlay;
    ImageView buttonStop;
    Calendar calendar;
    Date currentTime;
    int FLAG = 0;
    View v;


    public RadioFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_radio, container, false);


        rippleBackground = (RippleBackground) v.findViewById(R.id.content);
        buttonPlay = (ImageView) v.findViewById(R.id.play_button);
        buttonStop = (ImageView) v.findViewById(R.id.stop_button);
        buttonPlay.setImageResource(R.drawable.ic_multimedia_play_key);
        mTextViewHead = (TextView) v.findViewById(R.id.schedule_head);
        mTextVievOap = (TextView) v.findViewById(R.id.oap);
        mTextViewTopic = (TextView) v.findViewById(R.id.schedule_topic);

            //Allow hardware audio buttons to control volume
            getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        DateFormat df = new SimpleDateFormat("HH:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        Log.i("Time of the Day", date);


            //registerReceiver(broadcastReceiver, new IntentFilter(RadioService.BROADCAST_ACTION));

            if (isOnline()) {


                buttonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FLAG = 1;
                        rippleBackground.startRippleAnimation();
                        buttonPlay.setImageResource(R.drawable.ic_circle_outline);

                        Intent intent = new Intent(getActivity(), RadioService.class);
                        intent.putExtra(RadioService.START_PLAY, true);
                        getActivity().startService(intent);


                        buttonPlay.setImageResource(R.drawable.ic_circle_outline);
                        rippleBackground.startRippleAnimation();

                        Log.i("Value Clicked: ", Integer.toString(FLAG));

                    }
                });

                buttonStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rippleBackground.stopRippleAnimation();
                        buttonPlay.setImageResource(R.drawable.ic_multimedia_play_key);
                        getActivity().stopService(new Intent(getActivity(), RadioService.class));
                    }
                });

            } else {

                buttonPlay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "No internet Connection", Toast.LENGTH_SHORT).show();

                    }
                });

                buttonStop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "No internet Connection", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        updateText();

          return v;
    }




    public boolean isOnline() {
            ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnectedOrConnecting()) {
                return true;
            }
            return false;
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            Log.i("Value Saved Before: ", Integer.toString(FLAG));
            if(FLAG == 1){
                outState.putInt("key", FLAG);
                Log.i("Value Saved After: ", Integer.toString(FLAG));



            }
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.i("Value Working: ", Integer.toString(FLAG));

        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
            FLAG = savedInstanceState.getInt("key", 0);
            Log.i("Value Recreated: ", Integer.toString(FLAG));
            buttonPlay.setImageResource(R.drawable.ic_circle_outline);
            rippleBackground.startRippleAnimation();

        }
    }






    public void updateText(){
        /*
         * Getting the day of the week
         */
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);

        DateFormat df = new SimpleDateFormat("HH:mm a");
        String date = df.format(Calendar.getInstance().getTime());
        Log.i("Time of the Day", date);
        final String six = "6:00 AM";
        final String eleven = "11:00 AM";
        final String three ="15:00 AM";
        final String four = "16:00 PM";
        final String seven = "19:00 PM";

        //currentTime = Calendar.getInstance().getTime();

        switch (day) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");

                }
                if (date.equalsIgnoreCase(eleven)) {

                }
                if (date.equalsIgnoreCase(four)) {

                }
                if (date.equalsIgnoreCase(seven)) {

                }


            case Calendar.MONDAY:
                // Current day is Monday

                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists on the planet and also get a chance to win BIG.");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(four)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");

                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");
                }

            case Calendar.TUESDAY:
                // Current day is Tuesday
                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists on the planet and also get a chance to win BIG.");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(three)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");
                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");;
                }
            case Calendar.WEDNESDAY:
                // Current day is Wednesday
                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists on the planet and also get a chance to win BIG.");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(three)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");
                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");;
                }
            case Calendar.THURSDAY:
                // Current day is Thursday
                if (date.equals(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(three)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");
                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");;
                }
            case Calendar.FRIDAY:
                // Current day is Friday
                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(three)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");
                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");;
                }
            case Calendar.SATURDAY:
                // Current day is Saturday
                if (date.equalsIgnoreCase(six)) {
                    mTextViewHead.setText("Day Break Benin");
                    mTextVievOap.setText("Hosted by EDK AND KEMI");
                    mTextViewTopic.setText("Together they bring you laughs, great conversations and you’ll be sure to hear the greatest songs from the biggest artists");
                }
                if (date.equalsIgnoreCase(eleven)) {
                    mTextViewHead.setText("Swing Low");
                    mTextVievOap.setText("Hosted by ANNIE, MONIQUE AND SUPER SANDY");
                    mTextViewTopic.setText("Swing Low is an embodiment of the Very Best of Music, Health Tips, Request show in a single box.");
                }
                if (date.equalsIgnoreCase(three)) {
                    mTextViewHead.setText("Traffic");
                    mTextVievOap.setText("Hosted by LARRY D & ANETA");
                    mTextViewTopic.setText("Larry D & Aneta team up with Dj Unbeatable in the mix to deliver a great 4-hour radio experience");
                }
                if (date.equalsIgnoreCase(seven)) {
                    mTextViewHead.setText("After Hours");
                    mTextVievOap.setText("Hosted by MIZ PHILZ");
                    mTextViewTopic.setText("LET'S SET YOU FOR A PERFECT NIGHT REST");;
                }



        }
    }
}








