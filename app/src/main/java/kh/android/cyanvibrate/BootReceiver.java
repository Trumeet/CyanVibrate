package kh.android.cyanvibrate;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Project CyanVibrate
 * 重启时重新添加磁贴
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class BootReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            TileManager.publish(context, TileManager.build(context).build());
        }
    }
}
