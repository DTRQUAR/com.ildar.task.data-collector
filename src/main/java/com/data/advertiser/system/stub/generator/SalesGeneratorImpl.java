package com.data.advertiser.system.stub.generator;

import com.data.advertiser.system.stub.storage.SalesResponseStorage;
import com.data.model.Advertiser;
import com.data.model.Offer;
import com.data.model.Publisher;
import com.data.model.ResponseDetail;
import com.data.repository.OfferRepository;
import com.data.util.CollectionsUtil;
import com.data.util.DateTimeUtil;
import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 * <p>
 * Генерирует Sale'ы для Offer'ов Advertiser'ов
 * Также привязывает к Sale'ам случайных Publisher'ов
 */
@Component
public class SalesGeneratorImpl implements SalesGenerator {

    @Autowired
    private OfferRepository offerRepository;

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void generateSales() {
        String randomTransactionNumber = RandomStringUtils.randomNumeric(11);
        String randomDateTime = DateTimeUtil.toString(LocalDateTime.now());

        Offer offer = CollectionsUtil.random(offerRepository.findAll());
        String randomOfferName = offer.getName();
        String randomOfferNumber = offer.getNumber();

        Advertiser advertiser = offer.getAdvertiser();
        String statisticUrl = advertiser.getStatisticUrl();
        Publisher randomPublisher = CollectionsUtil.random(advertiser.getPublishers());
        String randomPublisherName = randomPublisher.getName();

        ResponseDetail responseDetail = advertiser.getResponseDetail();

        JSONObject jsonSale = new JSONObject();
        jsonSale.put(responseDetail.getTransactionNumberName(), randomTransactionNumber);
        jsonSale.put(responseDetail.getDateTimeName(), randomDateTime);
        jsonSale.put(responseDetail.getOfferName(), randomOfferName);
        jsonSale.put(responseDetail.getOfferNumberName(), randomOfferNumber);
        jsonSale.put(responseDetail.getPublisherName(), randomPublisherName);

        ConcurrentMap<String, JSONArray> publisherSales = SalesResponseStorage.getJsonSales().get(statisticUrl);
        if (publisherSales == null) {
            publisherSales = new ConcurrentHashMap<>();
            JSONArray jsonPublisherSales = new JSONArray();
            jsonPublisherSales.add(jsonSale);
            publisherSales.put(randomPublisherName, jsonPublisherSales);

            SalesResponseStorage.getJsonSales().put(statisticUrl, publisherSales);
        } else {
            JSONArray jsonPublisherSales = publisherSales.get(randomPublisherName);
            if (jsonPublisherSales == null) {
                jsonPublisherSales = new JSONArray();
                jsonPublisherSales.add(jsonSale);
                publisherSales.put(randomPublisherName, jsonPublisherSales);
            } else {
                jsonPublisherSales.add(jsonSale);
            }
        }
    }

}
