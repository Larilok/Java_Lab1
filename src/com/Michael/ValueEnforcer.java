package com.Michael;



import javax.annotation.Nonnull;
import java.util.function.Supplier;


public class ValueEnforcer {

    /**
     * Check that the passed value is <code>> 0</code>.
     *
     *
     * @param dValue     value to enforce.
     * @param sName      the name of var.
     *
     * @throws IllegalArgumentException if the passed value is <code><= 0</code>
     *
     */
    public static void isGT0(final double dValue, final String sName) {
        if (dValue <= 0)
            throw new IllegalArgumentException("The value of '" +
                    sName.toUpperCase() +
                    "' must be > 0! The current value is: " +
                    dValue);
    }

    /**
     * Check that the passed value is is <code>>= 0</code>.
     *
     * @param dValue    value to enforce.
     * @param sName     the name of var.
     *
     * @throws IllegalArgumentException if the passed value is <code>< 0</code>
     *
     */
    public static void isGE0(final double dValue, final String sName) {
        if (dValue < 0)
            throw new IllegalArgumentException("The value of '" +
                    sName +
                    "' must be >= 0! The current value is: " +
                    dValue);
    }

//    public static double isGE0(final double dValue, @Nonnull final Supplier<? extends String> aName) {
//
//        if (dValue < 0)
//            throw new IllegalArgumentException("The value of '" +
//                    aName.get() +
//                    "' must be >= 0! The current value is: " +
//                    dValue);
//        return dValue;
//    }

    /**
     * Check that the passed value is not <code>null</code>.
     *
     * @param <T>    Type to be checked and returned
     * @param aValue The value to check.
     * @param sName  The name of the value (e.g. the parameter name)
     *
     * @throws NullPointerException if the passed value is <code>null</code>.
     *
     */
    public static <T> T notNull(final T aValue, final String sName) {
        if (aValue == null)
            throw new NullPointerException("The value of '" + sName + "' may not be null!");
        return aValue;
    }
}
