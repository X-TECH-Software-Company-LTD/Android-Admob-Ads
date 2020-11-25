package com.xtsmm.library.android.ads;

public interface adListener<T> {
    public void onAdLoaded();
    public void onAdFailedToLoad(T adError);
    public void onAdOpened();
    public void onAdClicked();
    public void onAdLeftApplication();
    public void onAdClosed();
}