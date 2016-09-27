package kh.android.cyanvibrate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;


/**
 * Project CyanVibrate
 * 接收磁贴点击更新
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class TileReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        if (TileManager.ACTION_UPDATE_STATUS.equals(intent.getAction())) {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
            boolean isRunning = sharedPreferences.getBoolean(VibrateService.PREFS_RUNNING, false);
            if (!isRunning) {
                context.startService(new Intent(context, VibrateService.class).setAction(TileManager.ACTION_UPDATE_STATUS));
            }
        }
    }
}
