package com.example.chuks.vibefmbenin;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monday extends Fragment {

    View view;

    public Monday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_monday, container, false);

//        View listItemView = view;
//        if (listItemView == null){
//            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
//        }

      //  TextView magnitudeView = (TextView) view.findViewById(R.id.magnitude);
    }

}
