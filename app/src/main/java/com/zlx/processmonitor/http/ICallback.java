package com.zlx.processmonitor.http;

/**
 * Created by zhulaixue on 2017/12/7.
 */

public interface ICallback {
    void onSuccess(String result);

    void onFailed(String result);
}
