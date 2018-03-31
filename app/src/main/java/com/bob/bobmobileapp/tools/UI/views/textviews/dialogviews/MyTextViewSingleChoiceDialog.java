package com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;

import java.util.ArrayList;

/**
 * Created by User on 22/11/2017.
 */

public class MyTextViewSingleChoiceDialog extends MyTextViewListDialog {


    public MyTextViewSingleChoiceDialog(Context context) {
        this(context, null);
    }

    public MyTextViewSingleChoiceDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextViewSingleChoiceDialog(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallbackSingleChoice(0, new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                MyTextViewSingleChoiceDialog.this.selectedItems.clear();
                MyTextViewSingleChoiceDialog.this.selectedItems.add(which);
                updateText();
                return false;
            }
        });
    }
}
