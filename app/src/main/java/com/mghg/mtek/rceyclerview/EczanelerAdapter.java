package com.mghg.mtek.rceyclerview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mghg.mtek.R;
import com.mghg.mtek.models.Result;

import java.util.List;

public class EczanelerAdapter extends RecyclerView.Adapter<EczanelerViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Result> resultList;
    @Override
    public void onClick(View v) {
        
    }

    public EczanelerAdapter(Context context, List<Result> kisilerList) {
        this.context = context;
        this.resultList = kisilerList;
    }

    @NonNull
    @Override
    public EczanelerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_eczaneler,parent,false);
        return new EczanelerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EczanelerViewHolder holder, final int position) {
        final Result result = resultList.get(position);
        holder.setData(result, position);

        holder.bind(result);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the current state of the item
                boolean expanded = result.isExpanded();
                // Change the state
                result.setExpanded(!expanded);
                // Notify the adapter that item has changed
                EczanelerAdapter.this.notifyItemChanged(position);
            }
        });

        if (position %2 == 0){
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }else {
            holder.itemView.setBackgroundColor(Color.parseColor("#F3E5F5"));
        }

    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }
}
