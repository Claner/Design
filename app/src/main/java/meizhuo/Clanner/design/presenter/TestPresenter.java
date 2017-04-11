package meizhuo.Clanner.design.presenter;

import meizhuo.Clanner.design.model.TestModel;

/**
 * Created by Clanner on 2017/4/11.
 */

public class TestPresenter {
    private TestModel testModel;

    public void tset(){
        testModel = new TestModel(this);
    }
}
