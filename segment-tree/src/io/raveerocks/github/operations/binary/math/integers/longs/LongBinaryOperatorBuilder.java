package io.raveerocks.github.operations.binary.math.integers.longs;

public class LongBinaryOperatorBuilder {

    public static GCD getLongGCD(){
        return new GCD();
    }

    public static Maximum getLongMaximum(){
        return new Maximum();
    }

    public static Minimum getLongMinimum(){
        return new Minimum();
    }

    public static Sum getLongSum(){
        return new Sum();
    }

    public static ModulusSum getLongModulusSum(int modulus){return new ModulusSum(modulus);}
}
