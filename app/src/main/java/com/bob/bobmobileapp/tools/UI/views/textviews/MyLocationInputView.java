package com.bob.bobmobileapp.tools.UI.views.textviews;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.googleapi.places.OnPlaceSelectListener;
import com.bob.bobmobileapp.googleapi.places.PlacesAutocompleteFragmentDialog;
import com.bob.bobmobileapp.googleapi.places.PlacesPickerFragmentDialog;
import com.bob.bobmobileapp.http.beans.WishPair;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.google.android.gms.location.places.Place;
import com.mikepenz.iconics.IconicsDrawable;

/**
 * Created by User on 24/12/2017.
 */

public class MyLocationInputView extends MyLocationOutputView {

    private Place place;

    public MyLocationInputView(Context context) {
        this(context, null);
    }

    public MyLocationInputView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLocationInputView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.place = null;
    }

    @Override
    protected void initMainView() {
        super.initMainView();

        this.setMapDrawableColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setMapDrawable(((IconicsDrawable) Icons.get().findDrawable(this.getContext(), "gmd_my_location")).sizeDp(24));
        this.setMapDrawableEnable(true);

        this.textView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final PlacesAutocompleteFragmentDialog placesAutocompleteFragmentDialog = new PlacesAutocompleteFragmentDialog();
                android.support.v4.app.FragmentManager fm = ((FragmentActivity)getContext()).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                placesAutocompleteFragmentDialog.setOnPlacesSelectListener(new OnPlaceSelectListener() {
                    @Override
                    public void onPlaceSelect(Place placeSelected) {
                        place = placeSelected;
                        if (placeSelected != null) {
                            textView.setText(placeSelected.getName());
                        }
                        placesAutocompleteFragmentDialog.getDialog().cancel();
                    }
                });
                placesAutocompleteFragmentDialog.show(ft, "Maps");
            }
        });

        this.mapIconImageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final PlacesPickerFragmentDialog placesPickerFragmentDialog = new PlacesPickerFragmentDialog();
                android.support.v4.app.FragmentManager fm = ((FragmentActivity)getContext()).getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction ft = fm.beginTransaction();
                placesPickerFragmentDialog.setOnPlacesSelectListener(new OnPlaceSelectListener() {
                    @Override
                    public void onPlaceSelect(Place placeSelected) {
                        place = placeSelected;
                        if (placeSelected != null) {
                            textView.setText(placeSelected.getName());
                        }
                        placesPickerFragmentDialog.getDialog().cancel();
                    }
                });
                placesPickerFragmentDialog.show(ft, "Maps");
            }
        });

//        this.setBackgroundColor(Color.RED);
//        this.mapIconImageView.setColorFilter(Color.GREEN);
//        this.textView.setBackgroundColor(Color.YELLOW);

    }

}

