package com.iwz.WzFramwork.mod.http.model;


import java.io.IOException;

public interface IHttpNetCallback {
    void onFailure(IOException e);
    void onResponse(String response) ;
}
