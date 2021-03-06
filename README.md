b# Android-Admob-Ads
Integration to various kinds Google Admob Ads

## Available Methods
1. Admob Banner (standard)
2. Admob Banner with Event Listener (advanced)
3. Admob Interstitial (standard)
4. Admob Interstitial with Event Listener (advanced)
5. Admob Rewarded Interstitial (advanced)
6. Admob Advanced Native (standard)
7. Admob Advanced Native Customized (advanced)

## Requirements

1 -  ``` multiDexEnabled true ```
on app gradle defaultConfig

2 - add your Admob App ID to your Application Manifest as Meta-Data
``` 
<meta-data
  android:name="com.google.android.gms.ads.APPLICATION_ID"
  android:value="ca-app-pub-3940256099942544~3347511713"/>
```
replace with your Application ID

3 - Download "Android Ads.zip" , extract and Import as module
https://cdn.xtsmm.com/android/libraries/Android%20Ads.zip

4 - add in app gradle 
```
implementation project(path: ':Android Ads')
```

## Usage

1 - Initiate Admob Ads
```
xAdmobAds ads = new xAdmobAds(this);
ads.init();
```

Banner Sizes
```
//Available                         Code
//320x50	                    BANNER              (Legacy)
//320x100	                    LARGE_BANNER        
//300x250	                    MEDIUM_RECTANGLE    (most visible)
//468x60	                    FULL_BANNER     
//728x90	                    LEADERBOARD   
//Screen                            SMART_BANNER        (Popular)
//Provided width x Adaptive height  ADAPTIVE            (Trending)
```

2 - Show Banner (standard)
```
FrameLayout fl=new FrameLayout(this);
ads.showBanner(fl,"ca-app-pub-3940256099942544/6300978111","LEADERBOARD"); // use with any view

```

3 - Show Banner with Event Listener (advanced)
```
FrameLayout fl=new FrameLayout(this);
//(FrameLayout,Ad ID, Ad Code - please check above Banner Sizes )
ads.showBannerPro(fl,"ca-app-pub-3940256099942544/6300978111", "ADAPTIVE", new adListener() {
  @Override
  public void onAdLoaded() {
    Log.d("xTechLog","onAdLoaded");
  }

  @Override
  public void onAdFailedToLoad(Object adError) {
    Log.d("xTechLog","onAdFailedToLoad");
  }

  @Override
  public void onAdOpened() {
    Log.d("xTechLog","onAdOpened");
  }

  @Override
  public void onAdClicked() {
    Log.d("xTechLog","onAdClicked");
  }

  @Override
  public void onAdLeftApplication() {
    Log.d("xTechLog","onAdLeftApplication");
  }

  @Override
  public void onAdClosed() {
    Log.d("xTechLog","onAdClosed");
  }
});
```

4 - Show Interstitial Ad (standard)

```
ads.showIntAd("ca-app-pub-3940256099942544/1033173712"); // replace with you Ad ID
```

5 - Show Interstitial Ad with Event Listener (Advanced)
```
ads.showIntAdPro("ca-app-pub-3940256099942544/1033173712", new adListener() {
  @Override
  public void onAdLoaded() {
    Log.d("xTechLog","onAdLoaded");
  }

  @Override
  public void onAdFailedToLoad(Object adError) {
    Log.d("xTechLog","onAdFailedToLoad");
  }

  @Override
  public void onAdOpened() {
    Log.d("xTechLog","onAdOpened");
  }

  @Override
  public void onAdClicked() {
    Log.d("xTechLog","onAdClicked");
  }

  @Override
  public void onAdLeftApplication() {
    Log.d("xTechLog","onAdLeftApplication");
  }

  @Override
  public void onAdClosed() {
    Log.d("xTechLog","onAdClosed");
  }
});
```

6 - Show Rewarded Ads 

```
ads.showRewardedAd("ca-app-pub-3940256099942544/5224354917", new rewardAdListener() {
  @Override
  public void onRewardedAdOpened() {

  }

  @Override
  public void onRewardedAdClosed() {

  }

  @Override
  public void onUserEarnedReward(Object rewardItem) {

  }

  @Override
  public void onRewardedAdFailedToShow(Object adError) {

  }
});
```

7 - Show Native Ads (standard)

```
FrameLayout fl=new FrameLayout(this);
ads.showNativeAd(fl,"ca-app-pub-3940256099942544/2247696110");
//(FrameLayout,Ad ID)
```

8 - Show Native Custom Ads (advanced)

```
FrameLayout fl=new FrameLayout(this);
ads.showNativeAdPro(fl,"ca-app-pub-3940256099942544/2247696110",300,"#FF0000","#000000","#FFFFFF");
//(FrameLayout,Ad ID, Ad Height , title Color,button Color, button Text Color) colors accept only hex strings 
// standard Ad Height = 175
```


