package huunhan.hn.com.appnhac.Service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import huunhan.hn.com.appnhac.Activity.MainActivity;
import huunhan.hn.com.appnhac.Activity.PlayNhacActivity;
import huunhan.hn.com.appnhac.Model.Baihat;
import huunhan.hn.com.appnhac.R;

public class MyService extends Service {

    Baihat baihat;
    public boolean isPlaying = false;

    public static final int ACTION_PAUSE = 1;
    public static final int ACTION_RESUME = 2;
    public static final int ACTION_NEXT = 3;
    public static final int ACTION_PRE = 4;
    public static final int ACTION_CLEAR = 5;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null) {
            Baihat mSong = intent.getParcelableExtra("baihatService");
            if (mSong != null) {
                baihat = mSong;
                isPlaying = true;
                sendMediaplayNotification(mSong);
            }
        }
        int actionMusic = intent.getIntExtra("action_service", 0);
        Log.e("number", actionMusic+" service");
        handleACtionMusic(actionMusic);
        return START_NOT_STICKY;
    }

    private void handleACtionMusic(int actionMusic) {
        switch (actionMusic) {
            case ACTION_PAUSE:
                if (isPlaying == true){
                    isPlaying = false;
                    sendMediaplayNotification(baihat);
                    sendActionToActivity(ACTION_PAUSE);
                }
                break;
            case ACTION_RESUME:
                if (isPlaying == false) {
                    isPlaying = true;
                    sendActionToActivity(ACTION_RESUME);
                    sendMediaplayNotification(baihat);
                }

                break;
            case ACTION_NEXT:
                sendActionToActivity(ACTION_NEXT);
                break;
            case ACTION_PRE:
                sendActionToActivity(ACTION_PRE);
                break;
            case ACTION_CLEAR:
                stopSelf();
                sendActionToActivity(ACTION_CLEAR);
                break;
        }
    }

    private void sendActionToActivity(int action) {
        Intent intent = new Intent("send_data_to_activity");
        intent.putExtra("action_music", action);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    private void sendMediaplayNotification(Baihat baihat) {
        Bitmap bitmap = getBitmapFromURL(baihat.getHinhbaihat());
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),5, intent, 0);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
                .setSmallIcon(R.drawable.ic_small_music)
                .setLargeIcon(bitmap)
                .setContentTitle(baihat.getTenbaihat())
                .setContentText(baihat.getCasi())
//                .setContentIntent(pendingIntent)

                .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(3));
        if (isPlaying) {
            notificationBuilder
                    .addAction(R.drawable.iconpreview, "Preview", getPendingIntent(this,ACTION_PRE))
                    .addAction(R.drawable.iconpause, "Pause", getPendingIntent(this,ACTION_PAUSE))
                    .addAction(R.drawable.iconnext, "Next", getPendingIntent(this,ACTION_NEXT))
                    .addAction(R.drawable.ic_clear, "Clear", getPendingIntent(this,ACTION_CLEAR));

        } else {
            notificationBuilder
                    .addAction(R.drawable.iconpreview, "Preview", getPendingIntent(this,ACTION_PRE))
                    .addAction(R.drawable.iconplay, "Resume", getPendingIntent(this,ACTION_RESUME))
                    .addAction(R.drawable.iconnext, "Next", getPendingIntent(this,ACTION_NEXT))
                    .addAction(R.drawable.ic_clear, "Clear", getPendingIntent(this,ACTION_CLEAR));
        }

        Notification notification = notificationBuilder.build();
        startForeground(1, notification);
    }

    private PendingIntent getPendingIntent(Context context, int action) {
        Intent intent = new Intent(this, MyReceiver.class);
        intent.putExtra("action_music", action);
        return PendingIntent.getBroadcast(context.getApplicationContext(), action, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }
}
