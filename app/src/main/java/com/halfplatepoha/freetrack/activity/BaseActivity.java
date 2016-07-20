package com.halfplatepoha.freetrack.activity;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.utils.IPref;

/**
 * Created by surajsau on 16/7/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements IPref {


    private Toast mShortToast;
    protected Toolbar toolbar;

    protected void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }

    public void showShortToast(String message) {

        if (mShortToast == null) {
            mShortToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        }

        if (!TextUtils.isEmpty(message)) {
            if (mShortToast != null && !mShortToast.getView().isShown()) {
                mShortToast.setText(message);
                mShortToast.show();
            }
        }
    }

    public void setHomeAsUP() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationIcon(R.drawable.back_arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}
