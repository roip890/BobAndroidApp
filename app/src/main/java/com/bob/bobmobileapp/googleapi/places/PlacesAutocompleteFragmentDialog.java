package com.bob.bobmobileapp.googleapi.places;

import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bob.bobmobileapp.R;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.location.places.ui.SupportPlaceAutocompleteFragment;

/**
 * Created by User on 05/01/2018.
 */

public class PlacesAutocompleteFragmentDialog extends DialogFragment {

    private View mainView;
    private PlaceAutocompleteFragment autocompleteFragment;
    private OnPlaceSelectListener onPlacesSelectListener = null;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Retrieve location and camera position from saved instance state.
//        if (savedInstanceState != null) {
//            this.lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
//            this.cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
//        }

        this.mainView = (LinearLayout) inflater.inflate(R.layout.fragment_places_autocomplete, container, false);

        this.autocompleteFragment  = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        final View root = this.autocompleteFragment.getView();

        if (root != null) {
            root.post(new Runnable() {
                @Override
                public void run() {
                    root.findViewById(R.id.place_autocomplete_search_input)
                            .performClick();
                }
            });

            root.setVisibility(View.INVISIBLE);
        }

        this.autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                if (onPlacesSelectListener != null) {
                    onPlacesSelectListener.onPlaceSelect(place);
                }
            }

            @Override
            public void onError(Status status) {

            }
        });


        this.autocompleteFragment.getView().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    getDialog().cancel();
                }
            }
        });
        return this.mainView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            PlaceAutocompleteFragment fragment = (PlaceAutocompleteFragment) getActivity().getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
            if (fragment != null) {
                getActivity().getFragmentManager().beginTransaction().remove(fragment).commit();
            }

        } catch (IllegalStateException e) {
            //handle this situation because you are necessary will get
            //an exception here :-(
        }
    }

    /**
     * Saves the state of the map when the activity is paused.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
//        if (map != null) {
//            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
//            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
//            super.onSaveInstanceState(outState);
//        }
    }

    public void setOnPlacesSelectListener(OnPlaceSelectListener onPlacesSelectListener) {
        this.onPlacesSelectListener = onPlacesSelectListener;
    }



}
