package com.xtsmm.library.android.ads;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.VideoController;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;

public class xAdmobAds {

    Activity activity;
    public xAdmobAds(Activity activity){
        this.activity=activity;
    }

    public void init(){
        MobileAds.initialize(activity,new OnInitializationCompleteListener() {@Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
    }


    public void showRewardedAd(String adID,rewardAdListener callback){
        RewardedAd rewardedAd = new RewardedAd(activity, adID);
        RewardedAdLoadCallback adLoadCallback = new RewardedAdLoadCallback() {
            @Override
            public void onRewardedAdLoaded() {
                Log.d("xTechLog","loaded");
                if (rewardedAd.isLoaded()) {
                    RewardedAdCallback adCallback = new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                            callback.onRewardedAdOpened();
                        }
                        @Override
                        public void onRewardedAdClosed() {
                            callback.onRewardedAdClosed();
                        }
                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            callback.onUserEarnedReward(rewardItem);
                        }
                        @Override
                        public void onRewardedAdFailedToShow(AdError adError) {
                            callback.onRewardedAdFailedToShow(adError);
                        }
                    };
                    rewardedAd.show(activity, adCallback);
                } else {
                    Log.d("TAG", "The rewarded ad wasn't loaded yet.");
                }

            }

            @Override
            public void onRewardedAdFailedToLoad(LoadAdError adError) {
                Log.d("xTechLog","failed");
            }
        };
        rewardedAd.loadAd(new AdRequest.Builder().build(), adLoadCallback);
    }
    public void showIntAd(String adID){
        InterstitialAd mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(adID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
            }

            @Override
            public void onAdOpened() {
            }

            @Override
            public void onAdClicked() {
            }

            @Override
            public void onAdLeftApplication() {
            }

            @Override
            public void onAdClosed() {
            }
        });
    }

    public void showIntAdPro(String adID,adListener callback){
        InterstitialAd mInterstitialAd = new InterstitialAd(activity);
        mInterstitialAd.setAdUnitId(adID);
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                mInterstitialAd.show();
                callback.onAdLoaded();
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                callback.onAdFailedToLoad(adError);
            }

            @Override
            public void onAdOpened() {
                callback.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                callback.onAdClicked();
            }

            @Override
            public void onAdLeftApplication() {
                callback.onAdLeftApplication();
            }

            @Override
            public void onAdClosed() {
                callback.onAdClosed();
            }
        });
    }

    public void showBanner(FrameLayout fl,String adID,String adSize){
        AdSize size = AdSize.SMART_BANNER;
        if(adSize.toUpperCase().equals("SMART_BANNER")){
            size = AdSize.SMART_BANNER;
        }
        else if(adSize.toUpperCase().equals("BANNER")){
            size = AdSize.BANNER;
        }
        else if(adSize.toUpperCase().equals("LARGE_BANNER")){
            size = AdSize.LARGE_BANNER;
        }
        else if(adSize.toUpperCase().equals("MEDIUM_RECTANGLE")){
            size = AdSize.MEDIUM_RECTANGLE;
        }
        else if(adSize.toUpperCase().equals("FULL_BANNER")){
            size = AdSize.FULL_BANNER;
        }
        else if(adSize.toUpperCase().equals("LEADERBOARD")){
            size = AdSize.LEADERBOARD;
        }
        else if(adSize.toUpperCase().equals("ADAPTIVE")){
            size = getAdSize();
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(activity);
        adView.setAdSize(size);
        adView.setAdUnitId(adID);
        adView.loadAd(adRequest);

        fl.addView(adView);

    }

    public FrameLayout showBannerPro(FrameLayout fl,String adID,String adSize,adListener callback){
        AdSize size = AdSize.SMART_BANNER;
        if(adSize.toUpperCase().equals("SMART_BANNER")){
            size = AdSize.SMART_BANNER;
        }
        else if(adSize.toUpperCase().equals("BANNER")){
            size = AdSize.BANNER;
        }
        else if(adSize.toUpperCase().equals("LARGE_BANNER")){
            size = AdSize.LARGE_BANNER;
        }
        else if(adSize.toUpperCase().equals("MEDIUM_RECTANGLE")){
            size = AdSize.MEDIUM_RECTANGLE;
        }
        else if(adSize.toUpperCase().equals("FULL_BANNER")){
            size = AdSize.FULL_BANNER;
        }
        else if(adSize.toUpperCase().equals("LEADERBOARD")){
            size = AdSize.LEADERBOARD;
        }
        else if(adSize.toUpperCase().equals("ADAPTIVE")){
            size = getAdSize();
        }

        AdRequest adRequest = new AdRequest.Builder().build();
        AdView adView = new AdView(activity);
        adView.setAdSize(size);
        adView.setAdUnitId(adID);
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                callback.onAdLoaded();
            }
            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                callback.onAdFailedToLoad(adError);
            }

            @Override
            public void onAdOpened() {
                callback.onAdOpened();
            }

            @Override
            public void onAdClicked() {
                callback.onAdClicked();
            }

            @Override
            public void onAdLeftApplication() {
                callback.onAdLeftApplication();
            }

            @Override
            public void onAdClosed() {
                callback.onAdClosed();
            }
        });

        fl.addView(adView);
        return fl;
    }

    private int setDp(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, activity.getResources().getDisplayMetrics());
    }


    public void showNativeAd(FrameLayout fl, String adID) {
        final UnifiedNativeAd[] nativeAd = {null};
        fl.removeAllViews();
        final FrameLayout frameLayout1=new FrameLayout(activity);
        RelativeLayout.LayoutParams frameLayout1_layoutparams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        UnifiedNativeAdView unifiedNativeAdView=new UnifiedNativeAdView(activity);
        unifiedNativeAdView.setTag("unifiedNativeAdView");
        FrameLayout.LayoutParams unifiedNativeAdView_layoutparams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        LinearLayout firtll=new LinearLayout(activity);
        firtll.setBackgroundColor(Color.parseColor("#90FFFFFF"));
        firtll.setMinimumHeight(setDp(50));
        firtll.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams firtll_layoutparams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        firtll_layoutparams.addRule(Gravity.CENTER);

        TextView tv_adsicon=new TextView(activity,null,R.style.Widget_MaterialComponents_AppBarLayout_PrimarySurface);
        LinearLayout.LayoutParams tv_adsicon_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout secondll=new LinearLayout(activity);
        secondll.setOrientation(LinearLayout.VERTICAL);
        secondll.setPadding(setDp(20),setDp(3),setDp(20),0);
        LinearLayout.LayoutParams secondll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        firtll.addView(tv_adsicon,tv_adsicon_layoutparams);
        firtll.addView(secondll,secondll_layoutparams);

        LinearLayout thirdll=new LinearLayout(activity);
        thirdll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams thirdll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        secondll.addView(thirdll,thirdll_layoutparams);

        ImageView appicon=new ImageView(activity);
        appicon.setTag("ad_app_icon");
        appicon.setAdjustViewBounds(true);
        appicon.setPadding(0,0,setDp(5),setDp(5));
        LinearLayout.LayoutParams appicon_layoutparams=new LinearLayout.LayoutParams(setDp(40), setDp(40));

        thirdll.addView(appicon,appicon_layoutparams);

        LinearLayout fourll=new LinearLayout(activity);
        fourll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams fourll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        thirdll.addView(fourll,fourll_layoutparams);

        TextView tv_adheadline=new TextView(activity);
        tv_adheadline.setTag("ad_headline");
        tv_adheadline.setTextColor(Color.parseColor("#0000FF"));
        tv_adheadline.setTextSize(16);
        tv_adheadline.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams tv_adheadline_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        fourll.addView(tv_adheadline,tv_adheadline_layoutparams);

        LinearLayout fivell=new LinearLayout(activity);
        fivell.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams fivell_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        fourll.addView(fivell,fivell_layoutparams);

        TextView tv_advertiser=new TextView(activity);
        tv_advertiser.setTag("ad_advertiser");
        tv_advertiser.setGravity(Gravity.BOTTOM);
        tv_advertiser.setTextSize(14);
        tv_advertiser.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams tv_advertiser_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        fivell.addView(tv_advertiser,tv_advertiser_layoutparams);

        RatingBar ratingBar= new RatingBar(activity, null, android.R.attr.ratingBarStyleSmall);
        ratingBar.setTag("ad_stars");
        ratingBar.setIsIndicator(true);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(5);
        LinearLayout.LayoutParams ratingBar_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fivell.addView(ratingBar,ratingBar_layoutparams);

        LinearLayout sixll=new LinearLayout(activity);
        sixll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams sixll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        secondll.addView(sixll,sixll_layoutparams);


        TextView tv_adbody=new TextView(activity);
        tv_adbody.setTag("ad_body");
        tv_adbody.setTextSize(12);
        LinearLayout.LayoutParams tv_adbody_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_adbody_layoutparams.setMargins(0,0,setDp(20),0);


        sixll.addView(tv_adbody,tv_adbody_layoutparams);

        MediaView mediaView=new MediaView(activity);
        mediaView.setTag("ad_media");
        mediaView.setForegroundGravity(Gravity.CENTER);
        LinearLayout.LayoutParams mediaView_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                setDp(175));
        mediaView_layoutparams.setMargins(0,setDp(5),0,0);
        mediaView_layoutparams.gravity=Gravity.CENTER_HORIZONTAL;

        sixll.addView(mediaView,mediaView_layoutparams);

        LinearLayout sevenll=new LinearLayout(activity);
        sevenll.setPadding(0,setDp(5),0,setDp(5));
        sevenll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams sevenll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        sevenll_layoutparams.gravity=Gravity.END;

        sixll.addView(sevenll,sevenll_layoutparams);

        Button button=new Button(activity);
        button.setTag("ad_call_to_action");
        button.setTextSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.BLACK));
            button.setTextColor(Color.WHITE);
        }

        button.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams button_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        sevenll.addView(button,button_layoutparams);

        LinearLayout eightll=new LinearLayout(activity);
        eightll.setPadding(0,setDp(10),0,setDp(10));
        eightll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams eightll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        eightll_layoutparams.gravity= Gravity.END;


        sixll.addView(eightll,eightll_layoutparams);

        TextView tv_adprice=new TextView(activity);
        tv_adprice.setTag("ad_price");
        tv_adprice.setTextSize(12);
        tv_adprice.setPadding(setDp(5),setDp(5),setDp(5),setDp(5));
        LinearLayout.LayoutParams tv_adprice_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        eightll.addView(tv_adprice,tv_adprice_layoutparams);

        TextView tv_adstore=new TextView(activity);
        tv_adstore.setTag("ad_store");
        tv_adstore.setTextSize(12);
        tv_adstore.setPadding(setDp(5),setDp(5),setDp(5),setDp(5));
        LinearLayout.LayoutParams tv_adstore_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        eightll.addView(tv_adstore,tv_adstore_layoutparams);
        unifiedNativeAdView.addView(firtll,firtll_layoutparams);
        frameLayout1.addView(unifiedNativeAdView,unifiedNativeAdView_layoutparams);
        fl.addView(frameLayout1,frameLayout1_layoutparams);


        AdLoader.Builder builder = new AdLoader.Builder(activity, adID);

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                if (nativeAd[0] != null) {
                    nativeAd[0].destroy();
                }
                nativeAd[0] = unifiedNativeAd;

               /* UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_unified, null);*/
                UnifiedNativeAdView adView=fl.findViewWithTag("unifiedNativeAdView");
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout1.removeAllViews();
                frameLayout1.addView(adView);
            }

        });
        VideoOptions videoOptions = new VideoOptions.Builder()
                //.setStartMuted(startVideoAdsMuted.isChecked())
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {

            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    public void showNativeAdPro(FrameLayout fl, String adID,int adHeight,String titleColor,String buttonColor,String buttonTextColor) {
        final UnifiedNativeAd[] nativeAd = {null};
        fl.removeAllViews();
        final FrameLayout frameLayout1=new FrameLayout(activity);
        RelativeLayout.LayoutParams frameLayout1_layoutparams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        UnifiedNativeAdView unifiedNativeAdView=new UnifiedNativeAdView(activity);
        unifiedNativeAdView.setTag("unifiedNativeAdView");
        FrameLayout.LayoutParams unifiedNativeAdView_layoutparams=new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);


        LinearLayout firtll=new LinearLayout(activity);
        firtll.setBackgroundColor(Color.parseColor("#90FFFFFF"));
        firtll.setMinimumHeight(setDp(50));
        firtll.setOrientation(LinearLayout.VERTICAL);
        RelativeLayout.LayoutParams firtll_layoutparams=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        firtll_layoutparams.addRule(Gravity.CENTER);

        TextView tv_adsicon=new TextView(activity,null,R.style.Widget_MaterialComponents_AppBarLayout_PrimarySurface);
        LinearLayout.LayoutParams tv_adsicon_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        LinearLayout secondll=new LinearLayout(activity);
        secondll.setOrientation(LinearLayout.VERTICAL);
        secondll.setPadding(setDp(20),setDp(3),setDp(20),0);
        LinearLayout.LayoutParams secondll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        firtll.addView(tv_adsicon,tv_adsicon_layoutparams);
        firtll.addView(secondll,secondll_layoutparams);

        LinearLayout thirdll=new LinearLayout(activity);
        thirdll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams thirdll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        secondll.addView(thirdll,thirdll_layoutparams);

        ImageView appicon=new ImageView(activity);
        appicon.setTag("ad_app_icon");
        appicon.setAdjustViewBounds(true);
        appicon.setPadding(0,0,setDp(5),setDp(5));
        LinearLayout.LayoutParams appicon_layoutparams=new LinearLayout.LayoutParams(setDp(40), setDp(40));

        thirdll.addView(appicon,appicon_layoutparams);

        LinearLayout fourll=new LinearLayout(activity);
        fourll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams fourll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        thirdll.addView(fourll,fourll_layoutparams);

        TextView tv_adheadline=new TextView(activity);
        tv_adheadline.setTag("ad_headline");
        tv_adheadline.setTextColor(Color.parseColor(titleColor));
        tv_adheadline.setTextSize(16);
        tv_adheadline.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams tv_adheadline_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        fourll.addView(tv_adheadline,tv_adheadline_layoutparams);

        LinearLayout fivell=new LinearLayout(activity);
        fivell.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams fivell_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        fourll.addView(fivell,fivell_layoutparams);

        TextView tv_advertiser=new TextView(activity);
        tv_advertiser.setTag("ad_advertiser");
        tv_advertiser.setGravity(Gravity.BOTTOM);
        tv_advertiser.setTextSize(14);
        tv_advertiser.setTypeface(null, Typeface.BOLD);
        LinearLayout.LayoutParams tv_advertiser_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        fivell.addView(tv_advertiser,tv_advertiser_layoutparams);

        RatingBar ratingBar= new RatingBar(activity, null, android.R.attr.ratingBarStyleSmall);
        ratingBar.setTag("ad_stars");
        ratingBar.setIsIndicator(true);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(5);
        LinearLayout.LayoutParams ratingBar_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        fivell.addView(ratingBar,ratingBar_layoutparams);

        LinearLayout sixll=new LinearLayout(activity);
        sixll.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams sixll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        secondll.addView(sixll,sixll_layoutparams);


        TextView tv_adbody=new TextView(activity);
        tv_adbody.setTag("ad_body");
        tv_adbody.setTextSize(12);
        LinearLayout.LayoutParams tv_adbody_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_adbody_layoutparams.setMargins(0,0,setDp(20),0);


        sixll.addView(tv_adbody,tv_adbody_layoutparams);

        MediaView mediaView=new MediaView(activity);
        mediaView.setTag("ad_media");
        mediaView.setForegroundGravity(Gravity.CENTER);
        LinearLayout.LayoutParams mediaView_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                setDp(adHeight));
        mediaView_layoutparams.setMargins(0,setDp(5),0,0);
        mediaView_layoutparams.gravity=Gravity.CENTER_HORIZONTAL;

        sixll.addView(mediaView,mediaView_layoutparams);

        LinearLayout sevenll=new LinearLayout(activity);
        sevenll.setPadding(0,setDp(5),0,setDp(5));
        sevenll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams sevenll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        sevenll_layoutparams.gravity=Gravity.END;

        sixll.addView(sevenll,sevenll_layoutparams);

        Button button=new Button(activity);
        button.setTag("ad_call_to_action");
        button.setTextSize(14);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(buttonColor)));
            button.setTextColor(Color.parseColor(buttonTextColor));
        }

        button.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams button_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        sevenll.addView(button,button_layoutparams);

        LinearLayout eightll=new LinearLayout(activity);
        eightll.setPadding(0,setDp(10),0,setDp(10));
        eightll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams eightll_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        eightll_layoutparams.gravity= Gravity.END;


        sixll.addView(eightll,eightll_layoutparams);

        TextView tv_adprice=new TextView(activity);
        tv_adprice.setTag("ad_price");
        tv_adprice.setTextSize(12);
        tv_adprice.setPadding(setDp(5),setDp(5),setDp(5),setDp(5));
        LinearLayout.LayoutParams tv_adprice_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        eightll.addView(tv_adprice,tv_adprice_layoutparams);

        TextView tv_adstore=new TextView(activity);
        tv_adstore.setTag("ad_store");
        tv_adstore.setTextSize(12);
        tv_adstore.setPadding(setDp(5),setDp(5),setDp(5),setDp(5));
        LinearLayout.LayoutParams tv_adstore_layoutparams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        eightll.addView(tv_adstore,tv_adstore_layoutparams);
        unifiedNativeAdView.addView(firtll,firtll_layoutparams);
        frameLayout1.addView(unifiedNativeAdView,unifiedNativeAdView_layoutparams);
        fl.addView(frameLayout1,frameLayout1_layoutparams);


        AdLoader.Builder builder = new AdLoader.Builder(activity, adID);

        builder.forUnifiedNativeAd(new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
            // OnUnifiedNativeAdLoadedListener implementation.
            @Override
            public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                // You must call destroy on old ads when you are done with them,
                // otherwise you will have a memory leak.
                if (nativeAd[0] != null) {
                    nativeAd[0].destroy();
                }
                nativeAd[0] = unifiedNativeAd;

               /* UnifiedNativeAdView adView = (UnifiedNativeAdView) getLayoutInflater()
                        .inflate(R.layout.ad_unified, null);*/
                UnifiedNativeAdView adView=fl.findViewWithTag("unifiedNativeAdView");
                populateUnifiedNativeAdView(unifiedNativeAd, adView);
                frameLayout1.removeAllViews();
                frameLayout1.addView(adView);
            }

        });
        VideoOptions videoOptions = new VideoOptions.Builder()
                //.setStartMuted(startVideoAdsMuted.isChecked())
                .build();

        NativeAdOptions adOptions = new NativeAdOptions.Builder()
                .setVideoOptions(videoOptions)
                .build();

        builder.withNativeAdOptions(adOptions);

        AdLoader adLoader = builder.withAdListener(new AdListener() {
            @Override
            public void onAdFailedToLoad(int errorCode) {

            }
        }).build();

        adLoader.loadAd(new AdRequest.Builder().build());

    }

    private void populateUnifiedNativeAdView(UnifiedNativeAd nativeAd, UnifiedNativeAdView adView) {

        adView.setMediaView((MediaView) adView.findViewWithTag("ad_media"));
        adView.setHeadlineView(adView.findViewWithTag("ad_headline"));
        adView.setBodyView(adView.findViewWithTag("ad_body"));
        adView.setCallToActionView(adView.findViewWithTag("ad_call_to_action"));
        adView.setIconView(adView.findViewWithTag("ad_app_icon"));
        adView.setPriceView(adView.findViewWithTag("ad_price"));
        adView.setStarRatingView(adView.findViewWithTag("ad_stars"));
        adView.setStoreView(adView.findViewWithTag("ad_store"));
        adView.setAdvertiserView(adView.findViewWithTag("ad_advertiser"));

        // The headline and mediaContent are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        adView.getMediaView().setMediaContent(nativeAd.getMediaContent());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        if (nativeAd.getBody() == null) {
            adView.getBodyView().setVisibility(View.INVISIBLE);
        } else {
            adView.getBodyView().setVisibility(View.VISIBLE);
            ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        }

        if (nativeAd.getCallToAction() == null) {
            adView.getCallToActionView().setVisibility(View.INVISIBLE);
        } else {
            adView.getCallToActionView().setVisibility(View.VISIBLE);
            ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());
        }

        if (nativeAd.getIcon() == null) {
            adView.getIconView().setVisibility(View.GONE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(
                    nativeAd.getIcon().getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // This method tells the Google Mobile Ads SDK that you have finished populating your
        // native ad view with this native ad.
        adView.setNativeAd(nativeAd);

        // Get the video controller for the ad. One will always be provided, even if the ad doesn't
        // have a video asset.
        VideoController vc = nativeAd.getVideoController();

        // Updates the UI to say whether or not this ad has a video asset.
        if (vc.hasVideoContent()) {


            // Create a new VideoLifecycleCallbacks object and pass it to the VideoController. The
            // VideoController will call methods on this object when events occur in the video
            // lifecycle.
            vc.setVideoLifecycleCallbacks(new VideoController.VideoLifecycleCallbacks() {
                @Override
                public void onVideoEnd() {
                    // Publishers should allow native ads to complete video playback before
                    // refreshing or replacing them with another ad in the same UI location.
                    //refresh.setEnabled(true);

                    super.onVideoEnd();
                }
            });
        } else {

            //refresh.setEnabled(true);
        }
    }

    private AdSize getAdSize() {
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        float widthPixels = outMetrics.widthPixels;
        float density = outMetrics.density;
        int adWidth = (int) (widthPixels / density);
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(activity, adWidth);
    }

}