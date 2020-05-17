package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;
import io.raveerocks.github.operations.binary.BinaryOperator;

public class Prefix extends AbstractBinaryOperator<String,String> {
    private static String DEFAULT_VALUE = "";

    @Override
    public String apply(String s1, String s2) {
        s1 = getDefaultOnNull(s1);
        s2 = getDefaultOnNull(s2);

        StringBuilder sb = new StringBuilder();
        int i=0,length=Math.min(s1.length(),s2.length());

        while (i<length && s1.charAt(i)==s2.charAt(i)){
            sb.append(s1.charAt(i));
            ++i;
        }

        return sb.toString();
    }

    @Override
    public String getDefaultValue() {
        return DEFAULT_VALUE;
    }

    private Prefix(){ }

    static class DefaultInstance{
        private static Prefix defaultSingletonInstance = new Prefix();
    }

    public synchronized static Prefix getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
