package com.rsmithio;

import java.util.*;
import java.util.stream.Collectors;

class Solution3 {
    public static void main(String[] argh) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            int n = in.nextInt();

            ArrayList<Integer> series = calculate_series(a, b, n);
            ArrayList<String> str_series = series.stream().map(j -> j.toString()).collect(Collectors.toCollection(ArrayList::new));
            System.out.println(String.join(" ", str_series));
        }
        in.close();
    }

    public static ArrayList<Integer> calculate_series(int a, int b, int n) {
        ArrayList<Integer> series = new ArrayList<Integer>();

        int value;
        for (int i = 0; i < n; ++i) {
            value = b * (int) Math.pow(2, i);

            if (i > 0) {
                value += series.get(i - 1);
            }
            series.add(value);
        }

        return series.stream().map(j -> a + j).collect(Collectors.toCollection(ArrayList::new));
    }
}
