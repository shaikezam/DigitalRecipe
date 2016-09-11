package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;


public class NewRecipeScreen extends AppCompatActivity {
    RelativeLayout relativeLayout;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_recipe_screen);
        Bundle extras = getIntent().getExtras();
        String sUserName = extras.getString("key");
        relativeLayout = new RelativeLayout(this);
        DatabaseHandler db = new DatabaseHandler(this);
        Log.e("Error: ", sUserName);
        extras = new Bundle();
        bindUI(sUserName);
    }
    public void bindUI(final String sUserName) {
        final Context oContext = this;
        this.spinner = (Spinner)findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> foodadapter = ArrayAdapter.createFromResource(this, R.array.ingredient_arrays, R.layout.spinner_item);
        foodadapter.setDropDownViewResource(R.layout.spinner_item);
        spinner.setAdapter(foodadapter);
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                String selectedText = parent.getItemAtPosition(pos).toString();
                if(!selectedText.equals(getResources().getString(R.string.ingredient_prompt))) {
                    parent.setSelection(0);

                    int myEditTextWidth = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 120, getResources().getDisplayMetrics());
                    int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 13, getResources().getDisplayMetrics());
                    int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 60, getResources().getDisplayMetrics());
                    int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 3, getResources().getDisplayMetrics());
                    int marginTop = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, 10, getResources().getDisplayMetrics());


                    EditText myEditText = new EditText(oContext);
                    myEditText.setHint("Please set amount");
                    myEditText.setBackground(getResources().getDrawable(R.drawable.rounded_edittext));
                    myEditText.setHeight(height);
                    myEditText.setWidth(myEditTextWidth);
                    //myEditText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    myEditText.setTextSize(textSize);
                    myEditText.setGravity(Gravity.CENTER | Gravity.BOTTOM);

                    TextView myTextView = new TextView(oContext);
                    myTextView.setText(selectedText);
                    myTextView.setBackground(getResources().getDrawable(R.drawable.rounded_option));
                    RelativeLayout myLayout = (RelativeLayout)findViewById(R.id.shaike);
                    myTextView.setHeight(height);
                    myTextView.setWidth(width);
                    myTextView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                    myTextView.setTextSize(textSize);
                    myTextView.setGravity(Gravity.CENTER | Gravity.BOTTOM);

                    int count = 0;
                    int i = 0;
                    ArrayList<TextView> aTextViews = new ArrayList<TextView>();
                    for( ; i < myLayout.getChildCount(); i++ )
                        if( myLayout.getChildAt( i ) instanceof TextView ) {
                            myLayout.getChildAt( i ).setId(i);
                            aTextViews.add((TextView) myLayout.getChildAt( i ));
                            count++;
                        }
                    myTextView.setId(311);
                    Log.e("Error :", String.valueOf(aTextViews.size()));
                    RelativeLayout.LayoutParams myTextViewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    RelativeLayout.LayoutParams myEditTextParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                    if(count == 0) {
                        myTextViewParams.addRule(RelativeLayout.BELOW, R.id.spinner1);
                        myEditTextParams.addRule(RelativeLayout.BELOW, R.id.spinner1);
                    } else {
                        myTextViewParams.addRule(RelativeLayout.BELOW, aTextViews.get(aTextViews.size() - 1).getId());
                        myEditTextParams.addRule(RelativeLayout.BELOW, aTextViews.get(aTextViews.size() - 1).getId());
                    }

                    myTextViewParams.setMargins(0, marginTop, 0, 0);
                    myLayout.addView(myTextView, myTextViewParams);
                    myEditTextParams.setMargins(0, marginTop, 0, 0);
                    myEditTextParams.addRule(RelativeLayout.RIGHT_OF, 311);
                    myLayout.addView(myEditText, myEditTextParams);
                }
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
