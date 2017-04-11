package meizhuo.Clanner.design;

import android.app.Application;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import meizhuo.Clanner.design.utils.okhttp.CookieJarImpl;
import meizhuo.Clanner.design.utils.okhttp.PersistentCookieStore;
import okhttp3.OkHttpClient;

/**
 * Created by Clanner on 2017/4/11.
 */

public class App extends Application{
    private static App instance;
    private static OkHttpClient okHttpClient;

    public static App getInstances(){
        return instance;
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("DEBUG","App onCreate");
        instance = this;
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .cookieJar(cookieJar)
                //其他配置
                .build();
    }
}
