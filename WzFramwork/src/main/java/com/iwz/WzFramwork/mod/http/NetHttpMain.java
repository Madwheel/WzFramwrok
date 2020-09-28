package com.iwz.WzFramwork.mod.http;


import com.iwz.WzFramwork.base.main.ModMain;
import com.iwz.WzFramwork.mod.http.serv.NetHttpServApi;

public class NetHttpMain extends ModMain {
    public String getModName() {
        return "NetHttpMain";
    }

    private static NetHttpMain mNetHttpMain;

    public static NetHttpMain getInstance() {
        if (mNetHttpMain == null) {
            synchronized (NetHttpMain.class) {
                if (mNetHttpMain == null) {
                    mNetHttpMain = new NetHttpMain();
                }
            }
        }
        return mNetHttpMain;
    }


    public NetHttpServApi pServApi;

    public void born() {
        super.born();
        pServApi = NetHttpServApi.getInstance(this);

    }

    public NetHttpServApi getServApi() {
        return pServApi;
    }
}
