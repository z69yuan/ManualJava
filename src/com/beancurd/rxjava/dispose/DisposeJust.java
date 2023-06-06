package com.beancurd.rxjava.dispose;

import com.beancurd.rxjava.Dispose;

public class DisposeJust implements Dispose {

    private volatile boolean mIsCancel;

    @Override
    public boolean isCancelled() {
        return mIsCancel;
    }

    @Override
    public void doCancel() {
        mIsCancel = true;
    }
}
