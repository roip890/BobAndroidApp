package com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews;

import android.content.Context;
import android.support.constraint.solver.ArrayLinkedVariables;
import android.util.AttributeSet;
import android.view.View;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by User on 22/11/2017.
 */

public class MyTextViewMultiChoiceDialog extends MyTextViewListDialog {


    public MyTextViewMultiChoiceDialog(Context context) {
        this(context, null);
    }

    public MyTextViewMultiChoiceDialog(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyTextViewMultiChoiceDialog(Context context, AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    protected void setItemsCallback() {
        this.dialogBuilder.itemsCallbackMultiChoice(new Integer[0], new MaterialDialog.ListCallbackMultiChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, Integer[] which, CharSequence[] text) {
                MyTextViewMultiChoiceDialog.this.selectedItems.clear();
                MyTextViewMultiChoiceDialog.this.selectedItems.addAll(Arrays.asList(which));
                updateText();
                return false;
            }
        });
    }
}
