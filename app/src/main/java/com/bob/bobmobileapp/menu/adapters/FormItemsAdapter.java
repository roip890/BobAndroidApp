package com.bob.bobmobileapp.menu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.formitem.base.MyViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.media.MyImageViewViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.media.MyVideoViewViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyButtonViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyEditTextViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyLocationOutputViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyPhoneEditTextViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyDateViewViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyListDialogViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyMultiChoiseDialogViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MySingleChoiseDialogViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.dialog.MyTimeViewViewHolder;
import com.bob.bobmobileapp.menu.viewholders.formitem.text.MyTextViewViewHolder;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by user on 01/09/2017.
 */

public class FormItemsAdapter extends RecyclerView.Adapter<MyViewHolder> {


    private final int FORM_ITEM_DEFAULT = -1;
    private final int FORM_ITEM_TEXT_VIEW = 0;
    private final int FORM_ITEM_EDIT_TEXT = 1;
    private final int FORM_ITEM_BUTTON = 2;
    private final int FORM_ITEM_DATE_VIEW = 3;
    private final int FORM_ITEM_TIME_VIEW = 4;
    private final int FORM_ITEM_PHONE_VIEW = 5;
    private final int FORM_ITEM_LOCATION_OUTPUT = 6;
    private final int FORM_ITEM_LOCATION_INPUT = 7;
    private final int FORM_ITEM_IMAGE_VIEW = 8;
    private final int FORM_ITEM_VIDEO_VIEW = 9;
    private final int FORM_ITEM_TEXT_LIST_VIEW = 10;
    private final int FORM_ITEM_SINGLE_CHOICE = 11;
    private final int FORM_ITEM_MULTI_CHOICE = 12;


    // The items to display in your RecyclerView
    private long parentMenuNodeId;
    private ArrayList<FormItem> formItems;
    private Context context;

    // Provide a suitable constructor (depends on the kind of dataset)
    public FormItemsAdapter(Context context, long parentMenuNodeId) {
        this.context = context;
        this.parentMenuNodeId = parentMenuNodeId;
        this.formItems = new ArrayList<FormItem>();
        this.setFormItems(RealmController.get().getFormItemsOfMenuNode(this.parentMenuNodeId));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.formItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        if (formItems.get(position).getType().equals("text_view")) {
            return FORM_ITEM_TEXT_VIEW;
        } else if (formItems.get(position).getType().equals("edit_text")) {
            return FORM_ITEM_EDIT_TEXT;
        } else if (formItems.get(position).getType().equals("button")) {
            return FORM_ITEM_BUTTON;
        } else if (formItems.get(position).getType().equals("date_view")) {
            return FORM_ITEM_DATE_VIEW;
        } else if (formItems.get(position).getType().equals("time_view")) {
            return FORM_ITEM_TIME_VIEW;
        } else if (formItems.get(position).getType().equals("phone_view")) {
            return FORM_ITEM_PHONE_VIEW;
        } else if (formItems.get(position).getType().equals("location_output_view")) {
            return FORM_ITEM_LOCATION_OUTPUT;
        } else if (formItems.get(position).getType().equals("location_input_view")) {
            return FORM_ITEM_LOCATION_INPUT;
        } else if (formItems.get(position).getType().equals("image_view")) {
            return FORM_ITEM_IMAGE_VIEW;
        } else if (formItems.get(position).getType().equals("video_view")) {
            return FORM_ITEM_VIDEO_VIEW;
        } else if (formItems.get(position).getType().equals("text_list_view")) {
            return FORM_ITEM_TEXT_LIST_VIEW;
        } else if (formItems.get(position).getType().equals("single_choice_view")) {
            return FORM_ITEM_SINGLE_CHOICE;
        } else if (formItems.get(position).getType().equals("multi_choice_view")) {
            return FORM_ITEM_MULTI_CHOICE;
        } else if (formItems.get(position).getType().equals("EditText")) {
            return FORM_ITEM_EDIT_TEXT;
        } else if (formItems.get(position).getType().equals("TextView")) {
            return FORM_ITEM_TEXT_VIEW;
        } else if (formItems.get(position).getType().equals("Time TextView")) {
            return FORM_ITEM_TIME_VIEW;
        } else if (formItems.get(position).getType().equals("Date TextView")) {
            return FORM_ITEM_DATE_VIEW;
        } else if (formItems.get(position).getType().equals("Button")) {
            return FORM_ITEM_BUTTON;
        } else {
            return FORM_ITEM_DEFAULT;
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case FORM_ITEM_TEXT_VIEW:
                return new MyTextViewViewHolder(context, inflater.inflate(R.layout.form_item_text_view_layout, viewGroup, false));
            case FORM_ITEM_EDIT_TEXT:
                return new MyEditTextViewHolder(context, inflater.inflate(R.layout.form_item_edit_text_layout, viewGroup, false));
            case FORM_ITEM_BUTTON:
                return new MyButtonViewHolder(context, inflater.inflate(R.layout.form_item_button_layout, viewGroup, false));
            case FORM_ITEM_DATE_VIEW:
                return new MyDateViewViewHolder(context, inflater.inflate(R.layout.form_item_date_text_view_layout, viewGroup, false));
            case FORM_ITEM_TIME_VIEW:
                return new MyTimeViewViewHolder(context, inflater.inflate(R.layout.form_item_time_text_view_layout, viewGroup, false));
            case FORM_ITEM_PHONE_VIEW:
                return new MyPhoneEditTextViewHolder(context, inflater.inflate(R.layout.form_item_phone_edit_text_layout, viewGroup, false));
            case FORM_ITEM_LOCATION_OUTPUT:
                return new MyLocationOutputViewHolder(context, inflater.inflate(R.layout.form_item_location_output_view_layout, viewGroup, false));
            case FORM_ITEM_LOCATION_INPUT:
                return new MyLocationOutputViewHolder(context, inflater.inflate(R.layout.form_item_location_input_view_layout, viewGroup, false));
            case FORM_ITEM_IMAGE_VIEW:
                return new MyImageViewViewHolder(context, inflater.inflate(R.layout.form_item_image_layout, viewGroup, false));
            case FORM_ITEM_VIDEO_VIEW:
                return new MyVideoViewViewHolder(context, inflater.inflate(R.layout.form_item_video_layout, viewGroup, false));
            case FORM_ITEM_TEXT_LIST_VIEW:
                return new MyListDialogViewHolder(context, inflater.inflate(R.layout.form_item_list_dialog_text_view_layout, viewGroup, false));
            case FORM_ITEM_SINGLE_CHOICE:
                return new MySingleChoiseDialogViewHolder(context, inflater.inflate(R.layout.form_item_single_choise_dialog_text_view_layout, viewGroup, false));
            case FORM_ITEM_MULTI_CHOICE:
                return new MyMultiChoiseDialogViewHolder(context, inflater.inflate(R.layout.form_item_multi_choise_dialog_text_view_layout, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        FormItem formItem = this.formItems.get(position);
        viewHolder.configureFormItem(formItem);
    }

    public void setFormItems(List<FormItem> formItems) {
        if (formItems != null) {
            this.formItems.clear();
            this.formItems.addAll(formItems);
        }
    }

    public void setParentMenuNode(long parentMenuNodeId) {
        this.parentMenuNodeId = parentMenuNodeId;
        this.setFormItems(RealmController.with(BOBApplication.get()).getFormItemsOfMenuNode(parentMenuNodeId));
        this.notifyDataSetChanged();
    }

}
