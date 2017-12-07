package com.zlx.processmonitor.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhulaixue on 2017/12/7.
 */

public class HttpProxy implements IHttpProcessor{
    public static final HttpProxy mInstance = new HttpProxy();

    private static IHttpProcessor mProcessor;

    private Map<String, Object> mParams = null;

    private void HttpProxy() {
        mParams = new HashMap<>();
    }

    public HttpProxy instance() {
        return mInstance;
    }


    public static void init(IHttpProcessor processor) {
        mProcessor = processor;
    }


    @Override
    public void post(String url, Map<String, Object> params, ICallback callback) {



        mProcessor.post(url, params, callback);
    }

    @Override
    public void get(String url, Map<String, Object> params, ICallback callback) {
        mProcessor.get(url, params, callback);
    }
}
