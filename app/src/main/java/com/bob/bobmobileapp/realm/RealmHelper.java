package com.bob.bobmobileapp.realm;

import com.bob.bobmobileapp.http.beans.MnLeaf;
import com.bob.bobmobileapp.http.beans.MnLeafProp;
import com.bob.bobmobileapp.http.beans.MnNode;
import com.bob.bobmobileapp.http.beans.MnNodeProp;
import com.bob.bobmobileapp.realm.objects.FormItem;
import com.bob.bobmobileapp.realm.objects.FormItemProperty;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;

/**
 * Created by User on 02/02/2018.
 */

public class RealmHelper {

    private static RealmHelper instance;

    public static RealmHelper get() {
        if (instance == null) {
            instance = new RealmHelper();
        }
        return instance;
    }

    public MenuNode makeNewMenuNode(long hotelId, long id, long parentId, boolean isForm) {
        MenuNode menuNode = new MenuNode();
        menuNode.setHotelId(hotelId);
        menuNode.setId(id);
        menuNode .setParentId(parentId);
        return menuNode;
    }

    public FormItem makeNewFormItem(long hotelId, long id, long parentId, String type) {
        FormItem formItem = new FormItem();
        formItem.setHotelId(hotelId);
        formItem.setId(id);
        formItem.setParentId(parentId);
        formItem.setType(type);
        return formItem;
    }

    public FormItemProperty makeNewFormItemProperty(long hotelId, long id, long parentId, String key, String value) {
        FormItemProperty formItemProperty = new FormItemProperty();
        formItemProperty.setHotelId(hotelId);
        formItemProperty.setId(id);
        formItemProperty.setParentId(parentId);
        formItemProperty.setKey(key);
        formItemProperty.setValue(value);
        return formItemProperty;
    }

    public MenuNodeProperty makeNewMenuNodeProperty(long hotelId, long id, long parentId, String key, String value) {
        MenuNodeProperty menuNodeProperty = new MenuNodeProperty();
        menuNodeProperty.setHotelId(hotelId);
        menuNodeProperty.setId(id);
        menuNodeProperty.setParentId(parentId);
        menuNodeProperty.setKey(key);
        menuNodeProperty.setValue(value);
        return menuNodeProperty;
    }

    public MenuNode menuNodeBeanToRealm(MnNode menuNodeBean) {
        if (menuNodeBean == null) {
            return null;
        } else {
            MenuNode menuNode = new MenuNode();
            menuNode.setId(menuNodeBean.getNodeId());
            menuNode.setParentId(menuNodeBean.getNodeParentId());
            menuNode.setHotelId(menuNodeBean.getHotelId());
            if (menuNodeBean.getNodeType().toLowerCase().equals("form")) {
                menuNode.setLeaf(true);
            } else if (menuNodeBean.getNodeType().toLowerCase().equals("menu")) {
                menuNode.setLeaf(false);
            }
            if ((menuNode.getId() == 0) && (menuNode.getParentId() == 0)) {
                menuNode.setParentId(-1L);
            }
            return menuNode;
        }
    }

    public FormItem formItemBeanToRealm(MnLeaf formItemBean) {
        if (formItemBean == null) {
            return null;
        } else {
            FormItem formItem = new FormItem();
            formItem.setId(formItemBean.getLeafId());
            formItem.setParentId(formItemBean.getLeafParentId());
            formItem.setType(formItemBean.getLeafType());
            formItem.setHotelId(formItemBean.getHotelId());
            return formItem;
        }
    }

    public FormItemProperty formItemPropertyBeanToRealm(MnLeafProp formItemPropertyBean) {
        if (formItemPropertyBean == null) {
            return null;
        } else {
            FormItemProperty formItemProperty = new FormItemProperty();
            formItemProperty.setHotelId(formItemPropertyBean.getHotelId());
            formItemProperty.setId(formItemPropertyBean.getLeafStyleId());
            formItemProperty.setParentId(formItemPropertyBean.getLeafParentId());
            formItemProperty.setValue((formItemPropertyBean.getValueProp()));
            formItemProperty.setKey(formItemPropertyBean.getKeyProp());
            return formItemProperty;
        }
    }

    public MenuNodeProperty menuNodePropertyBeanToRealm(MnNodeProp menuNodePropertyBean) {
        if (menuNodePropertyBean == null) {
            return null;
        } else {
            MenuNodeProperty menuNodeProperty = new MenuNodeProperty();
            menuNodeProperty.setHotelId(menuNodePropertyBean.getHotelId());
            menuNodeProperty.setId(menuNodePropertyBean.getNodeStyleId());
            menuNodeProperty.setParentId(menuNodePropertyBean.getNodeStyleParentId());
            menuNodeProperty.setValue((menuNodePropertyBean.getValueProp()));
            menuNodeProperty.setKey(menuNodePropertyBean.getKeyProp());
            return menuNodeProperty;
        }
    }

    public MnNode menuNodeRealmToBean(MenuNode menuNode) {
        if (menuNode == null) {
            return null;
        } else {
            MnNode menuNodeBean = new MnNode();
            menuNodeBean.setNodeId(menuNode.getId());
            menuNodeBean.setNodeParentId(menuNode.getParentId());
            menuNodeBean.setHotelId(menuNode.getHotelId());
            if (menuNode.isLeaf()) {
                menuNodeBean.setNodeType("form");
            } else {
                menuNodeBean.setNodeType("menu");
            }
            return menuNodeBean;
        }
    }

    public MnLeaf formItemRealmToBean(FormItem formItem) {
        if (formItem == null) {
            return null;
        } else {
            MnLeaf formItemBean = new MnLeaf();
            formItemBean.setLeafId(formItem.getId());
            formItemBean.setLeafParentId(formItem.getParentId());
            formItemBean.setLeafType(formItem.getType());
            formItemBean.setHotelId(formItem.getHotelId());
            return formItemBean;
        }
    }

    public MnLeafProp formItemPropertyRealmToBean(FormItemProperty formItemProperty) {
        if (formItemProperty == null) {
            return null;
        } else {
            MnLeafProp formItemPropertyBean = new MnLeafProp();
            formItemPropertyBean.setHotelId(formItemProperty.getHotelId());
            formItemPropertyBean.setLeafStyleId(formItemProperty.getId());
            formItemPropertyBean.setLeafParentId(formItemProperty.getParentId());
            formItemPropertyBean.setValueProp((formItemProperty.getValue()));
            formItemPropertyBean.setKeyProp(formItemProperty.getKey());
            return formItemPropertyBean;
        }
    }

    public MnNodeProp menuNodePropertyRealmToBean(MenuNodeProperty menuNodeProperty) {
        if (menuNodeProperty == null) {
            return null;
        } else {
            MnNodeProp menuNodePropertyBean = new MnNodeProp();
            menuNodePropertyBean.setHotelId(menuNodeProperty.getHotelId());
            menuNodePropertyBean.setNodeStyleId(menuNodeProperty.getId());
            menuNodePropertyBean.setNodeStyleParentId(menuNodeProperty.getParentId());
            menuNodePropertyBean.setValueProp((menuNodeProperty.getValue()));
            menuNodePropertyBean.setKeyProp(menuNodeProperty.getKey());
            return menuNodePropertyBean;
        }
    }
}
