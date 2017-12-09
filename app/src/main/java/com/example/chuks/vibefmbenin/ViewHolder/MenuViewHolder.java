package com.example.chuks.vibefmbenin.ViewHolder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chuks.vibefmbenin.Interface.ItemClickListener;
import com.example.chuks.vibefmbenin.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public ImageView imageView;
    public TextView textPod;
    public TextView descriptionPod;
    public TextView oapPod;
    public TextView episodePod;

    private ItemClickListener ItemClickListener;

    public MenuViewHolder(View itemView) {
        super(itemView);

        imageView = (ImageView) itemView.findViewById(R.id.pod_image);
        textPod = (TextView) itemView.findViewById(R.id.pod_title);
        descriptionPod = (TextView) itemView.findViewById(R.id.pod_description);
        oapPod = (TextView) itemView.findViewById(R.id.pod_oap);
        episodePod = (TextView) itemView.findViewById(R.id.pod_episode);

        itemView.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.ItemClickListener = itemClickListener;
    }

    @Override
    public void onClick(View view) {
        ItemClickListener.onClick(view,getAdapterPosition(),false);
    }
}
