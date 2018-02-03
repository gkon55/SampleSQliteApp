package com.girish.samplesqliteapp.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.girish.samplesqliteapp.R;
import com.girish.samplesqliteapp.model.Restaurant;
import com.girish.samplesqliteapp.sqlite.DatabaseHelper;

import java.util.ArrayList;

public class RestaurantListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView listView;
    private ArrayList<Restaurant> array;
    private RestaurantAdapter restaurantAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant_list);
        db = new DatabaseHelper(this);
        TextView textView = new TextView(this);
        array = new ArrayList<Restaurant>();
        for(int i =0; i< db.getAllRestaurantList().size(); i++) {
            array.add(db.getAllRestaurantList().get(i));
        }

        listView = (ListView) findViewById(R.id.restListId);
        listView.setOnItemClickListener(this);

        restaurantAdapter = new RestaurantAdapter(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(restaurantAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,WorkActivity.class);
        intent.putExtra("RestId", array.get(position).getId());
        intent.putExtra("RestName", array.get(position).getName());
        intent.putExtra("RestAdd", array.get(position).getAddress1());
        intent.putExtra("RestCity", array.get(position).getCity());
        intent.putExtra("RestState", array.get(position).getState());
        intent.putExtra("RestPhone", array.get(position).getPhone());

        startActivity(intent);
        finish();

    }
}

class RestaurantAdapter extends ArrayAdapter<Restaurant>{

    private ArrayList<Restaurant> array;
    private Context context;

    public RestaurantAdapter(@NonNull Context context, int resource, ArrayList<Restaurant> array) {
        super(context, resource);
        this.array = array;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LinearLayout layout = new LinearLayout(this.context);
        layout.setOrientation(LinearLayout.VERTICAL);

        TextView textView = new TextView(this.context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,35);
        Restaurant resObj = array.get(position);
        textView.setText(resObj.getName());
        layout.addView(textView);

        return layout;
    }

    @Override
    public int getCount() {
        return this.array.size();
    }
}