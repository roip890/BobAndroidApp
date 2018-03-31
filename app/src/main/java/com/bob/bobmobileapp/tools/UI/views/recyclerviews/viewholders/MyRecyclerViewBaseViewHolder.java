package com.bob.bobmobileapp.tools.UI.views.recyclerviews.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by User on 22/12/2017.
 */

public class MyRecyclerViewBaseViewHolder<ViewType> extends RecyclerView.ViewHolder{

    private LinearLayout linearLayout;
    private ViewType view;

    public MyRecyclerViewBaseViewHolder(ViewGroup itemView) {
        super(itemView);
        linearLayout = (LinearLayout) itemView;
    }

    public void setView(ViewType view) {
        this.linearLayout.removeAllViews();
        this.linearLayout.addView((View)view);
        this.view = view;
    }

    public ViewType getTextView() {
        return this.view;
    }

}
