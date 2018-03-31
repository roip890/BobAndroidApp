package com.bob.bobmobileapp.drawerItems.primary;

/**
 * Created by user on 18/08/2017.
 */

import com.bob.bobmobileapp.R;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;

public class CustomCenteredPrimaryDrawerItem extends PrimaryDrawerItem {

    @Override
    public int getLayoutRes() {
        return R.layout.material_drawer_item_primary_centered;
    }

    @Override
    public int getType() {
        return R.id.material_drawer_item_centered_primary;
    }

}