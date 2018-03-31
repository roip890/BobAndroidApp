package com.bob.bobmobileapp;

import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.StackingBehavior;

import java.util.HashMap;

/**
 * Created by user on 11/09/2017.
 */

public class finals {

    public static final HashMap<String, Integer> inputTypes;
    static {
        inputTypes = new HashMap<String, Integer>();
        inputTypes.put("date", 0x14);
        inputTypes.put("datetime", 0x4);
        inputTypes.put("none", 0x0);
        inputTypes.put("number", 0x2);
        inputTypes.put("numberDecimal", 0x2002);
        inputTypes.put("numberPassword", 0x12);
        inputTypes.put("numberSigned", 0x1002);
        inputTypes.put("phone", 0x3);
        inputTypes.put("text", 0x1);
        inputTypes.put("textAutoComplete", 0x10001);
        inputTypes.put("textAutoCorrect", 0x8001);
        inputTypes.put("textCapCharacters", 0x1001);
        inputTypes.put("textCapSentences", 0x4001);
        inputTypes.put("textCapWords", 0x2001);
        inputTypes.put("textEmailAddress", 0x21);
        inputTypes.put("textEmailSubject", 0x31);
        inputTypes.put("textFilter", 0xb1);
        inputTypes.put("textImeMultiLine", 0x40001);
        inputTypes.put("textLongMessage", 0x51);
        inputTypes.put("textMultiLine", 0x20001);
        inputTypes.put("textNoSuggestions", 0x80001);
        inputTypes.put("textPassword", 0x81);
        inputTypes.put("textPersonName", 0x61);
        inputTypes.put("textPhonetic", 0xc1);
        inputTypes.put("textPostalAddress", 0x71);
        inputTypes.put("textShortMessage", 0x41);
        inputTypes.put("textUri", 0x11);
        inputTypes.put("textVisiblePassword", 0x91);
        inputTypes.put("textWebEditText", 0xa1);
        inputTypes.put("textWebEmailAddress", 0xd1);
        inputTypes.put("textWebPassword", 0xe1);
        inputTypes.put("time", 0x24);


    }

    public static final HashMap<String, Integer> gravity;
    static {
        gravity = new HashMap<String, Integer>();
        gravity.put("axis_clip", 8);
        gravity.put("axispullafter", 4);
        gravity.put("axispullbefore", 2);
        gravity.put("axisspecified", 1);
        gravity.put("axisxshift", 0);
        gravity.put("axis_y_shift", 4);
        gravity.put("bottom", 80);
        gravity.put("center", 17);
        gravity.put("center_horizontal", 1);
        gravity.put("center_vertical", 16);
        gravity.put("clip_horizontal", 8);
        gravity.put("clip_vertical", 128);
        gravity.put("display_clip_horizontal", 16777216);
        gravity.put("display_clip_vertical", 268435456);
        gravity.put("end", 8388613);
        gravity.put("fill", 119);
        gravity.put("fill_horizontal", 7);
        gravity.put("textimemultiline", 112);
        gravity.put("fill_vertical", 112);
        gravity.put("horizontal_gravity_mask", 7);
        gravity.put("left", 3);
        gravity.put("no_gravity", 0);
        gravity.put("relative_horizontal_gravity_mask", 8388615);
        gravity.put("relative_layout_direction", 8388608);
        gravity.put("right", 5);
        gravity.put("start", 8388611);
        gravity.put("top", 48);
        gravity.put("vertical_gravity_mask", 112);


    }

    public static final HashMap<String, GravityEnum> dialogGravity;
    static {
        dialogGravity = new HashMap<String, GravityEnum>();
        dialogGravity.put("start", GravityEnum.START);
        dialogGravity.put("center", GravityEnum.CENTER);
        dialogGravity.put("end", GravityEnum.END);
    }

    public static final HashMap<String, StackingBehavior> dialogStackingBehavior;
    static {
        dialogStackingBehavior = new HashMap<String, StackingBehavior>();
        dialogStackingBehavior.put("never", StackingBehavior.NEVER);
        dialogStackingBehavior.put("adaptive", StackingBehavior.ADAPTIVE);
        dialogStackingBehavior.put("always", StackingBehavior.ALWAYS);
    }

    public static final HashMap<String, Integer> textAlignment;
    static {
        textAlignment = new HashMap<String, Integer>();
        textAlignment.put("inherit", 0);
        textAlignment.put("gravity", 1);
        textAlignment.put("text_start", 2);
        textAlignment.put("text_end", 3);
        textAlignment.put("center", 4);
        textAlignment.put("view_start", 5);
        textAlignment.put("view_end", 6);
    }


}
