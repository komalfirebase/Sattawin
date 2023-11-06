package satta.win;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage.getData().isEmpty()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                showNotifiaction(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
            }

        }
        else {
            showNotifiaction(remoteMessage.getData());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotifiaction(Map<String, String> data) {
        String Title=data.get("title".toString());
        String body=data.get("body".toString());

        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANEL_ID="satta.win";
        if (Build.VERSION.SDK_INT>=Build.VERSION.PREVIEW_SDK_INT);
        {
            Uri sound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("EDMT Channel");
            notificationChannel.enableLights(true);

            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    /*    @Override
        public void onMessageReceived(RemoteMessage remoteMessage) {
            super.onMessageReceived(remoteMessage);
            showNotifiaction(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
        }
    */
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showNotifiaction(String title, String body)
    {



        NotificationManager notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANEL_ID="satta.win";
        if (Build.VERSION.SDK_INT>=Build.VERSION.PREVIEW_SDK_INT);
        {
            NotificationChannel notificationChannel=new NotificationChannel(NOTIFICATION_CHANEL_ID,"Notification",NotificationManager.IMPORTANCE_DEFAULT);
            notificationChannel.setDescription("EDMT Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

}