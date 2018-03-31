package com.bob.bobmobileapp.http;

import java.util.HashMap;

/**
 * Created by User on 07/02/2018.
 */

public class HttpRequestsManager {

    private static HttpRequestsManager instance;
    private String serverUrl;

    public static HttpRequestsManager get() {
        if (instance == null) {
            instance = new HttpRequestsManager();
        }
        return instance;
    }

    public void registerRequest(String name, String mail, String password, HttpJsonResponseHandler responeHandler) {
        HashMap<String, String> headers = new HashMap<String, String>();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("name", name);
        params.put("mail", mail);
        params.put("password", password);
        HttpController.get().makeJsonRequest(this.serverUrl + "/register", null, responeHandler, headers, params);
    }

    public void loginRequest(String mail, String password, HttpJsonResponseHandler responeHandler) {
        HashMap<String, String> headers = new HashMap<String, String>();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("mail", mail);
        params.put("password", password);
        HttpController.get().makeJsonRequest(this.serverUrl + "/login", null, responeHandler, headers, params);
    }

    public void getAllMenu(String hotelId, HttpJsonResponseHandler responeHandler) {
        HashMap<String, String> headers = new HashMap<String, String>();
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("hotelId", hotelId);
        HttpController.get().makeJsonRequest(this.serverUrl + "/login", null, responeHandler, headers, params);
    }


}
