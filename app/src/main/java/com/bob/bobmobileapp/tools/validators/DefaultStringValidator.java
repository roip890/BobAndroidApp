package com.bob.bobmobileapp.tools.validators;

/**
 * Created by user on 04/10/2017.
 */

public class DefaultStringValidator extends Validator<String> {

    @Override
    public String validate(String text) {
        if (text.isEmpty()) {
            return "Please enter a text!";
        }
        return null;
    }

}
