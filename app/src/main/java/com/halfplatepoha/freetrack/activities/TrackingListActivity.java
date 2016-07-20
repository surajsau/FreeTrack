package com.halfplatepoha.freetrack.activities;

import android.os.Bundle;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;

public class TrackingListActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_list);
        initToolbar();
        setHomeAsUP();
        setTitle("Tracking List");


    }

}
