package com.exm.example.mysalonyproj;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.exm.example.mysalonyhusseinproj.ItemonClick;


public class MyservHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
 public TextView proname , prodesc  , proprice ,protype,proowner , city ;
TextView call , fav ;
     public ImageView proimg ;
    ItemonClick itemonClick;
    public MyservHolder(View itemView) {
        super(itemView);
        proimg = itemView.findViewById(R.id.proimg);
        proname=itemView.findViewById(R.id.proname1);
        prodesc=itemView.findViewById(R.id.prodesc);
        proprice=itemView.findViewById(R.id.proprice1);
        protype=itemView.findViewById(R.id.proadde);
        city=itemView.findViewById(R.id.city);
        proowner=itemView.findViewById(R.id.owner);
        call=itemView.findViewById(R.id.btncall);
        fav=itemView.findViewById(R.id.btnfav);
    }
public void setonclick (ItemonClick itemonClick){
this.itemonClick=itemonClick;

}
    @Override
    public void onClick(View view) {
itemonClick.onClick(view, getAdapterPosition(),false);
    }
}
