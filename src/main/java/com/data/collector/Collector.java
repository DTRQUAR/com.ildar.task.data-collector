package com.data.collector;

import com.data.model.Advertiser;
import com.data.model.Offer;
import com.data.model.Publisher;
import com.data.model.Sale;
import com.data.repository.OfferRepository;
import com.data.repository.PublisherRepository;
import com.data.repository.SaleRepository;
import com.data.util.DateTimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * Ildar Makhmutov
 * 08.08.2019.
 */
@Component
public class Collector {

    private static final Logger logger = LoggerFactory.getLogger(Collector.class);

    @Autowired
    private OfferRepository offerRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private CollectorRestTemplate restTemplate;

    /**
     * Получение статистики продаж по всем Publisher'ам
     * зарегистрированным в Партнерской сети
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Scheduled(fixedRate = 2000)
    protected void collectAllSaleStatistic() {
        logger.info("Start collect Publishers Statistic: {}", DateTimeUtil.DATE_TME_FORMATTER.format(LocalDateTime.now()));

        List<Publisher> publishers = publisherRepository.findAll();

        for (Publisher publisher : publishers) {
            Set<Advertiser> advertisers = publisher.getAdvertisers();

            for (Advertiser advertiser : advertisers) {
                List<SaleDto> saleStatistic
                        = restTemplate.getSaleStatistic(advertiser.getStatisticUrl(), publisher.getName());
                saleStatistic.forEach(this::saveSaleDto);
            }
        }
    }

    /**
     * Сохранение SaleDto в БД
     *
     * @param saleDto объект полученный у Advertiser'а
     *                и хранящий информацию о Sale
     */
    private void saveSaleDto(SaleDto saleDto) {
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
