package team31.cs2340.gatech.sheltermap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * Class for RegisterActivity (the register screen logic)
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText name;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button cancel;
        Button submit;
        Spinner typeSpinner;

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);
        typeSpinner = (Spinner) findViewById(R.id.typeSpinner);

        /*
          Set up the adapter to display the allowable user types in the spinner
        */
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, User.legalTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        typeSpinner.setVisibility(View.INVISIBLE);

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //old way of returning (created new activity)
                    //Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                    //startActivity(go2Welcome);
                //use onBackPressed to remove multiple instances
                RegisterActivity.super.onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean fail = false;
                if(email.getText() == null || email.getText().toString().equals("") ) {
                    fail = true;
                }
                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){
                        fail = true;
                        Context context = getApplicationContext();
                        CharSequence text = "Email Already Used!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 1100);
                        toast.show();
                    }
                }
                if(!fail){
                    User newUser = new User(name.getText().toString(),
                            email.getText().toString(),password.getText().toString(),
                            phone.getText().toString());
                    //User.users.add(newUser);
                    User.currentUser = newUser;
                    User.saveUsers(view.getContext());
                    //go to login instead of directly into account
                    //Intent go2Account = new Intent(RegisterActivity.this,
                            //LoginActivity.class);
                    //using Toast to inform user that account was created
                    Context context = getApplicationContext();
                    CharSequence text = "Account created!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 880);
                    toast.show();
                    //startActivity(go2Account);
                    RegisterActivity.super.onBackPressed();
                }
                //Register fail - duplicate email
            }
        });

    }
}
