package kh.android.cyanvibrate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;


/**
 * Project CyanVibrate
 * <p/>
 * Created by 宇腾 on 2016/9/27.
 * Edited by 宇腾
 */
public class WelcomeActivity extends Activity{
    @Override
    public void onCreate (Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setNavigationBarColor(Color.TRANSPARENT);

        super.onCreate(savedInstanceState);
        TileManager.publish(this, TileManager.build(this).build());

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.message_ok);
        builder.setMessage(R.string.message_ok_msg);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setCancelable(false);
        final AlertDialog alertDialog = builder.create();
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.button_hide), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PackageManager p = getPackageManager();
                ComponentName componentName = new ComponentName(WelcomeActivity.this, WelcomeActivity.class); // activity which is first time open in manifiest file which is declare as <category android:name="android.intent.category.LAUNCHER" />
                p.setComponentEnabledSetting(componentName,PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
                finish();
            }
        });
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                alertDialog.getButton(AlertDialog.BUTTON_NEUTRAL).setTextColor(Color.RED);
            }
        });
        alertDialog.show();
    }
}
