package com.bob.bobmobileapp.menu.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bob.bobmobileapp.BOBApplication;
import com.bob.bobmobileapp.R;
import com.bob.bobmobileapp.menu.viewholders.menunode.MenuNodeViewHolder;
import com.bob.bobmobileapp.realm.RealmController;
import com.bob.bobmobileapp.realm.objects.MenuNode;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmResults;

/**
 * Created by user on 17/09/2017.
 */

public class MenuNodesAdapter extends RecyclerView.Adapter<MenuNodeViewHolder> {

    private long parentMenuNodeId;
    private ArrayList<MenuNode> menuNodes;
    private Context context;

    public MenuNodesAdapter(Context context) {
        this(context, 0);
    }

    public MenuNodesAdapter(Context context, long parentMenuNodeId) {
        this.parentMenuNodeId = parentMenuNodeId;
        this.menuNodes = new ArrayList<MenuNode>();
        this.setMenuNodes(RealmController.with(BOBApplication.get()).getSubMenuNodes(this.parentMenuNodeId));
        this.context = context;
    }

    @Override
    public MenuNodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_node_item, null);
        return new MenuNodeViewHolder(this.context, this, layoutView);
    }

    @Override
    public void onBindViewHolder(MenuNodeViewHolder menuNodeViewHolder, int position) {
        menuNodeViewHolder.configureMenuNode(menuNodes.get(position));
    }

    @Override
    public int getItemCount() {
        return this.menuNodes.size();
    }

    public void setMenuNodes(List<MenuNode> menuNodes) {
        if (menuNodes != null) {
            this.menuNodes.clear();
            this.menuNodes.addAll(menuNodes);
        }
    }

    public void setParentMenuNode(long parentMenuNodeId) {
        this.parentMenuNodeId = parentMenuNodeId;
        this.setMenuNodes(RealmController.get().with(BOBApplication.get()).getSubMenuNodes(parentMenuNodeId));
        this.notifyDataSetChanged();
    }
}
