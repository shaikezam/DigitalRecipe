package com.example.shayzambrovski.digitalrecipe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        this.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Intent myIntent = new Intent(MainScreen.this, RegisterScreen.class);
                    startActivity(myIntent);
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
        this.about = (Button)findViewById(R.id.about_id);
        this.about.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Intent myIntent = new Intent(MainScreen.this, AboutScreen.class);
                    startActivity(myIntent);
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });

    }
}
