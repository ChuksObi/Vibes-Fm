package com.example.chuks.vibefmbenin;


import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.IOException;

public class RadioService extends Service {

    public static String START_PLAY = "START_PLAY";
    private static int classID = 579; // just a number
    public static final String BROADCAST_ACTION = "com.example.chuks.vibefmbenin";

    //Settings
    Settings settings = new Settings();

    //Variables
    private boolean isPlaying = false;
    private MediaPlayer mMediaPlayer; //The media player instance

    //Audio Manager
    private AudioManager audioManager;

    //Handle incoming phone calls
    private boolean ongoingCall = false;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;

    Notification notification;



    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Starts the streaming service
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent.getBooleanExtra(START_PLAY, false)) {
            playy();
        }
        return Service.START_STICKY;
    }


//    public static final String BROADCAST_ACTION = "com.example.chuks.vibefmbenin";
//    Intent intent = new Intent(BROADCAST_ACTION);
//    sendBroadcast(intent);


    /**
     * Starts radio URL stream
     */

    private void playy() {


        Intent intentUI = new Intent(BROADCAST_ACTION);
        sendBroadcast(intentUI);

        //Check connectivity status
        if (isOnline()) {
            //Check if player already streaming
            if (!isPlaying) {
                isPlaying = true;

                //Return to the current activity
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);

                //Build and show notification for radio playing
                 notification = new Notification.Builder(getApplicationContext())
                        .setContentTitle(settings.getRadioName())
                        .setContentText(settings.getNotificationMessage())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pi)
                        .build();


                //Get stream URL
                mMediaPlayer = new MediaPlayer();
                mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mMediaPlayer.setDataSource(settings.getRadioStreamUrl());
                    mMediaPlayer.prepareAsync();
                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    public void onPrepared(MediaPlayer mp) {
                        mMediaPlayer.start(); //Start radio stream
                    }
                });


                startForeground(classID, notification);

                //Display toast notification
                Toast.makeText(getApplicationContext(), settings.getPlayNotificationMessage(),
                        Toast.LENGTH_LONG).show();

                audioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);

                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(focusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN);


                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED && isOnline()) {
                // other app had stopped playing song now , so u can do u stuff now .

                }



            }
        } else {
            //Display no connectivity warning
            Toast.makeText(getApplicationContext(), "No internet connection",
                    Toast.LENGTH_LONG).show();
        }


    }

    @Override
    public void onCreate() {
        super.onCreate();
        /*
         * Perform one-time setup procedures
         */

        // Manage incoming phone calls during playback.
        // Stop MediaPlayer on incoming call,
        // Resume on hangup.
        callStateListener();
        //ACTION_AUDIO_BECOMING_NOISY -- change in audio outputs -- BroadcastReceiver
        registerBecomingNoisyReceiver();
    }

    /**
     * Stops the stream if activity destroyed
     */
    @Override
    public void onDestroy() {
        stop();
    }


    /**
     * Stops audio from the active service
     */
    private void stop() {
        if (isPlaying) {
            isPlaying = false;
            if (mMediaPlayer != null) {
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
            stopForeground(true);
        }
    }


    /**
     * Checks if there is a data or internet connection before starting the stream.
     * @return online status boolean
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    private AudioManager.OnAudioFocusChangeListener focusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    AudioManager audioManager =(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    switch (focusChange) {

                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) :
                            // Lower the volume while ducking.
                            mMediaPlayer.setVolume(0.2f, 0.2f);
                            break;
                        case (AudioManager.AUDIOFOCUS_LOSS_TRANSIENT) :
                            // Lost focus for a short time, but we have to stop
                            // we can't pause live streams, so we stop
                            stop();
                            break;

                        case (AudioManager.AUDIOFOCUS_LOSS) :

                            // Lost focus for an unbounded amount of time: stop playback and release media player
                            stop(); ;
                            break;

                        case (AudioManager.AUDIOFOCUS_GAIN) :
                            // Return the volume to normal and resume if paused.
                            mMediaPlayer.setVolume(1f, 1f);
                            mMediaPlayer.start();
                            break;

                        default: break;
                    }
                }
            };





    //Handle incoming phone calls
    private boolean callStateListener() {
        // Get the telephony manager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Starting listening for PhoneState changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    //if at least one call exists or the phone is ringing
                    //pause the MediaPlayer
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        if (mMediaPlayer != null) {
                            stop();
                            ongoingCall = true;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        // Phone idle. Start playing.
                        if (mMediaPlayer != null) {
                            if (ongoingCall) {
                                ongoingCall = false;
                                playy();
                            }
                        }
                        break;
                }
            }
        };
        // Register the listener with the telephony manager
        // Listen for changes to the device call state.
        telephonyManager.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);
        return true;
    }



    //Becoming noisy
    private BroadcastReceiver becomingNoisyReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //pause audio on ACTION_AUDIO_BECOMING_NOISY
            stop();
        }
    };

    private void registerBecomingNoisyReceiver() {
        //register after getting audio focus
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        registerReceiver(becomingNoisyReceiver, intentFilter);
    }




}
