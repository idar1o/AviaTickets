package com.example.myappavia.presentation.screens.searchscreen.childsearchscreens;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myappavia.R;
import com.example.myappavia.data.API.ModelPlace.SearchedPlace;

import java.util.ArrayList;

public class AdapterPlacesSearchItem extends RecyclerView.Adapter<AdapterPlacesSearchItem.ViewHolder>{

    private static ArrayList<SearchedPlace> placeModelArrayList= new ArrayList<>();
    private static OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(SearchedPlace item);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
    public AdapterPlacesSearchItem(ArrayList<SearchedPlace> placeModelArrayList) {

        this.placeModelArrayList = placeModelArrayList;

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.places_search_item_layout,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPlacesSearchItem.ViewHolder holder, int position) {
        SearchedPlace placeModel = placeModelArrayList.get(position);
        holder.city.setText(placeModel.getName());
        holder.country.setText(placeModel.getCountry_name());
        holder.code.setText(placeModel.getMain_airport_name());


    }

    @Override
    public int getItemCount() {return placeModelArrayList.size();}

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView city;
        public TextView code;
        public TextView country;
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            city = itemView.findViewById(R.id.city);
            code = itemView.findViewById(R.id.aiatcode);
            country = itemView.findViewById(R.id.country);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION && listener != null){
                        listener.onItemClick(placeModelArrayList.get(position));
                    }
                }
            });
        }

    }
}