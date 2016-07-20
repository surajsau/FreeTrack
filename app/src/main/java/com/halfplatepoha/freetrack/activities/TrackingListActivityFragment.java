package com.halfplatepoha.freetrack.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.adaptor.TrackingListAdapter;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.network.ResponseListener;
import com.angelhack.freetrack.network.TFApi;
import com.angelhack.freetrack.network.TFServiceGenerator;
import com.angelhack.freetrack.network.response.RouteEntity;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class TrackingListActivityFragment extends Fragment implements TextWatcher{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private ArrayList<RouteEntity.Entity> listBrand;
    private TrackingListAdapter adaptor;
    private TFApi tfAPI;

    private EditText etRouteSuggest;

    public TrackingListActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracking_list, container, false);
        init(view);
        return  view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adaptor = new TrackingListAdapter((BaseActivity) getActivity(), listBrand);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adaptor);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh items
                refreshItems();
            }
        });
        refreshItems();
    }


    private void init(View v) {
        mSwipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipeRefreshLayout);
        recyclerView = (RecyclerView)v.findViewById(R.id.recyclerView);
        etRouteSuggest = (EditText) v.findViewById(R.id.etRouteSuggest);
        listBrand = new ArrayList<>();

        etRouteSuggest.addTextChangedListener(this);
    }

    void refreshItems() {
        try {
            tfAPI = TFServiceGenerator.createService(TFApi.class);
            tfAPI.getAllActiveTracking().enqueue(listener);
            Log.d("sheetal", "refreshItems  ");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private ResponseListener<RouteEntity> listener =new ResponseListener<RouteEntity>() {
        @Override
        public void parseNetworkResponse(RouteEntity response) {
            Log.d("sheetal", "parseNetworkResponse   " + response);

            if(response != null && response.getStatus() == 200){
                listBrand.clear();
                adaptor.mListBrands.clear();
                adaptor.refresh(response.getEntity());
                mSwipeRefreshLayout.setRefreshing(false);
            }
        }
    };

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        adaptor.getFilter().filter(etRouteSuggest.getText().toString().toLowerCase());
    }

    @Override
    public void afterTextChanged(Editable s) {}
}
