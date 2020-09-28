package com.iwz.WzFramwork.mod;
//CMS详细介绍、垃圾回收算法、CMS、LMK：low memory kill

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import com.iwz.WzFramwork.WzFramworkApplication;
import com.iwz.WzFramwork.base.EAppPhase;
import com.iwz.WzFramwork.base.main.ModMain;
import com.iwz.WzFramwork.mod.core.config.CoreConfigMain;
import com.iwz.WzFramwork.mod.core.env.CoreEnvMain;
import com.iwz.WzFramwork.mod.core.signal.CoreSignalMain;
import com.iwz.WzFramwork.mod.test.TestMain;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMgr {
    private List<ModMain> allMods;
    public MyHandlerThread mThread;

    public interface IObtainAllMods {
        List<ModMain> preGetCoreMods();

        List<ModMain> getCoreMods();

        List<ModMain> preGetBusMods();

        List<ModMain> getBusMods();

        List<ModMain> preGetToolMods();

        List<ModMain> getToolMods();

        List<ModMain> preGetIoMods();

        List<ModMain> getIoMods();

        List<ModMain> preGetNetMods();

        List<ModMain> getNetMods();

        List<ModMain> preGetSdkMods();

        List<ModMain> getSdkMods();

        List<ModMain> preGetBizMods();

        List<ModMain> getBizMods();
    }

    protected abstract IObtainAllMods obtainAllMods();

    public List<ModMain> getAllMods() {
        if (allMods == null) {
            allMods = new ArrayList<>();
        }
        IObtainAllMods iObtainAllMods = obtainAllMods();
        //core
        getMods(iObtainAllMods.preGetCoreMods());
        allMods.add(TestMain.getInstance());
        allMods.add(CoreEnvMain.getInstance());
        allMods.add(CoreConfigMain.getInstance());
        allMods.add(CoreSignalMain.getInstance());
        getMods(iObtainAllMods.getCoreMods());
        //bus
        getMods(iObtainAllMods.preGetBusMods());
        getMods(iObtainAllMods.getBusMods());
        //tool
        getMods(iObtainAllMods.preGetToolMods());
        getMods(iObtainAllMods.getToolMods());
        //io
        getMods(iObtainAllMods.preGetIoMods());
        getMods(iObtainAllMods.getIoMods());
        //net
        getMods(iObtainAllMods.preGetNetMods());
        getMods(iObtainAllMods.getNetMods());
        //sdk
        getMods(iObtainAllMods.preGetSdkMods());
        getMods(iObtainAllMods.getSdkMods());
        //biz
        getMods(iObtainAllMods.preGetBizMods());
        getMods(iObtainAllMods.getBizMods());
        return allMods;
    }

    private void getMods(List<ModMain> bizMods) {
        if (bizMods != null && bizMods.size() > 0) {
            for (int i = 0; i < bizMods.size(); i++) {
                allMods.add(bizMods.get(i));
            }
        }
    }

    public void born() {
        System.out.println("born ");
        for (ModMain main : allMods) {
            main.born();
        }
    }

    public void create() {
        System.out.println("create ");

        for (ModMain main : allMods) {
            main.create();
        }
    }

    public void active() {
        for (ModMain main : allMods) {
            main.active();
        }
    }

    public void phase(EAppPhase phase) {
        for (ModMain main : allMods) {
            main.phase(phase);
        }
    }

    public void deactive() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).deactive();
        }
    }

    public void destroy() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).destroy();
        }
    }

    public void terminate() {
        for (int i = allMods.size() - 1; i >= 0; i--) {
            allMods.get(i).terminate();
        }
    }

    public void loop(Context context, String mode, String channel) {
        WzFramworkApplication.setChannel(context, mode, channel);
        mThread = new MyHandlerThread("BaseMgr");
        mThread.start();
    }

    public Handler getHandler() {
        return mThread.getHandler();
    }
}

class MyHandlerThread extends HandlerThread {

    Handler mHandler;

    public MyHandlerThread(String name) {
        super(name);
    }

    @Override
    protected void onLooperPrepared() {
        mHandler = new Handler(getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // process incoming messages here
                // this will run in non-ui/background thread
            }
        };
    }

    public Handler getHandler() {
        return mHandler;
    }
}