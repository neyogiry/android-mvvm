package com.neyogiry.android.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder>{

    private ArrayList<String> mDates;

    public DateAdapter(ArrayList<String> dates){
        this.mDates = dates;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View mView = inflater.inflate(R.layout.item_date, parent, false);
        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String dateTime = mDates.get(position);
        holder.date.setText(dateTime);
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public void updateList(ArrayList<String> list){
        this.mDates = list;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.tv_date);
        }
    }

}
