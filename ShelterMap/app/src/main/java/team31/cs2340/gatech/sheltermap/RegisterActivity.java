package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText name;
    private EditText phone;
    private Button cancel;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);

        /******************************************************************************************/

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {

            // https://androidsolved.wordpress.com/2015/07/01/how-to-move-from-one-activityscreen-to-another-activityscreen-in-android/
            @Override
            public void onClick(View view) {

                Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                startActivity(go2Welcome);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean fail = false;
                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){
                        fail = true;
                    }
                }
                if(!fail){
                    User newUser = new User(name.getText().toString(),email.getText().toString(),password.getText().toString(),phone.getText().toString());
                    User.users.add(newUser);
                    Intent go2Account = new Intent(RegisterActivity.this, AccountActivity.class);
                    startActivity(go2Account);
                }
                //Register fail - duplicate email
            }
        });

    }
}
