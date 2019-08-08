package com.data.util;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
public class StatisticUtil {
    public static double calculatePublisherEarning(Integer salesAmount, Integer commission) {
        return (commission * salesAmount) / 100;
    }
}
