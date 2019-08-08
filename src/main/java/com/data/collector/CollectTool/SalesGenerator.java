package com.data.collector.CollectTool;

import com.data.collector.model.Offer;
import com.data.collector.model.Publisher;
import com.data.collector.repository.OfferRepository;
import com.data.collector.util.CollectionsUtil;
import com.data.collector.util.DateTimeUtil;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SalesGenerator {

    @Autowired
    private OfferRepository offerRepository;

    private static ConcurrentMap<String, List<SaleDto>> sales = new ConcurrentHashMap<>();

    public static ConcurrentMap<String, List<SaleDto>> getSales() {
        return sales;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(fixedRate = 3000)
    public void generateSalesDto() {
        String randomTransactionNumber = RandomStringUtils.randomNumeric(11);
        String randomDateTime = DateTimeUtil.toString(LocalDateTime.now());

        Offer offer = CollectionsUtil.random(offerRepository.findAll());
        String randomOfferName = offer.getName();
        String randomOfferNumber = offer.getNumber();

        String statisticUrl = offer.getAdvertiser().getStatisticUrl();
        Publisher randomPublisher = CollectionsUtil.random(offer.getAdvertiser().getPublishers());
        String randomPublisherName = randomPublisher.getName();

        SaleDto saleDto = new SaleDto(
                randomTransactionNumber,
                randomDateTime,
                randomOfferName,
                randomOfferNumber,
                randomPublisherName);

        List<SaleDto> saleDtos = sales.get(statisticUrl);
        if (saleDtos == null) {
            saleDtos = new ArrayList<>();
            saleDtos.add(saleDto);
            sales.put(statisticUrl, saleDtos);
        } else {
            saleDtos.add(saleDto);
            sales.put(statisticUrl, saleDtos);
        }
    }


}
