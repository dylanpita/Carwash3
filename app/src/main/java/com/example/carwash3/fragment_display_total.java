package com.example.carwash3;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_display_total extends Fragment {

    private TextView CostTV;

    public fragment_display_total() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_display_total, container, false);

        CostTV = (TextView) view.findViewById(R.id.total_price);

        if (getArguments() !=null) {
            String price = getArguments().getString("Price");
            CostTV.setText(price);
        }

        return view;
    }

}
