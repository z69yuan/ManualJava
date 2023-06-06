package com.beancurd.rxjava;

public interface Dispose {
    /**
     * 订阅是否被取消了
     * @return
     */
    boolean isCancelled();

    void doCancel();
}
