package com.bob.bobmobileapp.drawerItems.secondary;

/**
 * Created by user on 18/08/2017.
 */

import com.bob.bobmobileapp.R;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;

public class CustomCenteredSecondaryDrawerItem extends SecondaryDrawerItem {

    @Override
    public int getLayoutRes() {
        return R.layout.material_drawer_item_secondary_centered;
    }

    @Override
    public int getType() {
        return R.id.material_drawer_item_centered_secondary;
    }

}