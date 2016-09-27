package kh.android.cyanvibrate;


import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.preference.PreferenceManager;

/**
 * Project CyanVibrate
 * <p/>
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class VibrateService extends IntentService{
    private SharedPreferences mPrefs;
    public static Vibrator mVibrator;
    int mSec;
    public static final String PREFS_RUNNING = "running"; // Simple! But isServiceRunning is not work?

    public VibrateService() {
        super("VibrateService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (TileManager.ACTION_UPDATE_STATUS.equals(intent.getAction())) {
            mPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            mPrefs.edit().putBoolean(PREFS_RUNNING, true).apply();
            mSec = mPrefs.getInt(TileManager.PREFS_SEC, TileManager.DEF_SEC);
            if (mSec > TileManager.MAX_SEC)
                mSec = TileManager.MAX_SEC;
            if (mVibrator == null) {
                mVibrator = (Vibrator)getApplicationContext().getSystemService(VIBRATOR_SERVICE);
            }
            TileManager.publish(VibrateService.this, TileManager.build(VibrateService.this).setLabel(Utils.decodeSec(mSec)).build());
            mVibrator.vibrate(mSec * 1000);

            for (int i = 0; i < mSec; i++) {
                TileManager.publish(VibrateService.this, TileManager.build(VibrateService.this).setLabel(Utils.decodeSec(mSec - i)).build());
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {

                }
            }
            TileManager.publish(VibrateService.this, TileManager.build(VibrateService.this).build());
            mPrefs.edit().putBoolean(PREFS_RUNNING, false).apply();
        }
    }
}
