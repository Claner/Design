package meizhuo.Clanner.design.base;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import butterknife.ButterKnife;
import meizhuo.Clanner.design.R;

/**
 * Created by Clanner on 2017/3/28.
 */

public abstract class FragmentActivity extends AppCompatActivity {
    private ActionBar actionBar;
    protected boolean isTranslucent = false;

    protected abstract BaseFragment getFirstFragment();

    protected abstract boolean setTranslucent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (setTranslucent()) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_base);
        ButterKnife.bind(this);
        //避免重复添加Fragment
        if (getSupportFragmentManager().getFragments() == null) {
            BaseFragment firstFragment = getFirstFragment();
            if (firstFragment != null) {
                addFragment(firstFragment);
            }
        }
        if (isTranslucent) getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        receiveMessage();
    }

    /**
     * 添加Fragment
     *
     * @param fragment
     */
    protected void addFragment(BaseFragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment, fragment.getClass().getSimpleName())
                    .addToBackStack(fragment.getClass().getSimpleName())
                    .commitAllowingStateLoss();
        }
    }

    /**
     * 移除Fragment
     */
    protected void removeFragment() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else {
            finish();
        }
    }

    protected void initToolbar(Toolbar toolbar, boolean isCanBack){
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

    protected void receiveMessage(){

    }
}
