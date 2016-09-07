package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainScreen extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button logIn;
    Button register;
    Button about;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        bindUI();
    }
    public void bindUI() {
        this.userName = (EditText)findViewById(R.id.user_name_id);
        this.password = (EditText)findViewById(R.id.password_id);
        this.logIn = (Button)findViewById(R.id.log_in_id);
        this.register = (Button)findViewById(R.id.register_id);
        final Context oContext = this;
        this.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    DatabaseHandler db = new DatabaseHandler(oContext);
                    String sUserName = userName.getText().toString();
                    String sPassword = password.getText().toString();
                    Toast.makeText(getApplicationContext(),String.valueOf(db.getUsersCount()),Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_SHORT).show();
                    if(sUserName.equals("") || sPassword.equals("")) {
                        Toast.makeText(getApplicationContext(),"Bad",Toast.LENGTH_SHORT).show();
                    } else {
                        db.addUser(new User(sUserName, sPassword));
                    }

                    //Intent myIntent = new Intent(MainScreen.this, RegisterScreen.class);
                    //startActivity(myIntent);
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
        this.about = (Button)findViewById(R.id.about_id);
        this.about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    DatabaseHandler db = new DatabaseHandler(oContext);
                    //Intent myIntent = new Intent(MainScreen.this, AboutScreen.class);
                    //startActivity(myIntent);
                    db.deleteDB();
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });

    }
}
