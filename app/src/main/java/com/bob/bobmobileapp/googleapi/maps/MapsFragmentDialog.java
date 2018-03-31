package com.bob.bobmobileapp.googleapi.maps;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.http.HttpController;
import com.bob.bobmobileapp.http.HttpJsonResponseHandler;
import com.bob.bobmobileapp.parsing.JsonParser;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * An activity that displays a map showing the place at the device's current location.
 */
public class MapsFragmentDialog extends DialogFragment
        implements OnMapReadyCallback {

    private int PROXIMITY_RADIUS = 100;

    private View mainView;
    private Activity activity;
    private Context context;

    private static final String TAG = MapsActivity.class.getSimpleName();
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private CameraPosition cameraPosition;

    // The entry points to the Places API.
    private GeoDataClient geoDataClient;
    private PlaceDetectionClient placeDetectionClient;

    // The entry point to the Fused Location Provider.
    private FusedLocationProviderClient fusedLocationProviderClient;

    // A default location (Tel Aviv, Israel) and default zoom to use when location permission is
    // not granted.
    private final LatLng defaultLocation = new LatLng(32.109333, 34.855499);
    private static final int DEFAULT_ZOOM = 15;
    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean locationPermissionGranted;

    // The geographical location where the device is currently located. That is, the last-known
    // location retrieved by the Fused Location Provider.
    private Location lastKnownLocation;

    // Keys for storing activity state.
    private static final String KEY_CAMERA_POSITION = "camera_position";
    private static final String KEY_LOCATION = "location";

    //cur marker
    private Place curMarkerLocation;

    // Used for selecting the current place.
    private static final int M_MAX_ENTRIES = 5;
    private int likelyCount;
    private ArrayList<Place> likelyPlaces;

    //Map Settings
    private boolean isBuildingsEnabled;
    private boolean isIndoorEnabled;
    private boolean isMyLocationEnabled;
    private boolean isTrafficEnabled;

    //Map UI
    private boolean isMapToolbarEnabled;
    private boolean isCompassEnabled;
    private boolean isIndoorLevelPickerEnabled;
    private boolean isMyLocationButtonEnabled;
    private boolean isRotateGesturesEnabled;
    private boolean isScrollGesturesEnabled;
    private boolean isTiltGesturesEnabled;
    private boolean isZoomControlsEnabled;
    private boolean isZoomGesturesEnabled;
    private boolean isAllGesturesEnabled;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Retrieve location and camera position from saved instance state.
        if (savedInstanceState != null) {
            this.lastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            this.cameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        this.mainView = (LinearLayout) inflater.inflate(R.layout.fragment_maps, container, false);
        // Construct a GeoDataClient.
        this.geoDataClient = Places.getGeoDataClient(this.getActivity(), null);

        // Construct a PlaceDetectionClient.
        this.placeDetectionClient = Places.getPlaceDetectionClient(this.getActivity(), null);

        // Construct a FusedLocationProviderClient.
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        // Build the map.
        this.mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager()
                .findFragmentById(R.id.map);
        this.mapFragment.getMapAsync(this);


        this.initMapPreferences();
        this.initUiSetting();

        this.likelyCount = M_MAX_ENTRIES;
        this.likelyPlaces = new ArrayList<Place>();

        return this.mainView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        if (context instanceof Activity) {
            this.activity = (Activity) context;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        try {
            SupportMapFragment fragment = (SupportMapFragment) getActivity()
                    .getSupportFragmentManager().findFragmentById(
                            R.id.map);
            if (fragment != null) getFragmentManager().beginTransaction().remove(fragment).commit();

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
        if (map != null) {
            outState.putParcelable(KEY_CAMERA_POSITION, map.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, lastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }

    /**
     * Manipulates the map when it's available.
     * This callback is triggered when the map is ready to be used.
     */
    @Override
    public void onMapReady(GoogleMap map) {
        this.map = map;

        this.configureMapPreferences();
        this.configureUiSettings();

        // Use a custom info window adapter to handle multiple lines of text in the
        // info window contents.
        this.map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            // Return null here, so that getInfoContents() is called next.
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                // Inflate the layouts for the info window, title and snippet.
                View infoWindow = getLayoutInflater().inflate(R.layout.custom_info_contents,
                        (FrameLayout) mainView.findViewById(R.id.map), false);

                TextView title = ((TextView) infoWindow.findViewById(R.id.title));
                title.setText(marker.getTitle());

                TextView snippet = ((TextView) infoWindow.findViewById(R.id.snippet));
                snippet.setText(marker.getSnippet());

                return infoWindow;
            }
        });

        this.map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                addLocationMrker(latLng);
            }
        });

        this.map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                showNearestPlaces(latLng);
                addLocationMrker(latLng);
            }
        });

        this.map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        // Prompt the user for permission.
        getLocationPermission();

        // Turn on the My Location layer and the related control on the map.
        updateLocationUI();

        // Get the current location of the device and set the position of the map.
        getDeviceLocation();
    }

    /**
     * Gets the current location of the device, and positions the map's camera.
     */
    private void getDeviceLocation() {
        /*
         * Get the best and most recent location of the device, which may be null in rare
         * cases when a location is not available.
         */
        try {
            if (locationPermissionGranted) {
                Task<Location> locationResult = fusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this.getActivity(), new OnCompleteListener<Location>() {
                    @Override
                    public void onComplete(@NonNull Task<Location> task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            lastKnownLocation = task.getResult();
                            map.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    new LatLng(lastKnownLocation.getLatitude(),
                                            lastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            map.moveCamera(CameraUpdateFactory
                                    .newLatLngZoom(defaultLocation, DEFAULT_ZOOM));
                            map.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }


    /**
     * Prompts the user for permission to use the device location.
     */
    private void getLocationPermission() {
        /*
         * Request location permission, so that we can get the location of the
         * device. The result of the permission request is handled by a callback,
         * onRequestPermissionsResult.
         */
        if (ContextCompat.checkSelfPermission(this.getContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            locationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this.getActivity(),
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }

    /**
     * Handles the result of the request for location permissions.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        locationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationPermissionGranted = true;
                }
            }
        }
        updateLocationUI();
    }

    /**
     * Displays a form allowing the user to select a place from a list of likely places.
     */
    private void openPlacesDialog() {
        // Ask the user to choose the place where they are now.
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // The "which" argument contains the position of the selected item.
                LatLng markerLatLng = likelyPlaces.get(which).getLatLng();
                String markerSnippet = likelyPlaces.get(which).getAddress().toString();
                if (likelyPlaces.get(which).getAttributions() != null) {
                    markerSnippet = markerSnippet + "\n" + likelyPlaces.get(which).getAttributions().toString();
                }

                // Add a marker for the selected place, with an info window
                // showing information about that place.
                map.addMarker(new MarkerOptions()
                        .title(likelyPlaces.get(which).getName().toString())
                        .position(markerLatLng)
                        .snippet(markerSnippet));

                // Position the map's camera at the location of the marker.
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(markerLatLng,
                        DEFAULT_ZOOM));
            }
        };

        String[] likelyPlaceNames = new String[likelyPlaces.size()];
        for (int i = 0; i < likelyPlaces.size(); i++) {
            likelyPlaceNames[i] = likelyPlaces.get(i).getName().toString();
        }

        // Display the dialog.
        AlertDialog dialog = new AlertDialog.Builder(this.getContext())
                .setTitle(R.string.pick_place)
                .setItems(likelyPlaceNames, listener)
                .show();
    }

    /**
     * Updates the map's UI settings based on whether the user has granted location permission.
     */
    private void updateLocationUI() {
        if (map == null) {
            return;
        }
        try {
            if (locationPermissionGranted) {
                map.setMyLocationEnabled(true);
                map.getUiSettings().setMyLocationButtonEnabled(true);
            } else {
                map.setMyLocationEnabled(false);
                map.getUiSettings().setMyLocationButtonEnabled(false);
                lastKnownLocation = null;
                getLocationPermission();
            }
        } catch (SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    private void showNearestPlaces(LatLng latLng) {

        map.clear();
        String url = getNearbyPlacesUrl(latLng.latitude, latLng.longitude);

        //Object[] DataTransfer = new Object[1];
        //DataTransfer[0] = url;
        HttpController.get().makeJsonRequest(url, null, new HttpJsonResponseHandler() {
            @Override
            public void OnResponse(JSONObject response) {
                List<HashMap<String, String>> nearbyPlacesList = null;
                JsonParser jsonParser = new JsonParser();
                nearbyPlacesList =  jsonParser.parseGooglePlaces(response);
                likelyPlaces.clear();

                likelyCount = M_MAX_ENTRIES;
                if (nearbyPlacesList.size() <M_MAX_ENTRIES)
                    likelyCount = nearbyPlacesList.size();

                for (int i = 0; i < likelyCount; i++) {
                    HashMap<String, String> googlePlace = nearbyPlacesList.get(i);
                    geoDataClient.getPlaceById(googlePlace.get("place_id")).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                        @Override
                        public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                            if (task.isSuccessful()) {
                                PlaceBufferResponse places = task.getResult();
                                final Place myPlace = places.get(0).freeze();
                                likelyPlaces.add(myPlace);
                                places.release();
                            }
                            if (likelyPlaces.size() == likelyCount) {
                                openPlacesDialog();
                            }
                        }
                    });
                }
            }

            @Override
            public void OnErrorResponse(VolleyError error) {

            }
        });

    }

    private void addLocationMrker(LatLng latLng) {

        map.clear();
        String url = getPlaceIdFromLatLngUrl(latLng.latitude, latLng.longitude);

        //Object[] DataTransfer = new Object[1];
        //DataTransfer[0] = url;
        HttpController.get().makeJsonRequest(url, null, new HttpJsonResponseHandler() {
            @Override
            public void OnResponse(JSONObject response) {
                curMarkerLocation = null;
                JsonParser jsonParser = new JsonParser();
                String placeId =  jsonParser.parseGeoPlecaIdByLatLng(response);
                geoDataClient.getPlaceById(placeId).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                        if (task.isSuccessful()) {
                            PlaceBufferResponse places = task.getResult();
                            curMarkerLocation = places.get(0).freeze();
                            if (curMarkerLocation != null) {
                                map.addMarker(new MarkerOptions()
                                        .title(curMarkerLocation.getName().toString())
                                        .position(curMarkerLocation.getLatLng())
                                        .snippet(curMarkerLocation.getAddress().toString()));
                            }
                            places.release();
                        }
                    }
                });
            }

            @Override
            public void OnErrorResponse(VolleyError error) {

            }
        });

    }


    private String getNearbyPlacesUrl(double latitude, double longitude) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlacesUrl.append("location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + PROXIMITY_RADIUS);
        googlePlacesUrl.append("&key=" + getString(R.string.google_maps_key));
        googlePlacesUrl.append("&sensor=true");
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    private String getPlaceIdFromLatLngUrl(double latitude, double longitude) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/geocode/json?");
        googlePlacesUrl.append("latlng=" + latitude + "," + longitude);
        googlePlacesUrl.append("&key=" + getString(R.string.google_maps_key));
        googlePlacesUrl.append("&sensor=true");
        return (googlePlacesUrl.toString());
    }

    private void initMapPreferences() {
        this.setBuildingsEnabled(true);
        this.setIndoorEnabled(true);
        this.setMyLocationEnabled(true);
        this.setTrafficEnabled(true);
    }

    private void configureMapPreferences() {
        this.map.setBuildingsEnabled(this.isBuildingsEnabled);
        this.map.setIndoorEnabled(this.isIndoorEnabled);
        this.map.setTrafficEnabled(this.isTrafficEnabled);
    }

    private void initUiSetting() {
        this.setCompassEnabled(true);
        this.setIndoorLevelPickerEnabled(true);
        this.setMapToolbarEnabled(true);
        this.setMyLocationButtonEnabled(true);
        this.setRotateGesturesEnabled(true);
        this.setScrollGesturesEnabled(true);
        this.setTiltGesturesEnabled(true);
        this.setZoomControlsEnabled(true);
        this.setZoomGesturesEnabled(true);
    }

    private void configureUiSettings() {
        this.map.getUiSettings().setCompassEnabled(this.isCompassEnabled);
        this.map.getUiSettings().setIndoorLevelPickerEnabled(this.isIndoorLevelPickerEnabled);
        this.map.getUiSettings().setMapToolbarEnabled(this.isMapToolbarEnabled);
        this.map.getUiSettings().setMyLocationButtonEnabled(this.isMyLocationButtonEnabled);
        this.map.getUiSettings().setRotateGesturesEnabled(this.isRotateGesturesEnabled);
        this.map.getUiSettings().setScrollGesturesEnabled(this.isScrollGesturesEnabled);
        this.map.getUiSettings().setTiltGesturesEnabled(this.isTiltGesturesEnabled);
        this.map.getUiSettings().setZoomControlsEnabled(this.isZoomControlsEnabled);
        this.map.getUiSettings().setZoomGesturesEnabled(this.isZoomGesturesEnabled);
    }

    public boolean isMapToolbarEnabled() {
        return this.isMapToolbarEnabled;
    }

    public void setMapToolbarEnabled(boolean mapToolbarEnabled) {
        this.isMapToolbarEnabled = mapToolbarEnabled;
    }

    public boolean isCompassEnabled() {
        return this.isCompassEnabled;
    }

    public void setCompassEnabled(boolean compassEnabled) {
        this.isCompassEnabled = compassEnabled;
    }

    public boolean isIndoorLevelPickerEnabled() {
        return this.isIndoorLevelPickerEnabled;
    }

    public void setIndoorLevelPickerEnabled(boolean indoorLevelPickerEnabled) {
        this.isIndoorLevelPickerEnabled = indoorLevelPickerEnabled;
    }

    public boolean isMyLocationButtonEnabled() {
        return this.isMyLocationButtonEnabled;
    }

    public void setMyLocationButtonEnabled(boolean myLocationButtonEnabled) {
        this.isMyLocationButtonEnabled = myLocationButtonEnabled;
    }

    public boolean isRotateGesturesEnabled() {
        return this.isRotateGesturesEnabled;
    }

    public void setRotateGesturesEnabled(boolean rotateGesturesEnabled) {
        this.isRotateGesturesEnabled = rotateGesturesEnabled;
    }

    public boolean isScrollGesturesEnabled() {
        return this.isScrollGesturesEnabled;
    }

    public void setScrollGesturesEnabled(boolean scrollGesturesEnabled) {
        this.isScrollGesturesEnabled = scrollGesturesEnabled;
    }

    public boolean isTiltGesturesEnabled() {
        return this.isTiltGesturesEnabled;
    }

    public void setTiltGesturesEnabled(boolean tiltGesturesEnabled) {
        this.isTiltGesturesEnabled = tiltGesturesEnabled;
    }

    public boolean isZoomControlsEnabled() {
        return this.isZoomControlsEnabled;
    }

    public void setZoomControlsEnabled(boolean zoomControlsEnabled) {
        this.isZoomControlsEnabled = zoomControlsEnabled;
    }

    public boolean isZoomGesturesEnabled() {
        return this.isZoomGesturesEnabled;
    }

    public void setZoomGesturesEnabled(boolean zoomGesturesEnabled) {
        this.isZoomGesturesEnabled = zoomGesturesEnabled;
    }

    public boolean isAllGesturesEnabled() {
        return this.isAllGesturesEnabled;
    }

    public void setAllGesturesEnabled(boolean allGesturesEnabled) {
        this.isAllGesturesEnabled = allGesturesEnabled;
    }

    public boolean isBuildingsEnabled() {
        return this.isBuildingsEnabled;
    }

    public void setBuildingsEnabled(boolean buildingsEnabled) {
        this.isBuildingsEnabled = buildingsEnabled;
    }

    public boolean isIndoorEnabled() {
        return this.isIndoorEnabled;
    }

    public void setIndoorEnabled(boolean indoorEnabled) {
        this.isIndoorEnabled = indoorEnabled;
    }

    public boolean isMyLocationEnabled() {
        return this.isMyLocationEnabled;
    }

    public void setMyLocationEnabled(boolean myLocationEnabled) {
        this.isMyLocationEnabled = myLocationEnabled;
    }

    public boolean isTrafficEnabled() {
        return this.isTrafficEnabled;
    }

    public void setTrafficEnabled(boolean trafficEnabled) {
        this.isTrafficEnabled = trafficEnabled;
    }

}
