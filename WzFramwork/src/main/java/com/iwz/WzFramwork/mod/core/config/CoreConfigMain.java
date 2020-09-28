package com.iwz.WzFramwork.mod.core.config;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.iwz.WzFramwork.base.main.ModMain;
import com.iwz.WzFramwork.mod.core.config.conf.CoreConfigConfApi;

public class CoreConfigMain extends ModMain {
    public String getModName() {
        return "CoreConfigMain";
    }

    private static CoreConfigMain instance = new CoreConfigMain();

    private CoreConfigMain() {
    }

    public static CoreConfigMain getInstance() {
        return instance;
    }

    public CoreConfigConfApi pConfApi;

    public void born() {
        super.born();
        pConfApi = CoreConfigConfApi.getInstance(this);
        pConfApi.born();

    }

    public <T> T getModConf(String key, Class<T> clazz) {
        return pConfApi.getModConf(key, clazz);
    }

    public JSONArray getJSONArrayConf(String key) {
        return pConfApi.getJSONArrayConf(key);
    }

    public JSONObject getJSONObjectConf(String key) {
        return pConfApi.getJSONObjectConf(key);
    }
}
