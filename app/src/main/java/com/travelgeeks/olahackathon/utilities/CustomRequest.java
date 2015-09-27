package com.travelgeeks.olahackathon.utilities;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.travelgeeks.olahackathon.data.CabAvailability;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gauravwadhwa on 26/09/15.
 */
public class CustomRequest<T> extends Request<T> {

    private static final String KEY_X_APP_TOKEN = "X-APP-Token";
    private static final String VALUE_X_APP_TOKEN = "cc81d11f433e44638b43b1b8af1feb00";

    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[]{"utf-8"});
    private static Map<String, String> header;

    static {
        header = new HashMap<>();
        header.put(KEY_X_APP_TOKEN, VALUE_X_APP_TOKEN);
        header.put("Authorization", "Bearer 5507154b30174d1f8af7a2a7414a260f");
    }

    private final Response.Listener<T> listener;
    private final Class<T> clazz;
    private String requestBody;

    public CustomRequest(int method, String url, Class<T> clazz, Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.clazz = clazz;
        this.listener = listener;
    }

    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public byte[] getBody() {
        try {
            return this.requestBody == null ? null : this.requestBody.getBytes("utf-8");
        } catch (UnsupportedEncodingException exception) {
            exception.printStackTrace();
            Logger.ex(exception);
        }
        return null;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        return header;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            T t = GsonHandler.getInstance().toObject(jsonString, clazz);
            return Response.success(t, HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Logger.ex(e);
        }
        return null;
    }

    @Override
    protected VolleyError parseNetworkError(VolleyError volleyError) {
        return super.parseNetworkError(volleyError);
    }

    @Override
    protected void deliverResponse(T response) {
        if (listener != null) {
            listener.onResponse(response);
        }
    }
}
