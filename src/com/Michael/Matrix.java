package com.Michael;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;

import static com.Michael.ValueEnforcer.notNull;

public class Matrix {
    private final double[][] m_Data;

    /**
     * Construct an Rows-by-Cols matrix of 0.0
     *
     * @param Rows      Number of rows.
     *
     * @param Cols      Number of columns.
     */
    public Matrix(@Nonnegative final int Rows, @Nonnegative final int Cols) {
        ValueEnforcer.isGT0(Rows, "Rows");
        ValueEnforcer.isGT0(Cols, "Cols");

        m_Data = new double[Rows][Cols];
    }

    /**
     * @return <code>true</code> if number of rows equals columns
     *
     */

    public boolean isSymmetrical() {
        return m_Data.length == m_Data[0].length;
    }


    /**
     * Get a mutable element;
     *
     * @param nRow             Row.
     * @param nCol             Column.
     *
     */

    public double get(@Nonnegative final int nRow, @Nonnegative final int nCol) {
        return m_Data[nRow][nCol];
    }

    /**
     * Set an element of a matrix to a double value
     * @param nRow      row index.
     * @param nCol      column index.
     * @param dValue    value.
     *
     */

    public void set(@Nonnegative final int nRow, @Nonnegative final int nCol, final double dValue) {
        m_Data[nRow][nCol] = dValue;
    }

    /**
     * @return a reference to an 2-d array of matrix elems.
     *
     */

    private double[][] internalGetArray() {
        return m_Data;
    }

    /**
     * Transpose a matrix.
     *
     * --->                | | |
     * --->     into       | | |
     * --->                V V V
     *
     */

    public Matrix selfTranspose() {
        if(this.isSymmetrical()) {
            double temp;
            for (int nRow = 0; nRow < m_Data.length; nRow++)
                for (int nCol = nRow + 1; nCol < m_Data[0].length; nCol++) {
                    temp = m_Data[nRow][nCol];
                    m_Data[nRow][nCol] = m_Data[nCol][nRow];
                    m_Data[nCol][nRow] = temp;
                }
            return this;
        } else {
            Matrix aTransposedMatrix = new Matrix(m_Data[0].length,m_Data.length);
            final double[][] aNewArray = aTransposedMatrix.internalGetArray();
            for (int nRow = 0; nRow < m_Data.length; nRow++) {
                for (int nCol = 0; nCol < m_Data[0].length; nCol++)
                    aNewArray[nCol][nRow] = m_Data[nRow][nCol];
            }

            return aTransposedMatrix;
        }
    }

    /**
     * Create a matrix with random elements.
     *
     * @param nRows     Number of  rows.
     * @param nCols     Number of columns.
     * @return An rows-by-columns matrix filled with random elements.
     *
     */

    public static Matrix random(@Nonnegative final int nRows, @Nonnegative final int nCols) {
        final Matrix NewMatrix = new Matrix(nRows, nCols);
        final double[][] aNewArray = NewMatrix.internalGetArray();

        for (int nRow = 0; nRow < nRows; nRow++) {
            final double[] aDstRow = aNewArray[nRow];
            for (int nCol = 0; nCol < nCols; nCol++)
                aDstRow[nCol] = Math.random();
        }
        return NewMatrix;
    }

    /**
     * Print the matrix to the output stream. Line the elements up in columns using
     * NumberFormat. Put at least one space in between values, specify it by setting
     * greater nWidth;
     *
     * @param aPW             Output stream.
     * @param nWidth          Column width.
     * @param nFractionDigits Number of digits after the decimal.
     *
     */

    public void print(@Nonnull final PrintWriter aPW,
                      @Nonnegative final int nWidth,
                      @Nonnegative final int nFractionDigits) {

        ValueEnforcer.isGE0(nWidth, "nWidth");
        ValueEnforcer.isGT0(nFractionDigits, "nFractionDigits");

        final NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        format.setMinimumIntegerDigits(1);
        format.setMaximumFractionDigits(nFractionDigits);
        format.setMinimumFractionDigits(nFractionDigits);
        format.setGroupingUsed(false);
        int nWidthWithSpacesInBetween = nWidth + 2;

        aPW.println(); // start on new line.
        for (int nRow = 0; nRow < m_Data.length; nRow++) {
            for (int nCol = 0; nCol < m_Data[0].length; nCol++) {
                // format the number
                final String s = format.format(m_Data[nRow][nCol]);
                // At _least_ 1 space
                final int padding = Math.max(1, nWidthWithSpacesInBetween - s.length());
                for (int spaces = 0; spaces < padding; spaces++) {
                    aPW.print(' ');
                }
                aPW.printf(s);
            }
            aPW.println();
        }
        // end with blank line.
        aPW.println();
    }

}