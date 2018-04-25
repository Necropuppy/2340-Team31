package team31.cs2340.gatech.sheltermap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Class for LoginActivity (the login screen logic)
 */
public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button cancel;
    private Button submit;

    private TextView lockedOut;
    private TextView forgotPassword;

    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button cancel;
        Button submit;
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        final TextView errorText = (TextView) findViewById(R.id.error);
        errorText.setTextColor(Color.RED);

        cancel = (Button) findViewById(R.id.cancel);
        submit = (Button) findViewById(R.id.submit);

        lockedOut = (TextView) findViewById(R.id.lockedOut);
        forgotPassword = (TextView) findViewById(R.id.forgotPassword);

        /******************************************************************************************/

        cancel = (Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                //old way of returning (created new activity)
                    //Intent go2Welcome = new Intent(getApplicationContext(), WelcomeActivity.class);
                    //startActivity(go2Welcome);
                //use onBackPressed to remove multiple activities
                LoginActivity.super.onBackPressed();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean fail = true;

                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){
                        if(u.getPassword().equals(password.getText().toString())){
                            //login success
                            User.currentUser = u;
                            User.annonymous = false;
                            Intent go2Account = new Intent(LoginActivity.this,
                                    AccountActivity.class);
                            startActivity(go2Account);
                            fail = false;
                        }
                    }
                }
                if(fail) {
                    //login fail
                        //errorText.setVisibility(View.VISIBLE);
                    //using Toast instead of TextView
                    Context context = getApplicationContext();
                    CharSequence text = "Login Failed!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 1100);
                    toast.show();
                    count++; //lock out count

                    if (count == 3) {

                        submit.setVisibility(View.INVISIBLE);
                        lockedOut.setVisibility(View.VISIBLE);

                        new CountDownTimer(10000, 1000) {

                            public void onTick(long millisUntilFinished) {
                                lockedOut.setTextColor(Color.parseColor("#FFFF4444"));
                                lockedOut.setText("You are locked out for: " + (millisUntilFinished / 1000 + 1) + " seconds.");
                            }

                            public void onFinish() {
                                lockedOut.setTextColor(Color.parseColor("#FF99CC00"));
                                lockedOut.setText("You can log back in.");
                                submit.setVisibility(View.VISIBLE);
                                count = 0;
                            }
                        }.start();
                    }
                }
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go2ForgotPassword = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(go2ForgotPassword);
            }
        });
    }
}
