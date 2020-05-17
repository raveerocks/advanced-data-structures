package io.raveerocks.github.operations.binary.string;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;
import io.raveerocks.github.operations.binary.BinaryOperator;

public class CountCharacter extends AbstractBinaryOperator<String,Integer> {
    private static int DEFAULT_NUMERAL_VALUE =0;
    private static String DEFAULT_STRING_VALUE ="";
    private char character;

    @Override
    public Integer apply(Integer count1, Integer count2) {
        return count1+count2;
    }

    @Override
    public Integer apply(String element) {
        element = element==null?DEFAULT_STRING_VALUE:element;
        int count=0;
        for (int i=0;i<element.length();i++){
            count = element.charAt(i)==character?count+1:count;
        }
        return count;
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_NUMERAL_VALUE;
    }

    private CountCharacter(){ }

    static class DefaultInstance{
        private static CountCharacter defaultSingletonInstance = new CountCharacter();
    }

    public synchronized static CountCharacter getCountCharacter() {
        return DefaultInstance.defaultSingletonInstance;
    }

}
