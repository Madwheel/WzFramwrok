package com.iwz.WzFramwork.mod.core.config.conf;


import android.content.res.AssetManager;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.iwz.WzFramwork.WzFramworkApplication;
import com.iwz.WzFramwork.base.api.ConfApi;
import com.iwz.WzFramwork.mod.core.config.CoreConfigMain;
import com.iwz.WzFramwork.mod.core.env.CoreEnvMain;

import java.io.InputStream;

public class CoreConfigConfApi extends ConfApi {
    private static CoreConfigConfApi instance = null;

    protected CoreConfigConfApi(CoreConfigMain main) {
        super(main);
    }

    public static CoreConfigConfApi getInstance(CoreConfigMain main) {
        if (instance == null) {
            instance = new CoreConfigConfApi(main);
        }
        return instance;
    }

    private String devData = "";
    private String productData = "";
    protected JSONObject mConfigJson;

    private String readFileStr(InputStream is) {
        String res = "";
        try {
            byte[] buf = new byte[is.available()];
            int n = is.read(buf);
            if (n > 0) {
                res = new String(buf, "UTF8");
            }
            is.close();
        } catch (Exception e) {
            res = "";
        }
        return res;
    }

    public void born() {
        String configJson;
        AssetManager aM = WzFramworkApplication.getmContext().getAssets();
        try {
            String config = "config." + CoreEnvMain.getInstance().getEnvName() + ".json";
            d("config", config);
            InputStream is = aM.open(config);
            configJson = readFileStr(is);
            is.close();
        } catch (Exception e) {
            if (CoreEnvMain.getInstance().getEnvName().equals("dev")) {
                configJson = devData;
            } else {
                configJson = productData;
            }
            d(e.toString());
        }
        d("CoreConfig", configJson);
        try {
            mConfigJson = JSON.parseObject(configJson);
        } catch (JSONException e) {
            d("NetCoreConf", "parseFail");
            mConfigJson = null;
        }
    }

    public <T> T getModConf(String key, Class<T> clazz) {
        d("getModConf", key);
        JSONObject item = mConfigJson.getJSONObject(key);
        if (item == null) {
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e) {
                return null;
            } catch (InstantiationException e) {
                return null;
            }
        }
        d("getmodconf", key + ":" + item.toJSONString());
        return JSON.parseObject(item.toJSONString(), clazz);
    }

    public JSONArray getJSONArrayConf(String key) {
        return mConfigJson.getJSONArray(key);
    }

    public JSONObject getJSONObjectConf(String key) {
        return mConfigJson.getJSONObject(key);
    }
}
