package com.example.shayzambrovski.digitalrecipe;

import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Shay Zambrovski on 11/09/2016.
 */
public class IngredientControl {
    TextView myTextView;
    EditText myEditText;
    static int iId = 0;

    public IngredientControl(TextView myTextView, EditText myEditText) {
        setMyTextView(myTextView);
        setMyEditText(myEditText);

    }

    public TextView getMyTextView() {
        return myTextView;
    }

    public void setMyTextView(TextView myTextView) {
        myTextView.setId(this.iId++);
        this.myTextView = myTextView;
    }

    public EditText getMyEditText() {
        return myEditText;
    }

    public void setMyEditText(EditText myEditText) {
        myTextView.setId(this.iId++);
        this.myEditText = myEditText;
    }
}
