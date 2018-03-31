package com.bob.bobmobileapp.tools.UI.views.recyclerviews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.bob.bobmobileapp.tools.UI.views.recyclerviews.viewholders.MyRecyclerViewBaseViewHolder;

import java.util.ArrayList;

/**
 * Created by User on 17/12/2017.
 */

public class MyRecyclerViewBaseAdapter<ViewType extends View> extends RecyclerView.Adapter<MyRecyclerViewBaseViewHolder<ViewType>> {

    protected ArrayList<ViewType> views;
    protected ArrayList<Integer> disabledItemsIndices;

    public MyRecyclerViewBaseAdapter(ArrayList<ViewType> views){

        this.views = views;

    }

    public MyRecyclerViewBaseAdapter(){

        this(new ArrayList<ViewType>());

    }

    @Override
    public MyRecyclerViewBaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        // create a new view
        LinearLayout view = new LinearLayout(parent.getContext());
        return this.createViewHolder(view);

    }

    @Override
    public void onBindViewHolder(MyRecyclerViewBaseViewHolder holder, int position) {

        holder.setView(views.get(position));

    }

    @Override
    public int getItemCount() {

        return views.size();

    }

    protected MyRecyclerViewBaseViewHolder createViewHolder(ViewGroup view) {
        return new MyRecyclerViewBaseViewHolder<ViewType>(view);
    }

    public boolean isViewExist(ViewType viewToFind) {
        for (ViewType view : this.views) {
            if (view == viewToFind) {
                return true;
            }
        }
        return false;
    }

    public void addView(ViewType viewToAdd) {
        this.addView(this.views.size(), viewToAdd);
    }

    public void addView(int index, ViewType viewToAdd) {
        if (!this.isViewExist(viewToAdd)) {
            this.views.add(index, viewToAdd);
        }
        this.notifyDataSetChanged();
    }

    public void addAllViews(ArrayList<ViewType> viewsToAdd) {
        this.addAllViews(this.views.size(), viewsToAdd);
    }

    public void addAllViews(int index, ArrayList<ViewType> viewsToAdd) {
        ArrayList<ViewType> views = new ArrayList<ViewType>();
        for (ViewType view : viewsToAdd) {
            if (!this.isViewExist(view)) {
                views.add(view);
            }
        }
        this.views.addAll(index, viewsToAdd);
        this.notifyDataSetChanged();
    }

    public void removeView(ViewType viewToRemove) {
        if (this.isViewExist(viewToRemove)) {
            this.views.remove(viewToRemove);
        }
        this.notifyDataSetChanged();
    }

    public void removeView(int indexToRemove) {
        this.removeView(this.views.get(indexToRemove));
    }

    public ViewType getView(int index) {
        return this.views.get(index);
    }

    public ArrayList<ViewType> getViews() {
        return this.views;
    }

    private void updateDisableViews() {
        for (int index : this.disabledItemsIndices) {
            if (index < this.views.size()) {
                this.views.get(index).setEnabled(false);
            }
        }

    }

    public void setItemEnable(int index, boolean enable) {
        if ((!enable) && (!this.disabledItemsIndices.contains(index)) && (index < this.views.size())) {
            this.disabledItemsIndices.add(index);
        }
        this.updateDisableViews();
        this.notifyDataSetChanged();
    }

    public void setItemEnable(RadioButton item, boolean enable) {
        if (this.views.contains(item)) {
            this.setItemEnable(this.views.indexOf(item), enable);
        }
    }


}
