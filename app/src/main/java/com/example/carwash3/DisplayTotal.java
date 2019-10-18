package com.example.carwash3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

public class DisplayTotal extends AppCompatActivity {

    private ConstraintLayout CLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_total);

        String price = getIntent().getStringExtra("Price");

        Bundle bundle = new Bundle();
        bundle.putString("Price", price);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragment_display_total frag = new fragment_display_total();

        frag.setArguments(bundle);

        CLayout = findViewById(R.id.cost_frag_container);

        CLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getIntent();
                intent.putExtra("message","Thank you for choosing our services");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        ft.add(R.id.cost_frag_container, frag);
        ft.addToBackStack(null);
        ft.commit();
    }
}
