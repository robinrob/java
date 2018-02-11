package com.rsmithio;

import java.util.*;
import java.io.*;



class Solution4 {
    public static void main(String []argh)
    {



        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();

        for(int i=0;i<t;i++)
        {

            try
            {
                long x=sc.nextLong();
                System.out.println(x+" can be fitted in:");
                print_types(get_types_for_x(x));
            }
            catch(Exception e)
            {
                System.out.println(sc.next()+" can't be fitted anywhere.");
            }

        }
    }

    private static void print_types(ArrayList<String> types) {
        for (String type : types) {
            System.out.println("* " + type);
        }
    }

    public static ArrayList<String> get_types_for_x(long x) {
        ArrayList<String> types = new ArrayList<String>();

//        if (x_within_range(x, Byte.MIN_VALUE, Byte.MAX_VALUE)) {
//            types.add("byte");
//        }
//        if (x_within_range(x, Short.MIN_VALUE, Short.MAX_VALUE)) {
//            types.add("short");
//        }
//        if (x_within_range(x, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
//            types.add("int");
//        }
//        if (x_within_range(x, Long.MIN_VALUE, Long.MAX_VALUE)) {
//            types.add("long");
//        }
        if (x_within_bytes(x, 8)) {
            types.add("byte");
        }
        if (x_within_bytes(x, 16)) {
            types.add("short");
        }
        if (x_within_bytes(x, 32)) {
            types.add("int");
        }
        if (x_within_bytes(x, 64)) {
            types.add("long");
        }

        return types;
    }

    private static boolean x_within_range(long x, long min, long max) {
        return x >= min && x <= max;
    }

    private static boolean x_within_bytes(long x, int n_bytes) {
        long TwoToThePower = (long) Math.pow(2, n_bytes - 1);
        long min = -TwoToThePower;
        long max = TwoToThePower - 1;
        if (n_bytes == 64) {
            min -= 1;
            max += 1;
        }
        return x >= min && x <= max;
    }
}