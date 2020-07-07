package com.victoribarra.petagram;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


import java.util.Map;

import static android.content.ContentValues.TAG;

public class NotificacionService extends FirebaseMessagingService {

    public void onMessageReceived(RemoteMessage remoteMessage) {

        Map<String, String> data ;
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            data  = remoteMessage.getData();
            sendNotification(data);

        }

    }

    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
    }
    private void sendNotification(Map<String,String> data){
        //abrir perfil
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        //com.victoribarra.petagram.Follow
        Intent follow = new Intent();
        follow.setAction("follow");
        PendingIntent pendingfollow = PendingIntent.getBroadcast(this, 1, follow, PendingIntent.FLAG_UPDATE_CURRENT);

        //ver usuario
        Intent usuario = new Intent(this,Nusuario.class);
        usuario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingusuario = PendingIntent.getActivity(this,2,usuario,PendingIntent.FLAG_ONE_SHOT);

        String channelId = getString(R.string.default_notification_channel_id);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId)
                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
                        .setContentTitle(data.get("title"))
                        .setContentText(data.get("body"))
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent)
                        .addAction(R.drawable.icons8_usuario_de_g_nero_neutro_96,getString(R.string.nPerfil),pendingIntent)
                        .addAction(R.drawable.icons8_huellas_humanas_96,getString(R.string.nFollow),pendingfollow)
                        .addAction(R.drawable.icons8_visible_24,getString(R.string.nUsuario),pendingusuario);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());

    }




}
