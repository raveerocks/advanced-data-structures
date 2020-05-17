package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class Concatenate implements BinaryOperator<String,String> {
    private static String DEFAULT_VALUE = "";

    @Override
    public String apply(String s1, String s2) {
        s1 = s1==null?DEFAULT_VALUE:s1;
        s2 = s2==null?DEFAULT_VALUE:s2;
        return s1.concat(s2);
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return String.class;
    }

    private Concatenate(){ }

    static class DefaultInstance{
        private static Concatenate defaultSingletonInstance = new Concatenate();
    }

    public synchronized static Concatenate getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
