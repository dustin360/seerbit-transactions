package com.serbit.transaction.demo.algorithim;


import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by David on 03 Apr, 2023
 **/
public class AlgorithmSolutions {


    public static List<Pair<LocalDateTime, LocalDateTime>> mergeIntervals(List<Pair<LocalDateTime, LocalDateTime>> intervals) {

        //if the size of the list less <= 1
        if (intervals.size() <= 1) {
            return intervals;
        }

        // sort the list by the start timestamp
        intervals.sort(Comparator.comparing(Pair::getKey));

        List<Pair<LocalDateTime, LocalDateTime>> mergedIntervals = new ArrayList<>();
        Pair<LocalDateTime, LocalDateTime> currentInterval = intervals.get(0);

        // loop through the list
        for (int i = 1; i < intervals.size(); i++) {
            Pair<LocalDateTime, LocalDateTime> nextInterval = intervals.get(i);

            //If the current interval and the next interval overlap i.e. the end timestamp of the current interval
            // is >= to the start timestamp of the next interval, then we'll merge the intervals
            if (currentInterval.getValue().isEqual(nextInterval.getKey()) ||
                    currentInterval.getValue().isAfter(nextInterval.getKey())) {

                LocalDateTime maxEndTime = currentInterval.getValue().isAfter(nextInterval.getValue()) ?
                        currentInterval.getValue() : nextInterval.getValue();

                currentInterval = new Pair<>(currentInterval.getKey(), maxEndTime);
            } else {
                mergedIntervals.add(currentInterval);
                currentInterval = nextInterval;
            }
        }

        mergedIntervals.add(currentInterval);

        return mergedIntervals;
    }


    static int[] getSubArrayWithMaxXOR(int[] arr)
    {

        int n = arr.length;
        int maxXor = 0;
        int[] subArray = new int[2];

        // Calculating xor of each pair
        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {

                if ((arr[i] ^ arr[j]) > maxXor) {
                    maxXor = arr[i] ^ arr[j];

                    subArray[0] = arr[i];
                    subArray[1] = arr[j];
                }
            }
        }
        return subArray;
    }

}
