package com.halfplatepoha.freetrack.filters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.network.ResponseListener;
import com.angelhack.freetrack.network.TFApi;
import com.angelhack.freetrack.network.TFServiceGenerator;
import com.angelhack.freetrack.network.response.RouteEntity;

import java.util.ArrayList;

/**
 * Created by surajsau on 16/7/16.
 */
public class FilterFragment extends Fragment {
    private ArrayList<RouteEntity.Entity> listBrand = new ArrayList<>();
    private RecyclerView rvFilterList;
    private FilterAdapter adapter;
    private EditText etSearch;
    private TFApi tfAPI;

    public FilterFragment() {
        listBrand = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_screen, container, false);
        refreshItems();
        initResources(v);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new FilterAdapter((BaseActivity) getActivity(), listBrand);
        rvFilterList.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        rvFilterList.setAdapter(adapter);
        etSearch.addTextChangedListener(setTextWatcher());
    }


    private void initResources(View v){
        etSearch = (EditText)v.findViewById(R.id.etLocationsSearch);
        rvFilterList = (RecyclerView)v.findViewById(R.id.rvLocations);
        v.findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }


    private TextWatcher setTextWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(etSearch.getText().toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
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
                adapter.refresh(response.getEntity());
            }
        }
    };
}

