package com.example.chuks.vibefmbenin;

import android.content.Context;
import android.content.Intent;

public class IntentReceiver extends android.content.BroadcastReceiver {

    public void onReceive(Context ctx, Intent intent) {
        if (intent.getAction().equals(
                android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
            Intent myIntent = new Intent(ctx, RadioService.class);
            ctx.stopService(myIntent);
        }
    }
}