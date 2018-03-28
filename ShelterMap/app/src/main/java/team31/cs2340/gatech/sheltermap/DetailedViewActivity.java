package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Elmer on 3/3/2018.
 */

public class DetailedViewActivity extends AppCompatActivity{

    private Button checkin;
    private Button checkout;
    private EditText famnum;

    protected void onCreate(Bundle savedInstanceState) {



        Log.d(AccountActivity.TAG, "Successful on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        checkin = (Button) findViewById(R.id.checkin);
        checkout = (Button) findViewById(R.id.checkout);
        famnum = (EditText) findViewById(R.id.familyNum);

        int position = getIntent().getExtras().getInt("Position");
        Shelter shelter = Shelter.shelters.get(position);
        Log.d(AccountActivity.TAG, "Successful extra");
        TextView name = (TextView) findViewById(R.id.shelter_name);
        name.setText("Shelter Name: " + shelter.getName());
        Log.d(AccountActivity.TAG, "Successful name");
        TextView key = (TextView) findViewById(R.id.shelter_key);
        key.setText("Shelter ID: " + shelter.getKey());
        TextView capacity = (TextView) findViewById(R.id.shelter_cap);
        capacity.setText("Capacity: " + shelter.getPop() + "/" + shelter.getCap());
        TextView restriction = (TextView) findViewById(R.id.shelter_restriction);
        restriction.setText("" + shelter.getRestriction());
        TextView longitude = (TextView) findViewById(R.id.shelter_longitude);
        longitude.setText("Longitude: " + shelter.getLongitude());
        TextView latitude = (TextView) findViewById(R.id.shelter_latitude);
        latitude.setText("Latitude: " + shelter.getLatitude());
        TextView address = (TextView) findViewById(R.id.shelter_address);
        address.setText("Address: " + shelter.getAddress());





        checkin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                String value= famnum.getText().toString();
                int i=Integer.parseInt(value);

                if(i > 0){
                    if(User.Reserved == 0){
                        if((shelter.getCap()-shelter.getPop()) >= i){
                            User.Reserved = 1;
                            User.ResNum = i;
                            shelter.addPop(i);
                            User.ShelterId = shelter.getKey();
                            capacity.setText("Capacity: " + shelter.getPop() + "/" + shelter.getCap());
                        }
                    }

                }
            }
        });



        checkout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

               if(User.Reserved == 1){
                   if(User.ShelterId == shelter.getKey()){
                       User.Reserved = 0;
                       User.ShelterId = -1;
                       shelter.subPop(User.ResNum);
                       capacity.setText("Capacity: " + shelter.getPop() + "/" + shelter.getCap());
                   }
               }
            }
        });
    }


}
