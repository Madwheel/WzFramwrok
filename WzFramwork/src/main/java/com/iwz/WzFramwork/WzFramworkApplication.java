package com.iwz.WzFramwork;

import android.content.Context;

/**
 * author : 亚辉
 * e-mail : 2372680617@qq.com
 * date   : 2020/9/2713:28
 * desc   :
 */
public class WzFramworkApplication {
    private static Context mContext;
    private static String mMode;
    private static String mChannel;

    public static void setChannel(Context context, String mode, String channel) {
        mContext = context;
        mMode = mode;
        mChannel = channel;
    }

    public static Context getmContext() {
        return mContext;
    }

    public static String getmMode() {
        return mMode;
    }

    public static String getmChannel() {
        return mChannel;
    }
}
