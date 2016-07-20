package com.halfplatepoha.freetrack.filters;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.angelhack.freetrack.R;
import com.angelhack.freetrack.base.activity.BaseActivity;
import com.angelhack.freetrack.network.response.RouteEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by surajsau on 16/7/16.
 */
public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.CustomViewHolder>
        implements Filterable {
    private List<RouteEntity.Entity> mListRouteEntities;
    private List<RouteEntity.Entity> listFilteredRouteEntities;
    private BaseActivity context;
    private String filterText;

    public FilterAdapter(BaseActivity context, List<RouteEntity.Entity> listRouteEntities){
        this.context = context;
        this.listFilteredRouteEntities = new ArrayList<>();
        mListRouteEntities = listRouteEntities;

        filterText = "";

        for(RouteEntity.Entity routeEntity : listRouteEntities)
            listFilteredRouteEntities.add(routeEntity);
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_filter_list, parent, false);
        CustomViewHolder holder = new CustomViewHolder(v);

        return holder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, final int position) {
        RouteEntity.Entity b = listFilteredRouteEntities.get(position);
        holder.itemView.setTag(b);
        holder.keywordSuggText.setText(b.getTrackingEntity());
    }

    @Override
    public int getItemCount() {
        return (listFilteredRouteEntities != null ? listFilteredRouteEntities.size() : 0);
    }

    @Override
    public Filter getFilter() {
        return new BrandFilter(this, mListRouteEntities);
    }

    public void refresh(List<RouteEntity.Entity> entity) {
        this.listFilteredRouteEntities = new ArrayList<>();
        mListRouteEntities = entity;

        filterText = "";

        for(RouteEntity.Entity routeEntity : entity)
            listFilteredRouteEntities.add(routeEntity);
        notifyDataSetChanged();

    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{


        private final TextView keywordSuggText;

        public CustomViewHolder(final View itemView) {
            super(itemView);
            keywordSuggText = (TextView)  itemView.findViewById(R.id.keyword_sugg_text);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   RouteEntity.Entity routeEntity = (RouteEntity.Entity) itemView.getTag();
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result", routeEntity);
                    context.setResult(Activity.RESULT_OK,returnIntent);
                    context.finish();
                }
            });
        }
    }



    private class BrandFilter extends Filter{
        private final FilterAdapter adapter;
        private final List<RouteEntity.Entity> originalList;
        private final List<RouteEntity.Entity> filteredList;

        private BrandFilter(FilterAdapter adapter, List<RouteEntity.Entity> originalList){
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

