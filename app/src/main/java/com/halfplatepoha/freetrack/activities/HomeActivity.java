package com.halfplatepoha.freetrack.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.map.activity.ProviderMapActivity;
import com.angelhack.freetrack.task.PullProviderLocationManger;
import com.angelhack.freetrack.utils.TFPreference;
import com.mopub.mobileads.MoPubView;

public class HomeActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private PullProviderLocationManger manger;
    private MoPubView moPubView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initToolbar();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initNavHeader();
        initViews();
        init();
    }



    private void init() {
        manger = new PullProviderLocationManger(this);
    }

    private void initViews() {
        findViewById(R.id.cvConsumer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showConsumerScreen();
            }
        });
        findViewById(R.id.cvProvider).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showProviderScreen();
            }
        });
        moPubView = (MoPubView) findViewById(R.id.mopub_sample_ad);
        // TODO: Replace this test id with your personal ad unit id
        moPubView.setAdUnitId("d4a0aba637d64a9f9a05a575fa757ac2");
        moPubView.loadAd();

    }

    private void initNavHeader() {

        toolbar.setNavigationIcon(R.drawable.hamburger);
        setTitle("Dashboard");
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        View navHeaderView = navigationView.inflateHeaderView(R.layout.nav_header_home);

        ((TextView)navHeaderView.findViewById(R.id.tvName)).setText(TFPreference.getFromPref(PREF_EMAIL, ""));
        ((TextView)navHeaderView.findViewById(R.id.tvEmail)).setText(TFPreference.getFromPref(PREF_LOGIN_NAME, ""));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_consumer) {
            showProviderScreen();
        } else if (id == R.id.nav_provider) {
            showConsumerScreen();
        } else if (id == R.id.nav_logout) {
            logoutUser();

        } else if (id == R.id.nav_share) {

            shareApp();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showConsumerScreen() {
        Intent intent =   new Intent(HomeActivity.this, TrackingListActivity.class);
        startActivity(intent);

    }

    private void showProviderScreen() {
        Intent intent =   new Intent(HomeActivity.this, ProviderMapActivity.class);
        startActivity(intent);
    }

    private void logoutUser() {

        TFPreference.clearUserData(this);
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    private void shareApp() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,
                "Hey check out my app at: https://play.google.com/store/apps/details?id="+getPackageName());
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

    @Override
    protected void onDestroy() {
        try{
            if(moPubView!= null){
                moPubView.destroy();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        moPubView = null;

        super.onDestroy();
    }
}
