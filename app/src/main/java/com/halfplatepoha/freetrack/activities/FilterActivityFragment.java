package com.halfplatepoha.freetrack.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.angelhack.freetrack.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class FilterActivityFragment extends Fragment {

    public FilterActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_filter, container, false);
    }
}
