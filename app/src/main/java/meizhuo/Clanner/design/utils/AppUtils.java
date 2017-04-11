package meizhuo.Clanner.design.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.view.KeyEvent;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import meizhuo.Clanner.design.R;

/**
 * Created by Clanner on 2017/4/11.
 */

public class AppUtils {
    private final Handler handler = new Handler();
    private boolean isFirstBacking = false;
    private Runnable onBackTimeRunnable = new Runnable() {
        @Override
        public void run() {
            isFirstBacking = false;
        }
    };

    public static AppUtils getInstance() {
        return AppUtilsHolder.instance;
    }

    public String getAppVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(),
                    0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "1.0.0";
        }
    }

    public String timeStamp2String(String timeStamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        timeStamp = timeStamp.substring(0, timeStamp.length() - 3);
        int i = Integer.parseInt(timeStamp);
        String times = sdf.format(new Date(i * 1000L));
        return times;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event, Activity activity) {
        if (isFirstBacking) {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                activity.finish();
            }
            return true;
        } else {
            if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
                Toast.makeText(activity, R.string.exit, Toast.LENGTH_SHORT).show();
                isFirstBacking = true;
                handler.postDelayed(onBackTimeRunnable, 3000);
            }
            return true;
        }
    }

    private static final class AppUtilsHolder {
        private static final AppUtils instance = new AppUtils();
    }
}
