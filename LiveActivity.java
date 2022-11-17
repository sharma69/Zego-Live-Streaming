package com.example.payapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig;
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment;

public class LiveActivity extends AppCompatActivity {

    private boolean isHost;
    private String mLiveID,roomid,uname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        isHost = getIntent().getBooleanExtra("host", false);
        uname = getIntent().getStringExtra("uname");
        roomid = getIntent().getStringExtra("roomid");
        mLiveID = getIntent().getStringExtra("liveID");

        addFragment();
    }

    private void addFragment() {
        long appID =1729216539 ;
        String appSign = "1bb905bcec5c68a341d06909d5d524e56651f194e336322977b9d57ac8931d8c";

        String liveID = mLiveID;
        String userID = roomid;
        String userName = uname;

        ZegoUIKitPrebuiltLiveStreamingConfig config;
        if (isHost) {
            config = new ZegoUIKitPrebuiltLiveStreamingConfig(ZegoUIKitPrebuiltLiveStreamingConfig.ROLE_HOST);
        } else {
            config = new ZegoUIKitPrebuiltLiveStreamingConfig(ZegoUIKitPrebuiltLiveStreamingConfig.ROLE_AUDIENCE);
        }

        ZegoUIKitPrebuiltLiveStreamingFragment fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
            appID, appSign, userID, userName, liveID, config);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow();
    }
}