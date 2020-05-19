package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Concatenate extends AbstractBinaryOperator<String,String> {
    private static String LAZY_CONSTANT = "";
    private static String IDENTITY_CONSTANT = "";


    @Override
    public String apply(String element) {
        return getIdentityOnNull(element);
    }

    @Override
    public String apply(String s1, String s2) {
        return getLazyOnNullNonLeaf(s1).concat(getLazyOnNullNonLeaf(s2));
    }


    @Override
    public String getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public String getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Concatenate(){ }

    static class DefaultInstance{
        private static Concatenate defaultSingletonInstance = new Concatenate();
    }

    public synchronized static Concatenate getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
