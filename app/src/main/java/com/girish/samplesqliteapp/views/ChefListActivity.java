package com.girish.samplesqliteapp.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.girish.samplesqliteapp.R;
import com.girish.samplesqliteapp.model.Chef;
import com.girish.samplesqliteapp.sqlite.DatabaseHelper;

import java.util.ArrayList;


public class ChefListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<Chef> array;
    private ChefAdapter chefAdapter;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chef_list);
        db = new DatabaseHelper(this);
        TextView textView = new TextView(this);
        array = new ArrayList<Chef>();
        for(int i =0; i< db.getAllChefsList().size(); i++) {
            array.add(db.getAllChefsList().get(i));
        }

        listView = (ListView) findViewById(R.id.chefListId);

        chefAdapter = new ChefAdapter(this, android.R.layout.simple_list_item_1, array);
        listView.setAdapter(chefAdapter);
    }

}


class ChefAdapter extends ArrayAdapter{

    private ArrayList<Chef> array;
    private Context context;

    public ChefAdapter(@NonNull Context context, int resource, ArrayList<Chef> array) {
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
        Chef chefObj = array.get(position);
        textView.setText(chefObj.getFirstName() + " " +chefObj.getSecondName());
        layout.addView(textView);

        return layout;
    }

    @Override
    public int getCount() {
        return this.array.size();
    }
}