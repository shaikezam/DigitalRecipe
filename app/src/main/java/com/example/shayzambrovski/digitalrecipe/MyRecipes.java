package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MyRecipes extends AppCompatActivity {

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_recipes_screen);
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
            DatabaseHandler db = new DatabaseHandler(oContext);
            final List<Recipe> recipeList = db.getRecipesByUserName(sUserName);
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
                    Log.e("Error :", selectedText + " " + String.valueOf(pos));

                    RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.shaike);
                    int myEditTextWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 120, getResources().getDisplayMetrics());
                    int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 13, getResources().getDisplayMetrics());
                    int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 60, getResources().getDisplayMetrics());
                    int textViewTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 3, getResources().getDisplayMetrics());
                    int editTextTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 3, getResources().getDisplayMetrics());
                    int marginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 10, getResources().getDisplayMetrics());

                    TextView myRecipeName = new TextView(oContext);
                    myRecipeName.setText(selectedText);
                    myRecipeName.setBackground(getResources().getDrawable(R.drawable.rounded_option));
                    myRecipeName.setHeight(height);
                    myRecipeName.setWidth(width);

                    myRecipeName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    myRecipeName.setTextSize(textViewTextSize);

                    //myRecipeName.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    RelativeLayout.LayoutParams myRecipeNameParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    myRecipeNameParams.addRule(RelativeLayout.BELOW, R.id.spinner1);
                    myRecipeNameParams.setMargins(0, marginTop, 0, 0);
                    myRecipeNameParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    myLayout.addView(myRecipeName, myRecipeNameParams);

                    String[] aIngredients = recipeList.get(pos - 1).getIngredients().split("@", -1);
                    ArrayList<TextView> aTextView = new ArrayList<TextView>();
                    for(int i = 0, j = 0 ; i < aIngredients.length / 2; i++) {
                        TextView oTextView = new TextView(oContext);
                        oTextView.setText(aIngredients[j++] + " " + aIngredients[j++]);
                        oTextView.setBackground(getResources().getDrawable(R.drawable.rounded_option));
                        oTextView.setHeight(height);
                        oTextView.setWidth(width);

                        oTextView.setId(i);
                        oTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                        oTextView.setTextSize(textViewTextSize);
                        RelativeLayout.LayoutParams oTextViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                        if(i == 0) {
                            oTextViewParams.addRule(RelativeLayout.BELOW, myRecipeName.getId());
                        } else {
                            oTextViewParams.addRule(RelativeLayout.BELOW, aTextView.get(i-1).getId());
                        }
                        oTextViewParams.setMargins(0, marginTop, 0, 0);
                        oTextViewParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                        aTextView.add(oTextView);
                        myLayout.addView(oTextView, oTextViewParams);
                    }

                    TextView myRecipeInstructions = new TextView(oContext);
                    myRecipeInstructions.setText(recipeList.get(pos - 1).getInstructions());
                    myRecipeInstructions.setBackground(getResources().getDrawable(R.drawable.rounded_option));
                    myRecipeInstructions.setHeight(height);
                    myRecipeInstructions.setWidth(width);

                    myRecipeInstructions.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    myRecipeInstructions.setTextSize(textViewTextSize);

                    //myRecipeName.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
                    RelativeLayout.LayoutParams myRecipeInstructionsParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    myRecipeInstructionsParams.addRule(RelativeLayout.BELOW, aTextView.get(aTextView.size() - 1).getId());
                    myRecipeInstructionsParams.setMargins(0, marginTop, 0, 0);
                    myRecipeInstructionsParams.addRule(RelativeLayout.CENTER_IN_PARENT);
                    myLayout.addView(myRecipeInstructions, myRecipeInstructionsParams);
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
