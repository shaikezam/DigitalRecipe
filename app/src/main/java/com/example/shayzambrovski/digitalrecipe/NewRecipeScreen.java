package com.example.shayzambrovski.digitalrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


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
        final RelativeLayout relativeLayout = this.relativeLayout;
        this.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View view, int pos, long id) {
                String selectedText = parent.getItemAtPosition(pos).toString();
                if(!selectedText.equals(getResources().getString(R.string.ingredient_prompt))) {
                    Toast.makeText(parent.getContext(), "The planet is " +
                            parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
                    TextView tv = new TextView(oContext);
                    tv.setText(selectedText);
                    tv.setId(R.id.register_id+1);
                    tv.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT));

                    relativeLayout.addView(tv);
                }
            }

            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }
}
