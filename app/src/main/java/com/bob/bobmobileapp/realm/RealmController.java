package com.bob.bobmobileapp.realm;



import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;

import java.util.Collection;

import io.realm.Realm;
import io.realm.RealmResults;


public class RealmController {

    private static RealmController instance;
    private final Realm realm;

    public RealmController(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static RealmController with(Fragment fragment) {
        if (instance == null) {
            instance = new RealmController(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static RealmController with(Activity activity) {
        if (instance == null) {
            instance = new RealmController(activity.getApplication());
        }
        return instance;
    }

    public static RealmController with(Application application) {
        if (instance == null) {
            instance = new RealmController(application);
        }
        return instance;
    }

    public static RealmController get() {
        return instance;
    }

    public Realm getRealm() {
        return realm;
    }

    //Refresh the realm istance
    public void refresh() {
        realm.refresh();
    }

    //clear all objects
    public void clearAll() {
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
    }

    //menu node
    public RealmResults<MenuNode> getMenuNodes() {
        return realm.where(MenuNode.class).findAll();
    }

    public MenuNode getMenuNodeById(long menuNodeId) {
        return realm.where(MenuNode.class).equalTo("id", menuNodeId).findFirst();
    }

    public void insertMenuNode(MenuNode menuNode) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(menuNode);
        realm.commitTransaction();
    }

    public void insertMenuNodes(Collection<MenuNode> menuNodes) {
        for (MenuNode menuNode:menuNodes) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(menuNode);
            realm.commitTransaction();
        }
    }

    public void insertOrUpdateMenuNode(MenuNode menuNode) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(menuNode);
        realm.commitTransaction();
    }

    public void insertOrUpdateMenuNodes(Collection<MenuNode> menuNodes) {
        for (MenuNode menuNode:menuNodes) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(menuNode);
            realm.commitTransaction();
        }
    }

    public void deleteMenuNodeById(long menuNodeId) {
        realm.where(MenuNode.class).equalTo("id", menuNodeId).findAll().deleteAllFromRealm();
    }

    public void deleteAllMenuNodes() {
        realm.beginTransaction();
        realm.delete(MenuNode.class);
        realm.commitTransaction();
    }

    public RealmResults<MenuNode> getSubMenuNodes(long menuNodeId) {
        return realm.where(MenuNode.class).equalTo("parentId", menuNodeId).findAll();
    }


    //form item
    public RealmResults<FormItem> getFormItems() {
        return realm.where(FormItem.class).findAll();
    }

    public FormItem getFormItemById(long formItemId) {
        return realm.where(FormItem.class).equalTo("id", formItemId).findFirst();
    }

    public void insertFormItem(FormItem formItem) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(formItem);
        realm.commitTransaction();
    }

    public void insertFormItems(Collection<FormItem> formItems) {
        for (FormItem formItem:formItems) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(formItem);
            realm.commitTransaction();
        }
    }

    public void insertOrUpdateFormItem(FormItem formItem) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(formItem);
        realm.commitTransaction();
    }

    public void insertOrUpdateFormItems(Collection<FormItem> formItems) {
        for (FormItem formItem:formItems) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(formItem);
            realm.commitTransaction();
        }
    }

    public void deleteFormItemById(long formItemId) {
        realm.where(FormItem.class).equalTo("id", formItemId).findAll().deleteAllFromRealm();
    }

    public void deleteAllFormItems() {
        realm.beginTransaction();
        realm.delete(FormItem.class);
        realm.commitTransaction();
    }

    public RealmResults<FormItem> getFormItemsOfMenuNode(long menuNodeId) {
        return realm.where(FormItem.class).equalTo("parentId", menuNodeId).findAll();
    }

    //form item property
    public RealmResults<FormItemProperty> getFormItemProperty() {
        return realm.where(FormItemProperty.class).findAll();
    }

    public FormItemProperty getFormItemPropertyById(long formItemPropertyId) {
        return realm.where(FormItemProperty.class).equalTo("id", formItemPropertyId).findFirst();
    }

    public void insertFormItemProperty(FormItemProperty formItemProperty) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(formItemProperty);
        realm.commitTransaction();
    }

    public void insertFormItemProperties(Collection<FormItemProperty> formItemProperties) {
        for (FormItemProperty formItemProperty:formItemProperties) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(formItemProperty);
            realm.commitTransaction();
        }
    }

    public void insertOrUpdateFormItemProperty(FormItemProperty formItemProperty) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(formItemProperty);
        realm.commitTransaction();
    }

    public void insertOrUpdateformItemProperties(Collection<FormItemProperty> formItemProperties) {
        for (FormItemProperty formItemProperty:formItemProperties) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(formItemProperty);
            realm.commitTransaction();
        }
    }

    public void deleteFormItemPropertyById(long formItemPropertyId) {
        realm.where(FormItemProperty.class).equalTo("id", formItemPropertyId).findAll().deleteAllFromRealm();
    }

    public void deleteAllFormItemProperties() {
        realm.beginTransaction();
        realm.delete(FormItemProperty.class);
        realm.commitTransaction();
    }

    public RealmResults<FormItemProperty> getPropertiesOfFormItem(long formItemPropertyId) {
        return realm.where(FormItemProperty.class).equalTo("parentId", formItemPropertyId).findAll();
    }

    //menu node property
    public RealmResults<MenuNodeProperty> getMenuNodeProperty() {
        return realm.where(MenuNodeProperty.class).findAll();
    }

    public MenuNodeProperty getMenuNodePropertyById(long menuNodePropertyId) {
        return realm.where(MenuNodeProperty.class).equalTo("id", menuNodePropertyId).findFirst();
    }

    public void insertMenuNodeProperty(MenuNodeProperty menuNodeProperty) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(menuNodeProperty);
        realm.commitTransaction();
    }

    public void insertMenuNodeProperties(Collection<MenuNodeProperty> menuNodeProperties) {
        for (MenuNodeProperty menuNodeProperty:menuNodeProperties) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(menuNodeProperty);
            realm.commitTransaction();
        }
    }

    public void insertOrUpdateMenuNodeProperty(MenuNodeProperty menuNodeProperty) {
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(menuNodeProperty);
        realm.commitTransaction();
    }

    public void insertOrUpdatemenuNodeProperties(Collection<MenuNodeProperty> menuNodeProperties) {
        for (MenuNodeProperty menuNodeProperty:menuNodeProperties) {
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(menuNodeProperty);
            realm.commitTransaction();
        }
    }

    public void deleteMenuNodePropertyById(long menuNodePropertyId) {
        realm.where(MenuNodeProperty.class).equalTo("id", menuNodePropertyId).findAll().deleteAllFromRealm();
    }

    public void deleteAllMenuNodeProperties() {
        realm.beginTransaction();
        realm.delete(MenuNodeProperty.class);
        realm.commitTransaction();
    }

    public RealmResults<MenuNodeProperty> getPropertiesOfMenuNode(long menuNodePropertyId) {
        return realm.where(MenuNodeProperty.class).equalTo("parentId", menuNodePropertyId).findAll();
    }


}