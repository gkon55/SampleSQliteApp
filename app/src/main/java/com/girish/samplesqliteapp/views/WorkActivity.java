package com.girish.samplesqliteapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.util.TypedValue;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.girish.samplesqliteapp.R;
import com.girish.samplesqliteapp.model.Chef;
import com.girish.samplesqliteapp.sqlite.DatabaseHelper;

import java.util.ArrayList;

public class WorkActivity extends AppCompatActivity {
    private TextView textViewRestaurant;
    private TextView [] textViewChef;
    private DatabaseHelper db;
    private ArrayList<Chef> array;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linearLayout);
        db = new DatabaseHelper(this);


        intent = getIntent();
        int  restID = intent.getIntExtra("RestId",0);
        String RestName =  intent.getStringExtra("RestName");
        String RestAdd =  intent.getStringExtra("RestAdd");
        String RestCity =  intent.getStringExtra("RestCity");
        String RestState =  intent.getStringExtra("RestState");
        String RestPhone =  intent.getStringExtra("RestPhone");

        // Setting text details for selected Restaurant to Textview
        textViewRestaurant = new TextView(this);
        textViewRestaurant.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        textViewRestaurant.setText(Html.fromHtml(RestName + "<br/>" + RestAdd + "<br/>" + RestCity + ", " + RestState + "<br/> " + RestPhone));
        linearLayout.addView(textViewRestaurant);

        // Get all Chefs working in a particular restaurant selected.
        array = new ArrayList<Chef>();
        array.clear();
        for(int i =0; i< db.getAllChefsByRestaurant(restID).size(); i++) {
            array.add(db.getAllChefsByRestaurant(restID).get(i));
        }


        textViewChef = new TextView[array.size()];
        for(int i = 0; i < array.size(); i++) {
            textViewChef[i] = new TextView(this);
        }

        // Setting Chefs details text for selected restaurant to Textview's
        for (int i =0; i< array.size(); i++){

            textViewChef[i].setText(Html.fromHtml(array.get(i).getFirstName() + " " + array.get(i).getSecondName()
                    + ",<br/> " + array.get(i).getAddress() + ",<br/> " + array.get(i).getCity()
                    + ", " + array.get(i).getState()));

            textViewChef[i].setPadding(10,30,10,5);
            textViewChef[i].setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
            linearLayout.addView(textViewChef[i]);
            Log.e("WorkActivity", array.get(i).getFirstName());
        }
    }
}

