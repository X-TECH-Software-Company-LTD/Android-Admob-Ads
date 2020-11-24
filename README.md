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

2 - Show Banner 
```
FrameLayout fl=new FrameLayout(this);
setContentView(ads.showBanner(fl,"LEADERBOARD")); // use with any view , it returns frameLayout

//Available Adsizes
//320x50	BANNER
//320x100	LARGE_BANNER
//300x250	MEDIUM_RECTANGLE
//468x60	FULL_BANNER
//728x90	LEADERBOARD
//Screen SMART_BANNER
```
