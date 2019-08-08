package com.data.collector.util;

public class StatisticUtil {
    public static double calculatePublisherEarning(Integer salesAmount, Integer commission) {
        return (commission * salesAmount) / 100;
    }
}
