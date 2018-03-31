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

public class DemoCounterLeaveMessage {

    private long parentId;
    private MenuNode leaveMessageMenuNode;
    private FormItem leaveMessageMessageEditText;
    private static final String LEAVE_MESSAGE_TITLE = "Leave Message";
    private static final String LEAVE_MESSAGE_IMAGE_URL = "IMAGE_URL";


    public DemoCounterLeaveMessage(long parentId) {
        this.parentId = parentId;
        this.addLeaveMessageMessageMenuNode();
        this.addLeaveMessageMessageEditText();
    }

    private void addLeaveMessageMessageMenuNode() {

        //menu node
//        this.leaveMessageMenuNode = RealmHelper.get().makeNewMenuNode(DemoHelper.DEMO_HOTEL_ID,
//                DemoHelper.get().getFreeMenuNodeId(), this.parentId, LEAVE_MESSAGE_TITLE,
//                LEAVE_MESSAGE_IMAGE_URL, true);

        RealmController.with(BOBApplication.get()).insertMenuNode(this.leaveMessageMenuNode);

    }

    private void addLeaveMessageMessageEditText() {

        //edit text
        this.leaveMessageMessageEditText = RealmHelper.get().makeNewFormItem(DemoHelper.DEMO_HOTEL_ID,
                DemoHelper.get().getFreeFormItemId(), this.leaveMessageMenuNode.getId() ,"edit_text");

        RealmController.with(BOBApplication.get()).insertFormItem(leaveMessageMessageEditText);

        //edit text text
        FormItemProperty leaveMessageMessageEditTextText = RealmHelper.get().makeNewFormItemProperty(
                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
                this.leaveMessageMessageEditText.getId() ,"text", "Example Message");

        RealmController.with(BOBApplication.get()).insertFormItemProperty(leaveMessageMessageEditTextText);

        //edit text text
        FormItemProperty leaveMessageMessageEditTextTitle = RealmHelper.get().makeNewFormItemProperty(
                DemoHelper.DEMO_HOTEL_ID, DemoHelper.get().getFreeFormItemPropertyId(),
                this.leaveMessageMessageEditText.getId() ,"title_text", "Leave Message");

        RealmController.with(BOBApplication.get()).insertFormItemProperty(leaveMessageMessageEditTextTitle);

    }

    public MenuNode getLeaveMessageMenuNode() {
        return leaveMessageMenuNode;
    }

    public void setLeaveMessageMenuNode(MenuNode leaveMessageMenuNode) {
        this.leaveMessageMenuNode = leaveMessageMenuNode;
    }

    public FormItem getLeaveMessageMessageEditText() {
        return leaveMessageMessageEditText;
    }

    public void setLeaveMessageMessageEditText(FormItem leaveMessageMessageEditText) {
        this.leaveMessageMessageEditText = leaveMessageMessageEditText;
    }
}
