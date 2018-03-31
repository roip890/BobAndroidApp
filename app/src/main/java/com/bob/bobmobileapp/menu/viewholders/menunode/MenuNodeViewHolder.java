package com.bob.bobmobileapp.menu.viewholders.menunode;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.activities.MainActivity;
import com.bob.bobmobileapp.finals;
import com.bob.bobmobileapp.menu.adapters.MenuNodesAdapter;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.MenuNode;
import com.bob.bobmobileapp.realm.objects.MenuNodeProperty;
import com.bob.bobmobileapp.tools.UI.UIUtilsManager;
import com.bob.bobmobileapp.tools.UI.style.Fonts;
import com.bob.bobmobileapp.tools.UI.style.Icons;
import com.bob.bobmobileapp.tools.UI.views.menu.MyMenuNodeView;
import com.bumptech.glide.Glide;

import java.util.HashMap;

import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public class MenuNodeViewHolder extends RecyclerView.ViewHolder {

    private Context context;
    private MenuNodesAdapter adapter;
    private MyMenuNodeView menuNodeView;
    private MenuNode menuNode;
//    private View view;
//    private ImageView menuImageView;
//    private TextView menuTitleView;
//    private long menuNodeId;
//    private int fontColor, layoutBackgroundColor, imageColor,
//            gravity, imageMaxHeight, imageMaxWidth;
//    private float fontSize;
//    private Typeface fontType;
//    private String title, imageUrl;
//    private Drawable layoutBackground, imageDrawable;
//    private boolean boldText, underlineText, italicText;

    public MenuNodeViewHolder(Context context, MenuNodesAdapter adapter, View itemView) {
        super(itemView);
        this.context = context;
        this.adapter = adapter;

        this.setMenuNodeView((MyMenuNodeView) itemView.findViewById(R.id.menu_node));
        this.initialize();
    }

    public MyMenuNodeView getMenuNodeView() {
        return menuNodeView;
    }

    public void setMenuNodeView(MyMenuNodeView menuNodeView) {
        this.menuNodeView = menuNodeView;

    }

    private void initialize() {
        this.menuNode = null;
        this.menuNodeView.setBackgroundColor(ContextCompat.getColor(context, R.color.transparent));
        this.menuNodeView.setLabelTextColor(ContextCompat.getColor(context, R.color.textColorPrimary));
        this.menuNodeView.setLabelTextSize((int)context.getResources().getDimension(R.dimen.text_size_x_large));
        this.menuNodeView.setBackgroundImage(null);
        this.menuNodeView.setLabelTextTypeface(null);
        this.menuNodeView.setLabelBoldEnable(false);
        this.menuNodeView.setLabelUnderlineEnable(false);
        this.menuNodeView.setLabelItalicEnable(false);
        //this.gravity = finals.gravity.get("center");
        this.menuNodeView.setImageHeight(UIUtilsManager.get().convertPixelsToDp(this.context, 150));
        this.menuNodeView.setImageWidth(UIUtilsManager.get().convertPixelsToDp(this.context, 150));
        this.menuNodeView.setLabelText("def node");
        this.menuNodeView.setImageUri(null);
        //this.imageDrawable = null;
        //this.imageColor = ContextCompat.getColor(context, R.color.transparent);
        this.menuNodeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (MenuNodeViewHolder.this.menuNode != null) {
                    ((MainActivity) context).setCurMenuNode(MenuNodeViewHolder.this.menuNode.getId());
                }
            }
        });
        this.menuNodeView.setGravity(finals.gravity.get("center"));
        this.menuNodeView.setImageGravity(finals.gravity.get("center"));
        this.menuNodeView.setLabelGravity(finals.gravity.get("center"));
        this.menuNodeView.setLabeLTextAlignment(finals.textAlignment.get("center"));
    }

    private void updateProperties(HashMap<String, String> properties) {
        String curProperty;
        if ((curProperty = properties.get("id")) != null) {
            try {
                this.menuNode = RealmController.with(BOBApplication.get()).getMenuNodeById(Long.parseLong(curProperty));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_color")) != null) {
            try {
                this.menuNodeView.setLabelTextColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background_color")) != null) {
            try {
                this.menuNodeView.setBackgroundColor(Color.parseColor(curProperty));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("font_size")) != null) {
            try {
                this.menuNodeView.setLabelTextSize(UIUtilsManager.get().convertPixelsToSp(this.context, Integer.parseInt(curProperty)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("layout_background")) != null) {
            this.menuNodeView.setBackgroundImage(ContextCompat.getDrawable(context, context.getResources().getIdentifier(curProperty, "drawable", context.getPackageName())));
        }
        if ((curProperty = properties.get("font_type")) != null) {
            this.menuNodeView.setLabelTextTypeface(Fonts.get().findTypeface(this.context, curProperty));
        }
        if ((curProperty = properties.get("bold_text")) != null) {
            if (curProperty.equals("true")) {
                this.menuNodeView.setLabelBoldEnable(true);
            } else if (curProperty.equals("false")) {
                this.menuNodeView.setLabelBoldEnable(false);
            }
        }
        if ((curProperty = properties.get("underline_text")) != null) {
            if (curProperty.equals("true")) {
                this.menuNodeView.setLabelUnderlineEnable(true);
            } else if (curProperty.equals("false")) {
                this.menuNodeView.setLabelUnderlineEnable(false);
            }
        }
        if ((curProperty = properties.get("italic_text")) != null) {
            if (curProperty.equals("true")) {
                this.menuNodeView.setLabelItalicEnable(true);
            } else if (curProperty.equals("false")) {
                this.menuNodeView.setLabelItalicEnable(false);
            }
        }
        //if ((curProperty = properties.get("gravity")) != null) {
        //    this.gravity = finals.gravity.get(curProperty);
        //}
        if ((curProperty = properties.get("image_height")) != null) {
            try {
                this.menuNodeView.setImageHeight(UIUtilsManager.get().convertPixelsToDp(this.context, Integer.parseInt(curProperty)));

            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("image_width")) != null) {
            try {
                this.menuNodeView.setImageWidth(UIUtilsManager.get().convertPixelsToDp(this.context, Integer.parseInt(curProperty)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("image_size")) != null) {
            try {
                this.menuNodeView.setImageHeight(UIUtilsManager.get().convertPixelsToDp(this.context, Integer.parseInt(curProperty)));
                this.menuNodeView.setImageWidth(UIUtilsManager.get().convertPixelsToDp(this.context, Integer.parseInt(curProperty)));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if ((curProperty = properties.get("Title")) != null) {
            this.menuNodeView.setLabelText(curProperty);
        }
        if ((curProperty = properties.get("label")) != null) {
            this.menuNodeView.setLabelText(curProperty);
        }
        if ((curProperty = properties.get("image_url")) != null) {
            this.menuNodeView.setImageUri(curProperty);
        }
        //if ((curProperty = properties.get("image_drawable")) != null) {
        //    this.imageDrawable = Icons.get().findDrawable(this.context, curProperty);
        //}
        //if ((curProperty = properties.get("image_color")) != null) {
        //    try {
        //        this.imageColor = Color.parseColor(curProperty);
        //    } catch (IllegalArgumentException e) {
        //        e.printStackTrace();
        //    }
        //}
    }

    public void configureMenuNode(MenuNode menuNode) {
        HashMap<String, String> properties = new HashMap<String, String>();
        RealmResults<MenuNodeProperty> RealmProperties = RealmController.with(BOBApplication.get()).getPropertiesOfMenuNode(menuNode.getId());
        properties.put("id", String.valueOf(menuNode.getId()));
        for (MenuNodeProperty property : RealmProperties) {
            properties.put(property.getKey(), property.getValue());
        }
        updateProperties(properties);
    }


}
