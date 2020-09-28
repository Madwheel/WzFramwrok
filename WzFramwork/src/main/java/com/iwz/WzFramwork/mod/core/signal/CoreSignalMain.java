package com.iwz.WzFramwork.mod.core.signal;


import com.iwz.WzFramwork.base.main.ModMain;

/*
import sun.misc.Signal;
import sun.misc.SignalHandler;
*/
public class CoreSignalMain extends ModMain {
    public String getModName() {
        return "CoreSignalMain";
    }

    private static CoreSignalMain instance = new CoreSignalMain();

    private CoreSignalMain() {
    }

    public static CoreSignalMain getInstance() {
        return instance;
    }

    static final int MAX_T = 3;
    //static ExecutorService pool = Executors.newFixedThreadPool(MAX_T);

    private boolean mSigint = false;

    public void born() {
        /*
        Signal.handle(new Signal("INT"), new SignalHandler() {
            public void handle(Signal sig) {
                CoreSignalMain.this.setSigintOk();
            }
        });*/

    }

    public void setSigintOk() {
        mSigint = true;
    }

    public boolean isSigintOk() {
        return mSigint;
    }
}



