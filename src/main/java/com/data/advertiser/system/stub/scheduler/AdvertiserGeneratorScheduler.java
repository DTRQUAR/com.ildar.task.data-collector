package com.data.advertiser.system.stub.scheduler;

import com.data.advertiser.system.stub.generator.AdvertiserGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Component
public class AdvertiserGeneratorScheduler {

    @Autowired
    private AdvertiserGenerator advertiserGenerator;

    @Scheduled(fixedDelay = 2000)
    public void generateAdvertiserScheduler() {
        advertiserGenerator.generateAdvertiser();
    }

}
