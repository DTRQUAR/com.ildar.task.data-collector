package com.data.collector.CollectTool;

import com.data.collector.model.Advertiser;
import com.data.collector.model.Offer;
import com.data.collector.model.Publisher;
import com.data.collector.repository.AdvertiserRepository;
import com.data.collector.repository.PublisherRepository;
import com.data.collector.util.CollectionsUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class AdvertiserGenerator {

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(fixedDelay = 2000)
    public void generateAdvertiser() {
        Advertiser advertiser = new Advertiser();

        advertiser.setName("Adv_"
                + RandomStringUtils.randomAlphabetic(3)
                + "_"
                + RandomStringUtils.randomAlphabetic(3));
        advertiser.setStatisticUrl("https://"
                + RandomStringUtils.random(10)
                + ".com/" + RandomStringUtils.random(3) + "-statistics");
        advertiser.setCommission(RandomUtils.nextInt(2, 40));
        advertiser.setPublishers(getRandomPublishers());
        advertiser.setOffers(getRandomGeneratedOffers(advertiser));

        advertiserRepository.save(advertiser);
    }

    /**
     * Метод получает коллекцию из случайных Publisher'ов
     *
     * @return коллекция из случайных Publisher'ов
     */
    private Set<Publisher> getRandomPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();
        Set<Publisher> randomPublisher = new HashSet<>();
        for (int i = 0; (i < publishers.size() / 2); i++) {
            Publisher publisher = CollectionsUtil.random(publishers);
            randomPublisher.add(publisher);
        }
        return randomPublisher;
    }

    private List<Offer> getRandomGeneratedOffers(Advertiser advertiser) {
        List<Offer> randomGeneratedOffers = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 8); i++) {
            randomGeneratedOffers.add(getRandomGeneratedOffer(advertiser));
        }
        return randomGeneratedOffers;
    }

    private Offer getRandomGeneratedOffer(Advertiser advertiser) {
        Offer offer = new Offer();
        offer.setNumber(RandomStringUtils.randomNumeric(9));
        offer.setName("OfferName_"
                + RandomStringUtils.randomAlphabetic(5)
                + "_"
                + RandomStringUtils.randomAlphabetic(3));
        offer.setPrice(Integer.valueOf(RandomStringUtils.randomNumeric(2, 4)));
        offer.setAdvertiser(advertiser);

        return offer;
    }


}
