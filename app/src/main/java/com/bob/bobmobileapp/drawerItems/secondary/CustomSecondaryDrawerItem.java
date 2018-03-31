package com.bob.bobmobileapp.drawerItems.secondary;


import com.mikepenz.materialdrawer.holder.ColorHolder;
import com.mikepenz.materialdrawer.model.AbstractBadgeableDrawerItem;

import java.util.List;

public class CustomSecondaryDrawerItem extends AbstractBadgeableDrawerItem<CustomSecondaryDrawerItem> {

  private ColorHolder background;

  public CustomSecondaryDrawerItem withBackgroundColor(int backgroundColor) {
    this.background = ColorHolder.fromColor(backgroundColor);
    return this;
  }

  public CustomSecondaryDrawerItem withBackgroundRes(int backgroundRes) {
    this.background = ColorHolder.fromColorRes(backgroundRes);
    return this;
  }

  @Override
  public void bindView(ViewHolder holder, List payloads) {
    super.bindView(holder, payloads);

    if (background != null) {
      background.applyToBackground(holder.itemView);
    }
  }
}
