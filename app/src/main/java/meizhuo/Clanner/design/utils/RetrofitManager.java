package meizhuo.Clanner.design.utils;

import meizhuo.Clanner.design.App;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Clanner on 2016/11/29.
 */
public class RetrofitManager {

    private RetrofitManager() {
    }

    public static Retrofit getRetrofit() {
        return SingleRetrofitManager.retrofit;
    }

    private static final class SingleRetrofitManager {
        private static final Retrofit retrofit = new Retrofit.Builder()
                .client(App.getInstances().getOkHttpClient())
                .baseUrl("https://api.github.com/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
//                上传文件
//                .addConverterFactory(new FileRequestBodyConverterFactory())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                //增加返回值为Oservable<T>的支持
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
