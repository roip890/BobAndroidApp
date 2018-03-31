package com.bob.bobmobileapp.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.blankj.utilcode.util.RegexUtils;
import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.demo.democounter.DemoCounterExtraBed;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.http.HttpController;
import com.bob.bobmobileapp.http.HttpJsonResponseHandler;
import com.bob.bobmobileapp.http.beans.ApplicativeResponse;
import com.bob.bobmobileapp.http.beans.DesignResponse;
import com.bob.bobmobileapp.http.beans.MnGuest;
import com.bob.bobmobileapp.http.beans.MnHotel;
import com.bob.bobmobileapp.http.beans.MnLeaf;
import com.bob.bobmobileapp.http.beans.MnLeafProp;
import com.bob.bobmobileapp.http.beans.MnNode;
import com.bob.bobmobileapp.http.beans.MnNodeProp;
import com.bob.bobmobileapp.http.beans.RequestWraper;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.RealmHelper;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.progressbar.MyProgressBar;
import com.bob.bobmobileapp.tools.UI.progressbar.OnProgressIntervalListener;
import com.bob.bobmobileapp.tools.UI.progressbar.ProgressBarTimer;
import com.bob.bobmobileapp.tools.UI.style.BackgroundColorTimer;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyButton;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyPhoneEditText;
import com.bob.bobmobileapp.tools.validators.Validator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.mikepenz.iconics.IconicsDrawable;

import com.bob.bobmobileapp.http.beans.CustomResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 27/09/2017.
 */


public class LoadingActivity extends AppCompatActivity{

    private static String BOB_SERVER_IP_ADDRESS = "46.101.88.78";
    private static String BOB_SERVER_PORT = "8080";
    private static String BOB_SERVER_SERVICES_URL = "http://" + BOB_SERVER_IP_ADDRESS + ":"
            + BOB_SERVER_PORT + "/BOB_SERVER_5/services";



    private static int CHECK_USER = 0;
    private static int LOAD_MENU = 1;

    //screen states
    private static final int NONE = -1;
    private static final int PROGRESS = 0;
    private static final int LOADING = 1;
    private static final int LOGIN = 2;
    private static final int REGISTER= 3;
    private static final int SMS_AUTHENTICATION = 4;

    //progress bar
    private LinearLayout progressLayout;
    private ImageView progressImageView;
    private MyProgressBar progressBar;

    //loading
    private LinearLayout loadingLayout;
    private ImageView loadingImageView;


    //check session
    private boolean checkingSession, isSessionValid;
    private static final String CHECK_SESSION_URL = BOB_SERVER_SERVICES_URL + "/MobileLogin/CheckSession";


    //login
    private LinearLayout loginLayout;
    private MyButton loginButton;
    private TextView loginTitle, loginSignUpMessageTextView, loginSignUpLinkTextView, loginErrorTextView;
    private MyEditText loginEmailEditText, loginPasswordEditText;
    private static final String LOGIN_URL = BOB_SERVER_SERVICES_URL + "/MobileLogin/Login";


    //register
    private LinearLayout registerLayout;
    private MyButton registerButton;
    private TextView registerTitle, registerSignInMessageTextView, registerSignInLinkTextView, registerErrorTextView;
    private MyPhoneEditText registerPhoneEditText;
    private MyEditText registerUsernameEditText, registerFullNameEditText, registerEmailEditText,
            registerPasswordEditText, registerConfirmPasswordEditText;
    private static final String REGISTER_URL = BOB_SERVER_SERVICES_URL +"/MobileRegister/Register";


    //sms authentication
    private LinearLayout smsAuthLayout;
    private MyButton smsAuthButton;
    private TextView smsAuthTitle, smsAuthInfoFirstTextView, smsAuthInfoSecondTextView,
            smsAuthSignInMessageTextView, smsAuthSignInLinkTextView, smsAuthResendCodeLinkTextView,
            smsAuthErrorTextView;
    private MyEditText smsAuthFirstEditText, smsAuthSecondEditText,
            smsAuthThirdEditText, smsAuthFourthEditText;
    private static final String SMS_VERIFY_URL = BOB_SERVER_SERVICES_URL +"/MobileRegister/Verify";
    private static final String SMS_RESEND_VERIFICATION_CODE_URL = BOB_SERVER_SERVICES_URL +"/MobileRegister/ResendVerificationCode";


    //menu design
    private static final String GET_ALL_MENU_URL = BOB_SERVER_SERVICES_URL +"/MobileDesign/GetAllMenuByHotelId";

    private RelativeLayout backgroundLayout;
    private BackgroundColorTimer backgroundColorTimer;
    private ProgressBarTimer progressBarTimer;
    private int screenState = PROGRESS;

    public LoadingActivity() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.loading_layout);


        this.progressLayout = (LinearLayout) findViewById(R.id.progress_layout);
        this.loadingLayout = (LinearLayout) findViewById(R.id.loading_layout);
        this.loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        this.registerLayout = (LinearLayout) findViewById(R.id.register_layout);
        this.smsAuthLayout = (LinearLayout) findViewById(R.id.sms_authentication_layout);


//        BOBApplication.get().getSecureSharedPreferences().edit().clear().apply();
        this.initBackgroundAnimation();
        this.initLoadingLayout();
        this.initProgressLayout();
        this.initForm();

        this.checkSession();
        this.loginEmailEditText.setText("fkfkfk@fk.fk");
        this.loginPasswordEditText.setText("Aa111111");
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.backgroundColorTimer.startTimer(50);
        if (this.screenState == PROGRESS) {
            this.progressBarTimer.startTimer(20);
        }
        this.setScreenState(this.screenState);
//        this.getLastRequestedFocus();
    }

    @Override
    protected void onPause() {
        this.backgroundColorTimer.stopTimer();
        this.progressBarTimer.stopTimer();
//        this.saveLastRequestedFocus();
        super.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void checkSession() {
        this.isSessionValid = false;
        this.checkingSession = true;
        this.setScreenState(PROGRESS);
        try {
            String email = BOBApplication.get().getSecureSharedPreferences().getString("guestEmail", null);
            String guid = BOBApplication.get().getSecureSharedPreferences().getString("guestGuid", null);
            if ((guid != null) && (email != null)) {
                this.makeCheckSessionRequest(email, guid);
            } else {
                this.checkingSession = false;
                this.isSessionValid = false;
            }
        } catch (Exception e) {
            this.checkingSession = false;
            this.isSessionValid = false;
        }
    }

    private void saveSession(MnGuest guest) {
        if (guest != null) {
            if (guest.getGuestEmail() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestEmail", guest.getGuestEmail()).apply();
            }
            if (guest.getGuestGuid() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestGuid", guest.getGuestGuid()).apply();
            }
            if (guest.getGuestGuidValid() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestGuidValid", guest.getGuestGuidValid()).apply();
            }
            if (guest.getGuestId() != -1) {
                BOBApplication.get().getSecureSharedPreferences().edit().putInt("guestId", guest.getGuestId()).apply();
            }
            if (guest.getGuestName() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestName", guest.getGuestName()).apply();
            }
            if (guest.getGuestPassword() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestPassword", guest.getGuestPassword()).apply();
            }
            if (guest.getGuestPhone() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestPhone", guest.getGuestPhone()).apply();
            }
            if (guest.getGuestRoom() != -1) {
                BOBApplication.get().getSecureSharedPreferences().edit().putInt("guestRoom", guest.getGuestRoom()).apply();
            }
            if (guest.getGuestStatus() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestStatus", guest.getGuestStatus()).apply();
            }
            if (guest.getInsertTs() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestInsertTs", guest.getInsertTs().toString()).apply();
            }
            if (guest.getLmTs() != null) {
                BOBApplication.get().getSecureSharedPreferences().edit().putString("guestLmTs", guest.getLmTs().toString()).apply();
            }
            MnHotel hotel = guest.getMnHotel();
            if (hotel != null) {
                if (hotel.getHotelId() != -1){
                    BOBApplication.get().getSecureSharedPreferences().edit().putLong("hotelId", hotel.getHotelId()).apply();
                }
                if (hotel.getHotelName() != null){
                    BOBApplication.get().getSecureSharedPreferences().edit().putString("hotelName", hotel.getHotelName()).apply();
                }
            }
        }
    }


    private void makeCheckSessionRequest(String email, String guid) {
        try {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
            RequestWraper request = new RequestWraper();
            MnGuest guest = new MnGuest();
            guest.setGuestEmail(email);
            guest.setGuestGuid(guid);
            request.setGuest(guest);
            JsonObject jsonRequest = new JsonObject();
            jsonRequest.add("request", new Gson().toJsonTree(request, RequestWraper.class));
            HttpController.get().makeJsonRequest(CHECK_SESSION_URL, new JSONObject( new Gson().toJson(jsonRequest)), new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        CustomResponse customResponse = new Gson().fromJson(response.getJSONObject("response").toString(), CustomResponse.class);
                        switch (customResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                LoadingActivity.this.checkingSession = false;
                                LoadingActivity.this.isSessionValid = false;
                                break;
                            case ApplicativeResponse.SUCCESS:
                                LoadingActivity.this.checkingSession = false;
                                LoadingActivity.this.isSessionValid = true;
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                LoadingActivity.this.checkingSession = false;
                                LoadingActivity.this.isSessionValid = false;
                                break;
                            default:
                                LoadingActivity.this.checkingSession = false;
                                LoadingActivity.this.isSessionValid = false;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.checkingSession = false;
                        LoadingActivity.this.isSessionValid = false;
                    }
                }
                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.checkingSession = false;
                    LoadingActivity.this.isSessionValid = false;
                }
            }, headers, params);
        } catch (JSONException e) {
            LoadingActivity.this.checkingSession = false;
            LoadingActivity.this.isSessionValid = false;
            e.printStackTrace();
        }
    }

    //manage screen state
    private void setScreenState(int state) {

        this.screenState = state;
        this.progressLayout.setVisibility(View.GONE);
        this.loadingLayout.setVisibility(View.GONE);
        this.loginLayout.setVisibility(View.GONE);
        this.registerLayout.setVisibility(View.GONE);
        this.smsAuthLayout.setVisibility(View.GONE);

        switch (state) {
            case NONE:
                break;
            case PROGRESS:
                this.progressLayout.setVisibility(View.VISIBLE);
                break;
            case LOADING:
                this.loadingLayout.setVisibility(View.VISIBLE);
                break;
            case LOGIN:
                this.loginLayout.setVisibility(View.VISIBLE);
                break;
            case REGISTER:
                this.registerLayout.setVisibility(View.VISIBLE);
                break;
            case SMS_AUTHENTICATION:
                this.smsAuthLayout.setVisibility(View.VISIBLE);
                break;
        }

    }


    //set error
    private void setError(String errorText, int state) {

        this.loginErrorTextView.setVisibility(View.GONE);
        this.registerErrorTextView.setVisibility(View.GONE);
        this.smsAuthErrorTextView.setVisibility(View.GONE);

        switch (state) {
            case NONE:
                break;
            case LOGIN:
                this.loginErrorTextView.setVisibility(View.VISIBLE);
                this.loginErrorTextView.setText(errorText);
                break;
            case REGISTER:
                this.registerErrorTextView.setVisibility(View.VISIBLE);
                this.registerErrorTextView.setText(errorText);
                break;
            case SMS_AUTHENTICATION:
                this.smsAuthErrorTextView.setVisibility(View.VISIBLE);
                this.smsAuthErrorTextView.setText(errorText);
                break;
        }

    }


    //background animation
    private void initBackgroundAnimation() {
        this.backgroundLayout = (RelativeLayout) findViewById(R.id.loading_layout_background);

        this.backgroundColorTimer = new BackgroundColorTimer(this, backgroundLayout, ContextCompat.getColor(this, R.color.colorPrimary), ContextCompat.getColor(this, R.color.colorPrimary));
        this.backgroundColorTimer.setColorIntervals(1, 0, 0, 1, 0);
        this.backgroundColorTimer.setColorIntervalRange(1, 0, 0, 30, 0);
    }


    //progress bar
    private void initProgressLayout() {
        this.initProgressImage();
        this.initProgressBar();
    }

    private void initProgressImage() {
        this.progressImageView = (ImageView) findViewById(R.id.progress_image_view);

        this.progressImageView.getLayoutParams().height = 500;
        this.progressImageView.getLayoutParams().width = 500;
        Glide.with(this)
                .load("http://www.freeiconspng.com/uploads/hotel-png-0.png")
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(this.progressImageView);
    }

    private void initProgressBar() {
        this.progressBar = (MyProgressBar) findViewById(R.id.progress_bar);
        this.progressBar.getLayoutParams().width = 700;
        this.progressBarTimer = new ProgressBarTimer(this, this.progressBar, 1, new OnProgressIntervalListener() {

            @Override
            public void OnProgressInterval(MyProgressBar progressBar, int progressInterval) {
                if (progressBar.getProgress() + progressInterval < 100) {
                    progressBar.incrementProgressBy(progressInterval);
                } else{
                    if (!LoadingActivity.this.checkingSession) {
                        if (LoadingActivity.this.isSessionValid) {
                            LoadingActivity.this.progressBarTimer.stopTimer();
//                            LoadingActivity.this.initDemoMenu();
                            LoadingActivity.this.makeGetAllMenuRequest();
                            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                            LoadingActivity.this.startActivity(intent);
//                            LoadingActivity.this.setError("Session is valid", LOGIN);
//                            LoadingActivity.this.setScreenState(LOGIN);
                        } else {
                            LoadingActivity.this.progressBarTimer.stopTimer();
                            LoadingActivity.this.setScreenState(LOGIN);
                        }
                    }
                }
            }
        });
    }


    //loading animation
    private void initLoadingLayout() {
        this.loadingImageView = (ImageView) findViewById(R.id.loading_image_view);

        this.loadingImageView.getLayoutParams().height = 300;
        this.loadingImageView.getLayoutParams().width = 300;
        Glide.with(this).load(R.drawable.progressbar)
                .apply(RequestOptions.fitCenterTransform())
                .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.NONE))
                .into(this.loadingImageView);
    }


    //forms
    private void initForm() {

        initLoginLayout();
        initRegisterLayout();
        initSMSAuthLayout();

    }


    //login
    private void initLoginLayout() {

        //login title
        this.loginTitle = (TextView) findViewById(R.id.login_title_text_view);
        this.initTextView(this.loginTitle, "Sign In", 12, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.loginTitle.setTypeface(this.loginTitle.getTypeface(), Typeface.BOLD);

        //login email
        this.loginEmailEditText = (MyEditText) findViewById(R.id.login_email_edit_text);
        this.initEditText(this.loginEmailEditText, "Email", "faw_user");
        this.loginEmailEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (RegexUtils.isEmail(text) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid email";
                }
            }
        });
        this.loginEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkLoginFields();
            }
        });

        //login password
        this.loginPasswordEditText = (MyEditText) findViewById(R.id.login_password_edit_text);
        this.initEditText(this.loginPasswordEditText, "Password", "faw_lock");
        this.loginPasswordEditText.setTextInputType(finals.inputTypes.get("textPassword"));
        this.loginPasswordEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (((RegexUtils.isMatch("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", text))
                        && (text.length() >= 8) && (text.length() <= 15)) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid password";
                }
            }
        });
        this.loginPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkLoginFields();
            }
        });

        //login error
        this.loginErrorTextView = (TextView) findViewById(R.id.login_error_text_view);
        this.initTextView(this.loginErrorTextView, "", 4, ContextCompat.getColor(this, R.color.colorError),3 );

        //login button
        this.loginButton = (MyButton) findViewById(R.id.login_sign_in_button);
        this.initButton(this.loginButton, "Sing In");
        this.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.onLoginButtonClick(
                        LoadingActivity.this.loginEmailEditText.getText().toString(),
                        LoadingActivity.this.loginPasswordEditText.getText().toString()
                );
            }
        });
        this.loginButton.setEnabled(false);

        //login sign up message
        this.loginSignUpMessageTextView = (TextView) findViewById(R.id.login_sign_up_message_text_view);
        this.initTextView(this.loginSignUpMessageTextView, "If you are new please sign up:", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );

        //login sign up link
        this.loginSignUpLinkTextView = (TextView) findViewById(R.id.login_sign_up_link_text_view);
        this.initTextView(this.loginSignUpLinkTextView, "Sign Up", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.loginSignUpLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.setScreenState(REGISTER);
            }
        });
        this.loginSignUpLinkTextView.setPaintFlags(this.loginSignUpLinkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void checkLoginFields() {
        if (((this.loginEmailEditText.getErrorText() != null) && (!this.loginEmailEditText.getErrorText().toString().equals("")))
        || ((this.loginPasswordEditText.getErrorText() != null) && (!this.loginPasswordEditText.getErrorText().toString().equals("")))
        || this.loginEmailEditText.getText().toString().equals("")
        || this.loginPasswordEditText.getText().toString().equals("")) {
            this.loginButton.setEnabled(false);
        } else {
            this.loginButton.setEnabled(true);
        }
    }

    private void resetLoginFields() {
        this.loginEmailEditText.setText("");
        this.loginPasswordEditText.setText("");
        this.loginErrorTextView.setText("");
        this.loginErrorTextView.setVisibility(View.GONE);
    }

    private void onLoginButtonClick(String username, String password){
        this.makeLoginRequest(username, password);
    }

    private void makeLoginRequest(String email, String password) {
        try {
            this.setScreenState(LOADING);
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
            RequestWraper request = new RequestWraper();
            MnGuest guest = new MnGuest();
            guest.setGuestEmail(email);
            guest.setGuestPassword(password);
            request.setGuest(guest);

            JsonObject jsonRequest = new JsonObject();
            jsonRequest.add("request", new Gson().toJsonTree(request, RequestWraper.class));
            HttpController.get().makeJsonRequest(LOGIN_URL, new JSONObject( new Gson().toJson(jsonRequest)), new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        CustomResponse customResponse = new Gson().fromJson(response.getJSONObject("response").toString(), CustomResponse.class);
                        switch (customResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), LOGIN);
                                } else {
                                    LoadingActivity.this.setError("Login Error", LOGIN);
                                }
                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            case ApplicativeResponse.SUCCESS:
                                LoadingActivity.this.saveSession(customResponse.getGuest());
                                //move to app
//                                LoadingActivity.this.initDemoMenu();
                                LoadingActivity.this.makeGetAllMenuRequest();
                                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                                LoadingActivity.this.startActivity(intent);
//                                LoadingActivity.this.setError("Success", LOGIN);
//                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                LoadingActivity.this.saveSession(customResponse.getGuest());
                                if (customResponse.getStatusResponse().getMsg().toLowerCase().contains("verification")) {
                                    LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                }
                                break;
                            default:
                                LoadingActivity.this.setError("Login Error", LOGIN);
                                LoadingActivity.this.setScreenState(LOGIN);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.setScreenState(LOGIN);
                        LoadingActivity.this.setError("Login Error", LOGIN);
                    }
                }
                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.setScreenState(LOGIN);
                    LoadingActivity.this.setError("Login Error", LOGIN);
                }
            }, headers, params);
        } catch (JSONException e) {
            this.setScreenState(LOGIN);
            this.setError("Login Error", LOGIN);
            e.printStackTrace();
        }
    }


    //register
    private void initRegisterLayout() {

        //register title
        this.registerTitle = (TextView) findViewById(R.id.register_title_text_view);
        this.initTextView(this.registerTitle, "Sign Up", 12, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.registerTitle.setTypeface(this.registerTitle.getTypeface(), Typeface.BOLD);

        //register username
        this.registerUsernameEditText = (MyEditText) findViewById(R.id.register_username_edit_text);
        this.initEditText(this.registerUsernameEditText, "Username", "faw_user");
        this.registerUsernameEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (RegexUtils.isUsername(text) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid username";
                }
            }
        });
        this.registerUsernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });

        //register username
        this.registerFullNameEditText = (MyEditText) findViewById(R.id.register_full_name_edit_text);
        this.initEditText(this.registerFullNameEditText, "Full Name", "faw_user");
        this.registerFullNameEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (RegexUtils.isMatch("^[\\p{L} .'-]+$", text) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter full name";
                }
            }
        });
        this.registerFullNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });


        //register email
        this.registerEmailEditText = (MyEditText) findViewById(R.id.register_email_edit_text);
        this.initEditText(this.registerEmailEditText, "Email", "faw_envelope");
        this.registerEmailEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (RegexUtils.isEmail(text) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid email";
                }
            }
        });
        this.registerEmailEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });

        //register phone
        this.registerPhoneEditText = (MyPhoneEditText) findViewById(R.id.register_phone_edit_text);
        this.initPhoneEditText(this.registerPhoneEditText, "Phone");
        this.registerPhoneEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                    if (RegexUtils.isMatch(
                            "(\\+\\d+|0)(\\d{9})$",
                        "+" + LoadingActivity.this.registerPhoneEditText.getCountryCode() + text)
                        || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid phone number";
                }
            }
        });
        this.registerPhoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });

        //register password
        this.registerPasswordEditText = (MyEditText) findViewById(R.id.register_password_edit_text);
        this.initEditText(this.registerPasswordEditText, "Password", "faw_lock");
        this.registerPasswordEditText.setTextInputType(finals.inputTypes.get("textPassword"));
        this.registerPasswordEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (((RegexUtils.isMatch("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})", text))
                        && (text.length() >= 8) && (text.length() <= 15)) || text.equals("")) {
                    return null;
                } else {
                    return "Please enter valid password";
                }
            }
        });
        this.registerPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });

        //register confirm password
        this.registerConfirmPasswordEditText = (MyEditText) findViewById(R.id.register_confirm_password_edit_text);
        this.initEditText(this.registerConfirmPasswordEditText, "Confirm Password", "faw_lock");
        this.registerConfirmPasswordEditText.setTextInputType(finals.inputTypes.get("textPassword"));
        this.registerConfirmPasswordEditText.setValidator(new Validator<String>() {
            @Override
            public String validate(String text) {
                if (text.equals(LoadingActivity.this.registerPasswordEditText.getText().toString())  || text.equals("")) {
                    return null;
                } else {
                    return "Passwords do not match";
                }
            }
        });
        this.registerConfirmPasswordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkRegisterFields();
            }
        });

        //register error
        this.registerErrorTextView = (TextView) findViewById(R.id.register_error_text_view);
        this.initTextView(this.registerErrorTextView, "", 4, ContextCompat.getColor(this, R.color.colorError),5 );

        //register button
        this.registerButton = (MyButton) findViewById(R.id.register_sign_up_button);
        this.initButton(this.registerButton, "Sing Up");
        this.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.onRegisterButtonClick(
                        LoadingActivity.this.registerUsernameEditText.getText().toString(),
                        LoadingActivity.this.registerEmailEditText.getText().toString(),
                        "+" + LoadingActivity.this.registerPhoneEditText.getCountryCode() + LoadingActivity.this.registerPhoneEditText.getText().toString(),
                        LoadingActivity.this.registerPasswordEditText.getText().toString()
                        );
            }
        });
        this.registerButton.setEnabled(false);

        //register sign in message
        this.registerSignInMessageTextView = (TextView) findViewById(R.id.register_sign_in_message_text_view);
        this.initTextView(this.registerSignInMessageTextView, "If have a user please sign in:", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );

        //register sign in link
        this.registerSignInLinkTextView = (TextView) findViewById(R.id.register_sign_in_link_text_view);
        this.initTextView(this.registerSignInLinkTextView, "Sign In", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.registerSignInLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.setScreenState(LOGIN);
            }
        });
        this.registerSignInLinkTextView.setPaintFlags(this.registerSignInLinkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void checkRegisterFields() {
        if (((this.registerUsernameEditText.getErrorText() != null) && (!this.registerUsernameEditText.getErrorText().toString().equals("")))
                || ((this.registerEmailEditText.getErrorText() != null) && (!this.registerEmailEditText.getErrorText().toString().equals("")))
                || ((this.registerFullNameEditText.getErrorText() != null) && (!this.registerFullNameEditText.getErrorText().toString().equals("")))
                || ((this.registerPhoneEditText.getErrorText() != null) && (!this.registerPhoneEditText.getErrorText().toString().equals("")))
                || ((this.registerPasswordEditText.getErrorText() != null) && (!this.registerPasswordEditText.getErrorText().toString().equals("")))
                || ((this.registerConfirmPasswordEditText.getErrorText() != null) && (!this.registerConfirmPasswordEditText.getErrorText().toString().equals("")))
                || this.registerUsernameEditText.getText().toString().equals("")
                || this.registerEmailEditText.getText().toString().equals("")
                || this.registerFullNameEditText.getText().toString().equals("")
                || this.registerPhoneEditText.getText().toString().equals("")
                || this.registerPasswordEditText.getText().toString().equals("")
                || this.registerConfirmPasswordEditText.getText().toString().equals("")){
            this.registerButton.setEnabled(false);
        } else {
            this.registerButton.setEnabled(true);
        }
    }

    private void resetRegisterFields() {
        this.registerUsernameEditText.setText("");
        this.registerEmailEditText.setText("");
        this.registerFullNameEditText.setText("");
        this.registerPhoneEditText.setText("");
        this.registerPhoneEditText.setCountryCodeByCode(972);
        this.registerPasswordEditText.setText("");
        this.registerConfirmPasswordEditText.setText("");
        this.registerErrorTextView.setText("");
        this.registerErrorTextView.setVisibility(View.GONE);

    }

    private void onRegisterButtonClick(String username, String email, String phoneNumber, String password){
        this.makeRegisterRequest(username, email, phoneNumber, password);
    }

    private void makeRegisterRequest(String username, final String email, String phoneNumber, final String password) {
        try {
            this.setScreenState(LOADING);
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
            RequestWraper request = new RequestWraper();
            MnGuest guest = new MnGuest();
            guest.setGuestName(username);
            guest.setGuestEmail(email);
            guest.setGuestPhone(phoneNumber);
            guest.setGuestPassword(password);
            request.setGuest(guest);
            JsonObject jsonRequest = new JsonObject();
//            Gson gson = new GsonBuilder().registerTypeAdapter(MnGuest.class, new MnGuest.GuestSerializer())
//                    .registerTypeAdapter(MnGuest.class, new MnGuest.GuestDeserializer()).create();
            jsonRequest.add("request", new Gson().toJsonTree(request, RequestWraper.class));
            HttpController.get().makeJsonRequest(REGISTER_URL, new JSONObject( new Gson().toJson(jsonRequest)), new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        CustomResponse customResponse = new Gson().fromJson(response.getJSONObject("response").toString(), CustomResponse.class);
                        switch (customResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), REGISTER);
                                } else {
                                    LoadingActivity.this.setError("Register Error", REGISTER);
                                }
                                LoadingActivity.this.setScreenState(REGISTER);
                                break;
                            case ApplicativeResponse.SUCCESS:
                                MnGuest guest = customResponse.getGuest();
                                if (guest != null) {
                                    LoadingActivity.this.loginEmailEditText.setText(guest.getGuestEmail());
                                    LoadingActivity.this.loginPasswordEditText.setText(guest.getGuestPassword());
                                    makeLoginRequest(guest.getGuestEmail(), guest.getGuestPassword());
                                } else {
                                    LoadingActivity.this.loginEmailEditText.setText(email);
                                    LoadingActivity.this.loginPasswordEditText.setText(password);
                                    makeLoginRequest(email, password);
                                }
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                LoadingActivity.this.saveSession(customResponse.getGuest());
                                if (customResponse.getStatusResponse().getMsg().toLowerCase().contains("verification")) {
                                    LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                }
                                break;
                            default:
                                LoadingActivity.this.setError("Register Error", REGISTER);
                                LoadingActivity.this.setScreenState(REGISTER);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.setScreenState(REGISTER);
                        LoadingActivity.this.setError("Register Error", REGISTER);
                    }
                }
                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.setScreenState(REGISTER);
                    LoadingActivity.this.setError("Register Error", REGISTER);
                }
            }, headers, params);
        } catch (JSONException e) {
            this.setScreenState(REGISTER);
            this.setError("Register Error", REGISTER);
            e.printStackTrace();
        }
    }


    //sms authentication
    private void initSMSAuthLayout() {

        //register title
        this.smsAuthTitle = (TextView) findViewById(R.id.sms_authentication_title_text_view);
        this.initTextView(this.smsAuthTitle, "SMS Verification", 12, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.smsAuthTitle.setTypeface(this.smsAuthTitle.getTypeface(), Typeface.BOLD);

        //sms authentication first info
        this.smsAuthInfoFirstTextView = (TextView) findViewById(R.id.sms_authentication_info_first_text_view);
        this.initTextView(this.smsAuthInfoFirstTextView, "Verification code has sent to your phone,", 6, ContextCompat.getColor(this, R.color.colorAccent),1 );

        //sms authentication first info
        this.smsAuthInfoSecondTextView = (TextView) findViewById(R.id.sms_authentication_info_second_text_view);
        this.initTextView(this.smsAuthInfoSecondTextView, "Please enter your code here:", 6, ContextCompat.getColor(this, R.color.colorAccent),1 );

        //sms authentication first code
        this.smsAuthFirstEditText = (MyEditText) findViewById(R.id.sms_authentication_first_code_edit_text);
        this.initSMSCodeEditText(this.smsAuthFirstEditText);
        this.smsAuthFirstEditText.setTextInputType(finals.inputTypes.get("number"));
        this.smsAuthFirstEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.smsAuthSecondEditText.getEditText().requestFocus();
                LoadingActivity.this.checkSMSAuthFields();
            }
        });

        //sms authentication second code
        this.smsAuthSecondEditText = (MyEditText) findViewById(R.id.sms_authentication_second_code_edit_text);
        this.initSMSCodeEditText(this.smsAuthSecondEditText);
        this.smsAuthSecondEditText.setTextInputType(finals.inputTypes.get("number"));
        this.smsAuthSecondEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.smsAuthThirdEditText.getEditText().requestFocus();
                LoadingActivity.this.checkSMSAuthFields();
            }
        });

        //sms authentication third code
        this.smsAuthThirdEditText = (MyEditText) findViewById(R.id.sms_authentication_third_code_edit_text);
        this.initSMSCodeEditText(this.smsAuthThirdEditText);
        this.smsAuthThirdEditText.setTextInputType(finals.inputTypes.get("number"));
        this.smsAuthThirdEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.smsAuthFourthEditText.getEditText().requestFocus();
                LoadingActivity.this.checkSMSAuthFields();
            }
        });

        //sms authentication fourth code
        this.smsAuthFourthEditText = (MyEditText) findViewById(R.id.sms_authentication_fourth_code_edit_text);
        this.initSMSCodeEditText(this.smsAuthFourthEditText);
        this.smsAuthFourthEditText.setTextInputType(finals.inputTypes.get("number"));
        this.smsAuthFourthEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                LoadingActivity.this.checkSMSAuthFields();
            }
        });

        //sms authentication error
        this.smsAuthErrorTextView = (TextView) findViewById(R.id.sms_authentication_error_text_view);
        this.initTextView(this.smsAuthErrorTextView, "", 4, ContextCompat.getColor(this, R.color.colorError),3 );

        //smsAuth button
        this.smsAuthButton = (MyButton) findViewById(R.id.sms_authentication_button);
        this.initButton(this.smsAuthButton, "Verify");
        this.smsAuthButton.setEnabled(false);
        this.smsAuthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.onSMSAuthButtonClick(LoadingActivity.this.smsAuthFirstEditText.getText().toString()
                + LoadingActivity.this.smsAuthSecondEditText.getText().toString()
                + LoadingActivity.this.smsAuthThirdEditText.getText().toString()
                + LoadingActivity.this.smsAuthFourthEditText.getText().toString());
            }
        });

        //smsAuth sign in message
        this.smsAuthSignInMessageTextView = (TextView) findViewById(R.id.sms_authentication_sign_in_message_text_view);
        this.initTextView(this.smsAuthSignInMessageTextView, "Go back to login:", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );

        //smsAuth sign in link
        this.smsAuthSignInLinkTextView = (TextView) findViewById(R.id.sms_authentication_sign_in_link_text_view);
        this.initTextView(this.smsAuthSignInLinkTextView, "Sign In", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.smsAuthSignInLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.setScreenState(LOGIN);
            }
        });
        this.smsAuthSignInLinkTextView.setPaintFlags(this.smsAuthSignInLinkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        //smsAuth sign in link
        this.smsAuthResendCodeLinkTextView= (TextView) findViewById(R.id.sms_authentication_resend_code_link_text_view);
        this.initTextView(this.smsAuthResendCodeLinkTextView, "Resend Code", 4, ContextCompat.getColor(this, R.color.colorAccent),1 );
        this.smsAuthResendCodeLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadingActivity.this.onSMSAuthResendCodeButtonClick();
            }
        });
        this.smsAuthResendCodeLinkTextView.setPaintFlags(this.smsAuthResendCodeLinkTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    }

    private void checkSMSAuthFields() {
        if ((this.smsAuthFirstEditText.getText().toString().equals(""))
        || (this.smsAuthSecondEditText.getText().toString().equals(""))
        || (this.smsAuthThirdEditText.getText().toString().equals(""))
        || (this.smsAuthFourthEditText.getText().toString().equals(""))) {
            this.smsAuthButton.setEnabled(false);
        } else {
            this.smsAuthButton.setEnabled(true);
        }
    }

    private void resetSMSAuthFields() {
        this.smsAuthFirstEditText.setText("");
        this.smsAuthSecondEditText.setText("");
        this.smsAuthThirdEditText.setText("");
        this.smsAuthFourthEditText.setText("");
        this.smsAuthErrorTextView.setText("");
        this.smsAuthErrorTextView.setVisibility(View.GONE);
    }

    private void onSMSAuthButtonClick(String smsAuthCode){
        this.makeSMSAuthVerifyRequest(smsAuthCode);
    }

    private void makeSMSAuthVerifyRequest(String code) {
        this.setScreenState(LOADING);
        String phone = BOBApplication.get().getSecureSharedPreferences().getString("guestPhone", null);
        String email = BOBApplication.get().getSecureSharedPreferences().getString("guestEmail", null);
        if ((phone != null) && (email != null)) {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("phone", phone);
            params.put("email", email);
            params.put("code", code);
            HttpController.get().makeJsonRequest(SMS_VERIFY_URL, null, new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        CustomResponse customResponse = new Gson().fromJson(response.getJSONObject("response").toString(), CustomResponse.class);
                        switch (customResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), SMS_AUTHENTICATION);
                                } else {
                                    LoadingActivity.this.setError("SMS Verification Error", SMS_AUTHENTICATION);
                                }
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                break;
                            case ApplicativeResponse.SUCCESS:
                                LoadingActivity.this.setError("Success", LOGIN);
                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), SMS_AUTHENTICATION);
                                } else {
                                    LoadingActivity.this.setError("SMS Verification Warning", SMS_AUTHENTICATION);
                                }
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                break;
                            default:
                                LoadingActivity.this.setError("SMS Verification Error", SMS_AUTHENTICATION);
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                        LoadingActivity.this.setError("SMS Verification Error", SMS_AUTHENTICATION);
                    }
                }

                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.setError("SMS Verification Error", SMS_AUTHENTICATION);
                    LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                }
            }, headers, params);
        } else {
            this.setError("SMS Verification Error", SMS_AUTHENTICATION);
            this.setScreenState(SMS_AUTHENTICATION);
        }
    }

    private void onSMSAuthResendCodeButtonClick(){
        this.makeSMSAuthResendVerifyCodeRequest();
    }

    private void makeSMSAuthResendVerifyCodeRequest() {
        this.setScreenState(LOADING);
        String phone = BOBApplication.get().getSecureSharedPreferences().getString("guestPhone", null);
        String email = BOBApplication.get().getSecureSharedPreferences().getString("guestEmail", null);
        if ((phone != null) && (email != null)) {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("phone", phone);
            params.put("email", email);
            HttpController.get().makeJsonRequest(SMS_RESEND_VERIFICATION_CODE_URL, null, new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        CustomResponse customResponse = new Gson().fromJson(response.getJSONObject("response").toString(), CustomResponse.class);
                        switch (customResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), SMS_AUTHENTICATION);
                                } else {
                                    LoadingActivity.this.setError("SMS Resend Error", SMS_AUTHENTICATION);
                                }
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                break;
                            case ApplicativeResponse.SUCCESS:
                                LoadingActivity.this.setError("Success", SMS_AUTHENTICATION);
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                if (customResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(customResponse.getStatusResponse().getMsg(), SMS_AUTHENTICATION);
                                } else {
                                    LoadingActivity.this.setError("SMS Resend Warning", SMS_AUTHENTICATION);
                                }
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                                break;
                            default:
                                LoadingActivity.this.setError("SMS Resend Error", SMS_AUTHENTICATION);
                                LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                        LoadingActivity.this.setError("SMS Resend Error", SMS_AUTHENTICATION);
                    }
                }

                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.setError("SMS Resend Error", SMS_AUTHENTICATION);
                    LoadingActivity.this.setScreenState(SMS_AUTHENTICATION);
                }
            }, headers, params);
        } else {
            this.setError("SMS Resend Error", SMS_AUTHENTICATION);
            this.setScreenState(SMS_AUTHENTICATION);
        }
    }



    //init fields
    private void initPhoneEditText(MyPhoneEditText myPhoneEditText, String title) {
        myPhoneEditText.setTitleText(title);
        myPhoneEditText.setEndDrawableOnFocusOnly(true);
        myPhoneEditText.getTextView().setMaxLines(1);
        myPhoneEditText.setWidth(UIUtilsManager.get().convertDpToPixels(this, 260));
        myPhoneEditText.setTextInputType(finals.inputTypes.get("phone"));
        myPhoneEditText.setCountryCodePickerDialogBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        myPhoneEditText.setCountryCodePickerDialogTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccentTransparent));
        myPhoneEditText.setEndDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setStartDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setBottomLineColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setCursorColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setTitleColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        myPhoneEditText.setErrorTextColor(ContextCompat.getColor(this, R.color.colorError));
        myPhoneEditText.setBottomLineEnable(false);
        myPhoneEditText.setCountryCodeByCode(972);

//        myEditText.getTextView().setBackgroundColor(Color.GREEN);
//        myEditText.setBottomLineEnable(false);
//        myEditText.setValidator(new DefaultStringValidator());
    }

    private void initEditText(MyEditText myEditText, String title, String icon) {
        myEditText.setTitleText(title);
        myEditText.setEndDrawableOnFocusOnly(true);
        myEditText.getTextView().setMaxLines(1);
        IconicsDrawable startDrawable = (IconicsDrawable)Icons.get().findDrawable(this,icon);
        if (startDrawable != null) {
            myEditText.setStartDrawable(startDrawable.sizeDp(20).color(ContextCompat.getColor(this, R.color.colorAccent)));
        }
        myEditText.setWidth(UIUtilsManager.get().convertDpToPixels(this, 260));
        myEditText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccentTransparent));
        myEditText.setEndDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setStartDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setBottomLineColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setCursorColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setTitleColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setErrorTextColor(ContextCompat.getColor(this, R.color.colorError));
        myEditText.setBottomLineEnable(false);
    }

    private void initSMSCodeEditText(MyEditText myEditText) {
        myEditText.setEndDrawableEnable(false);
        myEditText.getTextView().setMaxLines(1);
        myEditText.getEditText().setFilters(new InputFilter[]{ new InputFilter.LengthFilter(1) });
        myEditText.setTextSize(UIUtilsManager.get().convertSpToPixels(this, 8));
        myEditText.setWidth(UIUtilsManager.get().convertDpToPixels(this, 50));
        myEditText.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccentTransparent));
        myEditText.setEndDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setStartDrawableColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setBottomLineColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setCursorColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setTitleColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));
        myEditText.setErrorTextColor(ContextCompat.getColor(this, R.color.colorError));
        myEditText.setBottomLineEnable(false);
        myEditText.getEditText().setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        ViewGroup.LayoutParams layoutParams = myEditText.getEditText().getLayoutParams();
        layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        myEditText.getEditText().setSelectAllOnFocus(true);
    }

    private void initTextView(TextView myTextView, String text, int textSize, int color, int maxLines) {
        myTextView.setText(text);
        myTextView.setTextSize(UIUtilsManager.get().convertSpToPixels(this, textSize));
        myTextView.setMaxLines(maxLines);
        myTextView.setTextColor(color);
//        myEditText.setValidator(new DefaultStringValidator());
    }

    private void initButton(MyButton myButton, String text) {
        myButton.setText(text);
        myButton.setTextSize(UIUtilsManager.get().convertSpToPixels(this, 8));
        myButton.getTextView().setMaxLines(1);
        myButton.setBoldEnable(true);
        myButton.setWidth(UIUtilsManager.get().convertDpToPixels(this, 260));
        myButton.setButtonBorderRadius(10);
        //default colors
        myButton.setButtonColor(ContextCompat.getColor(this, R.color.colorAccentTransparent));
        myButton.setButtonBorderColor(ContextCompat.getColor(this, R.color.colorAccent));
        myButton.setTextColor(ContextCompat.getColor(this, R.color.colorAccent));

        //pressed
        myButton.addButtonBorderColorState(android.R.attr.state_pressed, ContextCompat.getColor(this, R.color.colorAccentHalfTransparent));
        myButton.addButtonTextColorState(android.R.attr.state_pressed, ContextCompat.getColor(this, R.color.colorAccentHalfTransparent));
        myButton.addButtonBorderColorState(-android.R.attr.state_enabled, ContextCompat.getColor(this, R.color.colorAccentExtreTransparent));
        myButton.addButtonTextColorState(-android.R.attr.state_enabled, ContextCompat.getColor(this, R.color.colorAccentExtreTransparent));
        myButton.setButtonBorderEnable(true);

//        myEditText.setValidator(new DefaultStringValidator());
    }


    //tools
    private byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    private void initDemoMenu() {
        RealmController.with(BOBApplication.get()).deleteAllMenuNodes();
        RealmController.with(BOBApplication.get()).deleteAllFormItems();
        RealmController.with(BOBApplication.get()).deleteAllFormItemProperties();
        RealmController.with(BOBApplication.get()).deleteAllMenuNodeProperties();
        DemoCounterExtraBed menu = new DemoCounterExtraBed(-1L);
    }

    private void makeGetAllMenuRequest() {
        this.setScreenState(LOADING);
        String sid = BOBApplication.get().getSecureSharedPreferences().getString("guestGuid", null);
        String hotelId = BOBApplication.get().getSecureSharedPreferences().getString("hotelId", "1");
        if ((sid != null) && (hotelId != null)) {
            HashMap<String, String> headers = new HashMap<String, String>();
            headers.put("Content-Type", "application/json");
            headers.put("Accept", "application/json");
            HashMap<String, String> params = new HashMap<String, String>();
//            params.put("hotelId", hotelId);
//            params.put("sid", sid);
            HttpController.get().makeJsonRequest(GET_ALL_MENU_URL + "?hotelId="+hotelId+"&sid="+sid, null, new HttpJsonResponseHandler() {
                @Override
                public void OnResponse(JSONObject response) {
                    try {
                        DesignResponse designResponse = new Gson().fromJson(response.getJSONObject("designResponse").toString(), DesignResponse.class);
                        switch (designResponse.getStatusResponse().getCode()) {
                            case ApplicativeResponse.FAILURE:
                                if (designResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(designResponse.getStatusResponse().getMsg(), LOGIN);
                                } else {
                                    LoadingActivity.this.setError("SMS Verification Error", LOGIN);
                                }
                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            case ApplicativeResponse.SUCCESS:
                                LoadingActivity.this.insertMenuToDB(designResponse);
//                                LoadingActivity.this.setError("Got Menu", LOGIN);
//                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            case ApplicativeResponse.SUCCESS_WITH_MESSAGE:
                                if (designResponse.getStatusResponse().getMsg() != null) {
                                    LoadingActivity.this.setError(designResponse.getStatusResponse().getMsg(), LOGIN);
                                } else {
                                    LoadingActivity.this.setError("Getting Menu Warning", LOGIN);
                                }
                                LoadingActivity.this.setScreenState(LOGIN);
                                break;
                            default:
                                LoadingActivity.this.setError("Getting Menu Error", LOGIN);
                                LoadingActivity.this.setScreenState(LOGIN);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        LoadingActivity.this.setScreenState(LOGIN);
                        LoadingActivity.this.setError("Getting Menu Error", LOGIN);
                    }
                }

                @Override
                public void OnErrorResponse(VolleyError error) {
                    LoadingActivity.this.setError("SMS Verification Error", LOGIN);
                    LoadingActivity.this.setScreenState(LOGIN);
                }
            }, headers, params);
        } else {
            this.setError("Getting Menu Error", LOGIN);
            this.setScreenState(LOGIN);
        }
    }

    private void insertMenuToDB(DesignResponse designResponse) {

//        RealmController.with(BOBApplication.get()).deleteAllMenuNodes();
//        RealmController.with(BOBApplication.get()).deleteAllFormItems();
//        RealmController.with(BOBApplication.get()).deleteAllMenuNodeProperties();
//        RealmController.with(BOBApplication.get()).deleteAllFormItemProperties();

        List<MnNode> mnNodes = designResponse.getNodes();
        List<MnNodeProp> mnNodeProps = designResponse.getNodesProperties();
        List<MnLeaf> mnLeaves = designResponse.getBullets();
        List<MnLeafProp> mnLeafProps = designResponse.getBulletsProperties();

        for (MnNode mnNode : mnNodes) {
            RealmController.with(BOBApplication.get()).insertOrUpdateMenuNode(
                    RealmHelper.get().menuNodeBeanToRealm(mnNode));
        }

        for (MnNodeProp mnNodeProp : mnNodeProps) {
            RealmController.with(BOBApplication.get()).insertOrUpdateMenuNodeProperty(
                    RealmHelper.get().menuNodePropertyBeanToRealm(mnNodeProp));
        }

        for (MnLeaf mnLeaf : mnLeaves) {
            RealmController.with(BOBApplication.get()).insertOrUpdateFormItem(
                    RealmHelper.get().formItemBeanToRealm(mnLeaf));
        }

        for (MnLeafProp mnLeafProp : mnLeafProps) {
            RealmController.with(BOBApplication.get()).insertOrUpdateFormItemProperty(
                    RealmHelper.get().formItemPropertyBeanToRealm(mnLeafProp));
        }
    }

}
