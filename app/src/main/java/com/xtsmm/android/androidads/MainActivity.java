package com.xtsmm.android.androidads;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.xtsmm.library.android.ads.adListener;
import com.xtsmm.library.android.ads.rewardAdListener;
import com.xtsmm.library.android.ads.xAdmobAds;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        xAdmobAds ads = new xAdmobAds(this);
        ads.init();

        LinearLayout ll=new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);

        FrameLayout fl=new FrameLayout(this);
        ads.showNativeAdPro(fl,"ca-app-pub-3940256099942544/2247696110",300,"#FF0000","#000000","#FFFFFF");

        ll.addView(fl);
        setContentView(ll);
    }
}