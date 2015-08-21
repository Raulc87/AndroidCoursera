package labs.android.dailyselfie;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Raul on 8/20/2015.
 */
public class SelfieNotificationReceiver extends BroadcastReceiver {

    public static final int SELFIE_NOTIFICATION_ID = 1;

    // Notification action elements
    private Intent mNotificationIntent;
    private PendingIntent mPendingIntent;

    //private final long[] mVibrationPattern = { 0, 200, 200, 300 };

    @Override
    public void onReceive(Context context, Intent intent) {
        mNotificationIntent = new Intent(context, DailySelfieMainActivity.class);
        mPendingIntent = PendingIntent.getActivity(context, 0, mNotificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        // Build notification
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setTicker("Time for another selfie")
                .setSmallIcon(R.drawable.ic_camera)
                .setAutoCancel(true)
                .setContentTitle(context.getString(R.string.app_name))
                .setContentText("Time for another selfie")
                .setContentIntent(mPendingIntent);

        // Get NotificationManager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(SELFIE_NOTIFICATION_ID, notificationBuilder.build());
    }
}