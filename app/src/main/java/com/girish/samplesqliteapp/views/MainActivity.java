package com.girish.samplesqliteapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.girish.samplesqliteapp.R;
import com.girish.samplesqliteapp.model.Chef;
import com.girish.samplesqliteapp.model.Restaurant;
import com.girish.samplesqliteapp.sqlite.DatabaseHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRestaurants, btnChefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRestaurants = (Button)findViewById(R.id.btnRestaurants);
        btnRestaurants.setOnClickListener(this);
        btnChefs = (Button)findViewById(R.id.btnChefs);
        btnChefs.setOnClickListener(this);
        fillTables();
    }

    public void fillTables(){

        Restaurant restaurant1 =  new Restaurant(1,"McDonald","5002 NW RD","","San Antonio","TX","USA","2107778382");
        Restaurant restaurant2 =  new Restaurant(2,"Subway","122 Kings Dr","","Dallas","TX","USA","6577339088");
        Restaurant restaurant3 =  new Restaurant(3,"Chickfilla","145 oakdell way","","Chicago","IL","USA","8743332143");
        Restaurant restaurant4 =  new Restaurant(4,"Lubys","57 SW Ingram Rd","","Buffalo","NY","USA","2210019288");
        Restaurant restaurant5 =  new Restaurant(5,"Dominos","5900 Highway 90","","San Diego","CA","USA","9933848800");


        DatabaseHelper db = new DatabaseHelper(this);
        db.createRestaurant(restaurant1);
        db.createRestaurant(restaurant2);
        db.createRestaurant(restaurant3);
        db.createRestaurant(restaurant4);
        db.createRestaurant(restaurant5);

        //
        Chef chef1 = new Chef(1, "Chris","Boyd","1001 West Dr","San Antonio","Tx","USA","chris.boyd@gmail.com");
        Chef chef2 = new Chef(2, "Elizabeth","Redmond","1001 West Dr","Queens","NY","USA","elizabeth.123@yahoo.com");
        Chef chef3 = new Chef(3, "Victoria","Gomez","123 E Windam Rd","Phoenix","AZ","USA","vic.345@Ggail.com");
        Chef chef4 = new Chef(4, "Fernandis","Gabriel","45th St Westminster","San Antonio","Tx","USA","fernadis.1989@hotmail.com");
        Chef chef5 = new Chef(5, "Dean","Carter","3900 Davis rd","Kingsville","Tx","USA","dean.carter@yahoo.com");
        Chef chef6 = new Chef(6, "Travis","Mcman","90th NW loop","San Jose","CA","USA","travis.mcman@gmail.com");

        db.createChef(chef1);
        db.createChef(chef2);
        db.createChef(chef3);
        db.createChef(chef4);
        db.createChef(chef5);
        db.createChef(chef6);



        if(!db.isTableExists()) {

            db.createWork(restaurant1.getId(), chef1.getId());
            db.createWork(restaurant1.getId(), chef3.getId());
            db.createWork(restaurant1.getId(), chef4.getId());

            db.createWork(restaurant2.getId(), chef2.getId());
            db.createWork(restaurant2.getId(), chef5.getId());
            db.createWork(restaurant2.getId(), chef1.getId());

            db.createWork(restaurant3.getId(), chef6.getId());
            db.createWork(restaurant3.getId(), chef2.getId());

            db.createWork(restaurant4.getId(), chef4.getId());

            db.createWork(restaurant5.getId(), chef3.getId());
            db.createWork(restaurant5.getId(), chef1.getId());
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent;

        if(v.getId() == R.id.btnRestaurants){
            intent = new Intent(this, RestaurantListActivity.class);
            startActivity(intent);
        }else{
            intent = new Intent(this, ChefListActivity.class);
            startActivity(intent);
        }
    }


}
