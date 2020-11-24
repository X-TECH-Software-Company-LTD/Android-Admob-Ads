# Android-Admob-Ads
Integration to various kinds Google Admob Ads

## Requirements

1 -  ``` multiDexEnabled true ```
on app gradle

2 - add your Admob App ID to your Application Manifest as Meta-Data
``` 
<meta-data
  android:name="com.google.android.gms.ads.APPLICATION_ID"
  android:value="ca-app-pub-3940256099942544~3347511713"/>
```
replace with your Application ID

3 - Download xAdmobZip and Import as module

4 - add in app gradle 
```
implementation project(path: ':Android Ads')
```

## Usage

1 - Initiate Admob
```
xAdmobAds ads = new xAdmobAds(this);
ads.init();
```

Banner Sizes
```
//Available                         Adsizes
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
setContentView(ads.showBanner(fl,"ca-app-pub-3940256099942544/6300978111","LEADERBOARD")); // use with any view , it returns frameLayout

```

3 - Show Banner with Callback Events (advanced)
```
FrameLayout fl=new FrameLayout(this);
setContentView(ads.showBannerPro(fl,"ca-app-pub-3940256099942544/6300978111", "ADAPTIVE", new bannerAdListener() {
            @Override
            public void onAdLoaded() {
                Log.d("xTechLod","onAdLoaded");
            }

            @Override
            public void onAdFailedToLoad(Object adError) {
                Log.d("xTechLod","onAdFailedToLoad");
            }

            @Override
            public void onAdOpened() {
                Log.d("xTechLod","onAdOpened");
            }

            @Override
            public void onAdClicked() {
                Log.d("xTechLod","onAdClicked");
            }

            @Override
            public void onAdLeftApplication() {
                Log.d("xTechLod","onAdLeftApplication");
            }

            @Override
            public void onAdClosed() {
                Log.d("xTechLod","onAdClosed");
            }
        }));
```
