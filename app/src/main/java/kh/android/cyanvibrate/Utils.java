package kh.android.cyanvibrate;

import android.app.ActivityManager;
import android.content.Context;

import java.util.ArrayList;

/**
 * Project CyanVibrate
 * <p/>
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class Utils {
    public static String decodeSec (int sec) {
        int min = sec/60;
        int sec_ = sec - min*60;
        return min + ":" + sec_;
    }
    public static boolean isServiceRunning(Context context, String serviceName)
    {
        ActivityManager myManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ArrayList<ActivityManager.RunningServiceInfo> runningService = (ArrayList<ActivityManager.RunningServiceInfo>) myManager.getRunningServices(30);
        for(int i = 0 ; i<runningService.size();i++)
        {
            if(runningService.get(i).service.getClassName().equals(
                    serviceName
            ))
            {
                return true;
            }
        }
        return false;
    }
}
