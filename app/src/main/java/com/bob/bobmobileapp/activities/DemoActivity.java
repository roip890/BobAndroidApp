package com.bob.bobmobileapp.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyPhoneEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

/**
 * Created by User on 10/03/2018.
 */

public class DemoActivity extends AppCompatActivity {

    LinearLayout myViewGroup;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_demo);
        this.myViewGroup = (LinearLayout) findViewById(R.id.my_view_group);
        this.addTextView();
        this.addEditText();
        this.addPhoneEditText();

    }



    private void addTextView() {
        MyTextView myTextView = new MyTextView(this);
        myTextView.setText("My Text View");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        this.myViewGroup.addView(myTextView, layoutParams);
    }

    private void addEditText() {
        MyEditText myEditText = new MyEditText(this);
        myEditText.setText("My Edit Text");
        myEditText.getEditText().setBackgroundColor(Color.RED);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        this.myViewGroup.addView(myEditText, layoutParams);
    }

    private void addPhoneEditText() {
        MyPhoneEditText myPhoneEditText = new MyPhoneEditText(this);
        myPhoneEditText.setText("My Edit Text");
        myPhoneEditText.setCountryCodeByCode(972);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        this.myViewGroup.addView(myPhoneEditText, layoutParams);
    }

}
