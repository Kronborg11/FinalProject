package com.example.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryRecyclerViewAdapter extends RecyclerView.Adapter<HistoryRecyclerViewAdapter.ViewHolder> {

    ArrayList<String> names;
    ArrayList<String> dates;
    ArrayList<String> types;
    ArrayList<String> calories;
    Context context;

    public HistoryRecyclerViewAdapter(ArrayList<String> names, ArrayList<String> dates, ArrayList<String> types, ArrayList<String> calories, Context context) {
        this.names = names;
        this.dates = dates;
        this.types = types;
        this.calories = calories;
        this.context = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewName;
        private final TextView textViewType;
        private final TextView textViewDate;
        private final TextView textViewCalories;

        public ViewHolder(View view) {
            super(view);

            textViewName = (TextView) view.findViewById(R.id.textViewNameHolder);
            textViewType = (TextView) view.findViewById(R.id.textViewType);
            textViewDate = (TextView) view.findViewById(R.id.textViewDate);
            textViewCalories = (TextView) view.findViewById(R.id.textViewCalories);
        }

        public TextView getTextViewNameHolder() {
            return textViewName;
        }
        public TextView getTextViewType() {
            return textViewType;
        }
        public TextView getTextViewDate() {
            return textViewDate;
        }
        public TextView getTextViewCalories() {
            return textViewCalories;
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.history_recycler, viewGroup, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewName.setText(names.get(position));
        holder.textViewDate.setText(dates.get(position));
        holder.textViewType.setText(types.get(position));
        if (types.get(position).equals("Activity")) {
            holder.textViewCalories.setText("-" + calories.get(position));
        } else {
            holder.textViewCalories.setText(calories.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return names.toArray().length;
    }
}
