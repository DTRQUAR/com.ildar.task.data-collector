package com.data.advertiser.system.stub.scheduler;

import com.data.advertiser.system.stub.generator.SalesGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Component
public class SalesGeneratorScheduler {

    @Autowired
    private SalesGenerator salesGenerator;

    @Scheduled(fixedRate = 3000)
    public void generateSalesScheduler() {
        salesGenerator.generateSales();
    }

}
