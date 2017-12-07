package com.zlx.processmonitor.http;

import java.util.Map;

/**
 * Created by zhulaixue on 2017/12/7.
 */

public interface IHttpProcessor {
    void post(String url, Map<String, Object> params, ICallback callback);

    void get(String url, Map<String, Object> params, ICallback callback);
}
