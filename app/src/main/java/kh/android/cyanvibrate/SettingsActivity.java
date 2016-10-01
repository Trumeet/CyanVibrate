package kh.android.cyanvibrate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

/**
 * Project CyanVibrate
 * <p/>
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class SettingsActivity extends Activity{
    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.layout_settings, null);
        final SeekBar seekBar = (SeekBar)view.findViewById(R.id.seekbar_long_set);
        seekBar.setMax(TileManager.MAX_SEC);
        final SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        seekBar.setProgress(sharedPreferences.getInt(TileManager.PREFS_SEC, TileManager.DEF_SEC));
        final Toast toast = Toast.makeText(SettingsActivity.this,
                getString(R.string.toast_progress_change, sharedPreferences.getInt(TileManager.PREFS_SEC, TileManager.DEF_SEC)),
                Toast.LENGTH_SHORT);
        toast.show();
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                toast.cancel();
                toast.setText(getString(R.string.toast_progress_change, seekBar.getProgress()));
                toast.show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        builder.setView(view);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sharedPreferences.edit().putInt(TileManager.PREFS_SEC, seekBar.getProgress()).apply();
                finish();
            }
        });
        builder.setTitle(R.string.text_time_set);
        builder.setCancelable(false);
        builder.setNegativeButton(android.R.string.cancel, null);
        builder.setNegativeButton(R.string.button_web, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    intent.setData(Uri.parse("https://lyt54.wordpress.com"));
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {

                }
                finish();
            }
        });
        builder.setNeutralButton(R.string.button_restore_icon, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PackageManager p = getPackageManager();
                ComponentName componentName = new ComponentName(SettingsActivity.this, WelcomeActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
                p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
                finish();
            }
        });
        builder.show();
    }
}
