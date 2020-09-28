package com.iwz.WzFramwork.mod.test.control;

import com.iwz.WzFramwork.base.app.ControlApp;
import com.iwz.WzFramwork.mod.test.TestMain;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/9/2517:10
 * desc   :
 */
public class TestControlApp extends ControlApp {
    private TestMain mMain;

    protected TestControlApp(TestMain main) {
        super(main);
        this.mMain = main;
    }

    private static TestControlApp mTestControlApp = null;

    public static TestControlApp getInstance(TestMain main) {
        synchronized (TestControlApp.class) {
            if (mTestControlApp == null) {
                mTestControlApp = new TestControlApp(main);
            }
        }
        return mTestControlApp;
    }

    private String test = "";

    @Override
    public void born() {
        super.born();
        test = "测试数据";
    }

    public String getTest() {
        return test;
    }
}
