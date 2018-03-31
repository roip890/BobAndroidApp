package com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyTextViewViewHolder;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTextViewListDialog;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by user on 17/09/2017.
 */

public class MyListDialogViewHolder extends MyTextViewViewHolder {

    protected MyTextViewListDialog listDialogTextView;

    public MyListDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTextView) view.findViewById(R.id.my_list_dialog_text_view));
    }

    @Override
    public void setTextView(MyTextView textView) {
        this.textView = textView;
        this.listDialogTextView = (MyTextViewListDialog) textView;
    }

    @Override
    protected void initialize() {
        super.initialize();
        this.listDialogTextView.setItems(new ArrayList<String>());
        this.listDialogTextView.setSelectedItems(new ArrayList<Integer>());
        this.listDialogTextView.setItemsColor(ContextCompat.getColor(context, R.color.colorPrimary));
        this.listDialogTextView.setItemsGravity(finals.dialogGravity.get("start"));
        this.listDialogTextView.setDisabledItems(new ArrayList<Integer>());
    }

    protected void updateProperties(HashMap<String, String> properties) {
        super.updateProperties(properties);
        String curProperty;
        if ((curProperty = properties.get("dialog_items")) != null) {
            this.listDialogTextView.setItems(new ArrayList<String>(Arrays.asList(new Gson().fromJson(curProperty, String[].class))));
        }
        if ((curProperty = properties.get("dialog_selected_items")) != null) {
            this.listDialogTextView.setSelectedItems(new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class))));
        }
        if ((curProperty = properties.get("dialog_items_color")) != null) {
            try {
                this.listDialogTextView.setItemsColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("dialog_items_gravity")) != null) {
            this.listDialogTextView.setItemsGravity(finals.dialogGravity.get(curProperty));
        }
        if ((curProperty = properties.get("dialog_disabled_items")) != null) {
            this.listDialogTextView.setDisabledItems(new ArrayList<Integer>(Arrays.asList(new Gson().fromJson(curProperty, Integer[].class))));
        }
    }
}
