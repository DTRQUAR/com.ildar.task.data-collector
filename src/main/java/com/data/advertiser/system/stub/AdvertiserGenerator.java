package com.data.advertiser.system.stub;

import com.data.model.Advertiser;
import com.data.model.Offer;
import com.data.model.Publisher;
import com.data.model.ResponseDetail;
import com.data.repository.AdvertiserRepository;
import com.data.repository.PublisherRepository;
import com.data.repository.ResponseDetailRepository;
import com.data.util.CollectionsUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Генератор Advertiser'ов
 * Имитирует регистрацию в Партнерской сети Advertiser'ов и их Offer'ов
 */
@Component
public class AdvertiserGenerator {

    private static final int MIN_COMMISSION = 2;
    private static final int MAX_COMMISSION = 40;
    private static final int STANDARD_API_PERCENT = 40;

    @Autowired
    private AdvertiserRepository advertiserRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private ResponseDetailRepository responseDetailRepository;

    /**
     * Генерация случайного Advertiser'а вместе с Offer'ами
     * Также к Advertiser'у привязывается случайных Publisher
     */
    @Transactional
    @Scheduled(fixedDelay = 2000)
    public void generateAdvertiser() {
        Advertiser advertiser = new Advertiser();

        advertiser.setName("Adv_"
                + RandomStringUtils.randomAlphabetic(3)
                + "_"
                + RandomStringUtils.randomAlphabetic(3));
        advertiser.setStatisticUrl("https://"
                + RandomStringUtils.randomAlphabetic(10)
                + ".com/" + RandomStringUtils.randomAlphabetic(3) + "-statistics");

        advertiser.setCommission(RandomUtils.nextInt(MIN_COMMISSION, MAX_COMMISSION));
        advertiser.setPublishers(getRandomPublishers());
        advertiser.setOffers(getRandomGeneratedOffers(advertiser));

        int i = RandomUtils.nextInt(1, 100);
        if (i < STANDARD_API_PERCENT) {
            ResponseDetail standardResponseDetail = responseDetailRepository.findByName("Standard");
            advertiser.setResponseDetail(standardResponseDetail);
        } else {
            advertiser.setResponseDetail(generateRandomResponseDetail());
        }
        advertiserRepository.save(advertiser);
    }

    /**
     * Получение коллекции из случайных Publisher'ов
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

    /**
     * Получение списка случайно сгененированных Offer'ов
     *
     * @param advertiser advertiser для которого генерится список Offer'ов
     * @return список случайно сгененированных Offer'ов
     */
    private List<Offer> getRandomGeneratedOffers(Advertiser advertiser) {
        List<Offer> randomGeneratedOffers = new ArrayList<>();
        for (int i = 0; i < RandomUtils.nextInt(1, 8); i++) {
            randomGeneratedOffers.add(generateRandomOffer(advertiser));
        }
        return randomGeneratedOffers;
    }

    /**
     * Генерация случайного Offer'а
     *
     * @param advertiser advertiser для которого генерится Offer
     * @return сгененированный Offer
     */
    private Offer generateRandomOffer(Advertiser advertiser) {
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

    /**
     * Генерация случайного ResponseDetail, который отвечает за
     * хранение информации о названиях атрибутов ответа Api Advertiser'а
     *
     * @return ResponseDetail
     */
    private ResponseDetail generateRandomResponseDetail() {
        ResponseDetail customResponseDetail = new ResponseDetail();
        customResponseDetail.setName("Name_" + RandomStringUtils.randomAlphabetic(6));
        customResponseDetail.setTransactionNumberName("Transactional_" + RandomStringUtils.randomAlphabetic(6));
        customResponseDetail.setDateTimeName("DateTime_" + RandomStringUtils.randomAlphabetic(6));
        customResponseDetail.setOfferName("OfferName_" + RandomStringUtils.randomAlphabetic(6));
        customResponseDetail.setOfferNumberName("OfferNumber_" + RandomStringUtils.randomAlphabetic(6));
        customResponseDetail.setPublisherName("PublisherName_" + RandomStringUtils.randomAlphabetic(6));

        return customResponseDetail;
    }

}
