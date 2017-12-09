package com.example.chuks.vibefmbenin.ViewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chuks.vibefmbenin.Interface.ItemClickListener;
import com.example.chuks.vibefmbenin.R;

/**
 * Created by chuks on 9/23/2017.
 */

public class PodListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    public ImageView podImage;
    public ImageButton downloadPod;
    public TextView podTitle;
    public TextView podOap;
    //public TextView podDescription;
    public TextView podTime;
    public TextView podDate;
    //public String podLink;

    private ItemClickListener itemClickListener;


    public PodListViewHolder(View itemView) {
        super(itemView);

        podImage = (ImageView) itemView.findViewById(R.id.display_image);
        podTitle = (TextView) itemView.findViewById(R.id.display_title_pod);
        podOap = (TextView) itemView.findViewById(R.id.display_oap);
        podTime = (TextView) itemView.findViewById(R.id.display_duration);
        podDate = (TextView) itemView.findViewById(R.id.display_calender_date);
        downloadPod = (ImageButton) itemView.findViewById(R.id.download_pod);


//        podImage = (ImageView) itemView.findViewById(R.id.pod_image_list);
//        podTitle = (TextView) itemView.findViewById(R.id.pod_title_list);
//        podOap = (TextView) itemView.findViewById(R.id.pod_title_oap);
//        podTime = (TextView) itemView.findViewById(R.id.pod_list_duration);
//        podDate = (TextView) itemView.findViewById(R.id.pod_list_calender_date);
//        downloadPod = (ImageButton) itemView.findViewById(R.id.download_pod);
        //TODO: podDescription = (TextView) itemView.findViewById(R.id.pod_description); set pod description in xml file



        itemView.setOnClickListener(this);
        podImage.setOnClickListener(this);
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view, getAdapterPosition(), false);
    }
}
