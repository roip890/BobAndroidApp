package com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.ArrayList;

/**
 * Created by User on 22/11/2017.
 */

public class MyTextViewListDialog extends MyTextView {

    protected ArrayList<String> items;
    protected ArrayList<Integer> selectedItems, disabledItemsIndices;
    protected int itemsColor;
    protected GravityEnum itemsGravity;

    public MyTextViewListDialog(Context context) {
        this(context, null);
    }

    public MyTextViewListDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextViewListDialog(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.setItems(new ArrayList<String>());
        this.setDisabledItems(new ArrayList<Integer>());
        this.setSelectedItems(new ArrayList<Integer>());
        this.setItemsColor(ContextCompat.getColor(this.getContext(), R.color.textColorPrimary));
        this.setItemsGravity(finals.dialogGravity.get("start"));
        this.setTitleText("Select Items:");
        setItemsCallback();
    }

    protected void updateText() {
        ArrayList<String> selectedItems = new ArrayList<>();
        for (Integer index : this.selectedItems) {
            selectedItems.add(this.items.get(index));
        }
        textView.setText(TextUtils.join(", ", selectedItems));
    }

    public void setItems(ArrayList<String> items) {
        this.items = items;
        this.dialogBuilder.items(this.items);
        this.setDisabledItems(new ArrayList<Integer>());
    }

    public void setItemsColor(int itemsColor) {
        this.itemsColor = itemsColor;
        this.dialogBuilder.itemsColor(this.itemsColor);
    }

    public void setItemsGravity(GravityEnum itemsGravity) {
        this.itemsGravity = itemsGravity;
        this.dialogBuilder.itemsGravity(this.itemsGravity);
    }

    public void setDisabledItems(ArrayList<Integer> disabledItemsIndices) {
        this.disabledItemsIndices = disabledItemsIndices;
        this.dialogBuilder.itemsDisabledIndices((Integer[])this.disabledItemsIndices.toArray(new Integer[disabledItemsIndices.size()]));
    }

    public void setSelectedItems(ArrayList<Integer> selectedItems) {
        ArrayList<Integer> tempSelectedItems = new ArrayList<>();
        for (Integer index : selectedItems) {
            if ((index >= 0) && (index < this.items.size()) && (!this.selectedItems.contains(index))) {
                tempSelectedItems.add(index);
            }
        }
        this.selectedItems = tempSelectedItems;
        updateText();
    }

    public void addItem(String item) {
        if (!this.items.contains(item)) {
            this.items.add(item);
        }
    }

    public void addItem(String item, boolean disabled) {
        this.items.add(item);
        if ((disabled) && (!this.disabledItemsIndices.contains(this.items.indexOf(item)))) {
            this.disabledItemsIndices.add(this.items.indexOf(item));
        }
    }

    public void removeItem(String item) {
        if (this.disabledItemsIndices.contains(item)) {
            int itemIndex = this.disabledItemsIndices.indexOf(item);
            ArrayList<Integer> tempDisabled = new ArrayList<Integer>();
            for (Integer disabledIndex : this.disabledItemsIndices) {
                if (disabledIndex < itemIndex) {
                    tempDisabled.add(itemIndex);
                } else if (disabledIndex > itemIndex) {
                    tempDisabled.add(itemIndex+1);
                }
            }
            this.setDisabledItems(tempDisabled);
        }
    }

    public void setItemEnable(int index, boolean enable) {
        if ((!enable) && (!this.disabledItemsIndices.contains(index)) && (index < this.items.size())) {
            this.disabledItemsIndices.add(index);
        }
    }

    public void setItemEnable(String item, boolean enable) {
        if (this.items.contains(item)) {
            this.setItemEnable(this.items.indexOf(item), enable);
        }
    }

    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallback(new MaterialDialog.ListCallback() {
            @Override
            public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                MyTextViewListDialog.this.selectedItems.clear();
                MyTextViewListDialog.this.selectedItems.add(position);
                updateText();
            }
        });
    }
}
