package io.raveerocks.github.operations.binary;

import io.raveerocks.github.operations.binary.math.integer.Maximum;
import io.raveerocks.github.operations.binary.math.integer.Minimum;
import io.raveerocks.github.operations.binary.string.Concatenate;
import io.raveerocks.github.operations.binary.math.integer.Addition;
import io.raveerocks.github.operations.binary.math.integer.GCD;
import io.raveerocks.github.operations.binary.math.integer.bit.SetBitCount;
import io.raveerocks.github.operations.binary.string.CountCharacter;

public final class BinaryOperatorBuilder {
    private BinaryOperatorBuilder(){

    }
    public static GCD getGCD() {
        return GCD.getGCD();
    }
    public static Addition getIntegerAddition() {
        return Addition.getIntegerAddition();
    }
    public static Maximum getIntegerMaximum() {
        return Maximum.getIntegerMaximum();
    }
    public static Minimum getIntegerMinimum() {
        return Minimum.getIntegerMinimum();
    }
    public static Concatenate getPrefix() {
        return Concatenate.getPrefix();
    }
    public static SetBitCount getBinarySetBitCount(){return SetBitCount.getBinarySetBitCount();};
    public static CountCharacter getCountCharacter(){return CountCharacter.getCountCharacter();}
}
