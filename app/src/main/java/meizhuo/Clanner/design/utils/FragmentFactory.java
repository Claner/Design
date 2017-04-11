package meizhuo.Clanner.design.utils;

import android.os.Bundle;
import android.util.Log;

import meizhuo.Clanner.design.base.BaseFragment;


/**
 * Created by Clanner on 2016/11/29.
 */
public class FragmentFactory {
    public static <T extends BaseFragment> T createFragment(String className) {
        return createFragment(className, null);
    }

    public static <T extends BaseFragment> T createFragment(String className, Bundle content) {
        BaseFragment fragment = null;
        try {
            fragment = (BaseFragment) Class.forName(className).newInstance();
            if (content != null) {
                fragment.setArguments(content);
            }
        } catch (Exception e) {
            Log.e("TAG", "创建fragment异常");
        }
        return (T) fragment;
    }
}
