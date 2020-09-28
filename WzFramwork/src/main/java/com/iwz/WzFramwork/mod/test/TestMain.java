package com.iwz.WzFramwork.mod.test;

import com.iwz.WzFramwork.base.main.ModMain;
import com.iwz.WzFramwork.mod.test.control.TestControlApp;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/9/2517:05
 * desc   :
 */
public class TestMain extends ModMain {
    private static TestMain mTestMain = null;
    private final TestControlApp mControl;

    public static TestMain getInstance() {
        synchronized (TestMain.class) {
            if (mTestMain == null) {
                mTestMain = new TestMain();
            }
        }
        return mTestMain;
    }

    private TestMain() {
        mControl = TestControlApp.getInstance(this);
    }

    @Override
    public String getModName() {
        return "TestMain";
    }

    @Override
    public void born() {
        super.born();
        mControl.born();
    }

    public String getName() {
        return mControl.getTest();
    }
}
