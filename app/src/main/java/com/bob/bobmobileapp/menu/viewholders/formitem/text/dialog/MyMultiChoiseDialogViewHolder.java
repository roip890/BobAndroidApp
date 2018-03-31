package com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog;

import android.content.Context;
import android.view.View;

import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.tools.UI.views.textviews.MyTextView;
import com.bob.bobmobileapp.tools.UI.views.textviews.dialogviews.MyTextViewMultiChoiceDialog;

/**
 * Created by user on 17/09/2017.
 */

public class MyMultiChoiseDialogViewHolder extends MyListDialogViewHolder {

    public MyMultiChoiseDialogViewHolder(Context context, View view) {
        super(context, view);
    }

    @Override
    protected void initView(View view) {
        this.setTextView((MyTextView) view.findViewById(R.id.my_multi_choice_dialog_text_view));
    }

    @Override
    public void setTextView(MyTextView textView) {
        this.textView = textView;
        this.listDialogTextView = (MyTextViewMultiChoiceDialog) textView;
    }

}
