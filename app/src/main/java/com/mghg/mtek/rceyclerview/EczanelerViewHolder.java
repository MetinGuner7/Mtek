package com.mghg.mtek.rceyclerview;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mghg.mtek.R;
import com.mghg.mtek.models.Result;

class EczanelerViewHolder extends RecyclerView.ViewHolder {
    private TextView tvEczaneAdi, tvAdres, tvIlce, tvTelefon;
    View sub_item;
    public EczanelerViewHolder(@NonNull View itemView) {
        super(itemView);

        tvAdres = itemView.findViewById(R.id.tvAdres);
        tvIlce = itemView.findViewById(R.id.tvEczaneIlce);
        tvTelefon = itemView.findViewById(R.id.tvTelefonEczane);
        tvEczaneAdi = itemView.findViewById(R.id.tvEczaneAdi);
        sub_item = itemView.findViewById(R.id.sub_item);

    }

    public void setData(Result result, int position){
        this.tvEczaneAdi.setText(""+result.getName());
        this.tvIlce.setText(result.getDist());
        this.tvTelefon.setText(result.getPhone());
        this.tvAdres.setText(result.getAddress());
    }

    public void bind(Result result) {
        // Get the state
        boolean expanded = result.isExpanded();
        // Set the visibility based on state
        sub_item.setVisibility(expanded ? View.VISIBLE : View.GONE);

    }

}
