package com.bob.bobmobileapp.googleapi.places;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bob.bobmobileapp.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by User on 05/01/2018.
 */

public class PlacesPickerFragmentDialog extends DialogFragment {

    private View mainView;
    private OnPlaceSelectListener onPlacesSelectListener = null;
    int PLACE_PICKER_REQUEST = 1;
    int RESULT_OK = -1;
    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();



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

        this.mainView = (LinearLayout) inflater.inflate(R.layout.empty_layout, container, false);

        try {

            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        }
        catch (GooglePlayServicesRepairableException e)
        {
            Toast.makeText(getActivity(),"ServiceRepaire Exception",Toast.LENGTH_SHORT).show();
        }
        catch (GooglePlayServicesNotAvailableException e)
        {
            Toast.makeText(getActivity(),"SeerviceNotAvailable Exception", Toast.LENGTH_SHORT).show();
        }



        return this.mainView;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, getActivity());
                if (onPlacesSelectListener != null) {
                    onPlacesSelectListener.onPlaceSelect(place);
                }
            }
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
