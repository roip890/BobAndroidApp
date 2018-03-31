package com.bob.bobmobileapp.demo;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;

import java.util.ArrayList;
import java.util.Collections;

import io.realm.RealmResults;

/**
 * Created by User on 19/02/2018.
 */

public class DemoHelper {

    public static final long DEMO_HOTEL_ID = -2L;

    private static DemoHelper instance;
    private ArrayList<Long> menuNodesIds, menuNodePopertiesIds,
            formItemsIds, formItemPropertiesIds;

    public static DemoHelper get() {
        if (instance == null) {
            instance = new DemoHelper();
        }
        return instance;
    }

    private DemoHelper() {
        this.prepareDemo();
        this.menuNodesIds = new ArrayList<Long>();
        this.menuNodePopertiesIds = new ArrayList<Long>();
        this.formItemsIds = new ArrayList<Long>();
        this.formItemPropertiesIds = new ArrayList<Long>();
    }

    private void prepareDemo() {
        this.clearDemoMenuNodes();
        this.clearDemoMenuNodeProperties();
        this.clearDemoFormItems();
        this.clearDemoFormItemProperties();
    }

    private void clearDemoMenuNodes() {
        RealmResults<MenuNode> menuNodes = RealmController.get().getMenuNodes();
        for (MenuNode menuNode : menuNodes) {
            if ((menuNode != null) && (menuNode.getId() < -1L)) {
                RealmController.with(BOBApplication.get()).deleteMenuNodeById(menuNode.getId());
            }
        }
    }

    private void clearDemoMenuNodeProperties() {
        RealmResults<MenuNodeProperty> menuNodeProperties = RealmController.get().getMenuNodeProperty();
        for (MenuNodeProperty menuNodeProperty : menuNodeProperties) {
            if ((menuNodeProperty != null) && (menuNodeProperty.getId() < -1L)) {
                RealmController.with(BOBApplication.get()).deleteMenuNodePropertyById(menuNodeProperty.getId());
            }
        }
    }

    private void clearDemoFormItems() {
        RealmResults<FormItem> formItems = RealmController.get().getFormItems();
        for (FormItem formItem : formItems) {
            if ((formItem != null) && (formItem.getId() < -1L)) {
                RealmController.with(BOBApplication.get()).deleteFormItemById(formItem.getId());
            }
        }
    }

    private void clearDemoFormItemProperties() {
        RealmResults<FormItemProperty> formItemProperties = RealmController.get().getFormItemProperty();
        for (FormItemProperty formItemProperty : formItemProperties) {
            if ((formItemProperty != null) && (formItemProperty.getId() < -1L)) {
                RealmController.with(BOBApplication.get()).deleteFormItemPropertyById(formItemProperty.getId());
            }
        }
    }

    public void updateDemoIds() {
        this.clearDemoMenuNodes();
        this.clearDemoMenuNodeProperties();
        this.clearDemoFormItems();
        this.clearDemoFormItemProperties();
    }

    private void updateDemoMenuNodesIds() {
        this.menuNodesIds.clear();
        RealmResults<MenuNode> menuNodes = RealmController.get().getMenuNodes();
        for (MenuNode menuNode : menuNodes) {
            if ((menuNode != null) && (menuNode.getId() < -1L)) {
                this.menuNodesIds.add(menuNode.getId());
            }
        }
    }

    private void updateDemoMenuNodePropertiesIds() {
        this.menuNodePopertiesIds.clear();
        RealmResults<MenuNodeProperty> menuNodeProperties = RealmController.get().getMenuNodeProperty();
        for (MenuNodeProperty menuNodeProperty : menuNodeProperties) {
            if ((menuNodeProperty != null) && (menuNodeProperty.getId() < -1L)) {
                this.menuNodePopertiesIds.add(menuNodeProperty.getId());
            }
        }
    }

    private void updateDemoFormItemsIds() {
        this.formItemsIds.clear();
        RealmResults<FormItem> formItems = RealmController.get().getFormItems();
        for (FormItem formItem : formItems) {
            if ((formItem != null) && (formItem.getId() < -1L)) {
                this.formItemsIds.add(formItem.getId());
            }
        }
    }

    private void updateDemoFormItemPropertiesIds() {
        this.formItemPropertiesIds.clear();
        RealmResults<FormItemProperty> formItemProperties = RealmController.get().getFormItemProperty();
        for (FormItemProperty formItemProperty : formItemProperties) {
            if ((formItemProperty != null) && (formItemProperty.getId() < -1L)) {
                this.formItemPropertiesIds.add(formItemProperty.getId());
            }
        }
    }

    private long getFreeId(ArrayList<Long> ids) {
        if (ids.size() == 0){
            ids.add(-2L);
            return -2L;
        } else {
            long minId = Collections.min(ids);
            long maxId = Collections.max(ids);
            if (minId > -2L) {
                ids.add(-2L);
                return -2L;
            } else {
                for (long index = maxId; index > minId; index--) {
                    if (!ids.contains(index)) {
                        ids.add(index);
                        return index;
                    }
                }
                ids.add(minId - 1L);
                return minId - 1L;
            }
        }
    }

    public long getFreeMenuNodeId() {
        return this.getFreeId(this.menuNodesIds);
    }

    public long getFreeMenuNodePropertyId() {
        return this.getFreeId(this.menuNodePopertiesIds);
    }

    public long getFreeFormItemId() {
        return this.getFreeId(this.formItemsIds);
    }

    public long getFreeFormItemPropertyId() {
        return this.getFreeId(this.formItemPropertiesIds);
    }

}
