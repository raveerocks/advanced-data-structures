package io.raveerocks.github.operations.binary.special;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class SortAndMerge<T extends Comparable> extends AbstractBinaryOperator<T[],T[]> {
    private static Object[] IDENTITY_CONSTANT = new Comparable[0];
    private static Object[] LAZY_CONSTANT = new Comparable[0];

    @Override
    public T[] apply(T[] element) {
        return element;
    }

    @Override
    public T[] apply(T[] element1, T[] element2) {
        int i=0,j=0,k=0,n1=element1.length,n2=element2.length;

        T[] result = (T[]) new Comparable[n1+n2];

        for (k=0; k<n1+n2; k++){
            if (j>=n2||i<n1 && element1[i].compareTo(element2[j])<=0){
                result[k] = element1[i++];
            }
            else {
                result[k] = element2[j++];
            }
        }
        return result;
    }

    @Override
    public T[] getIdentityConstant() {
        return (T[])IDENTITY_CONSTANT;
    }

    @Override
    public T[] getLazyConstant() {
        return (T[])LAZY_CONSTANT;
    }

    protected SortAndMerge(){ }
}
