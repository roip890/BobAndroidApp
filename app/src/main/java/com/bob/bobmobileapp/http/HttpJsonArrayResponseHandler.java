package com.bob.bobmobileapp.http;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by user on 30/09/2017.
 */

public abstract class HttpJsonArrayResponseHandler {

    public abstract void OnResponse(JSONArray response);

    public abstract void OnErrorResponse(VolleyError error);

}
