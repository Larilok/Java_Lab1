package com.Michael;

import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) {
        Matrix matrix = null;
        try {
            matrix = Matrix.random(6, 5);

            PrintWriter PW = new PrintWriter(System.out);
            matrix.print(PW, 5, 3);
            matrix = matrix.selfTranspose();
            matrix.print(PW, 5, 3);
            PW.close();
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}