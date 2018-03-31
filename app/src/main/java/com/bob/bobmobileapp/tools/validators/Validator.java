package com.bob.bobmobileapp.tools.validators;

/**
 * Created by user on 05/09/2017.
 */

public abstract class Validator<Type> {

    public Validator() {}

    public abstract String validate(Type object);
}
