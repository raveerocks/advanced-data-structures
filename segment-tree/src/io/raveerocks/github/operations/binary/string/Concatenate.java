package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Concatenate extends AbstractBinaryOperator<String,String> {
    private static String DEFAULT_VALUE = "";

    @Override
    public String apply(String s1, String s2) {
        return getDefaultOnNull(s1).concat(getDefaultOnNull(s2));
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    private Concatenate(){ }

    static class DefaultInstance{
        private static Concatenate defaultSingletonInstance = new Concatenate();
    }

    public synchronized static Concatenate getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
