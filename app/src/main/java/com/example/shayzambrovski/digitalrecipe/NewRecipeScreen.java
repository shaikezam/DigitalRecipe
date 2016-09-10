package com.example.shayzambrovski.digitalrecipe;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class NewRecipeScreen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe_screen);
        Bundle extras = getIntent().getExtras();
        String sUserName = extras.getString("key");
        final Context oContext = this;
        DatabaseHandler db = new DatabaseHandler(oContext);
        Toast.makeText(getApplicationContext(),String.valueOf(db.getRecipeCount()),Toast.LENGTH_SHORT).show();
        Log.e("Error: ", sUserName);
        extras = new Bundle();
        bindUI(sUserName);
    }
    public void bindUI(final String sUserName) {

    }
}
