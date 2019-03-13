package com.example.platecalculator;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Plate {
    double[] weight = new double[7];

    public Plate(String a) {
        if (a.equalsIgnoreCase("kg")) {
            weight[0] = 1.25;
            weight[1] = 2.5;
            weight[2] = 5;
            weight[3] = 10;
            weight[4] = 15;
            weight[5] = 20;
            weight[6] = 25;
        } else if (a.equalsIgnoreCase("lb")) {
            weight[0] = 5;
            weight[1] = 10;
            weight[2] = 15;
            weight[3] = 20;
            weight[4] = 25;
            weight[5] = 35;
            weight[6] = 45;
        }
    }

    public List<Double> Calculate(double a) {
        List<Double> result = new ArrayList<>();

        for(int i = weight.length - 1; i >= 0; i--) {
            while (a - weight[i] >= 0) {

                result.add(weight[i]);
                a -= weight[i];
            }
        }

        return result;
    }
}