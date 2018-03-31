package com.bob.bobmobileapp.http;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;

/**
 * Created by user on 30/09/2017.
 */

public abstract class HttpImageResponseHandler {

    public abstract void OnResponse(ImageLoader.ImageContainer response, boolean arg1);

    public abstract void OnErrorResponse(VolleyError error);

}
