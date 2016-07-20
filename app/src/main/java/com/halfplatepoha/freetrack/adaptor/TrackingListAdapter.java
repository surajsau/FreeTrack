package com.halfplatepoha.freetrack.adaptor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.map.activity.ConsumerMapActivity;
import com.angelhack.freetrack.network.response.RouteEntity;
import com.angelhack.freetrack.utils.IPref;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surajsau on 16/7/16.
 */
public class TrackingListAdapter extends RecyclerView.Adapter<TrackingListAdapter.CustomViewHolder>
                            implements Filterable {
    public List<RouteEntity.Entity> mListBrands;
    private List<RouteEntity.Entity> listFilteredRouteEntities;
    private BaseActivity context;
    private String filterText;

    public TrackingListAdapter(BaseActivity context, List<RouteEntity.Entity> listBrands){
        this.context = context;
        this.listFilteredRouteEntities = new ArrayList<>();
        mListBrands = listBrands;

        filterText = "";

        for(RouteEntity.Entity routeEntity : listBrands)
            listFilteredRouteEntities.add(routeEntity);

    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_tracking_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        RouteEntity.Entity b = listFilteredRouteEntities.get(position);
        holder.itemView.setTag(b);
        holder.keywordSuggText.setText(b.getTrackingEntity());
        holder.time.setText("Starting Time : " + b.getStartTime());

        if(b.getStatus().equalsIgnoreCase("active")) {
            holder.active.setText(b.getStatus());
            holder.active.setTextColor(ContextCompat.getColor(context, R.color.theme_secondary_green));
        }
        else{
            holder.active.setText(b.getStatus());
            holder.active.setTextColor(ContextCompat.getColor(context, R.color.theme_red));
        }
    }

    @Override
    public int getItemCount() {
        return (listFilteredRouteEntities != null ? listFilteredRouteEntities.size() : 0);
    }

    public void refresh(List<RouteEntity.Entity> listBrand) {
       // mListBrands.clear();
        mListBrands.clear();
        listFilteredRouteEntities.clear();
        mListBrands.addAll(listBrand);
        listFilteredRouteEntities.addAll(listBrand);
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new BrandFilter(this, mListBrands);
    }


    public class CustomViewHolder extends RecyclerView.ViewHolder{


        private final TextView keywordSuggText;
        private final TextView active;
        private final TextView time;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            keywordSuggText = (TextView)  itemView.findViewById(R.id.keyword_sugg_text);
            active = (TextView)  itemView.findViewById(R.id.active);
            time = (TextView)  itemView.findViewById(R.id.time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RouteEntity.Entity routeEntity = (RouteEntity.Entity) itemView.getTag();
                    Intent intent = new Intent(context, ConsumerMapActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(IPref.ENTITY, routeEntity);
                    intent.putExtras(bundle);
                    context.startActivity(intent);

                }
            });
        }
    }

    private class BrandFilter extends Filter{
        private final TrackingListAdapter adapter;
        private final List<RouteEntity.Entity> originalList;
        private final List<RouteEntity.Entity> filteredList;

        private BrandFilter(TrackingListAdapter adapter, List<RouteEntity.Entity> originalList){
            super();
            this.adapter = adapter;
            this.originalList = originalList;
            this.filteredList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filteredList.clear();
            final FilterResults results = new FilterResults();
            filterText = constraint.toString();

            if(constraint.length() == 0){
                filteredList.addAll(originalList);
            }else{
                for (final RouteEntity.Entity routeEntity : originalList){
                    if(routeEntity.getTrackingEntity().toLowerCase().contains(constraint)){
                        filteredList.add(routeEntity);
                    }
                }
            }

            results.values = filteredList;
            results.count = filteredList.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.listFilteredRouteEntities.clear();
            adapter.listFilteredRouteEntities.addAll((ArrayList<RouteEntity.Entity>) results.values);
            adapter.notifyDataSetChanged();
        }
    }

}

