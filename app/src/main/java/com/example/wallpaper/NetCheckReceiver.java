package com.example.wallpaper;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class NetCheckReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (isNetworkConnected(context)) {
//            Toast.makeText(context, "on", Toast.LENGTH_SHORT).show();
            sendMessage("on", context);
        } else {
//            Toast.makeText(context, "off", Toast.LENGTH_SHORT).show();
            sendMessage("off", context);
        }

    }

    private boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }

    private void sendMessage(String msg, Context context) {

        Intent intent = new Intent("custom-event-name");
        intent.putExtra("message", msg);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
