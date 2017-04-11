package meizhuo.Clanner.design.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;
import meizhuo.Clanner.design.R;

/**
 * Created by Clanner on 2017/4/11.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Intent intent;
    protected boolean isTranslucent = false;
    private ActionBar actionBar;

    protected abstract int ContentView();

    protected abstract void init(Bundle savedInstanceState);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(ContentView());
        ButterKnife.bind(this);
        intent = new Intent();
        init(savedInstanceState);
        if (isTranslucent) getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        receiveMessage();
    }

    protected void ChangeActivity(Class<?> cls) {
        intent.setClass(this, cls);
        startActivity(intent);
    }

    protected void ChangeActivity(Class<?> cls, String str) {
        intent.setClass(this, cls);
        intent.putExtra("STRING", str);
        startActivity(intent);
    }

    protected void ChangeActivity(Class<?> cls, int i) {
        intent.setClass(this, cls);
        intent.putExtra("INT", i);
        startActivity(intent);
    }

    protected void toast(String content) {
        Toast.makeText(this, content, Toast.LENGTH_SHORT).show();
    }

    protected void initToolBar(Toolbar toolbar, boolean isCanBack) {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isCanBack);
    }

    protected void initToolBar(Toolbar toolbar, int title, boolean isCanBack) {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isCanBack);
    }

    protected void initToolBar(Toolbar toolbar, String title, boolean isCanBack) {
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setTitle(title);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(isCanBack);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void receiveMessage(){

    }
}
