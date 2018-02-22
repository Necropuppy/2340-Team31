package team31.cs2340.gatech.sheltermap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

<<<<<<< HEAD
    private EditText username;
=======
    private EditText email;
>>>>>>> nathan
    private EditText password;
    private Button cancel;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

<<<<<<< HEAD
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        final TextView errorText = (TextView) findViewById(R.id.password);
=======
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        final TextView errorText = (TextView) findViewById(R.id.error);
>>>>>>> nathan
        errorText.setTextColor(Color.RED);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);

        /******************************************************************************************/

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
            @Override
            public void onClick(View view) {
                // TODO Auto-generated method stub
                Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(go2Welcome);
            }
        });

<<<<<<< HEAD

//        submit = (Button) findViewById(R.id.submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//
//            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
//            @Override
//            public void onClick(View view) {
//                // TODO Auto-generated method stub
//                Intent go2Account = new Intent(getApplicationContext(), AccountActivity.class);
//                startActivity(go2Account);
//            }
//        });


        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (username.getText().toString().equals("user") && password.getText().toString().equals("pass")){
                    Intent go2Account = new Intent(LoginActivity.this, AccountActivity.class);
                    startActivity(go2Account);
                } else {
=======
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean fail = true;
                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){
                        if(u.getPassword().equals(password.getText().toString())){
                            //login success
                            Intent go2Account = new Intent(LoginActivity.this, AccountActivity.class);
                            startActivity(go2Account);
                            fail = false;
                        }
                    }
                }
                if(fail) {
                    //login fail
>>>>>>> nathan
                    errorText.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}
