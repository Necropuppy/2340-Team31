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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button checkin;
        Button checkout;
        EditText famnum;


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
        String name1 = shelter.getName();
        name.setText(name1);
        Log.d(AccountActivity.TAG, "Successful name");
        TextView key = (TextView) findViewById(R.id.shelter_key);
        String name2 = "" + shelter.getKey();
        key.setText(name2);
        TextView capacity = (TextView) findViewById(R.id.shelter_cap);
        String name3 = "" + shelter.getPop() + "/" + shelter.getCap();
        capacity.setText(name3);
        TextView restriction = (TextView) findViewById(R.id.shelter_restriction);
        String name4 = "" + shelter.getRestriction();
        restriction.setText(name4);
        TextView longitude = (TextView) findViewById(R.id.shelter_longitude);
        String name5 = "" + shelter.getLongitude();
        longitude.setText(name5);
        TextView latitude = (TextView) findViewById(R.id.shelter_latitude);
        String name6 = "" + shelter.getLatitude();
        latitude.setText(name6);
        TextView address = (TextView) findViewById(R.id.shelter_address);
        String name7 = "" + shelter.getAddress();
        address.setText(name7);





        checkin.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(User.annonymous) return;
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
                            String name1 = "Capacity: " + shelter.getPop() + "/"
                                    + shelter.getCap() ;
                            capacity.setText(name1);
                        }
                    }

                }
            }
        });



        checkout.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if(User.annonymous)return;

               if(User.currentUser.getUserReserved() == 1){
                   if(User.currentUser.getUserShelterId() == shelter.getKey()){
                       User.currentUser.setUserReserved(0);
                       User.currentUser.setUserShelterId(-1);
                       User.saveUsers(view.getContext());
                       shelter.subPop(User.currentUser.getUserResNum());
                       User.currentUser.setUserResNum(0);
                       String name1 = "Capacity: " + shelter.getPop() + "/"
                               + shelter.getCap();
                       capacity.setText(name1);
                   }
               }
            }
        });
    }


}
