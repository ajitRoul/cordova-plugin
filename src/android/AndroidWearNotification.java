package cordova.basicnotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.cordova.hellocordova.R;

/**
 * This class echoes a string called from JavaScript.
 *
 * @param action,[ContentTitle,ContentText,SubText,smallicon ]
 */
public class AndroidWearNotification extends CordovaPlugin {
    public static final int NOTIFICATION_ID = 5;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean ret = false;
        if (action.equalsIgnoreCase("basicNotification")) {
            ret = this.basicNotification(action, args, callbackContext);
        }
        this.response(ret, callbackContext);
        return true;
    }

    private boolean basicNotification(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        boolean ret = false;
        try {
            String title = args.getString(0) != null ? args.getString(0) : "BasicNotifications";
            ;
            String contentText = args.getString(1) != null ? args.getString(1) : "please provide custom text in 2nd parameter";
            String subText = args.getString(2) != null ? args.getString(2) : "please provide custom text in 3nd parameter";
            String smallIcon = args.getString(3);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.cordova.getActivity().getApplicationContext());
            builder.setAutoCancel(true);
            builder.setContentTitle(title);
            builder.setContentText(contentText);
            builder.setSubText(subText);

            if (smallIcon != null) {
                if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder.setSmallIcon(R.drawable.flight_takeoff);
                    int color = 0xff005eb8;
                    builder.setColor(color);
                }

            }

            builder.setDefaults(Notification.DEFAULT_VIBRATE);
            NotificationManager notificationManager = (NotificationManager) this.cordova.getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.notify(NOTIFICATION_ID, builder.build());
            ret = true;
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

    private void response(boolean ret, CallbackContext callbackContext) {
        if (ret) {
            callbackContext.success("notification sent successfully");
        } else {
            callbackContext.error("exception happened.Please check log");
        }
    }
}
