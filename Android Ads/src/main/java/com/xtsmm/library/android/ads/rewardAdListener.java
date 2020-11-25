package com.xtsmm.library.android.ads;

public interface rewardAdListener<T> {
    public void onRewardedAdOpened();
    public void onRewardedAdClosed();
    public void onUserEarnedReward(T rewardItem);
    public void onRewardedAdFailedToShow(T adError);
}