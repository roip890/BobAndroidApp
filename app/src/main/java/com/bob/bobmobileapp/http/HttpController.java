package com.bob.bobmobileapp.http;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.bob.bobmobileapp.BOBApplication;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 30/09/2017.
 */

public class HttpController {

    private static HttpController instance;
    private final String TAG_JSON_OBJECT = "jobj_req", TAG_JSON_ARRAY = "jarray_req",
            TAG_STRING = "string";

    public HttpController() {
    }

    public static HttpController get() {
        if (instance == null) {
            instance = new HttpController();
        }
        return instance;
    }


    //json request
    public void makeJsonRequest(String url, JSONObject jsonObject, final HttpJsonResponseHandler responseHandler, final HashMap<String, String> headers, final HashMap<String, String> params) {

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(url, jsonObject,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        responseHandler.OnResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        BOBApplication.get().addToRequestQueue(jsonObjReq, TAG_JSON_OBJECT);

    }

    public void makeJsonRequest(String url, JSONObject jsonObject, final HttpJsonResponseHandler responseHandler) {
        this.makeJsonRequest(url, jsonObject, responseHandler, new HashMap<String, String>(), new HashMap<String, String>());
    }


    //json array request
    public void makeJsonArrayRequest(String url, final HttpJsonArrayResponseHandler responseHandler, final HashMap<String, String> headers, final HashMap<String, String> params) {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        responseHandler.OnResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        BOBApplication.get().addToRequestQueue(jsonArrayRequest, TAG_JSON_ARRAY);

    }

    public void makeArrayRequest(String url, JSONArray jsonArray, final HttpJsonArrayResponseHandler responseHandler, final HashMap<String, String> headers, final HashMap<String, String> params) {
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, jsonArray,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        responseHandler.OnResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        BOBApplication.get().addToRequestQueue(jsonArrayRequest, TAG_JSON_ARRAY);
    }


    //string request
    public void makeStringRequest(String url, final HttpStringResponseHandler responseHandler, final HashMap<String, String> headers, final HashMap<String, String> params) {

        StringRequest jsonObjReq = new StringRequest(url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        responseHandler.OnResponse(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return headers;
            }

            @Override
            public Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };

        BOBApplication.get().addToRequestQueue(jsonObjReq, TAG_STRING);

    }

    public void makeStringRequest(String url, final HttpStringResponseHandler responseHandler) {
        this.makeStringRequest(url, responseHandler, new HashMap<String, String>(), new HashMap<String, String>());
    }


    //image request
    public void makeImageRequest(String url, final HttpImageResponseHandler responseHandler) {

        ImageLoader imageLoader = BOBApplication.get().getImageLoader();

        // If you are using normal ImageView
		imageLoader.get(url, new ImageLoader.ImageListener() {

			@Override
			public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
				responseHandler.OnResponse(response, arg1);
			}

            @Override
            public void onErrorResponse(VolleyError error) {
                responseHandler.OnErrorResponse(error);
            }

        });

    }


}
