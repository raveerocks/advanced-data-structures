package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class Prefix implements BinaryOperator<String,String> {
    private static String DEFAULT_VALUE = "";

    @Override
    public String apply(String s1, String s2) {
        s1 = s1==null?DEFAULT_VALUE:s1;
        s2 = s2==null?DEFAULT_VALUE:s2;
        StringBuilder sb = new StringBuilder();
        int i=0;
        while (s1.charAt(i)==s2.charAt(i)){
            sb.append(s1.charAt(i));
            ++i;
        }
        return sb.toString();
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return String.class;
    }

    private Prefix(){ }

    static class DefaultInstance{
        private static Prefix defaultSingletonInstance = new Prefix();
    }

    public synchronized static Prefix getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
