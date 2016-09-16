package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;


public class SaveRecipeScreen extends AppCompatActivity {
    RelativeLayout relativeLayout;
    Spinner spinner;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_new_recipe);
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1200);
        view.startAnimation(mLoadAnimation);
        Bundle extras = getIntent().getExtras();
        String sUserName = extras.getString("key");
        Log.e("Error: ", sUserName);

        extras = new Bundle();
        bindUI();
    }
    public void bindUI() {

        final Context oContext = this;

    }
}
