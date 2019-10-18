package com.example.carwash3;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * A simple {@link Fragment} subclass.
 */
public class fragment_package_select extends Fragment {

    private final int REQUEST_CODE = 2;

    private EditText amountWanted;
    private RadioGroup packageOptions;
    private Button calculatePrice;

    private CarwashOrder order;

    public fragment_package_select() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_package_select, container, false);

        amountWanted = (EditText) view.findViewById(R.id.amountWanted);
        packageOptions = (RadioGroup) view.findViewById(R.id.packageOptions);
        calculatePrice = (Button) view.findViewById(R.id.calculate_price);

        order = new CarwashOrder();

        calculatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculatePrice();
            }
        });

        packageOptions.setOnCheckedChangeListener(washTypeListener);

        return view;
    }

    private RadioGroup.OnCheckedChangeListener washTypeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int packageChoice) {
            switch (packageChoice) {
                case R.id.exterior:
                    order.setPrice(order.EXTERIOR_ONLY);
                    break;
                case R.id.interior_exterior:
                    order.setPrice(order.INTERIOR_EXTERIOR);
                    break;
            }
        }
    };

    private void calculatePrice() {

        if (amountWanted.getText().toString().length() != 0) {

            int amount = Integer.parseInt(amountWanted.getText().toString());
            order.setAmount(amount);

            String price = NumberFormat.getCurrencyInstance().format(order.getTotalPrice());

            int orientation = getActivity().getResources().getConfiguration().orientation;
            if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                // In landscape

                Bundle bundle = new Bundle();
                bundle.putString("Price", price);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                fragment_display_total frag = new fragment_display_total();

                frag.setArguments(bundle);

                ft.replace(R.id.cost_text_fragment_placeholder, frag);
                ft.addToBackStack(null);
                ft.commit();
            } else {
                // In portrait
                Intent intent = new Intent(getActivity(), DisplayTotal.class);
                intent.putExtra("Price", price);
                getActivity().startActivityForResult(intent, REQUEST_CODE);
            }
        }
        else
            emptyNumberException();
    }

    private void emptyNumberException() {
        Toast.makeText(getContext(), "Amount field cannot be empty", Toast.LENGTH_LONG).show();
    }

}
