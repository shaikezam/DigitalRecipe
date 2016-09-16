package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class MainScreen extends AppCompatActivity {

    EditText userName;
    EditText password;
    Button logIn;
    Button register;
    Button about;
    Bundle extras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1200);
        view.startAnimation(mLoadAnimation);
        extras = new Bundle();
        bindUI();
    }
    public void bindUI() {
        final Context oContext = this;
        this.userName = (EditText)findViewById(R.id.user_name_id);

        this.password = (EditText)findViewById(R.id.password_id);

        this.logIn = (Button)findViewById(R.id.log_in_id);

        this.logIn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    DatabaseHandler db = new DatabaseHandler(oContext);
                    String sUserName = userName.getText().toString();
                    String sPassword = password.getText().toString();
                    //Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_SHORT).show();
                    if(sUserName.equals("") || sPassword.equals("")) {

                        DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.authentication_error), getResources().getString(R.string.fill_fields));
                        dm.show();

                    } else {
                        User oUser = db.getUser(sUserName, sPassword);
                        if (oUser == null) {
                            DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.authentication_error), (getResources().getString(R.string.cant_found_user)));
                            dm.show();
                        }
                        else {
                            //DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.success),sUserName + ": " + getResources().getString(R.string.log_in_success));
                            //dm.show();

                            Intent myIntent = new Intent(MainScreen.this, MenuScreen.class);
                            myIntent.putExtra("key", sUserName); //Optional parameters
                            startActivity(myIntent);
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        }
                    }

                    //Intent myIntent = new Intent(MainScreen.this, RegisterScreen.class);
                    //startActivity(myIntent);
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });

        this.register = (Button)findViewById(R.id.register_id);

        this.register.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    DatabaseHandler db = new DatabaseHandler(oContext);
                    String sUserName = userName.getText().toString();
                    String sPassword = password.getText().toString();
                    //Toast.makeText(getApplicationContext(),"Good",Toast.LENGTH_SHORT).show();
                    if(sUserName.equals("") || sPassword.equals("")) {

                        DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.authentication_error), getResources().getString(R.string.fill_fields));
                        dm.show();

                    } else {
                        long number = db.addUser(new User(sUserName, sPassword));
                        if(number == -1) {
                            DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.registration_error), getResources().getString(R.string.user_duplicate));
                            dm.show();
                        } else {
                            DialogManager dm = new DialogManager(oContext, getResources().getString(R.string.success),sUserName + ": " + getResources().getString(R.string.registration_success));
                            dm.show(); //TODO: move to main menu screen
                        }
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
