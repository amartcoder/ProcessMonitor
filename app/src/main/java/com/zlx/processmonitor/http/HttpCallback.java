package com.zlx.processmonitor.http;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by zhulaixue on 2017/12/7.
 */

public abstract class HttpCallback<T> implements ICallback {
    @Override
    public void onSuccess(String result) {
        Gson gson = new Gson();
        Class<?> cls = analysisClassInfo(this);
        T t = (T)gson.fromJson(result, cls);

        onSuccess(t);
    }

    public abstract void onSuccess(T t);

    private Class<?> analysisClassInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType)genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }
}
