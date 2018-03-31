package com.bob.bobmobileapp.tools.validators;

import android.view.View;
import android.widget.TextView;
import java.util.ArrayList;

/**
 * Created by user on 05/09/2017.
 */

public abstract class GroupValidator<Type> {

    public GroupValidator() {}

    public abstract String validate(ArrayList<Type> textViews);
}
