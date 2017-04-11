package meizhuo.Clanner.design.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import meizhuo.Clanner.design.R;
import meizhuo.Clanner.design.base.BaseActivity;
import meizhuo.Clanner.design.presenter.TestPresenter;
import meizhuo.Clanner.design.utils.RxBus;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @BindView(R.id.testTextView)
    TextView testTextView;

    @Override
    protected int ContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
    }

    @OnClick(R.id.btn_test)
    public void onClick() {
        TestPresenter testPresenter = new TestPresenter();
        testPresenter.tset();
    }

    @Override
    protected void receiveMessage() {
        RxBus.getInstance().toObserverable(String.class).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                testTextView.setText(s);
            }
        });
    }
}
