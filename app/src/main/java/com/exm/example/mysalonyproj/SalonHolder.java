package com.exm.example.mysalonyproj;



import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.exm.example.mysalonyhusseinproj.ItemonClick;


public class SalonHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
 public TextView name , adress, phone , price , desc;
     public ImageView proimg ;
     Button b ;
    ItemonClick itemonClick;
    public SalonHolder(View itemView) {
        super(itemView);
        name=itemView.findViewById(R.id.names);
        adress=itemView.findViewById(R.id.addresss);
        phone=itemView.findViewById(R.id.phones);
        proimg=itemView.findViewById(R.id.cover);
        price=itemView.findViewById(R.id.pricee);
        desc=itemView.findViewById(R.id.desc);
        b=itemView.findViewById(R.id.btncall);;
    }
public void setonclick (ItemonClick itemonClick){
this.itemonClick=itemonClick;

}
    @Override
    public void onClick(View view) {
itemonClick.onClick(view, getAdapterPosition(),false);
    }
}
