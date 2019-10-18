package com.example.carwash3;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private final int REQUEST_CODE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentTransaction ft =getSupportFragmentManager().beginTransaction();
        fragment_package_select frag = new fragment_package_select();
        ft.add(R.id.package_detail_fragment_placeholder, frag);
        ft.addToBackStack(null);
        ft.commit();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        Log.d("hello", "onActivityResult: hrllo " +requestCode+ " : " +resultCode);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                String message = data.getStringExtra("message");

                if (message != null) {
                    Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
                }
            }
        }

        //super.onActivityResult(requestCode, resultCode, data);
    }
}

