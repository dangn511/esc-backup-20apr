package com.example.env;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.ListingViewHolder> {
    Context context;
    LayoutInflater mInflater;
    UserListings userListings;
    static private RecyclerViewItemListener callback; //to get item position when clicked

    public ListingAdapter(Context context, UserListings userListings, RecyclerViewItemListener callback){
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.userListings = userListings;
        this.callback = callback;
    }


    @NonNull
    @Override
    public ListingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View itemView = mInflater.inflate(R.layout.listing_layout, viewGroup, false);
        return new ListingViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ListingViewHolder listingViewHolder, int i){
        listingViewHolder.textViewTitle.setText(userListings.getTitle(i));
        listingViewHolder.textViewPrice.setText(userListings.getPrice(i));
        listingViewHolder.imageViewListing.setImageBitmap(userListings.getImage(i));

    }

    public int getItemCount(){
        return userListings.getSize();
    }

    static class ListingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageViewListing;
        TextView textViewTitle;
        TextView textViewPrice;
        ListingViewHolder(View view){
            super(view);
            imageViewListing = view.findViewById(R.id.listing_image);
            textViewPrice = view.findViewById(R.id.listing_price);
            textViewTitle = view.findViewById(R.id.listing_title);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view){
            callback.onItemClicked(getLayoutPosition());
        }

    }

    //search function: by name
    //private ArrayList<Integer> listOfListings;
    private List<Integer> listofListings;
    private List<Integer> listOfListingsFull;
}
