package team31.cs2340.gatech.sheltermap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/*
 * Created by Elmer on 3/3/2018.
 */

/**
 * Class for the DetailedViewActivity (the view logic after clicking on shelter in main screen)
 */
public class DetailedViewActivity extends AppCompatActivity{

    private Button checkin;
    private Button checkout;
    private EditText famnum;

    @Override
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
                int i;
                try {
                    i=Integer.parseInt(value);
                } catch (Exception e) {
                    i = 0;
                }

                if(i > 0){
                    if(User.currentUser.getUserReserved() == 0){
                        if((shelter.getCap()-shelter.getPop()) >= i){
                            User.currentUser.setUserReserved(1);
                            User.currentUser.setUserResNum(i);
                            shelter.addPop(i);
                            User.currentUser.setUserShelterId(shelter.getKey());
                            User.saveUsers(view.getContext());
                            capacity.setText("Capacity: " + shelter.getPop() + "/"
                                    + shelter.getCap());
                        }
                    }

                }
            }
        });



        checkout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {

               if(User.currentUser.getUserReserved() == 1){
                   if(User.currentUser.getUserShelterId() == shelter.getKey()){
                       User.currentUser.setUserReserved(0);
                       User.currentUser.setUserShelterId(-1);
                       User.saveUsers(view.getContext());
                       shelter.subPop(User.currentUser.getUserResNum());
                       User.currentUser.setUserResNum(0);
                       capacity.setText("Capacity: " + shelter.getPop() + "/" + shelter.getCap());
                   }
               }
            }
        });
    }


}
