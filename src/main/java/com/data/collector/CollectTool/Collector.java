package com.data.collector.CollectTool;

import com.data.collector.model.Advertiser;
import com.data.collector.model.Offer;
import com.data.collector.model.Publisher;
import com.data.collector.model.Sale;
import com.data.collector.repository.OfferRepository;
import com.data.collector.repository.PublisherRepository;
import com.data.collector.repository.SaleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Component
public class Collector {

    private static final Logger logger = LoggerFactory.getLogger(Collector.class);

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    MockRestTemplate restTemplate;

    @Scheduled(fixedRate = 2000)
    @Transactional
    public void scheduleTaskWithFixedRate() {
        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()));

        List<Publisher> publishers = publisherRepository.findAll();

        for (Publisher publisher : publishers) {
            Set<Advertiser> advertisers = publisher.getAdvertisers();

            for (Advertiser advertiser : advertisers) {
                SalesDtoList saleStatistic
                        = restTemplate.getSaleStatistic(advertiser.getStatisticUrl(), publisher.getName());
                saveSaleDtoList(saleStatistic);
            }
        }
    }

    @Transactional
    public void saveSaleDtoList(SalesDtoList salesDtoList) {
        salesDtoList.getSaleDtoList().forEach(saleDto -> saveSaleDto(saleDto));
    }

    @Transactional
    public void saveSaleDto(SaleDto saleDto) {
        Offer offer = offerRepository.findByNameAndNumber(saleDto.getOfferName(), saleDto.getOfferNumber());
        Publisher publisher = publisherRepository.findByName(saleDto.getPublisherName());
        Sale foundSale
                = saleRepository.findByTransactionNumberAndOfferId(saleDto.getTransactionNumber(), offer.getId());

        if (foundSale == null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime formatDateTime = LocalDateTime.parse(saleDto.getDateTime(), formatter);

            Sale sale = new Sale(saleDto.getTransactionNumber(), formatDateTime, offer, publisher);
            saleRepository.save(sale);
        }
    }

}
