package com.data.service;

import com.data.dto.StatisticDto;
import com.data.model.Advertiser;
import com.data.model.Offer;
import com.data.model.Publisher;
import com.data.model.Sale;
import com.data.repository.PublisherRepository;
import com.data.repository.SaleRepository;
import com.data.util.StatisticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Service
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Override
    public List<StatisticDto> getStatisticForAllPublishers() {
        List<StatisticDto> statisticForAll = new ArrayList<>();
        List<Publisher> all = publisherRepository.findAll();
        for (Publisher publisher : all) {
            statisticForAll.addAll(getStatisticForOnePublisher(publisher.getId()));
        }
        return statisticForAll;
    }

    @Override
    public List<StatisticDto> getStatisticForOnePublisher(Integer publisherId) {
        List<StatisticDto> statisticsForPublisher = new ArrayList<>();

        Publisher publisher = publisherRepository.findById(publisherId).get();
        List<Sale> sales = saleRepository.findAllByPublisher(publisher);

        if (!sales.isEmpty()) {
            Map<Offer, Integer> offerSalesAmount = sales.stream()
                    .collect(Collectors.groupingBy(s -> s.getOffer(),
                            Collectors.summingInt(s -> s.getOffer().getPrice())));

            Map<Advertiser, List<Offer>> advertiserSoldOffers = offerSalesAmount.entrySet().stream()
                    .collect(Collectors.groupingBy(e -> e.getKey().getAdvertiser(),
                            Collectors.mapping(Map.Entry::getKey, Collectors.toList())));

            advertiserSoldOffers.forEach((advertiser, offerList) ->
                    offerList.forEach(offer -> {
                        StatisticDto dto = new StatisticDto();
                        dto.setPublisherName(publisher.getName());
                        dto.setAdvertiserName(advertiser.getName());
                        dto.setOfferName(offer.getName());
                        dto.setSalesAmount(offerSalesAmount.get(offer));
                        dto.setCommission(advertiser.getCommission());
                        dto.setPublisherEarning(StatisticUtil.calculatePublisherEarning(
                                offerSalesAmount.get(offer),
                                advertiser.getCommission()));
                        statisticsForPublisher.add(dto);
                    })
            );
        }

        return statisticsForPublisher;
    }

}
