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
public class WomenFragment extends Fragment {

TextView message , Facial  , Threading , Hair ,BridalMakeup , Eyelash,MakeUp ,Nails ,Offers,PermanentMakeUp , Bodycare , Waxing ;
ImageView messageimg , Facialimg  , Threadingimg , Hairimg ,BridalMakeupimg , Eyelashimg,MakeUpimg ,Nailsimg ,Offersimg,PermanentMakeUpimg , Bodycareimg , Waxingimg ;

    public WomenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_women, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        message= getActivity().findViewById(R.id.message);
        Facial=getActivity().findViewById(R.id.Facial);
        Threading=getActivity().findViewById(R.id.ther);
        messageimg= getActivity().findViewById(R.id.imgmessage);
        Facialimg=getActivity().findViewById(R.id.imgfacial);
        Threadingimg=getActivity().findViewById(R.id.imgther);
        Threading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Threading");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Threadingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Threading");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Hair=getActivity().findViewById(R.id.hair);
        Hairimg=getActivity().findViewById(R.id.imghair);
        Hair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Hair");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Hairimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Hair");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        BridalMakeup=getActivity().findViewById(R.id.bac);
        BridalMakeupimg=getActivity().findViewById(R.id.imgbdc);
        BridalMakeup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Bridal Hair &amp; Makeup");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        BridalMakeupimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Bridal Hair &amp; Makeup");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Eyelash=getActivity().findViewById(R.id.eye);
        Eyelashimg=getActivity().findViewById(R.id.imgeye);
        MakeUp=getActivity().findViewById(R.id.makup);
        MakeUpimg=getActivity().findViewById(R.id.imgmakeup);
        MakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Make Up");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        MakeUpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Make Up");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Eyelash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Eyelash");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Eyelashimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Eyelash");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Offers=getActivity().findViewById(R.id.offers);
        Offersimg=getActivity().findViewById(R.id.imgoffers1);
        Offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Offers");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Offersimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Offers");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        PermanentMakeUp =getActivity().findViewById(R.id.pamak);
        PermanentMakeUpimg =getActivity().findViewById(R.id.imgpmk);
        PermanentMakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Permanent MakeUp");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        PermanentMakeUpimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Permanent MakeUp");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Nails =getActivity().findViewById(R.id.nails);
        Nailsimg =getActivity().findViewById(R.id.imgnails);
        Nails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Nails");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Nailsimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Nails");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Bodycare=getActivity().findViewById(R.id.bc);
        Bodycareimg=getActivity().findViewById(R.id.imgbcare);
        Bodycare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Body care");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Bodycareimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", "Body care");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Waxing =getActivity().findViewById(R.id.wax);
        Waxingimg =getActivity().findViewById(R.id.imgwax);
        Waxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", " Waxing");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Waxingimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub", " Waxing");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });


        Facial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Facial");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        Facialimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Facial");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Massage");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });
        messageimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(getActivity(),ViewServiceActivity.class);
                intent.putExtra("sub","Massage");
                intent.putExtra("w","Salon For Women");
                startActivity(intent);
            }
        });

    }
}
