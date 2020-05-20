package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;


public final class Prefix extends AbstractBinaryOperator<String,String> {
    private static String LAZY_CONSTANT = "";

    @Override
    public String apply(String element) {
        return getLazyOnNullLeaf(element);
    }

    @Override
    public String apply(String s1, String s2) {
        s1 = getLazyOnNullNonLeaf(s1);
        s2 = getLazyOnNullNonLeaf(s2);

        StringBuilder sb = new StringBuilder();
        int i=0,length=Math.min(s1.length(),s2.length());

        while (i<length && s1.charAt(i)==s2.charAt(i)){
            sb.append(s1.charAt(i));
            ++i;
        }

        return sb.toString();
    }

    @Override
    public String getIdentityConstant() {
        throw new RuntimeException("Identity Constant is Not Defined");
    }

    @Override
    public String getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Prefix(){ }

    static class DefaultInstance{
        private static Prefix defaultSingletonInstance = new Prefix();
    }

    public synchronized static Prefix getPrefix() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
