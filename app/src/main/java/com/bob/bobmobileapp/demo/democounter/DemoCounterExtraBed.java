package com.bob.bobmobileapp.demo.democounter;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.demo.DemoHelper;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.RealmHelper;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;

/**
 * Created by User on 19/02/2018.
 */

public class DemoCounterExtraBed {

    private long parentId;
    private MenuNode extraBedMenuNode;
    private FormItem extraBedDateView, extraBedTimeView;
    private static final String EXTRA_BED_TITLE = "Extra Bed";
    private static final String EXTRA_BED_IMAGE_URL = "IMAGE_URL";


    public DemoCounterExtraBed(long parentId) {
        this.parentId = parentId;
        this.addExtraBedMenuNode();
        this.addExtraBedDeliveryTimeView();
    }

    private void addExtraBedMenuNode() {

        //menu node
//        this.extraBedMenuNode = RealmHelper.get().makeNewMenuNode(DemoHelper.DEMO_HOTEL_ID,
//                DemoHelper.get().getFreeMenuNodeId(), this.parentId, EXTRA_BED_TITLE,
//                EXTRA_BED_IMAGE_URL, true);

        RealmController.with(BOBApplication.get()).insertMenuNode(this.extraBedMenuNode);

    }

    private void addExtraBedDeliveryDateView() {

        //date view
        this.extraBedDateView = RealmHelper.get().makeNewFormItem(DemoHelper.DEMO_HOTEL_ID,
                DemoHelper.get().getFreeFormItemId(), this.extraBedMenuNode.getId() ,"date_view");

        RealmController.with(BOBApplication.get()).insertFormItem(this.extraBedDateView);

        //edit text text
//        FormItemProperty extraBedMessageEditTextText = RealmHelper.get().makeNewFormItemProperty(
//                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
//                this.extraBedDateView.getId() ,"text", "Date of delivery");
//
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(extraBedMessageEditTextText);

        //date view title
        FormItemProperty extraBedMessageEditTextTitle = RealmHelper.get().makeNewFormItemProperty(
                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
                this.extraBedDateView.getId() ,"title_text", "Date of delivery");

        RealmController.with(BOBApplication.get()).insertFormItemProperty(extraBedMessageEditTextTitle);

    }

    private void addExtraBedDeliveryTimeView() {

        //date view
        this.extraBedTimeView = RealmHelper.get().makeNewFormItem(DemoHelper.DEMO_HOTEL_ID,
                DemoHelper.get().getFreeFormItemId(), this.extraBedMenuNode.getId() ,"text_view");

        RealmController.with(BOBApplication.get()).insertFormItem(this.extraBedTimeView);

        //edit text text
//        FormItemProperty extraBedMessageEditTextText = RealmHelper.get().makeNewFormItemProperty(
//                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
//                this.extraBedDateView.getId() ,"text", "Date of delivery");
//
//        RealmController.with(BOBApplication.get()).insertFormItemProperty(extraBedMessageEditTextText);

        //date view title
        FormItemProperty extraBedMessageEditTextTitle = RealmHelper.get().makeNewFormItemProperty(
                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
                this.extraBedTimeView.getId() ,"title_text", "Time of delivery");

        RealmController.with(BOBApplication.get()).insertFormItemProperty(extraBedMessageEditTextTitle);

    }

    public MenuNode getExtraBedMenuNode() {
        return extraBedMenuNode;
    }

    public void setExtraBedMenuNode(MenuNode extraBedMenuNode) {
        this.extraBedMenuNode = extraBedMenuNode;
    }

    public FormItem getExtraBedDateView() {
        return extraBedDateView;
    }

    public void setExtraBedDateView(FormItem extraBedDateView) {
        this.extraBedDateView = extraBedDateView;
    }
}
