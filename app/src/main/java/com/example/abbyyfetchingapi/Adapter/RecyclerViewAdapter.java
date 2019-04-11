package com.example.abbyyfetchingapi.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abbyyfetchingapi.Model.Answer;



import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    private List<Answer> data;

    public RecyclerViewAdapter(List<Answer> data) {
        this.data = data;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_selectable_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Answer ans = data.get(position);
        holder.text.setText(ans.toString());
        holder.itemView.setTag(ans.answerId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }
    }
}
