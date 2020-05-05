package com.exm.example.mysalonyproj;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



/**
 * A simple {@link Fragment} subclass.
 */
public class MenFragment extends Fragment {
    TextView haiercut , haierstyle  , beard, createn ,oil, skin,offers ;
    ImageView haiercutimg , haierstyleimg  , beardimg, createnimg ,oilimg, skinimg,offersimg ;
    public MenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_men, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        haiercutimg= getActivity().findViewById(R.id.imghaircut);
        haierstyleimg=getActivity().findViewById(R.id.imghairsty);
        beardimg=getActivity().findViewById(R.id.imgbeared);
        createnimg=getActivity().findViewById(R.id.imgcr);
        oilimg=getActivity().findViewById(R.id.imgoil);
        skinimg=getActivity().findViewById(R.id.imgskin);
        offersimg=getActivity().findViewById(R.id.imgoffers);

        haiercut= getActivity().findViewById(R.id.haircut);
        haierstyle=getActivity().findViewById(R.id.haierstyle);
        beard=getActivity().findViewById(R.id.Besred);
        createn=getActivity().findViewById(R.id.cp);
        oil=getActivity().findViewById(R.id.oil);
        skin=getActivity().findViewById(R.id.skin);
        offers=getActivity().findViewById(R.id.offers);
        haiercut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Haircut");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        haierstyle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Hairstyle");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        beard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Beared");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        createn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Creatine &amp; Protein");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        oil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Oil Bath");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        skin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Skin Cleaning");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Offers");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });




        haiercutimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Haircut");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        haierstyleimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Hairstyle");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        beardimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Beared");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        createnimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Creatine &amp; Protein");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        oilimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Oil Bath");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

        skinimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Skin Cleaning");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });
        offersimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Offers");
                intent.putExtra("w","Salon For Men");
                startActivity(intent);
            }
        });

    }
}
