package meizhuo.Clanner.design.model;

import android.util.Log;

import meizhuo.Clanner.design.entity.TestEntity;
import meizhuo.Clanner.design.presenter.TestPresenter;
import meizhuo.Clanner.design.utils.RetrofitManager;
import meizhuo.Clanner.design.utils.RxBus;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

/**
 * Created by Clanner on 2017/4/11.
 */

public class TestModel {
    private TestPresenter testPresenter;

    public TestModel(TestPresenter testPresenter){
        this.testPresenter = testPresenter;
        TestImp testImp = RetrofitManager.getRetrofit().create(TestImp.class);
        Call<TestEntity> call = testImp.test();
        call.enqueue(new Callback<TestEntity>() {
            @Override
            public void onResponse(Call<TestEntity> call, Response<TestEntity> response) {
                String s = response.body().getItems().get(0).getName();
                Log.e("DEBUG",s);
                RxBus.getInstance().post(s);
            }

            @Override
            public void onFailure(Call<TestEntity> call, Throwable t) {
                Log.e("DEBUG","请求失败");
            }
        });
    }

    private interface TestImp{
        @GET("search/repositories?q=javascript&sort=stars")
        Call<TestEntity> test();
    }
}
