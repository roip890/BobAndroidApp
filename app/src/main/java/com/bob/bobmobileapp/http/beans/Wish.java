package com.bob.bobmobileapp.http.beans;

import java.util.ArrayList;

/**
 * Created by User on 21/02/2018.
 */

public class Wish {

    private ArrayList<WishPair> wishes;

    public Wish() {
        this.wishes = new ArrayList<WishPair>();
    }

    public void addPair(WishPair pair) {
        this.wishes.add(pair);
    }

    public void removePair(WishPair pair) {
        if (this.wishes.contains(pair)) {
            this.wishes.remove(pair);
        }
    }

    public ArrayList<WishPair> getWishes() {
        return wishes;
    }

    public void setWishes(ArrayList<WishPair> wishes) {
        this.wishes = wishes;
    }
}
