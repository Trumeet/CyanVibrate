package kh.android.cyanvibrate;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import cyanogenmod.app.CMStatusBarManager;
import cyanogenmod.app.CustomTile;

/**
 * Project CyanVibrate
 * <p/>
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class TileManager {
    public static final String TAG = "CyanVibrateTile";
    public static final int ID = 0;
    public static final String ACTION_UPDATE_STATUS = "kh.android.cyanvibrate.ACTION_TOGGLE_TILE";

    public static final String PREFS_SEC = "sec";
    public static final String PREFS_REMOVED = "removed";
    public static final int MAX_SEC = 60;
    public static final int DEF_SEC = 3;

    public static CustomTile.Builder build (Context context) {
        Intent intent = new Intent();
        intent.setAction(ACTION_UPDATE_STATUS);
        CustomTile.Builder builder = new CustomTile.Builder(context);
        builder.setIcon(R.drawable.ic_vibration_white_24dp);
        builder.setOnClickIntent(PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        builder.setLabel(R.string.app_name);
        builder.shouldCollapsePanel(false);
        builder.setOnSettingsClickIntent(new Intent(context, SettingsActivity.class));
        return builder;
    }
    public static void publish (Context context, CustomTile customTile) {
        CMStatusBarManager.getInstance(context).publishTile(TAG, ID, customTile);
    }
}
