package com.bob.bobmobileapp.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.midi.MidiOutputPort;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TimePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.drawerItems.secondary.CustomCenteredSecondaryDrawerItem;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.http.beans.Wish;
import com.bob.bobmobileapp.menu.adapters.FormItemsAdapter;
import com.bob.bobmobileapp.menu.adapters.MenuNodesAdapter;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyMultiChoiseDialogViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyTimeViewViewHolder;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.RealmHelper;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.tools.UI.views.MyView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyButton;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyLocationInputView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyLocationOutputView;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyPhoneEditText;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyDateTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTextViewListDialog;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTextViewMultiChoiceDialog;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTextViewSingleChoiceDialog;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTimeTextView;
import com.mikepenz.fontawesome_typeface_library.FontAwesome;
import com.mikepenz.material_design_iconic_typeface_library.MaterialDesignIconic;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private AccountHeader headerResult = null;
    private Drawer result = null;
    private MenuNodesAdapter menuNodesAdapter;
    private FormItemsAdapter formItemsAdapter;
    private RecyclerView recyclerView;
    private long curMenuNodeId;


//    private MyDateTextView myDateTextView;
//    private MyTextViewListDialog myTextViewListDialog;
//    private MyTextViewMultiChoiceDialog myTextViewMultiChoiceDialog;
//    private MyTextViewSingleChoiceDialog myTextViewSingleChoiceDialog;
//    private MyTimeTextView myTimeTextView;
//    private MyButton myButton;
//    private MyEditText myEditText;
//    private MyLocationInputView myLocationInputView;
//    private MyLocationOutputView myLocationOutputView;
//    private MyPhoneEditText myPhoneEditText;
//    private MyTextView myTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);


        //db
//        this.insertItemToDB();

        //toolbar
        this.initToolbar();

        //nav drawer
        this.buildHeader(false, savedInstanceState);
        this.buildDrawer(savedInstanceState);

        //init cur menu node id
        this.initCurMenuNodeId();

        //adapters
        this.setAdapters();

        //recycler view
        this.initRecyclerView();

//        this.demoBullets();
    }

    //clear session
    private void clearSession() {
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestEmail")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestEmail").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestGuid")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestGuid").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestGuidValid")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestGuidValid").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestId")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestId").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestName")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestName").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestPassword")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestPassword").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestPhone")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestPhone").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestRoom")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestRoom").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestStatus")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestStatus").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestInsertTs")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestInsertTs").apply();
        }
        if (BOBApplication.get().getSecureSharedPreferences().contains("guestLmTs")) {
            BOBApplication.get().getSecureSharedPreferences().edit().remove("guestLmTs").apply();
        }
//            MnHotel hotel = guest.getMnHotel();
//            if (hotel != null) {
//                if (hotel.getHotelId() != -1){
//                    BOBApplication.get().getSecureSharedPreferences().edit().putLong("hotelId", hotel.getHotelId()).apply();
//                }
//                if (hotel.getHotelName() != null){
//                    BOBApplication.get().getSecureSharedPreferences().edit().putString("hotelName", hotel.getHotelName()).apply();
//                }
//            }
    }

    //toolbar
    public void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    //nav drawer
    private void buildHeader(boolean compact, Bundle savedInstanceState) {
        // Create the AccountHeader
    }

    private void buildDrawer(Bundle savedInstanceState) {
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult) //set the AccountHeader we created earlier for the header
                .addDrawerItems(
                        new PrimaryDrawerItem().withIdentifier(1).withName(R.string.drawer_item_home).withIcon(FontAwesome.Icon.faw_home),
                        new PrimaryDrawerItem().withIdentifier(2).withName(R.string.drawer_item_requests).withIcon(FontAwesome.Icon.faw_bell),
                        new PrimaryDrawerItem().withIdentifier(3).withName(R.string.drawer_item_account).withIcon(MaterialDesignIconic.Icon.gmi_account),
                        new SectionDrawerItem(),
                        new SecondaryDrawerItem().withIdentifier(4).withName(R.string.drawer_item_settings).withIcon(MaterialDesignIconic.Icon.gmi_settings),
                        new SecondaryDrawerItem().withIdentifier(5).withName(R.string.drawer_item_privacy_settings).withIcon(FontAwesome.Icon.faw_lock),
                        new SecondaryDrawerItem().withIdentifier(6).withName(R.string.drawer_item_help).withIcon(MaterialDesignIconic.Icon.gmi_help),
                        new SecondaryDrawerItem().withIdentifier(7).withName(R.string.drawer_item_about_us).withIcon(MaterialDesignIconic.Icon.gmi_info),
                        new SecondaryDrawerItem().withIdentifier(8).withName("Demo").withIcon(MaterialDesignIconic.Icon.gmi_menu).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                return false;
                            }
                        }),
                        new CustomCenteredSecondaryDrawerItem().withIdentifier(9).withName(R.string.drawer_item_logout).withTextColor(Color.RED).withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                MainActivity.this.clearSession();
                                Intent intent = new Intent(MainActivity.this, LoadingActivity.class);
                                MainActivity.this.startActivity(intent);
                                return false;
                            }
                        })
                ) // add the items we want to use with our Drawer
                .withOnDrawerNavigationListener(new Drawer.OnDrawerNavigationListener() {
                    @Override
                    public boolean onNavigationClickListener(View clickedView) {
                        //this method is only called if the Arrow icon is shown. The hamburger is automatically managed by the MaterialDrawer
                        //if the back arrow is shown. close the activity
                        MainActivity.this.finish();
                        //return true if we have consumed the event
                        return true;
                    }
                })
                .withSavedInstance(savedInstanceState)
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_1:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //menu and form adapter
    private void setAdapters() {
        this.menuNodesAdapter = new MenuNodesAdapter(this, this.curMenuNodeId);
        this.formItemsAdapter = new FormItemsAdapter(this, this.curMenuNodeId);
    }

    private void initCurMenuNodeId() {
        if (this.getIntent().hasExtra("curMenuNode")) {
            this.curMenuNodeId = this.getIntent().getLongExtra("curMenuNodeId", -1L);
        } else {
            RealmResults<MenuNode> menuNodes = RealmController.with(BOBApplication.get()).getSubMenuNodes(-1L);
            MenuNode mainMenuNode = RealmController.with(BOBApplication.get()).getMenuNodeById(0);
            MenuNode demoMenuNode = RealmController.with(BOBApplication.get()).getMenuNodeById(-2L);
            if ((menuNodes != null) && (menuNodes.size() >0)) {
                if (menuNodes.contains(mainMenuNode)) {
                    this.curMenuNodeId = mainMenuNode.getId();
                } else if (menuNodes.contains(demoMenuNode)) {
                    this.curMenuNodeId = demoMenuNode.getId();
                } else {
                    this.curMenuNodeId = menuNodes.get(0).getId();
                }
            } else {
                this.curMenuNodeId = -1L;
            }
        }
    }

    //recycler view
    private void initRecyclerView() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.setCurMenuNode(this.curMenuNodeId);
    }

    //change menu to form and backward
    public void setCurMenuNode(long curMenuNodeId) {
        MenuNode menuNode = RealmController.with(BOBApplication.get()).getMenuNodeById(curMenuNodeId);
        this.curMenuNodeId = menuNode == null ? 0 : menuNode.getId();
        if ((menuNode != null) && (menuNode.isLeaf())) {
            this.recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
            this.formItemsAdapter.setParentMenuNode(this.curMenuNodeId);
            this.recyclerView.setAdapter(this.formItemsAdapter);
            this.setSendButton();
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            this.menuNodesAdapter.setParentMenuNode(this.curMenuNodeId);
            this.recyclerView.setAdapter(this.menuNodesAdapter);
        }
    }

    //set send button
    private void setSendButton() {
        int viewsCount = this.recyclerView.getChildCount();
        for (int i = 0; i < viewsCount; i++) {
            if (this.recyclerView.getChildAt(i) instanceof MyButton) {
                MyButton sendButton = (MyButton) this.recyclerView.getChildAt(i);
                if ((sendButton.getButtonType() != null) && (sendButton.getButtonType().equals("send_button"))) {
                    sendButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Wish wish = new Wish();
                            int viewsCount = MainActivity.this.recyclerView.getChildCount();
                            for (int i = 0; i < viewsCount; i++) {
                                if ((MainActivity.this.recyclerView.getChildAt(i) instanceof MyView) &&
                                        ((MyView)(MainActivity.this.recyclerView.getChildAt(i))).getValue() != null) {
                                    wish.addPair(((MyView)(MainActivity.this.recyclerView.getChildAt(i))).getValue());
                                }
                            }
                        }
                    });
                }

            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        //add the values which need to be saved from the drawer to the bundle
        //outState = result.saveInstanceState(outState);
        //add the values which need to be saved from the accountHeader to the bundle
        //outState = headerResult.saveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        //handle the back press :D close the drawer first and if the drawer is closed close the activity
        if (result != null && result.isDrawerOpen()) {
            result.closeDrawer();
        } else {
            //super.onBackPressed();
            MenuNode menuNode = RealmController.with(BOBApplication.get()).getMenuNodeById(this.curMenuNodeId);
            if (menuNode != null && menuNode.getParentId() >= 0) {
                setCurMenuNode(menuNode.getParentId());
            }
        }
    }

    private void insertItemToDB() {
//        RealmController.with(BOBApplication.get()).deleteAllMenuNodes();
//        RealmController.with(BOBApplication.get()).deleteAllFormItems();
//        RealmController.with(BOBApplication.get()).deleteAllFormItemProperties();
//        RealmController.with(BOBApplication.get()).deleteAllMenuNodeProperties();
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 0, -1, "main", "https://image.flaticon.com/icons/svg/149/149176.svg", false));
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 1, 0, "food", "http://www.pvhc.net/img8/niexjjzstcseuzdzkvoq.png", true));
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 2, 0, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg", false));
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 3, 2, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg", false));
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 4, 2, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg", false));
//        RealmController.with(BOBApplication.get()).insertMenuNode(RealmHelper.get().makeNewMenuNode(0, 5, 2, "room service", "https://image.flaticon.com/icons/svg/201/201699.svg", false));
//        RealmController.with(BOBApplication.get()).insertFormItem(RealmHelper.get().makeNewFormItem(0, 0,1,"text_view"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 0,0,"text", "nanana"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 1,0,"text_color", "#ffcc00"));
//        RealmController.with(BOBApplication.get()).insertFormItem(RealmHelper.get().makeNewFormItem(0, 1,1,"text_view"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 2,1,"text", "nanana"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 3,1,"text_color", "#ffcc00"));
//        RealmController.with(BOBApplication.get()).insertFormItem(RealmHelper.get().makeNewFormItem(0, 2,1,"text_view"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 4,2,"text", "nanana"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 5,2,"text_color", "#ffcc00"));
//        RealmController.with(BOBApplication.get()).insertFormItem(RealmHelper.get().makeNewFormItem(0, 3,1,"video_view"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 6,3,"youtube_id", "ed4XYtWZqgQ"));
//        RealmController.with(BOBApplication.get()).insertFormItem(RealmHelper.get().makeNewFormItem(0, 4,1,"video_view"));
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(RealmHelper.get().makeNewFormItemProperty(0, 7,4,"youtube_id", "y1qPIDJM4Kk"));
    }


//    private void demoBullets() {
//
//        this.myDateTextView = (MyDateTextView) findViewById(R.id.my_date_text_view);
//        this.myDateTextView.setDays(25);
//        this.myDateTextView.setMonths(7);
//        this.myDateTextView.setYears(1991);
//
//
//        this.myTextViewListDialog = (MyTextViewListDialog) findViewById(R.id.my_text_view_list_dialog);
//        this.myTextViewListDialog.addItem("First List Item");
//        this.myTextViewListDialog.addItem("Second List Item");
//        this.myTextViewListDialog.addItem("Third List Item");
//        this.myTextViewListDialog.setText("My Text View List Dialog");
//
//
//        this.myTextViewMultiChoiceDialog = (MyTextViewMultiChoiceDialog) findViewById(R.id.my_text_view_multi_choice_dialog);
//        this.myTextViewMultiChoiceDialog.addItem("First Multi List Item");
//        this.myTextViewMultiChoiceDialog.addItem("Second Multi List Item");
//        this.myTextViewMultiChoiceDialog.addItem("Third Multi List Item");
//        this.myTextViewMultiChoiceDialog.setText("My Text View Multi Choice Dialog");
//
//        this.myTextViewSingleChoiceDialog = (MyTextViewSingleChoiceDialog) findViewById(R.id.my_text_view_single_choice_dialog);
//        this.myTextViewSingleChoiceDialog.addItem("First Single List Item");
//        this.myTextViewSingleChoiceDialog.addItem("Second Single List Item");
//        this.myTextViewSingleChoiceDialog.addItem("Third Single List Item");
//        this.myTextViewSingleChoiceDialog.setText("My Text View Single Choice Dialog");
//
//        this.myTimeTextView = (MyTimeTextView) findViewById(R.id.my_time_text_view);
//        this.myTimeTextView.setHours(12);
//        this.myTimeTextView.setMinutes(12);
//        this.myTimeTextView.setSeconds(12);
//
//        this.myButton = (MyButton) findViewById(R.id.my_button);
//        this.myButton.setText("My Button");
//
//        this.myEditText = (MyEditText) findViewById(R.id.my_edit_text);
//        this.myEditText.setText("My Edit Text");
//
//        this.myLocationInputView = (MyLocationInputView) findViewById(R.id.my_location_input_view);
//        this.myLocationInputView.setText("My Location Input View");
//
//        this.myLocationOutputView = (MyLocationOutputView) findViewById(R.id.my_location_output_view);
//        this.myLocationOutputView.setText("My Location Output View");
//
//        this.myPhoneEditText = (MyPhoneEditText) findViewById(R.id.my_phone_edit_text);
//        this.myPhoneEditText.setValue("+972526039309");
//
//        this.myTextView = (MyTextView) findViewById(R.id.my_text_view);
//        this.myTextView.setText("My Text View");
//
//    }
}
