package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MenuScreen extends AppCompatActivity {

    Button createNewRecpie;
    Button myRecipe;
    Button allRecipe;
    Button topRatedrecipe;
    Button logOut;
    Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);
        Bundle extras = getIntent().getExtras();
        String sUserName = extras.getString("key");
        final Context oContext = this;

        DatabaseHandler db = new DatabaseHandler(oContext);
        Log.e("Error: ", sUserName);
        extras = new Bundle();
        bindUI(sUserName);
    }
    public void bindUI(final String sUserName) {
        this.createNewRecpie = (Button)findViewById(R.id.new_recipe);

        this.createNewRecpie.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try{
                    Intent myIntent = new Intent(MenuScreen.this, NewRecipeScreen.class);
                    myIntent.putExtra("key", sUserName); //Optional parameters
                    startActivity(myIntent);
                } catch(Exception e) {
                    Log.e("Error: ", e.toString());
                }
            }
        });
    }
}
