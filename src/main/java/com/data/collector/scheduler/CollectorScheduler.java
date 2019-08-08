package com.data.collector.scheduler;

import com.data.collector.Collector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Component
public class CollectorScheduler {

    @Autowired
    private Collector collector;

    @Scheduled(fixedRate = 2000)
    public void collectAllSaleStatisticScheduler() {
        collector.collectAllSaleStatistic();
    }

}
