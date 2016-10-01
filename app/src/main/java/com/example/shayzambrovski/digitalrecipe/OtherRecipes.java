package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;

import java.util.Arrays;
import java.util.List;

public class OtherRecipes extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_recipes_screen);
        View view = findViewById(android.R.id.content);
        Animation mLoadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), android.R.anim.fade_in);
        mLoadAnimation.setDuration(1200);
        view.startAnimation(mLoadAnimation);
        Bundle extras = getIntent().getExtras();
        String sUserName = extras.getString("key");



        Log.e("Error: ", sUserName);
        extras = new Bundle();


        bindUI(sUserName);
    }
    public void bindUI(final String sUserName) {
        try {
            final Context oContext = this;
            final DatabaseHandler db = new DatabaseHandler(oContext);
            final List<Recipe> recipeList = db.getRecipesByOtherUserName(sUserName);
            String myRecipesNames[] = new String[recipeList.size() + 1];
            myRecipesNames[0] = getResources().getString(R.string.select_recipe);
            for(int i = 1 ; i < recipeList.size() + 1 ; i++) {
                myRecipesNames[i] = recipeList.get(i-1).getName();
            }
            this.spinner = (Spinner)findViewById(R.id.spinner1);
            this.spinner.setSelection(0);
            ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.my_text_view, myRecipesNames);
            spinnerArrayAdapter.setDropDownViewResource(R.layout.my_text_view); // The drop down view
            this.spinner.setAdapter(spinnerArrayAdapter);

            this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parent,
                                           View view, int pos, long id) {

                    String selectedText = parent.getItemAtPosition(pos).toString();
                    if(selectedText.equals(getResources().getString(R.string.select_recipe)))
                        return;

                    RatingBar ratingBar = (RatingBar)findViewById(R.id.my_rate_bar);
                    final Recipe recipe = recipeList.get(pos - 1);
                    final int numOfStars = recipeList.get(pos - 1).getRate();
                    ratingBar.setRating(numOfStars);
                    Log.e("Error: ", String.valueOf(numOfStars));
                    ratingBar.setVisibility(View.VISIBLE);

                    ratingBar.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                float touchPositionX = event.getX();
                                float width = ((RatingBar)findViewById(R.id.my_rate_bar)).getWidth();
                                float starsf = (touchPositionX / width) * 5.0f;
                                int stars = (int)starsf + 1;
                                recipe.setRate(stars);

                                Log.e("Error: ", String.valueOf(recipe.getRate()));
                                db.updateRecipeRate(recipe.getName(), recipe.getRate(), recipe); //update rate in DB
                                RatingBar rBar = (RatingBar)v;
                                rBar.setRating(recipe.getRate());
                            }
                            return true;
                        }
                    });

                    String[] aIngredients = recipeList.get(pos - 1).getIngredients().split("@", -1);
                    String[] myIngredients = new String[(aIngredients.length / 2) + 2];
                    Arrays.copyOf(aIngredients, aIngredients.length-1);
                    myIngredients[0] = getResources().getString(R.string.made_by) + ": " + recipeList.get(pos - 1).getUserName();
                    for(int i = 1, j = 0 ; i < aIngredients.length / 2 + 1; i++) {

                        myIngredients[i] = aIngredients[j++] + " " + aIngredients[j++];

                    }
                    myIngredients[myIngredients.length - 1] = recipeList.get(pos - 1).getInstructions();
                    ListView listView = (ListView)findViewById(R.id.myListView);
                    listView.setTextFilterEnabled(true);
                    listView.setAdapter(new ArrayAdapter<String>(oContext, R.layout.my_item,myIngredients));
                }

                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });

        }catch(Exception e) {
            Log.e("Error: ", e.toString());
        }
    }
}
