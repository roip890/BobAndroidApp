package com.bob.bobmobileapp.http;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * Created by user on 30/09/2017.
 */

public abstract class HttpJsonResponseHandler {

    public abstract void OnResponse(JSONObject response);

    public abstract void OnErrorResponse(VolleyError error);

}
