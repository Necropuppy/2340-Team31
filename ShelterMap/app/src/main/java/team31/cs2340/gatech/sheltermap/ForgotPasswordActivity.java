package team31.cs2340.gatech.sheltermap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ForgotPasswordActivity extends AppCompatActivity {

    private EditText email;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = (EditText) findViewById(R.id.email);
        send = (Button) findViewById(R.id.send);

        final TextView invalidEmail = (TextView) findViewById(R.id.invalidEmail);
        final TextView validEmail = (TextView) findViewById(R.id.validEmail);

        /******************************************************************************************/

        send.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean fail = true;
                for(User u: User.users){
                    if(u.getEmail().equals(email.getText().toString())){
                        //login success
                        Intent sendEmail = new Intent(Intent.ACTION_SEND);
                        sendEmail.putExtra(Intent.EXTRA_EMAIL, new String[]{u.getEmail()});
                        sendEmail.putExtra(Intent.EXTRA_SUBJECT, "Shelter Mapp Password Recovery");
                        sendEmail.putExtra(Intent.EXTRA_TEXT, "Your password is: " + u.getPassword());
                        sendEmail.setType("message/rfc822");
                        startActivity(Intent.createChooser(sendEmail, "Choose email client..."));

                        fail = false;
                        invalidEmail.setVisibility(View.INVISIBLE);
                        validEmail.setVisibility(View.VISIBLE);
                    }
                }
                if(fail) {
                    //login fail
                    validEmail.setVisibility(View.INVISIBLE);
                    invalidEmail.setVisibility(View.VISIBLE);
                }
            }
        });
    }

}
